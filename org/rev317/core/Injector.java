package org.rev317.core;

import java.awt.Image;

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
import org.parabot.core.asm.ASMUtils;
import org.parabot.core.asm.adapters.AddGetterAdapter;

/**
 * 
 * Injects bytecode required for this server to support parabot
 * 
 * @author Clisprail
 *
 */
public class Injector implements Opcodes {
	
	public static void injectPaint() {
		new AddGetterAdapter(ASMUtils.getClass("RSImageProducer"),
				ASMUtils.getField(ASMUtils.getClass("RSImageProducer"),
						"anImage320"), "getImage").inject();

		ClassNode cg = ASMUtils.getClass("RSImageProducer");

		for (MethodNode m : cg.methods) {
			InsnList list = m.instructions;
			AbstractInsnNode n = null;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getOpcode() == INVOKESPECIAL) {
					n = list.get(i);
				}
			}

			if (m.name.equals("drawGraphics")
					&& m.desc.equals("(ILjava/awt/Graphics;I)V")) {
				InsnList inject = new InsnList();
				inject.add(new VarInsnNode(ALOAD, 0));
				inject.add(new FieldInsnNode(GETFIELD, cg.name, "anImage320",
						"Ljava/awt/Image;"));
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
	
	public static void addModelGetter() {
		ClassNode into = ASMUtils.getClass("Animable");
		MethodNode m = new MethodNode(ACC_PUBLIC, "getModel", "()" + "Lorg/rev317/accessors/Model;", null, null);
		m.visitCode();
		Label l0 = new Label();
		m.visitLabel(l0);
		m.visitVarInsn(ALOAD, 0);
		m.visitMethodInsn(INVOKEVIRTUAL, "Animable", "getRotatedModel", "()LModel;");
		//m.visitMethodInsn(INVOKEVIRTUAL, "Player", methodNode.name, methodNode.desc);
		m.visitInsn(ARETURN);
		Label l1 = new Label();
		m.visitLabel(l1);
		m.visitLocalVariable("this", "LAnimable;", null, l0, l1, 0);
		m.visitMaxs(1, 1);
		m.visitEnd();
		into.methods.add(m);
	}
	
	public static void hookMessageListener() {
		ClassNode node = ASMUtils.getClass("client");
		for (MethodNode m : node.methods) {
			// Find the method
			if (m.desc.equals("(Ljava/lang/String;ILjava/lang/String;)V")) {
				InsnList inject = new InsnList();
				Label l0 = new Label();
				inject.add(new LabelNode(l0));
				//inject.add(new VarInsnNode(ALOAD, 0));
				inject.add(new VarInsnNode(ILOAD, 2));
				inject.add(new VarInsnNode(ALOAD, 3));
				inject.add(new VarInsnNode(ALOAD, 1));
				inject.add(new MethodInsnNode(INVOKESTATIC, "org/rev317/core/MessageDispatcher",
						"messageListenerHook",
						"(ILjava/lang/String;Ljava/lang/String;)V"));
				m.instructions.insert(inject);
				break;
			}
		}
	}
	

	

}
