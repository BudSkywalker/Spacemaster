package com.spacegame.ge.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.spacegame.Log;

public class SpriteSheet {	
	public int xOffset = 0;
	public int yOffset = 0;
	
	public static String path;
	public static int width;
	public static int height;
	
	public int[] pixels;

	private static BufferedImage spriteSheet;
	private static final int TILE_SIZE = 16;

	public static BufferedImage loadSprite(String file) {

		BufferedImage image = null;

		try {
            image = ImageIO.read(new File(file));
            Log.out("Sprite Sheet Pathed");
        } catch (IOException e) {
            Log.error(e);
        }
		
		path = file;
		width = image.getWidth();
		height = image.getHeight();
		
        return image;
	}

    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("resources/images/sprite_sheet.png");
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}
