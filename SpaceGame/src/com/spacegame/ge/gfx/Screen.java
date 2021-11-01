package com.spacegame.ge.gfx;

import com.spacegame.Log;

public class Screen {
	public int[] pixels;
	
	public static int xOffset = 0;
	public static int yOffset = 0;
	
	public int width;
	public int height;
	
	public SpriteSheet spriteSheet;
	
	public Screen(int width, int height) {
		this.width = width;
		Log.element("ge.gfx.Screen", "width", "Set to " + width);
		this.height = height;
		Log.element("ge.gfx.Screen", "height", "Set to " + height);
		
		pixels = new int[width * height];
	}
	
    public void setOffset(int xOffset, int yOffset) {
        Screen.xOffset = xOffset;
        Screen.yOffset = yOffset;
        Log.out("Set offset");
    }
}