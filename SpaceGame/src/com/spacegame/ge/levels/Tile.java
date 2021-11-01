package com.spacegame.ge.levels;

import com.spacegame.Log;

public class Tile {
	
	//65793
	public static final Tile[] tiles = new Tile[1024];
	public static final Tile SPACE = new Tile(0, 0, 0, -16777216);
	public static final Tile ROCKET = new Tile(1, 1, 0, -10240);
	public static final Tile FLOOR = new Tile(2, 2, 0, -3618616);
	public static final Tile LTCORNER = new Tile(3, 0, 3, -3684409);
	public static final Tile TWALL = new Tile(4, 1, 3, -3750202);
	public static final Tile RTCORNER = new Tile(5, 2, 3, -3815995);
	public static final Tile LWALL = new Tile(6, 3, 3, -3881788);
	public static final Tile RWALL = new Tile(7, 4, 3, -3947581);
	public static final Tile LBCORNER = new Tile(8, 5, 3, -4013374);
	public static final Tile BWALL = new Tile(9, 6, 3, -4079167);
	public static final Tile RBCORNER = new Tile(10, 7, 3, -4144960);
	public static final Tile TBWALL = new Tile(11, 8, 3, -4210753);
	public static final Tile RLWALL = new Tile(12, 9, 3, -4276546);
	public static final Tile TOPEN = new Tile(13, 10, 3, -4342339);
	public static final Tile BOPEN = new Tile(14, 11, 3, -4408132);
	public static final Tile LOPEN = new Tile(15, 12, 3, -4473925);
	public static final Tile ROPEN = new Tile(16, 13, 3, -4539718);
	public static final Tile CLOSED = new Tile(17, 14, 3, -4605511);
	public static final Tile LOADING = new Tile(18, 0, 1, 0);
	public static final Tile RESEARCH = new Tile(19, 1, 1, -16711681);
	public static final Tile TDESK = new Tile(20, 0, 2, -16711682);
	public static final Tile RDESK = new Tile(21, 1, 2, -16711683);
	public static final Tile BDESK = new Tile(22, 2, 2, -16711684);
	public static final Tile LDESK = new Tile(23, 3, 2, -16711685);
	public static final Tile LIMPORT = new Tile(24, 2, 1, -76032);
	public static final Tile DIMPORT = new Tile(25, 3, 1, -141824);
	public static final Tile RIMPORT = new Tile(26, 4, 1, -207616);
	public static final Tile UIMPORT = new Tile(27, 5, 1, -273408);
	public static final Tile FOOD = new Tile(28, 6, 1, -8441088);
	
    protected byte id;
    protected byte x;
    protected byte y;
    protected int color;
    
    public Tile(int id, int x, int y, int color) {
        this.id = (byte) id;
        this.x = (byte) x;
        this.y = (byte) y;
        this.color = color;
        
        if (tiles[id] != null){
        	Log.warning(id + " is already a tile name. Please try again.");
        }
        tiles[id] = this;
        
        Log.out("New tile created with id " + id + " at position " + x + ", " + y);
    }
    
    public static int getID(Tile tile) {
    	return tile.id;
    }
    
    public static int getTilePosX(Tile tile) {
    	return tile.x;
    }
    
    public static int getTilePosY(Tile tile) {
    	return tile.y;
    }
    
    public static int getMapColor(Tile tile) {
    	return tile.color;
    }
    
    public static int getIDByColor(int color) {
    	for(int i = 0; i < Tile.tiles.length; i++) {
    		if(color == Tile.tiles[i].color) {
    			return Tile.tiles[i].id;
    		}
    	}
    	return -1;
    }
    
    public static Tile getTileByColor(int color) {
    	for(int i = 0; i < Tile.tiles.length; i++) {
    		if(color == Tile.tiles[i].color) {
    			return Tile.tiles[i];
    		}
    	}
    	return null;
    }
}
