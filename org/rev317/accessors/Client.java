package org.rev317.accessors;

public interface Client extends RSApplet {
	
	public String getUsername();
	
	public Player getLocalPlayer();
	
	public boolean isLoggedIn();
	
	public int getCameraX();
	
	public int getCameraY();
	
	public int getCameraZ();
	
	public int getCameraPitch();

	public int getCameraYaw();
	
	public int getPlane();
	
	public byte[][][] getSceneFlags();
	
	public int[][][] getTileOffsets();
	
	public int getBaseX();
	
	public int getBaseY();
	
	public int[] getPlayerIndices();
	
	public Player[] getPlayers();
	
	public int[] getNpcIndices();
	
	public Npc[] getNpcs();
	
	public Scene getScene();

	public Deque[][][] getGroundItems();
	
	public int getCrosshairType();
	
	public int getMenuActionRow();
	
	public String[] getMenuActions();
	
	public int getMenuOffsetX();
	
	public int getMenuOffsetY();
	
	public int getMenuScreenArea();
	
	public boolean isMenuOpen();
	
	public int getMenuWidth();
	
	public int getMenuHeight();

	public Interface[] getInterfaceCache();

	public int getChatboxInterfaceId();

	public int getOpenInterfaceId();
	
	public int getOpenTab();
	
	public int[] getCurrentExp();
	
	public int[] getCurrentStats();
	
	public int getMinimapInt1();
	
	public int getMinimapInt2();
	
	public int getMinimapInt3();
	
	public int getDestinationX();
	
	public int getDestinationY();
	
	public CollisionMap[] getCollisionMap();
	
	public void openTab(int tab);

	public int getLoopCycle();

}
