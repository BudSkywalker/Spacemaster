package com.spacegame.ge.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.spacegame.Config;
import com.spacegame.Log;
import com.spacegame.Sound;
import com.spacegame.ge.Game;
import com.spacegame.ge.handlers.EventHandler;
import com.spacegame.ge.handlers.InputHandler;
import com.spacegame.ge.levels.LevelSaves;
import com.spacegame.ge.levels.Tile;

public class Render extends Game {
	private static final long serialVersionUID = 1L;
	
	public static int tileX = (int) ((-1) * frame.getWidth() * 0.3);
	
	public void render(Graphics graphics, BufferStrategy bufferStrategy) {
		if(settings == true) {
			pause = true;
			settings(graphics);
		} else if(homeMenu == true) {
			pause = true;
			homeMenu(graphics);
		} else if(newSave == true) {
			pause = true;
			newSave(graphics);
		} else if(loadSave == true) {
			pause = true;
			loadSave(graphics);
		} else if(InputHandler.researchMenu == true && InputHandler.inMenu == false) {
			researchMenu(graphics);
		} else {
			board(graphics);
			tile(graphics);
			overlay(graphics);
			
			if(InputHandler.inMenu == true) {
				pauseMenu(graphics);
			} else if(InputHandler.importMenu == true) {
				importMenu(graphics);
			}
		}
		fps(graphics);
		
		if(victory == true) {
			victory(graphics);
		} else if(gameOver == true) {
			gameOver(graphics);
		}
		
		graphics.dispose();
		bufferStrategy.show();
	}
	
	private void settings(Graphics graphics) {
		try {
			BufferedImage menu = ImageIO.read(new File("resources/images/settings.png"));
			BufferedImage soundOn = ImageIO.read(new File("resources/images/settings_sound_on.png"));
			BufferedImage soundOff = ImageIO.read(new File("resources/images/settings_sound_off.png"));
			BufferedImage musicOn = ImageIO.read(new File("resources/images/settings_music_on.png"));
			BufferedImage musicOff = ImageIO.read(new File("resources/images/settings_music_off.png"));
			Graphics2D graphics2D = (Graphics2D) graphics;		
			
			graphics2D.drawImage(menu, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, menu.getWidth(), menu.getHeight(), null);
			
			if(Sound.doSound == true) {
				graphics2D.drawImage(soundOn, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, menu.getWidth(), menu.getHeight(), null);
			} else if(Sound.doSound == false) {
				graphics2D.drawImage(soundOff, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, menu.getWidth(), menu.getHeight(), null);
			}
			
			if(Sound.doMusic == true) {
				graphics2D.drawImage(musicOn, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, menu.getWidth(), menu.getHeight(), null);
			} else if(Sound.doMusic == false) {
				graphics2D.drawImage(musicOff, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, menu.getWidth(), menu.getHeight(), null);
			}
		} catch (IOException e) {
            Log.error(e);
        }
	}
	
	private void homeMenu(Graphics graphics) {
		try {
			BufferedImage menu = ImageIO.read(new File("resources/images/home.png"));
			Graphics2D graphics2D = (Graphics2D) graphics;
			
			for(int y = (yOffset / HEIGHT); y < (yOffset + screenHeight) / HEIGHT + 1 && y < screenHeight; y++) {
				for(int x = (xOffset / WIDTH); x < (xOffset + screenWidth) / WIDTH + 1 && x < screenWidth; x++) {
					if(x < 0) continue;
					graphics.drawImage(SpriteSheet.getSprite(Tile.getTilePosX(Tile.SPACE), Tile.getTilePosY(Tile.SPACE)), 
							(WIDTH * x - xOffset), 
							HEIGHT * y - yOffset, 
							WIDTH, HEIGHT, null);
				}
			}
			
			graphics2D.drawImage(menu, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, menu.getWidth(), menu.getHeight(), null);
			
		} catch (IOException e) {
            Log.error(e);
        }
	}
	
	private void newSave(Graphics graphics) {
		try {
			BufferedImage menu = ImageIO.read(new File("resources/images/new_level.png"));
			Graphics2D graphics2D = (Graphics2D) graphics;
			
			for(int y = (yOffset / HEIGHT); y < (yOffset + screenHeight) / HEIGHT + 1 && y < screenHeight; y++) {
				for(int x = (xOffset / WIDTH); x < (xOffset + screenWidth) / WIDTH + 1 && x < screenWidth; x++) {
					if(x < 0) continue;
					graphics.drawImage(SpriteSheet.getSprite(Tile.getTilePosX(Tile.SPACE), Tile.getTilePosY(Tile.SPACE)), 
							(WIDTH * x - xOffset), 
							HEIGHT * y - yOffset, 
							WIDTH, HEIGHT, null);
				}
			}
			
			graphics2D.drawImage(menu, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, menu.getWidth(), menu.getHeight(), null);
			
			graphics.setColor(Color.RED);
			graphics.setFont(new Font("Calibri", 0, screenHeight / 5 - ((EventHandler.saveName.length() % 31) * (screenWidth / 250))));
			graphics.drawString(EventHandler.saveName, (int) (0.24 * screenWidth), (int) (0.575 * screenHeight));
			
			graphics.setColor(Color.WHITE);
		} catch (IOException e) {
            Log.error(e);
        }
	}
	
	private void loadSave(Graphics graphics) {
		try {
			BufferedImage menu = ImageIO.read(new File("resources/images/load_save.png"));
			Graphics2D graphics2D = (Graphics2D) graphics;
			
			for(int y = (yOffset / HEIGHT); y < (yOffset + screenHeight) / HEIGHT + 1 && y < screenHeight; y++) {
				for(int x = (xOffset / WIDTH); x < (xOffset + screenWidth) / WIDTH + 1 && x < screenWidth; x++) {
					if(x < 0) continue;
					graphics.drawImage(SpriteSheet.getSprite(Tile.getTilePosX(Tile.SPACE), Tile.getTilePosY(Tile.SPACE)), 
							(WIDTH * x - xOffset), 
							HEIGHT * y - yOffset, 
							WIDTH, HEIGHT, null);
				}
			}
			
			graphics2D.drawImage(menu, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, menu.getWidth(), menu.getHeight(), null);
			
			graphics.setColor(Color.RED);
			graphics.setFont(new Font("Calibri", 0, screenHeight / 20));
			
			for(int i = scrollOffset; i < saveList.length; i++) {
				String string = saveList[i].toString().substring(17, saveList[i].toString().length() - 10);
				if(i == scrollOffset) {
					graphics.drawString(string, (int) (0.14375 * screenWidth), (int) (0.22777777777777777777777777777778 * screenHeight));
				} else if(i == scrollOffset + 1) {
					graphics.drawString(string, (int) (0.14375 * screenWidth), (int) (0.28333333333333333333333333333333 * screenHeight));
				} else if(i == scrollOffset + 2) {
					graphics.drawString(string, (int) (0.14375 * screenWidth), (int) (0.33888888888888888888888888888889 * screenHeight));
				} else if(i == scrollOffset + 3) {
					graphics.drawString(string, (int) (0.14375 * screenWidth), (int) (0.39444444444444444444444444444444 * screenHeight));
				} else if(i == scrollOffset + 4) {
					graphics.drawString(string, (int) (0.14375 * screenWidth), (int) (0.45 * screenHeight));
				} else if(i == scrollOffset + 5) {
					graphics.drawString(string, (int) (0.14375 * screenWidth), (int) (0.50555555555555555555555555555556 * screenHeight));
				} else if(i == scrollOffset + 6) {
					graphics.drawString(string, (int) (0.14375 * screenWidth), (int) (0.56111111111111111111111111111112 * screenHeight));
				} else if(i == scrollOffset + 7) {
					graphics.drawString(string, (int) (0.14375 * screenWidth), (int) (0.61666666666666666666666666666668 * screenHeight));
				} else if(i == scrollOffset + 8) {
					graphics.drawString(string, (int) (0.14375 * screenWidth), (int) (0.67222222222222222222222222222224 * screenHeight));
				} else if(i == scrollOffset + 9) {
					graphics.drawString(string, (int) (0.14375 * screenWidth), (int) (0.7277777777777777777777777777778 * screenHeight));
				}
			}
			
				graphics.setColor(Color.WHITE);
		} catch (IOException e) {
            Log.error(e);
        }
	}
	
	private void researchMenu(Graphics graphics) {
		try {
            BufferedImage menu = ImageIO.read(new File("resources/images/research.png"));
            BufferedImage researchLock = ImageIO.read(new File("resources/images/import_lock.png"));
            BufferedImage foodLock = ImageIO.read(new File("resources/images/food_lock.png"));
            BufferedImage winLock = ImageIO.read(new File("resources/images/win_lock.png"));
            
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(menu, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, menu.getWidth(), menu.getHeight(), null);
    		graphics.setColor(Color.CYAN);
    		graphics.setFont(new Font("Felix Tilting", 0, screenHeight / 40));
			graphics.drawString(" Increase to: " + (importLimit + 1) , (int) (0.0267857142857143 * screenWidth), (int) (0.3650793650793651 * screenHeight));
			graphics.setColor(Color.WHITE);
			
            if(research < 1) {
            	graphics2D.drawImage(researchLock, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, researchLock.getWidth(), researchLock.getHeight(), null);
            }
            if(research < 25 || Game.foodTileLock == false) {
            	graphics2D.drawImage(foodLock, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, researchLock.getWidth(), researchLock.getHeight(), null);
            }
            if(research < 1000) {
            	graphics2D.drawImage(winLock, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, winLock.getWidth(), winLock.getHeight(), null);
            }
        } catch (IOException e) {
            Log.error(e);
        }
	}
	
	private void importMenu(Graphics graphics) {
		try {
            BufferedImage menu = ImageIO.read(new File("resources/images/import.png"));
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(menu, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, menu.getWidth(), menu.getHeight(), null);
    		graphics.setColor(Color.RED);
    		graphics.setFont(new Font("Castellar", 0, screenHeight / 15));
			graphics.drawString("      Money: " + moneyImport, (int) (0.375 * screenWidth), (int) (0.3174603174603175 * screenHeight));
			graphics.drawString("      Oxygen: " + airImport, (int) (0.375 * screenWidth), (int) (0.380952380952381 * screenHeight));
			graphics.drawString("      Food: " + (foodImport / 5), (int) (0.375 * screenWidth), (int) (0.4444444444444445 * screenHeight));
			graphics.drawString("      People: " + people, (int) (0.375 * screenWidth), (int) (0.507936507936508 * screenHeight));
			graphics.setColor(Color.WHITE);
			
        } catch (IOException e) {
            Log.error(e);
        }
	}
	
	private void pauseMenu(Graphics graphics) {
		try {
            BufferedImage menu = ImageIO.read(new File("resources/images/menu.png"));
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics.setColor(new Color(0, 0, 0, 200));
			graphics.fillRect(0, 0, screenWidth, screenHeight);
			graphics.setColor(Color.WHITE);
            graphics2D.drawImage(menu, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, menu.getWidth(), menu.getHeight(), null);
        } catch (IOException e) {
            Log.error(e);
        }
	}
	
	private void board(Graphics graphics) {
		for(int y = (Screen.yOffset / HEIGHT); y < (Screen.yOffset + screenHeight) / HEIGHT + 1 && y < LevelSaves.height; y++) {
			for(int x = (Screen.xOffset / WIDTH); x < (Screen.xOffset + screenWidth) / WIDTH + 1 && x < LevelSaves.width; x++) {
				if(x < 0) continue;
				renderTile(graphics, Tile.getTileByColor(LevelSaves.getARGB(x, y)), x, y);
			}
		}
	}
	
	private void overlay(Graphics graphics) {
		graphics.drawImage(overlay, 0, 0, screenWidth, screenHeight, 0, 0, overlay.getWidth(), overlay.getHeight(), null);
		graphics.setFont(new Font("Castellar", 0, screenHeight / 40));
		graphics.drawString("Money: " + money, 0, screenHeight / 50);
		graphics.drawString("Research: " + research, 0, screenHeight / 50 + (screenHeight / 40));
		graphics.drawString("Food: " + food, 0, screenHeight - (screenHeight / 2) + (screenHeight / 40));
		graphics.drawString("Oxygen: " + air, 0, screenHeight - (screenHeight / 2));
	}
	
	private void tile(Graphics graphics) {
		if(InputHandler.tileOverlay == true) {
			if(tileX < 0){
				tileX += 10;
				if(tileX > 0) {
					tileX = 0;
				}
			}
		} else if(InputHandler.tileOverlay == false) {
			if(tileX > (int) ((-1) * frame.getWidth() * 0.3)){
				tileX -= 10;
				if(tileX < (int) ((-1) * frame.getWidth() * 0.3)){
					tileX = (int) ((-1) * frame.getWidth() * 0.3);
				}
			}
		} else {
			Log.warning("Something Broke");
		}
		try {
			BufferedImage tile;
			if(Game.foodTileLock == true) {
				tile = ImageIO.read(new File("resources/images/locked_tiles.png"));
			} else {
				tile = ImageIO.read(new File("resources/images/tiles.png"));
			}
			graphics.drawImage(tile, tileX, 0, frame.getWidth() + tileX, frame.getHeight(), 0, 0, tile.getWidth(), tile.getHeight(), null);
		} catch(IOException e) {
            Log.error(e);
		}
	}
	
	private void fps(Graphics graphics) {
		if(Config.get("showfps").equals("true")) {
			graphics.setFont(new Font("Century Gothic", 0, screenHeight / 50));
			graphics.drawString("FPS: " + Game.fps, screenWidth - (screenWidth / 50) - (screenWidth / 50), screenHeight);
		} else if(Config.get("showfps").equals("false")) {
		} else {
			Log.warning("Unknow argument " + Config.get("showfps") + " in config.properties. Must be true or false");
		}
	}
	
	private void victory(Graphics graphics) {
		graphics.setColor(new Color(0, 0, 0, 200));
		graphics.fillRect(0, 0, screenWidth, screenHeight);
		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("OCR A Extended", 0, screenHeight / 10));
		graphics.drawString("You Win!", screenWidth / 2 - (screenWidth / 10), screenHeight / 2 - (screenHeight / 10));
	}
	
	private void gameOver(Graphics graphics) {
		graphics.setColor(new Color(0, 0, 0, 200));
		graphics.fillRect(0, 0, screenWidth, screenHeight);
		graphics.setColor(Color.RED);
		graphics.drawString("Game Over!", screenWidth / 2 - (screenWidth / 20), screenHeight / 2 - (screenHeight / 20));
	}
	
	/**private void renderTile(Tile tile, int x, int y) {
		BufferStrategy bufferStrategy = getBufferStrategy();
		if(bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics graphics = bufferStrategy.getDrawGraphics();
	}*/
	
	private void renderTile(Graphics graphics, Tile tile, int x, int y) {
		graphics.drawImage(SpriteSheet.getSprite(Tile.getTilePosX(tile), Tile.getTilePosY(tile)), 
				(WIDTH * x - Screen.xOffset), 
				HEIGHT * y - Screen.yOffset, WIDTH, HEIGHT, null);
	}
}
