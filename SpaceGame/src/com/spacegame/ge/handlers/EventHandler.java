package com.spacegame.ge.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.spacegame.Log;
import com.spacegame.Sound;
import com.spacegame.ge.Game;
import com.spacegame.ge.Tick;
import com.spacegame.ge.gfx.Render;
import com.spacegame.ge.gfx.Screen;
import com.spacegame.ge.levels.LevelSaves;
import com.spacegame.ge.levels.Tile;

public class EventHandler {
	static InputHandler inputHandler;
	static Screen screen;
	static Game game;
	static Render render = new Render();
	static Tick tick = new Tick();
	
	public static String saveName = "SAVE";
	
	public static void startup() {
		LevelSaves.importTiles = 0;
		LevelSaves.researchTiles = 0;
		LevelSaves.mapList();
		Log.out("Mapped Level");
		
		try {
			FileInputStream propInput = new FileInputStream("resources/levels/" + saveName + ".resources");
	        Properties properties = new Properties();
	        properties.load(propInput);
	        
	        Game.money = Integer.parseInt(properties.getProperty("money"));
	        Log.element("ge.Game", "money", "Set to " + properties.getProperty("money"));
	        Game.research = Integer.parseInt(properties.getProperty("research"));
	        Log.element("ge.Game", "research", "Set to " + properties.getProperty("research"));
	        Game.air = Integer.parseInt(properties.getProperty("air"));
	        Log.element("ge.Game", "air", "Set to " + properties.getProperty("air"));
	        Game.people = Integer.parseInt(properties.getProperty("people"));
	        Log.element("ge.Game", "people", "Set to " + properties.getProperty("people"));
	        
	        Game.importTick = Integer.parseInt(properties.getProperty("importTick"));
	        Log.element("ge.Game", "importTick", "Set to " + properties.getProperty("importTick"));
	        Game.researchTick = Integer.parseInt(properties.getProperty("researchTick"));
	        Log.element("ge.Game", "researchTick", "Set to " + properties.getProperty("researchTick"));
	        Game.airTick = Integer.parseInt(properties.getProperty("airTick"));
	        Log.element("ge.Game", "airTick", "Set to " + properties.getProperty("airTick"));
	        
	        Game.importLimit = Integer.parseInt(properties.getProperty("importLimit"));
	        Log.element("ge.Game", "importLimit", "Set to " + properties.getProperty("importLimit"));
	        Game.airImport = Integer.parseInt(properties.getProperty("airImport"));
	        Log.element("ge.Game", "airImport", "Set to " + properties.getProperty("airImport"));
	        Game.moneyImport = Integer.parseInt(properties.getProperty("moneyImport"));
	        Log.element("ge.Game", "moneyImport", "Set to " + properties.getProperty("moneyImport"));
	        
	        
	        Game.foodTileLock = Boolean.parseBoolean(properties.getProperty("foodTileLock"));
	        Log.element("ge.Game", "foodTileLock", "Set to " + properties.getProperty("foodTileLock"));
	        
	        Screen.xOffset = Integer.parseInt(properties.getProperty("xOffset"));
	        Log.element("ge.gfx.Screen", "xOffset", "Set to " + properties.getProperty("xOffset"));
	        Screen.yOffset = Integer.parseInt(properties.getProperty("yOffset"));
	        Log.element("ge.gfx.Screen", "yOffset", "Set to " + properties.getProperty("yOffset"));
	        
	        propInput.close();
		} catch (FileNotFoundException e) {
			Log.error(e);
		} catch (IOException e) {
			Log.error(e);
		}
		
		try {
			File overlayPath = new File("resources/images/overlay.png");
			Game.overlay = ImageIO.read(overlayPath);
		} catch (IOException e) {
			Log.error(e);
		}
		
		CursorHandler.setCursor(CursorHandler.getID(CursorHandler.DEFAULT));
	}
	
	public static void restart() {
		Log.out("Restarting");
		
		try {
			File inputLocation = new File("resources/levels/template.png");
			File outputLocation = new File("resources/levels/" + saveName + ".png");
			
			FileInputStream input = new FileInputStream(inputLocation);
			Log.element("ge.handles.EventHandler", "input", "Template image mapped");
			FileOutputStream output = new FileOutputStream(outputLocation);
			Log.element("ge.handles.EventHandler", "output", "Save image mapped");
			
			byte[] buffer = new byte[1024];

	     	int length;
	        while ((length = input.read(buffer)) > 0){
	        	output.write(buffer, 0, length);
	        }
	        
	        Log.out(inputLocation.getAbsolutePath() + " copied to " + outputLocation.getAbsolutePath());
	        
	        FileOutputStream propOutput = new FileOutputStream("resources/levels/" + saveName + ".resources");
	        Properties properties = new Properties();
	        properties.setProperty("money", "500");
	        Log.out("Money stored with " + Game.getMoney() + " money.");
	        properties.setProperty("research", "0");
	        Log.out("Research stored with " + Game.getResearch() + " research.");
	        properties.setProperty("air", "60");
	        Log.out("Air stored with " + Game.air + " air.");
	        properties.setProperty("people", "1");
	        Log.out("People stored with " + Game.people + " people.");
	        
	        properties.setProperty("importTick", Game.importTick + "");
	        Log.out("Money Tick stored with " + Game.importTick + " ticks.");
	        properties.setProperty("researchTick", Game.researchTick + "");
	        Log.out("Research Tick stored with " + Game.researchTick + " ticks.");
	        properties.setProperty("airTick", Game.airTick + "");
	        Log.out("Air Tick stored with " + Game.airTick + " ticks.");
	        
	        properties.setProperty("foodTileLock", Game.foodTileLock + "");
	        Log.out("Food Tile Lock is " + Game.foodTileLock);
	        
	        properties.setProperty("airImport", Game.airImport + "");
	        Log.out("Air Import stored with " + Game.airImport);
	        properties.setProperty("moneyImport", Game.moneyImport + "");
	        Log.out("Money Import stored with " + Game.moneyImport);
	        
	        properties.store(propOutput, null);
	        Game.money = 500;
	        Game.research = 0;
	        Game.air = 60;
	        
	        Game.importLimit = 100;
	        Game.airImport = 30;
	        Game.foodImport = 15;
	        Game.moneyImport = 55;
	        
	        Game.foodTileLock = true;
	        
	        Game.importTick = 0;
	        Game.researchTick = 0;
	        
	        input.close();
	        output.close();
	        propOutput.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		LevelSaves.importTiles = 0;
		LevelSaves.researchTiles = 0;
		LevelSaves.mapList();
		Log.out("Mapped Level");
		
		try {
			File overlayPath = new File("resources/images/overlay.png");
			Game.overlay = ImageIO.read(overlayPath);
		} catch (IOException e) {
			Log.error(e);
		}
		
		CursorHandler.setCursor(CursorHandler.getID(CursorHandler.DEFAULT));
	}
	
	public static void save() {
		try {
	        FileOutputStream propOutput = new FileOutputStream("resources/levels/" + saveName + ".resources");
	        Properties properties = new Properties();
	        
	        properties.setProperty("money", Game.getMoney());
	        Log.out("Money stored with " + Game.getMoney() + " money.");
	        properties.setProperty("research", Game.getResearch());
	        Log.out("Research stored with " + Game.getResearch() + " research.");
	        properties.setProperty("air", Game.air + "");
	        Log.out("Air stored with " + Game.air + " air.");
	        properties.setProperty("people", Game.people + "");
	        Log.out("People stored with " + Game.people + " people.");
	        
	        properties.setProperty("importTick", Game.importTick + "");
	        Log.out("Money Tick stored with " + Game.importTick + " ticks.");
	        properties.setProperty("researchTick", Game.researchTick + "");
	        Log.out("Research Tick stored with " + Game.researchTick + " ticks.");
	        properties.setProperty("airTick", Game.airTick + "");
	        Log.out("Air Tick stored with " + Game.airTick + " ticks.");
	        
	        properties.setProperty("foodTileLock", Game.foodTileLock + "");
	        Log.out("Food Tile Lock is " + Game.foodTileLock);
	        
	        properties.setProperty("importLimit", Game.importLimit + "");
	        Log.out("Import Limit stored with " + Game.importLimit);
	        properties.setProperty("airImport", Game.airImport + "");
	        Log.out("Air Import stored with " + Game.airImport);
	        properties.setProperty("moneyImport", Game.moneyImport + "");
	        Log.out("Money Import stored with " + Game.moneyImport);
	        
	        properties.setProperty("xOffset", Screen.xOffset + "");
	        Log.out("X Offset stored at " + Screen.xOffset);
	        properties.setProperty("yOffset", Screen.yOffset + "");
	        Log.out("Y Offset stored at " + Screen.yOffset);
	        
	        Screen.xOffset = Integer.parseInt(properties.getProperty("xOffset"));
	        Log.element("ge.gfx.Screen", "xOffset", "Set to " + properties.getProperty("xOffset"));
	        Screen.yOffset = Integer.parseInt(properties.getProperty("yOffset"));
	        Log.element("ge.gfx.Screen", "yOffset", "Set to " + properties.getProperty("yOffset"));
	        
	        properties.store(propOutput, null);
	        propOutput.close();
	        
	        FileOutputStream settingsOutput = new FileOutputStream("resources/settings.properties");
	        Properties settingsProperties = new Properties();

	        settingsProperties.setProperty("doSound", Sound.doSound + "");
	        Log.out("Sound set to " + Sound.doSound);
	        settingsProperties.setProperty("doMusic", Sound.doMusic + "");
	        Log.out("Music set to " + Sound.doMusic);
	        
	        settingsProperties.store(settingsOutput, null);
	        settingsOutput.close();
    	} catch(FileNotFoundException e) {
    		Log.error(e);
    	} catch (IOException e) {
			Log.error(e);
		}
	}
	public static void shutdown() {
    	save();
    	Log.out("Goodbye!");
    	Log.dump();
    	System.exit(0);
	}
	
	public static void clickEvent(boolean isPressed) {
		//Settings
		if(Game.settings == true) {
			//Sound
			if(InputHandler.mouseRatioX >= 0.43125 && InputHandler.mouseRatioX <= 0.56875 &&
				InputHandler.mouseRatioY >= 0.05 && InputHandler.mouseRatioY <= 0.2611111111111111) {
				
				if(Sound.doSound == true) {
					Sound.doSound = false;
					Log.element("Sound", "doSound", "set to false");
				} else if(Sound.doSound == false) {
					Sound.doSound = true;
					Log.element("Sound", "doSound", "set to true");
				}
				
				Sound.play("click.wav");
			}
			//Music
			if(InputHandler.mouseRatioX >= 0.43125 && InputHandler.mouseRatioX <= 0.56875 &&
				InputHandler.mouseRatioY >= 0.2666666666666667 && InputHandler.mouseRatioY <= 0.4888888888888889) {
			
				if(Sound.doMusic == true) {
					Sound.doMusic = false;
					Log.element("Sound", "doSound", "set to false");
					Sound.music();
				} else if(Sound.doMusic == false) {
					Sound.doMusic = true;
					Log.element("Sound", "doSound", "set to true");
					Sound.music();
				}
				
				Sound.play("click.wav");
			}
			//Exit
			if(InputHandler.mouseRatioX >= 0.4625 && InputHandler.mouseRatioX <= 0.534375 &&
				InputHandler.mouseRatioY >= 0.9166666666666667 && InputHandler.mouseRatioY <= 0.9777777777777778) {
				Game.settings = false;
				Log.element("ge.Game", "settings", "set to false");
				Game.pause = false;
				Log.element("ge.Game", "pause", "set to false");
			}
		} else
			
		//Home Menu
		if(Game.homeMenu == true) {
			//New Game
			if(InputHandler.mouseRatioX >= 0.11875 && InputHandler.mouseRatioX <= 0.3543046357615894 &&
				InputHandler.mouseRatioY >= 0.2 && InputHandler.mouseRatioY <= 0.2555555555555556) {
				Game.homeMenu = false;
				Log.element("ge.Game", "homeMenu", "set to false");
				Game.newSave = true;
				Log.element("ge.Game", "newSave", "set to true");
				Sound.play("click.wav");
			}
			//Load Game
			if(InputHandler.mouseRatioX >= 0.11875 && InputHandler.mouseRatioX <= 0.315625 &&
				InputHandler.mouseRatioY >= 0.2777777777777778 && InputHandler.mouseRatioY <= 0.3666666666666667) {
					Game.homeMenu = false;
					Log.element("ge.Game", "homeMenu", "set to false");
					Game.loadSave = true;
					Log.element("ge.Game", "loadSave", "set to true");
					Sound.play("click.wav");
					Game.saveList = Game.saveFolder.listFiles(Game.filter);
				}
			//Options
			if(InputHandler.mouseRatioX >= 0.11875 && InputHandler.mouseRatioX <= 0.2625 &&
				InputHandler.mouseRatioY >= 0.6333333333333333 && InputHandler.mouseRatioY <= 0.6888888888888889) {
					Sound.play("click.wav");
					Game.settings = true;
					Log.element("ge.Game", "settings", "set to true");
				}
			//Exit
			if(InputHandler.mouseRatioX >= 0.11875 && InputHandler.mouseRatioX <= 0.184375 &&
				InputHandler.mouseRatioY >= 0.7444444444444444 && InputHandler.mouseRatioY <= 0.8) {
					shutdown();
				}
		} else
		
		//New Game
		if(Game.newSave == true) {
			/**Text Box
			if(InputHandler.mouseRatioX >= 0.234375 && InputHandler.mouseRatioX <= 0.76875 &&
				InputHandler.mouseRatioY >= 0.4166666666666667 && InputHandler.mouseRatioY <= 0.5888888888888889) {
				InputHandler.
			}
			if(InputHandler.mouseRatioX >= 0.76875 && InputHandler.mouseRatioX <= 0.234375 &&
				InputHandler.mouseRatioY >= 0.5888888888888889 && InputHandler.mouseRatioY <= 0.4166666666666667) {
					
			}*/
			//Go
			if(InputHandler.mouseRatioX >= 0.78125 && InputHandler.mouseRatioX <= 0.890625 &&
				InputHandler.mouseRatioY >= 0.4166666666666667 && InputHandler.mouseRatioY <= 0.5888888888888889) {
					Game.homeMenu = false;
					Log.element("ge.Game", "homeMenu", "set to false");
					Game.newSave = false;
					Log.element("ge.Game", "newSave", "set to false");
					Game.pause = false;
					Log.element("ge.Game", "pause", "set to false");
					Sound.play("click.wav");
					restart();	
			}
		} else
		
		//Load Game
		if(Game.loadSave == true) {
			//Scroll Down
			if(InputHandler.mouseRatioX >= 0.109375 && InputHandler.mouseRatioX <= 0.13125 &&
				InputHandler.mouseRatioY >= 0.75555555555555555555555555555556 && InputHandler.mouseRatioY <= 0.80555555555555555555555555555556) {
					Game.scrollOffset++;
					Sound.play("click.wav");
			} else
			//Scroll Up
			if(InputHandler.mouseRatioX >= 0.140625 && InputHandler.mouseRatioX <= 0.1625 &&
				InputHandler.mouseRatioY >= 0.75555555555555555555555555555556 && InputHandler.mouseRatioY <= 0.80555555555555555555555555555556) {
				Game.scrollOffset--;
				if(Game.scrollOffset < 0) {
					Game.scrollOffset = 0;
				}
				Sound.play("click.wav");
			} else
			//Save 1
			if(InputHandler.mouseRatioX >= 0.109375 && InputHandler.mouseRatioX <= 0.1375 &&
				InputHandler.mouseRatioY >= 0.19444444444444444444444444444444 && InputHandler.mouseRatioY <= 0.23333333333333333333333333333333) {
					saveName = Game.saveList[Game.scrollOffset + 0].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					Game.homeMenu = false;
					Log.element("ge.Game", "homeMenu", "set to false");
					Game.loadSave = false;
					Log.element("ge.Game", "loadSave", "set to false");
					Game.pause = false;
					Log.element("ge.Game", "pause", "set to false");
					Sound.play("click.wav");
					startup();
			} else
			//Save 2
			if(InputHandler.mouseRatioX >= 0.109375 && InputHandler.mouseRatioX <= 0.1375 &&
				InputHandler.mouseRatioY >= 0.25 && InputHandler.mouseRatioY <= 0.28888888888888888888888888888889) {
					saveName = Game.saveList[Game.scrollOffset + 1].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					Game.homeMenu = false;
					Log.element("ge.Game", "homeMenu", "set to false");
					Game.loadSave = false;
					Log.element("ge.Game", "loadSave", "set to false");
					Game.pause = false;
					Log.element("ge.Game", "pause", "set to false");
					startup();
			} else
			//Save 3
			if(InputHandler.mouseRatioX >= 0.109375 && InputHandler.mouseRatioX <= 0.1375 &&
				InputHandler.mouseRatioY >= 0.30555555555555555555555555555556 && InputHandler.mouseRatioY <= 0.34444444444444444444444444444444) {
					saveName = Game.saveList[Game.scrollOffset + 2].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					Game.homeMenu = false;
					Log.element("ge.Game", "homeMenu", "set to false");
					Game.loadSave = false;
					Log.element("ge.Game", "loadSave", "set to false");
					Game.pause = false;
					Log.element("ge.Game", "pause", "set to false");
					startup();
			} else
			//Save 4
			if(InputHandler.mouseRatioX >= 0.109375 && InputHandler.mouseRatioX <= 0.1375 &&
				InputHandler.mouseRatioY >= 0.36111111111111111111111111111111 && InputHandler.mouseRatioY <= 0.4) {
					saveName = Game.saveList[Game.scrollOffset + 3].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					Game.homeMenu = false;
					Log.element("ge.Game", "homeMenu", "set to false");
					Game.loadSave = false;
					Log.element("ge.Game", "loadSave", "set to false");
					Game.pause = false;
					Log.element("ge.Game", "pause", "set to false");
					startup();
			} else
			//Save 5
			if(InputHandler.mouseRatioX >= 0.109375 && InputHandler.mouseRatioX <= 0.1375 &&
				InputHandler.mouseRatioY >= 0.41666666666666666666666666666667 && InputHandler.mouseRatioY <= 0.45555555555555555555555555555556) {
					saveName = Game.saveList[Game.scrollOffset + 4].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					Game.homeMenu = false;
					Log.element("ge.Game", "homeMenu", "set to false");
					Game.loadSave = false;
					Log.element("ge.Game", "loadSave", "set to false");
					Game.pause = false;
					Log.element("ge.Game", "pause", "set to false");
					startup();
			} else
			//Save 6
			if(InputHandler.mouseRatioX >= 0.109375 && InputHandler.mouseRatioX <= 0.1375 &&
				InputHandler.mouseRatioY >= 0.47222222222222222222222222222222 && InputHandler.mouseRatioY <= 0.51111111111111111111111111111111) {
					saveName = Game.saveList[Game.scrollOffset + 5].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					Game.homeMenu = false;
					Log.element("ge.Game", "homeMenu", "set to false");
					Game.loadSave = false;
					Log.element("ge.Game", "loadSave", "set to false");
					Game.pause = false;
					Log.element("ge.Game", "pause", "set to false");
					startup();
			} else
			//Save 7
			if(InputHandler.mouseRatioX >= 0.109375 && InputHandler.mouseRatioX <= 0.1375 &&
				InputHandler.mouseRatioY >= 0.52777777777777777777777777777778 && InputHandler.mouseRatioY <= 0.56666666666666666666666666666667) {
					saveName = Game.saveList[Game.scrollOffset + 6].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					Game.homeMenu = false;
					Log.element("ge.Game", "homeMenu", "set to false");
					Game.loadSave = false;
					Log.element("ge.Game", "loadSave", "set to false");
					Game.pause = false;
					Log.element("ge.Game", "pause", "set to false");
					startup();
			} else
			//Save 8
			if(InputHandler.mouseRatioX >= 0.109375 && InputHandler.mouseRatioX <= 0.1375 &&
				InputHandler.mouseRatioY >= 0.58333333333333333333333333333333 && InputHandler.mouseRatioY <= 0.62222222222222222222222222222222) {
					saveName = Game.saveList[Game.scrollOffset + 7].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					Game.homeMenu = false;
					Log.element("ge.Game", "homeMenu", "set to false");
					Game.loadSave = false;
					Log.element("ge.Game", "loadSave", "set to false");
					Game.pause = false;
					Log.element("ge.Game", "pause", "set to false");
					startup();
			} else
			//Save 9
			if(InputHandler.mouseRatioX >= 0.109375 && InputHandler.mouseRatioX <= 0.1375 &&
				InputHandler.mouseRatioY >= 0.63888888888888888888888888888889 && InputHandler.mouseRatioY <= 0.67777777777777777777777777777778) {
					saveName = Game.saveList[Game.scrollOffset + 8].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					Game.homeMenu = false;
					Log.element("ge.Game", "homeMenu", "set to false");
					Game.loadSave = false;
					Log.element("ge.Game", "loadSave", "set to false");
					Game.pause = false;
					Log.element("ge.Game", "pause", "set to false");
					startup();
			} else
			//Save 10
			if(InputHandler.mouseRatioX >= 0.109375 && InputHandler.mouseRatioX <= 0.1375 &&
				InputHandler.mouseRatioY >= 0.69444444444444444444444444444444 && InputHandler.mouseRatioY <= 0.73333333333333333333333333333333) {
					saveName = Game.saveList[Game.scrollOffset + 9].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					Game.homeMenu = false;
					Log.element("ge.Game", "homeMenu", "set to false");
					Game.loadSave = false;
					Log.element("ge.Game", "loadSave", "set to false");
					Game.pause = false;
					Log.element("ge.Game", "pause", "set to false");
					startup();
			}
			//Delete 1
			if(InputHandler.mouseRatioX >= 0.86875 && InputHandler.mouseRatioX <= 0.890625 &&
				InputHandler.mouseRatioY >= 0.19444444444444444444444444444444 && InputHandler.mouseRatioY <= 0.23333333333333333333333333333333) {
					String string = Game.saveList[Game.scrollOffset + 0].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					File rFile = new File("resources/levels/" + string + ".resources");
					File sFile = new File("resources/levels/" + string + ".png");

					rFile.delete();
					sFile.delete();
					
					Log.out("Deleted level " + string);
					Sound.play("click.wav");
					Game.saveList = Game.saveFolder.listFiles(Game.filter);
			} else
			//Delete 2
			if(InputHandler.mouseRatioX >= 0.86875 && InputHandler.mouseRatioX <= 0.890625 &&
				InputHandler.mouseRatioY >= 0.25 && InputHandler.mouseRatioY <= 0.28888888888888888888888888888889) {
					String string = Game.saveList[Game.scrollOffset + 1].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					File rFile = new File("resources/levels/" + string + ".resources");
					File sFile = new File("resources/levels/" + string + ".resources");
					
					rFile.delete();
					sFile.delete();
					
					Log.out("Deleted level " + string);
					Sound.play("click.wav");
					Game.saveList = Game.saveFolder.listFiles(Game.filter);
			} else
			//Delete 3
			if(InputHandler.mouseRatioX >= 0.86875 && InputHandler.mouseRatioX <= 0.890625 &&
				InputHandler.mouseRatioY >= 0.30555555555555555555555555555556 && InputHandler.mouseRatioY <= 0.34444444444444444444444444444444) {
					String string = Game.saveList[Game.scrollOffset + 2].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					File rFile = new File("resources/levels/" + string + ".resources");
					File sFile = new File("resources/levels/" + string + ".resources");
					
					rFile.delete();
					sFile.delete();
					
					Log.out("Deleted level " + string);
					Sound.play("click.wav");
					Game.saveList = Game.saveFolder.listFiles(Game.filter);
			} else
			//Delete 4
			if(InputHandler.mouseRatioX >= 0.86875 && InputHandler.mouseRatioX <= 0.890625 &&
				InputHandler.mouseRatioY >= 0.36111111111111111111111111111111 && InputHandler.mouseRatioY <= 0.4) {
					String string = Game.saveList[Game.scrollOffset + 3].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					File rFile = new File("resources/levels/" + string + ".resources");
					File sFile = new File("resources/levels/" + string + ".resources");
					
					rFile.delete();
					sFile.delete();
					
					Log.out("Deleted level " + string);
					Sound.play("click.wav");
					Game.saveList = Game.saveFolder.listFiles(Game.filter);
			} else
			//Delete 5
			if(InputHandler.mouseRatioX >= 0.86875 && InputHandler.mouseRatioX <= 0.890625 &&
				InputHandler.mouseRatioY >= 0.41666666666666666666666666666667 && InputHandler.mouseRatioY <= 0.45555555555555555555555555555556) {
					String string = Game.saveList[Game.scrollOffset + 4].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					File rFile = new File("resources/levels/" + string + ".resources");
					File sFile = new File("resources/levels/" + string + ".resources");
					
					rFile.delete();
					sFile.delete();
					
					Log.out("Deleted level " + string);
					Sound.play("click.wav");
					Game.saveList = Game.saveFolder.listFiles(Game.filter);
			} else
			//Delete 6
			if(InputHandler.mouseRatioX >= 0.86875 && InputHandler.mouseRatioX <= 0.890625 &&
				InputHandler.mouseRatioY >= 0.47222222222222222222222222222222 && InputHandler.mouseRatioY <= 0.51111111111111111111111111111111) {
					String string = Game.saveList[Game.scrollOffset + 5].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					File rFile = new File("resources/levels/" + string + ".resources");
					File sFile = new File("resources/levels/" + string + ".resources");
					
					rFile.delete();
					sFile.delete();
					
					Log.out("Deleted level " + string);
					Sound.play("click.wav");
					Game.saveList = Game.saveFolder.listFiles(Game.filter);
			} else
			//Delete 7
			if(InputHandler.mouseRatioX >= 0.86875 && InputHandler.mouseRatioX <= 0.890625 &&
				InputHandler.mouseRatioY >= 0.52777777777777777777777777777778 && InputHandler.mouseRatioY <= 0.56666666666666666666666666666667) {
					String string = Game.saveList[Game.scrollOffset + 6].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					File rFile = new File("resources/levels/" + string + ".resources");
					File sFile = new File("resources/levels/" + string + ".resources");
					
					rFile.delete();
					sFile.delete();
					
					Log.out("Deleted level " + string);
					Sound.play("click.wav");
					Game.saveList = Game.saveFolder.listFiles(Game.filter);
			} else
			//Delete 8
			if(InputHandler.mouseRatioX >= 0.86875 && InputHandler.mouseRatioX <= 0.890625 &&
				InputHandler.mouseRatioY >= 0.58333333333333333333333333333333 && InputHandler.mouseRatioY <= 0.62222222222222222222222222222222) {
					String string = Game.saveList[Game.scrollOffset + 7].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					File rFile = new File("resources/levels/" + string + ".resources");
					File sFile = new File("resources/levels/" + string + ".resources");
					
					rFile.delete();
					sFile.delete();
					
					Log.out("Deleted level " + string);
					Sound.play("click.wav");
					Game.saveList = Game.saveFolder.listFiles(Game.filter);
			} else
			//Delete 9
			if(InputHandler.mouseRatioX >= 0.86875 && InputHandler.mouseRatioX <= 0.890625 &&
				InputHandler.mouseRatioY >= 0.63888888888888888888888888888889 && InputHandler.mouseRatioY <= 0.67777777777777777777777777777778) {
					String string = Game.saveList[Game.scrollOffset + 8].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					File rFile = new File("resources/levels/" + string + ".resources");
					File sFile = new File("resources/levels/" + string + ".resources");
					
					rFile.delete();
					sFile.delete();
					
					Log.out("Deleted level " + string);
					Sound.play("click.wav");
					Game.saveList = Game.saveFolder.listFiles(Game.filter);
			} else
			//Delete 10
			if(InputHandler.mouseRatioX >= 0.86875 && InputHandler.mouseRatioX <= 0.890625 &&
				InputHandler.mouseRatioY >= 0.69444444444444444444444444444444 && InputHandler.mouseRatioY <= 0.73333333333333333333333333333333) {
					String string = Game.saveList[Game.scrollOffset + 9].toString().substring(17, Game.saveList[Game.scrollOffset + 0].toString().length() - 10);
					File rFile = new File("resources/levels/" + string + ".resources");
					File sFile = new File("resources/levels/" + string + ".resources");
					
					rFile.delete();
					sFile.delete();
					
					Log.out("Deleted level " + string);
					Sound.play("click.wav");
					Game.saveList = Game.saveFolder.listFiles(Game.filter);
			}		
		} else
			
		//Pause Menu
		if(InputHandler.inMenu == true) {
			//Restart Button
			if(InputHandler.mouseRatioX >= 0.3303571428571429 && InputHandler.mouseRatioX <= 0.6696428571428571 && 
				InputHandler.mouseRatioY >= 0.0476190476190476 && InputHandler.mouseRatioY <= 0.1746031746031746) {
				
				Sound.play("click.wav");
				InputHandler.inMenu = false;
				Log.element("ge.handlers.InputHandler", "inMenu", "set to false");
				Game.pause = false;
				Log.element("ge.Game", "pause", "set to false");
				Log.out("Restart Button Pressed");
				EventHandler.restart();
				Log.element("ge.levels.LevelSaves", "inMenu", "set to false");
			
			//Resume Button
			} else if(InputHandler.mouseRatioX >= 0.3392857142857143 && InputHandler.mouseRatioX <= 0.6607142857142857 && 
					InputHandler.mouseRatioY >= 0.2063492063492063 && InputHandler.mouseRatioY <= 0.3333333333333333) {
				
				Sound.play("click.wav");
				InputHandler.inMenu = false;
				Log.element("ge.handlers.InputHandler", "inMenu", "set to false");
				Game.pause = false;
				Log.element("ge.Game", "pause", "set to false");
				Log.out("Resume Button Pressed");
				Log.element("ge.levels.LevelSaves", "inMenu", "set to false");
			//Settings Button
			} else if(InputHandler.mouseRatioX >= 0.2857142857142857 && InputHandler.mouseRatioX <= 0.7142857142857143 && 
					  InputHandler.mouseRatioY >= 0.3650793650793651 && InputHandler.mouseRatioY <= 0.4920634920634921) {
				
				Sound.play("click.wav");
				Game.settings = true;
				Log.element("ge.Game", "settings", "set to true");
			//Exit Button
			} else if(InputHandler.mouseRatioX >= 0.4017857142857143 && InputHandler.mouseRatioX <= 0.5982142857142857 && 
					  InputHandler.mouseRatioY >= 0.8412698412698413 && InputHandler.mouseRatioY <= 0.9682539682539683) {
				save();
				Sound.play("click.wav");
		    	Game.homeMenu = true;
		    	Log.element("ge.Game", "homeMenu", "set to false");
			}
		} else 
		
		//Game	
		if(InputHandler.inMenu == false) {
			//Import Menu
			if(InputHandler.importMenu == true && InputHandler.researchMenu == false && InputHandler.inMenu == false) {
				Game.imports = Game.moneyImport + Game.airImport + Game.foodImport;
				
				//Money +
				if(InputHandler.mouseRatioX >= 0.375 && InputHandler.mouseRatioX <= 0.4017857142857143 && 
					InputHandler.mouseRatioY >= 0.2698412698412698 && InputHandler.mouseRatioY <= 0.3174603174603175) {
					
					if(isPressed == true && Game.imports <= Game.importLimit - 10) {
						Game.moneyImport += 10;
					}
					if(isPressed == false && Game.imports < Game.importLimit) {
						Game.moneyImport++;
					}
				}
				
				//Money -
				if(InputHandler.mouseRatioX >= 0.4107142857142857 && InputHandler.mouseRatioX <= 0.4375 && 
					InputHandler.mouseRatioY >= 0.2698412698412698 && InputHandler.mouseRatioY <= 0.3174603174603175) {
					
					if(isPressed == true && Game.moneyImport >= 10) {
						Game.moneyImport -= 10;
					}
					
					if(isPressed == false && Game.moneyImport >= 1) {
						Game.moneyImport--;
					}
				}
				
				//Air +
				if(InputHandler.mouseRatioX >= 0.375 && InputHandler.mouseRatioX <= 0.4017857142857143 && 
					InputHandler.mouseRatioY >= 0.3333333333333333 && InputHandler.mouseRatioY <= 0.380952380952381) {
					
					if(isPressed == true && Game.imports <= Game.importLimit - 10) {
						Game.airImport += 10;
					}
					
					if(isPressed == false && Game.imports < Game.importLimit) {
						Game.airImport++;
					}
				}
				
				//Air -
				if(InputHandler.mouseRatioX >=  0.4107142857142857 && InputHandler.mouseRatioX <=  0.4375 && 
					InputHandler.mouseRatioY >=  0.3333333333333333 && InputHandler.mouseRatioY <= 0.380952380952381) {
					if(isPressed == true && Game.airImport >= 10) {
						Game.airImport -= 10;
					}
					
					if(isPressed == false && Game.airImport >= 1) {
						Game.airImport--;
					}
				}
				
				//Food +
				if(InputHandler.mouseRatioX >= 0.375 && InputHandler.mouseRatioX <= 0.4017857142857143 && 
					InputHandler.mouseRatioY >= 0.3968253968253968253968253968254 && InputHandler.mouseRatioY <= 0.44444444444444444444444444444444 &&
					Game.imports <= Game.importLimit - 5) {
						Game.foodImport += 5;
						Log.element("ge.Game", "foodImport", "Set to " + Game.foodImport);
				}
				//Food -
				if(InputHandler.mouseRatioX >=  0.4107142857142857 && InputHandler.mouseRatioX <=  0.4375 && 
					InputHandler.mouseRatioY >=  0.3968253968253968253968253968254 && InputHandler.mouseRatioY <= 0.44444444444444444444444444444444 &&
					Game.foodImport >= 5) {
						Game.foodImport -= 5;
						Log.element("ge.Game", "foodImport", "Set to " + Game.foodImport);
				}
				
				//People +
				if(InputHandler.mouseRatioX >= 0.375 && InputHandler.mouseRatioX <= 0.4017857142857143 && 
					InputHandler.mouseRatioY >= 0.4603174603174603253968253968254 && InputHandler.mouseRatioY <= 0.50793650793650788888888888888888) {
						Game.people++;
						Log.element("ge.Game", "people", "Set to " + Game.people);
				}
				//People -
				if(InputHandler.mouseRatioX >=  0.4107142857142857 && InputHandler.mouseRatioX <=  0.4375 && 
					InputHandler.mouseRatioY >=  0.4603174603174603253968253968254 && InputHandler.mouseRatioY <= 0.50793650793650788888888888888888) {
					if(!(Game.people <= 1) && Game.people > LevelSaves.researchTiles) {
						Game.people--;
						Log.element("ge.Game", "people", "Set to " + Game.people);
					}
				}
				Sound.play("click.wav");
			} else
			
			//Research Menu
			if(InputHandler.researchMenu == true && InputHandler.importMenu == false && InputHandler.inMenu == false) {
				//Exit
				if(InputHandler.mouseRatioX >= 0.9732142857142857 && InputHandler.mouseRatioX <= 1 && 
					InputHandler.mouseRatioY >= 0 && InputHandler.mouseRatioY <= 0.0476190476190476) {
					
					InputHandler.researchMenu = false;
					Log.element("ge.handlers.InputHandler", "researchMenu", "set to false");
					Sound.play("click.wav");
				}
				
				//Import Limit
				if(InputHandler.mouseRatioX >= 0.0267857142857143 && InputHandler.mouseRatioX <= 0.1696428571428571 && 
					InputHandler.mouseRatioY >= 0.0793650793650794 && InputHandler.mouseRatioY <= 0.3333333333333333) {
					
					if(Game.research >= 1) {
						Game.importLimit++;
						Log.element("ge.Game", "importLimit", "Set to " + Game.importLimit);
						Game.research--;
						Log.element("ge.Game", "research", "Set to " + Game.research);
						Sound.play("research.wav");
					} else {
						Sound.play("lock.wav");
					}
				}
				
				//Food
				if(InputHandler.mouseRatioX >= 0.1875 && InputHandler.mouseRatioX <= 0.3303571428571429 && 
					InputHandler.mouseRatioY >= 0.0793650793650794 && InputHandler.mouseRatioY <= 0.3333333333333333) {
					
					if(Game.research >= 25 && Game.foodTileLock == true) {
						Game.foodTileLock = false;
						Game.research -= 25;
						Log.element("ge.Game", "research", "Set to " + Game.research);
						Sound.play("research.wav");
					} else {
						Sound.play("lock.wav");
					}
				}
				
				//Victory
				if(InputHandler.mouseRatioX >= 0.3482142857142857 && InputHandler.mouseRatioX <= 0.4910714285714286 && 
					InputHandler.mouseRatioY >= 0.0793650793650794 && InputHandler.mouseRatioY <= 0.3333333333333333) {
					
					if(Game.research >= 1000) {
						Game.gameOver = true;
						Log.element("ge.Game", "gameOver", "Set to true");
						Game.victory = true;
						Log.element("ge.Game", "victory", "Set to true");
						Game.research -= 1000;
						Log.element("ge.Game", "research", "Set to " + Game.research);
						Sound.play("research.wav");
					} else {
						Sound.play("lock.wav");
					}
				}
			} else
			
			//Overlay
			if(InputHandler.mouseRatioX >= 0 && InputHandler.mouseRatioX <= 0.1125 && 
				InputHandler.mouseRatioY >= 0 && InputHandler.mouseRatioY <= 1) {
				
				//Tile Extension
				if(InputHandler.researchMenu == false &&
					InputHandler.importMenu == false &&
					InputHandler.mouseRatioX >= 0.0125 && InputHandler.mouseRatioX <= 0.1 && 
					InputHandler.mouseRatioY >= 0.85 && InputHandler.mouseRatioY <= 0.98333333333333333333333333333333) {
					
					if(InputHandler.tileOverlay == true){
						InputHandler.tileOverlay = false;
					} else if(InputHandler.tileOverlay == false) {
						InputHandler.tileOverlay = true;
					} else {
						Log.warning("Something broke");
					}
					Log.element("ge.handlers.InputHandler", "tileOverlay", "Set to true");
					Sound.play("click.wav");
				}
				//Import Menu
				if(InputHandler.researchMenu == false &&
					InputHandler.mouseRatioX >= 0.0125 && InputHandler.mouseRatioX <= 0.1 && 
					InputHandler.mouseRatioY >= 0.72777777777777777777777777777778 && InputHandler.mouseRatioY <= 0.83888888888888888888888888888889) {
					
					InputHandler.importMenu = true;
					Log.element("ge.handlers.InputHandler", "importMenu", "Set to true");
					Sound.play("click.wav");
				}
				//Research Menu
				if(InputHandler.importMenu == false &&
					InputHandler.mouseRatioX >= 0.0125 && InputHandler.mouseRatioX <= 0.1 && 
					InputHandler.mouseRatioY >= 0.55 && InputHandler.mouseRatioY <= 0.71666666666666666666666666666667) {
						
						InputHandler.researchMenu = true;
						Log.element("ge.handlers.InputHandler", "researchMenu", "Set to true");
						Sound.play("click.wav");
				}
			} else 
			
			//Tile Selection
			if(InputHandler.mouseRatioX >= 0.1125 && InputHandler.mouseRatioX <= 0.390625 &&
				InputHandler.mouseRatioY >= 0.8888888888888889 && InputHandler.mouseRatioY <= 1) {
				
				//Space
				if(InputHandler.mouseRatioX >= 0.115625 && InputHandler.mouseRatioX <= 0.1625 &&
					InputHandler.mouseRatioY >= 0.9 && InputHandler.mouseRatioY <= 0.9888888888888889) {
					
					Game.tile = true;
					CursorHandler.setCursor(CursorHandler.getID(CursorHandler.VOID));
					Sound.play("click.wav");
					
				}
				//Ship
				if(InputHandler.mouseRatioX >= 0.171875 && InputHandler.mouseRatioX <= 0.221875 &&
					InputHandler.mouseRatioY >= 0.9 && InputHandler.mouseRatioY <= 0.9888888888888889) {
					
					Game.tile = true;
					CursorHandler.setCursor(CursorHandler.getID(CursorHandler.SHIP));
					Sound.play("click.wav");
				}
				//Tile
				if(InputHandler.mouseRatioX >= 0.228125 && InputHandler.mouseRatioX <= 0.278125 &&
					InputHandler.mouseRatioY >= 0.9 && InputHandler.mouseRatioY <= 0.9888888888888889) {
					
					Game.tile = true;
					CursorHandler.setCursor(CursorHandler.getID(CursorHandler.TILE));
					Sound.play("click.wav");
				}
				//Research
				if(InputHandler.mouseRatioX >= 0.284375 && InputHandler.mouseRatioX <= 0.334375 &&
					InputHandler.mouseRatioY >= 0.9 && InputHandler.mouseRatioY <= 0.9888888888888889){
					
					Game.tile = true;
					CursorHandler.setCursor(CursorHandler.getID(CursorHandler.TDESK));
					Sound.play("click.wav");
				}
				//Food
				if(InputHandler.mouseRatioX >= 0.340625 && InputHandler.mouseRatioX <= 0.390625 &&
					InputHandler.mouseRatioY >= 0.9 && InputHandler.mouseRatioY <= 0.9888888888888889){
					
					if(Game.foodTileLock == false) {
						Game.tile = true;
						CursorHandler.setCursor(CursorHandler.getID(CursorHandler.FOOD));
						Sound.play("click.wav");
					} else {
						Sound.play("lock.wav");
					}
					
				}
			//Board
			} else if(InputHandler.inMenu == false && InputHandler.importMenu == false && InputHandler.researchMenu == false) {
				
				int x = ((InputHandler.x + Screen.xOffset) / Game.WIDTH);
				int y = ((InputHandler.y + Screen.yOffset) / Game.HEIGHT);
				//Tile
				if(Game.money >= 25 && 
					CursorHandler.cursorNum == CursorHandler.getID(CursorHandler.TILE) &&
				   !(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.FLOOR))){
			
					if(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.ROCKET) || 
						LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.LIMPORT) ||
						LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.DIMPORT) ||
						LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.RIMPORT) ||
						LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.UIMPORT)) {
						
						Game.money += 400;
						Log.element("ge.Game", "money", "set to " + Game.money);
					}
					if(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.FOOD)) {
						
						Game.money += 80;
						Log.element("ge.Game", "money", "set to " + Game.money);
					}
					if(!(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.TDESK)) ||
					   !(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.RDESK)) ||
					   !(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.BDESK)) ||
					   !(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.LDESK)) ||
					   !(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.FOOD))) {
						Game.money -= 25;
						Log.element("ge.Game", "money", "set to " + Game.money);
					}
					LevelSaves.setMap(x, y, Tile.getMapColor(Tile.FLOOR));
					Sound.play("tile.wav");
				}
				//Ship
				if(Game.money >= 500 && 
					CursorHandler.cursorNum == CursorHandler.getID(CursorHandler.SHIP) && 
					!(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.ROCKET) || 
					LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.LIMPORT) ||
					LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.DIMPORT) ||
					LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.RIMPORT) ||
					LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.UIMPORT))) {
					
					if(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.FLOOR)) {
						Game.money += 20;
						Log.element("ge.Game", "money", "set to " + Game.money);
					}
					if(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.FOOD)) {
						Game.money += 80;
						Log.element("ge.Game", "money", "set to " + Game.money);
					}
					
					LevelSaves.setMap(x, y, Tile.getMapColor(Tile.ROCKET));
					Game.money -= 500;
					Log.element("ge.Game", "money", "set to " + Game.money);
					Sound.play("tile.wav");
				}
				//Research
				if(CursorHandler.cursorNum == CursorHandler.getID(CursorHandler.TDESK) &&
					LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.FLOOR) &&
					Game.people > LevelSaves.researchTiles + (LevelSaves.foodTiles / 2)) {
					
					LevelSaves.setMap(x, y, Tile.getMapColor(Tile.TDESK));
					Sound.play("tile.wav");
				}
				if(CursorHandler.cursorNum == CursorHandler.getID(CursorHandler.RDESK) &&
						LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.FLOOR) &&
						Game.people > LevelSaves.researchTiles) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.RDESK));
						Sound.play("tile.wav");
					}
				if(CursorHandler.cursorNum == CursorHandler.getID(CursorHandler.BDESK) &&
						LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.FLOOR) &&
						Game.people > LevelSaves.researchTiles) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.BDESK));
						Sound.play("tile.wav");
					}
				if(CursorHandler.cursorNum == CursorHandler.getID(CursorHandler.LDESK) &&
						LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.FLOOR) &&
						Game.people > LevelSaves.researchTiles) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.LDESK));
						Sound.play("tile.wav");
					}
				//Space
				if(CursorHandler.cursorNum == CursorHandler.getID(CursorHandler.VOID)){
					
					if(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.ROCKET) || 
						LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.LIMPORT) ||
						LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.DIMPORT) ||
						LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.RIMPORT) ||
						LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.UIMPORT)) {
	
						Game.money += 400;
						Log.element("ge.Game", "money", "set to " + Game.money);
					} else
					if(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.FLOOR)) {
						Game.money += 20;
						Log.element("ge.Game", "money", "set to " + Game.money);
					} else
					if(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.TDESK) ||
					   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.RDESK) ||
					   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.BDESK) ||
					   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.LDESK)) {
						Game.money += 20;
						Log.element("ge.Game", "money", "set to " + Game.money);
					} else
					if(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.FOOD)) {
						Game.money += 80;
						Log.element("ge.Game", "money", "set to " + Game.money);
					}
					
					LevelSaves.setMap(x, y, Tile.getMapColor(Tile.SPACE));
					Sound.play("tile.wav");
				}
				//Food
				if(Game.money >= 100 &&
					CursorHandler.cursorNum == CursorHandler.getID(CursorHandler.FOOD) &&
					LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.FLOOR) &&
					Game.people > LevelSaves.researchTiles + (LevelSaves.foodTiles / 2)) {
					
					LevelSaves.setMap(x, y, Tile.getMapColor(Tile.FOOD));
					Game.money -= 100;
					Log.element("ge.Game", "money", "set to " + Game.money);
					Sound.play("tile.wav");
				}
				tick.renderTick(x, y);
			}
		}
		//Game Over
		if(Game.gameOver == true) {
			Sound.play("click.wav");
			Game.gameOver = false;
			EventHandler.restart();
		}
	}
}
