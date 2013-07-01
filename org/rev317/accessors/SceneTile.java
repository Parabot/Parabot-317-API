package org.rev317.accessors;

public interface SceneTile {
	
	public SceneObjectTile getWallObject();
	
	public SceneObjectTile getWallDecoration();
	
	public SceneObjectTile getGroundDecoration();
	
	public SceneObjectTile getGroundItem();
	
	public SceneObjectTile[] getInteractiveObjects();

}
