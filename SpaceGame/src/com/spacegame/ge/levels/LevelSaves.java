package com.spacegame.ge.levels;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.spacegame.Config;
import com.spacegame.Log;
import com.spacegame.ge.handlers.EventHandler;

public class LevelSaves {	
	static BufferedImage image;
	public static int width;
	public static int height;
	static byte[] pixels;
	static int[][] result;
	
	public static int importTiles;
	public static int researchTiles;
	public static int foodTiles;
	
	public static int[][] mapList() {
		try {
			image = ImageIO.read(new File("resources/levels/" + EventHandler.saveName + ".png"));
			Log.element("ge.LevelSaves", "image", "Pathed map");
		} catch(FileNotFoundException e) {
			Log.error(e);
		} catch(IOException e) {
			Log.error(e);
		}
		
		pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		Log.element("ge.LevelSaves", "pixels", "ARGB Data collect");
	      width = image.getWidth();
	      Log.element("ge.levels.LevelSaves", "width", "set to " + width);
	      height = image.getHeight();
	      Log.element("ge.levels.LevelSaves", "height", "set to " + height);

	      result = new int[height][width];
	      Log.element("ge.levels.LevelSaves", "result", "Initialized");
	      
	      return readMap();
      }
	
	public static int getARGB(int x, int y) {
		return result[x][y];
	}
	
	public static void setMap(int x, int y, int tileColor) {
		try {
			if(getARGB(x, y) == Tile.getMapColor(Tile.ROCKET) || 
				getARGB(x, y) == Tile.getMapColor(Tile.LIMPORT) ||
				getARGB(x, y) == Tile.getMapColor(Tile.DIMPORT) ||
				getARGB(x, y) == Tile.getMapColor(Tile.RIMPORT) ||
				getARGB(x, y) == Tile.getMapColor(Tile.UIMPORT)) {
	 	    		 	importTiles--;
			}
			if(getARGB(x, y) == Tile.getMapColor(Tile.TDESK) ||
			   getARGB(x, y) == Tile.getMapColor(Tile.RDESK) ||
			   getARGB(x, y) == Tile.getMapColor(Tile.BDESK) ||
			   getARGB(x, y) == Tile.getMapColor(Tile.LDESK)) {
	  				researchTiles--;
			}
			if(getARGB(x, y) == Tile.getMapColor(Tile.FOOD)) {
				foodTiles--;
			}
			image.setRGB(x, y, tileColor);
			Log.element("ge.levels.LevelSaves", "image", x + ", " + y + " set to " + tileColor);
			File image2 = new File("resources/levels/" + EventHandler.saveName + ".png");
			ImageIO.write(image, "png", image2);
			
			final boolean hasAlphaChannel = image.getAlphaRaster() != null;
			Log.out(hasAlphaChannel + "");
			if (hasAlphaChannel) {
				int pixel = (x * 4) + (y * 4); 
	 	        int argb = 0;
 	            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
 	            argb += ((int) pixels[pixel + 1] & 0xff); // blue
 	            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
 	            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
 	            result[x][y] = argb;
 	            Log.out(argb + "");
	 	      } else {
	 	    	  int pixel = (x * 3) + (y * image.getWidth() * 3); 
	 	    	  int argb = 0;
	 	    	  argb += -16777216; // 255 alpha
	 	    	  argb += ((int) pixels[pixel] & 0xff); // blue
	 	    	  argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
	 	    	  argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
	 	    	  result[x][y] = argb;
	 	    	  if(argb == Tile.getMapColor(Tile.ROCKET) || 
 	    			  argb == Tile.getMapColor(Tile.LIMPORT) ||
 	    			  argb == Tile.getMapColor(Tile.DIMPORT) ||
 	    			  argb == Tile.getMapColor(Tile.RIMPORT) ||
 	    			  argb == Tile.getMapColor(Tile.UIMPORT)) {
	 	    		 	importTiles++;
	 	    	  }
	 	    	  if(argb == Tile.getMapColor(Tile.TDESK) ||
    			     argb == Tile.getMapColor(Tile.RDESK) ||
 	    			 argb == Tile.getMapColor(Tile.BDESK) ||
 	    			 argb == Tile.getMapColor(Tile.LDESK)) {
            			researchTiles++;
	 	    	  }
	 	    	  if(argb == Tile.getMapColor(Tile.FOOD)) {
	 	    		  foodTiles++;
	 	    	  }
	 	    	  Log.out(pixel + "");
	 	      }
		} catch (IOException e) {
			Log.error(e);
		}
	}
	
	public static int[][] readMap() {
		final boolean hasAlphaChannel = image.getAlphaRaster() != null;
		
	      if(Config.get("quicklaunch").equals("true")) {
	    	  if (hasAlphaChannel) {
	 	         final int pixelLength = 4;
	 	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	 	            int argb = 0;
	 	            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
	 	            argb += ((int) pixels[pixel + 1] & 0xff); // blue
	 	            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
	 	            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
	 	            result[row][col] = argb;
	 	            if(argb == Tile.getMapColor(Tile.ROCKET) || 
 						argb == Tile.getMapColor(Tile.LIMPORT) ||
 						argb == Tile.getMapColor(Tile.DIMPORT) ||
	 					argb == Tile.getMapColor(Tile.RIMPORT) ||
	 					argb == Tile.getMapColor(Tile.UIMPORT)) {
	 	            	importTiles++;
	 	            }
	 	            if(argb == Tile.getMapColor(Tile.TDESK) ||
            		   argb == Tile.getMapColor(Tile.RDESK) ||
            		   argb == Tile.getMapColor(Tile.BDESK) ||
            		   argb == Tile.getMapColor(Tile.LDESK)) {
	 	            	researchTiles++;
	 	            }
	 	            if(argb == Tile.getMapColor(Tile.FOOD)) {
	 	            	foodTiles++;
	 	           	}
	 	            row++;
	 	            if (row == width) {
	 	               row = 0;
	 	               col++;
	 	            }
	 	         }
	 	      } else {
	 	         final int pixelLength = 3;
	 	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	 	            int argb = 0;
	 	            argb += -16777216; // 255 alpha
	 	            argb += ((int) pixels[pixel] & 0xff); // blue
	 	            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
	 	            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
	 	            result[row][col] = argb;
	 	            row++;
	 	            if (row == width) {
	 	               row = 0;
	 	               col++;
	 	            }
	 	         }
	 	      }
	      } else if(Config.get("quicklaunch").equals("false")) {
	    	  if (hasAlphaChannel) {
	 	         final int pixelLength = 4;
	 	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	 	            int argb = 0;
	 	            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
	 	            argb += ((int) pixels[pixel + 1] & 0xff); // blue
	 	            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
	 	            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
	 	            result[row][col] = argb;
	 	            Log.element("ge.levels.LevelSaves", "result", row + ", " + col + " is set to " + argb);
	 	            row++;
	 	            if (row == width) {
	 	               row = 0;
	 	               col++;
	 	            }
	 	         }
	 	      } else {
	 	         final int pixelLength = 3;
	 	         for (int pixel = 0, col = 0, row = 0; pixel < pixels.length; pixel += pixelLength) {
	 	            int argb = 0;
	 	            argb += -16777216; // 255 alpha
	 	            argb += ((int) pixels[pixel] & 0xff); // blue
	 	            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
	 	            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
	 	            result[row][col] = argb;
	 	            Log.element("ge.levels.LevelSaves", "result", row + ", " + col + " is set to " + argb);
	 	            row++;
	 	            if (row == width) {
	 	               row = 0;
	 	               col++;
	 	            }
	 	         }
	 	      }
	      } else {
	    	  Log.warning("Unknow argument " + Config.get("quicklaunch") + " in config.properties. Must be true or false");
	    	  if (hasAlphaChannel) {
	 	         final int pixelLength = 4;
	 	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	 	            int argb = 0;
	 	            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
	 	            argb += ((int) pixels[pixel + 1] & 0xff); // blue
	 	            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
	 	            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
	 	            result[row][col] = argb;
	 	            row++;
	 	            if (row == width) {
	 	               row = 0;
	 	               col++;
	 	            }
	 	         }
	 	      } else {
	 	         final int pixelLength = 3;
	 	         for (int pixel = 0, col = 0, row = 0; pixel < pixels.length; pixel += pixelLength) {
	 	            int argb = 0;
	 	            argb += -16777216; // 255 alpha
	 	            argb += ((int) pixels[pixel] & 0xff); // blue
	 	            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
	 	            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
	 	            result[row][col] = argb;
	 	            row++;
	 	            if (row == width) {
	 	            	row = 0;
	 	            	col++;
	 	            }
	 	         }
	 	      }
	      }
	      return result;
	}
}
