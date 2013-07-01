package org.rev317.accessors;

public interface Character extends Renderable {
	
	public int[] getQueueX();
	
	public int[] getQueueY();
	
	public int getInteractingId();
	
	public int getX();
	
	public int getY();
	
	public int getHeight();
	
	public int getTurnDirection();
	
	public int isWalking();
	
	public int getAnimation();
	
	public int getHealth();
	
	public int getMaxHealth();
	
	public String getDisplayedText();
	
	public int getLoopCycleStatus();

}
