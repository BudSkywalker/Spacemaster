package com.spacegame.ge.handlers;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import com.spacegame.Log;
import com.spacegame.ge.Game;

public class CursorHandler {
	public static int cursorNum = 0;
	
	public static final String[] cursorImage = new String[40];
	
	public static final CursorHandler[] cursor = new CursorHandler[40];
	public static final CursorHandler DEFAULT = new CursorHandler(0, "resources/images/cursor.png");
	public static final CursorHandler TILE = new CursorHandler(1, "resources/images/tile_cursor.png");
	public static final CursorHandler RESEARCH = new CursorHandler(2, "resources/images/research_cursor.png");
	public static final CursorHandler SHIP = new CursorHandler(3, "resources/images/ship_cursor.png");
	public static final CursorHandler VOID = new CursorHandler(4, "resources/images/space_cursor.png");
	public static final CursorHandler FOOD = new CursorHandler(5, "resources/images/food_cursor.png");
	public static final CursorHandler TDESK = new CursorHandler(10, "resources/images/desk_cursor_top.png");
	public static final CursorHandler RDESK = new CursorHandler(11, "resources/images/desk_cursor_right.png");
	public static final CursorHandler BDESK = new CursorHandler(12, "resources/images/desk_cursor_bottom.png");
	public static final CursorHandler LDESK = new CursorHandler(13, "resources/images/desk_cursor_left.png");
	
    protected byte id;
	
	public CursorHandler(int id, String imagePath) {
		this.id = (byte) id;
        
        if (cursor[id] != null){
        	Log.warning(id + " is already a cursor name. Please try again.");
        }
        cursor[id] = this;
        cursorImage[id] = imagePath;
	}
	
    public static int getID(CursorHandler cursor) {
    	return cursor.id;
    }
	
	public static String getImage(int id) {
		return cursorImage[id];
	}
	
	public static void setCursor(int id) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(getImage(id));
		Cursor c = toolkit.createCustomCursor(image , new Point(0, 0), "img");
		Game.frame.setCursor(c);
		cursorNum = id;
		Log.out("Cursor image set to: " + cursorImage[id]);
	}
}
