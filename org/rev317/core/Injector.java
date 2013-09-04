package org.rev317.core;

import java.awt.Image;
import java.util.HashMap;

import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.parabot.core.Context;
import org.parabot.core.asm.ASMUtils;

/**
 * 
 * Injects bytecode required for this server to support parabot
 * 
 * @author Everel
 * 
 */
public class Injector implements Opcodes {

	public static void injectPaint() {

		final HashMap<String, String> constants = Context.resolve()
				.getHookParser().getConstants();

		ClassNode cg = ASMUtils.getClass(constants.get("ImageProducer"));

		for (MethodNode m : cg.methods) {
			InsnList list = m.instructions;
			AbstractInsnNode n = null;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getOpcode() == INVOKESPECIAL) {
					n = list.get(i);
				}
			}

			if (m.name.equals(constants.get("paintGraphicsName"))
					&& m.desc.equals(constants.get("paintGraphicsDesc"))) {
				InsnList inject = new InsnList();
				inject.add(new VarInsnNode(ALOAD, 0));
				inject.add(new FieldInsnNode(GETFIELD, cg.name, constants
						.get("paintGraphicsImage"), "Ljava/awt/Image;"));
				inject.add(new VarInsnNode(ILOAD, 1));
				inject.add(new VarInsnNode(ILOAD, 3));
				inject.add(new MethodInsnNode(INVOKESTATIC, cg.name,
						"renderPaint", "(L"
								+ Image.class.getName().replace('.', '/')
								+ ";II)Z"));
				LabelNode ln = new LabelNode(new Label());
				inject.add(new JumpInsnNode(IFEQ, ln));
				inject.add(new InsnNode(RETURN));
				inject.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
				inject.add(ln);
				m.instructions.insertBefore(n.getNext(), inject);
				break;
			}

		}
	}

}
