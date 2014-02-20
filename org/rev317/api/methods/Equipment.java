package org.rev317.api.methods;

import java.awt.Point;

	public enum Equipment {
		HEAD(0, new Point(639, 228)), CAPE(1, new Point(601, 263));
	
		private int index;
		private Point point;
	
		Equipment(int index, Point point) {
			this.index = index;
			this.point = point;
		}
	
		public int getIndex() {
			return index;
		}
	
		public Point getPoint() {
			return point;
		}
	
		public int getEquippedId() {
			return Players.getLocal().getEquipment()[getIndex()];
		}
	}
