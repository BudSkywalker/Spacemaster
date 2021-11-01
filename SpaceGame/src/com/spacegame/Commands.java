package com.spacegame;

import java.util.ArrayList;

import com.spacegame.ge.Game;
import com.spacegame.ge.gfx.Render;
import com.spacegame.ge.handlers.EventHandler;
import com.spacegame.ge.levels.LevelSaves;

public class Commands {
	static ArrayList<String> commands = new ArrayList<>();
	static ArrayList<String> commandsUse = new ArrayList<>();
	static ArrayList<String> commandsDesc = new ArrayList<>();
	
	public static void add(String command, String commandUse, String commandDesc) {
		commands.add(command);
		commandsUse.add(commandUse);
		commandsDesc.add(commandDesc);
	}
	
	public static String get(int num, String string) {
		if(string == "command") {
			return commands.get(num);
		} else if(string == "commandUse") {
			return commandsUse.get(num);
		} else if(string == "commandDesc") {
			return commandsDesc.get(num);
		} else {
			return null;
		}
	}
	
	public static int getID(String command) {
		int spaces = 0;
		int s1 = command.length();

		for(int j = 0; j < command.length(); j++) {
			if(command.charAt(j) == ' ') {
				spaces++;
				if(spaces == 1) {
					s1 = j;
				}
			}
		}
		
		command = command.substring(0, s1);
		
		if(command.equals(get(0, "command"))) {
			return 0;
		} else if(command.equals(get(1, "command"))) {
			return 1;
		} else if(command.equals(get(2, "command"))) {
			return 2;
		} else if(command.equals(get(3, "command"))) {
			return 3;
		} else if(command.equals(get(4, "command"))) {
			return 4;
		} else if(command.equals(get(5, "command"))) {
			return 5;
		} else if(command.equals(get(6, "command"))) {
			return 6;
		} else {
			return 7;
		}
	}
	
	public static void run(String command) {
		int num = getID(command);
		
		int spaces = 0;
		int s1 = command.length();

		for(int j = 0; j < command.length(); j++) {
			if(command.charAt(j) == ' ') {
				spaces++;
				if(spaces == 1) {
					s1 = j;
				}
			}
		}
		
		String sub = command.substring(s1).trim();
		
		switch(num) {
		case 0:
			for(int i = 0; i < commands.size(); i++) {
				Log.out(commandsUse.get(i) + ": " + commandsDesc.get(i));
			}
			break;
		case 1:
			if(!(sub.isEmpty()) && (sub.contains("1") || sub.contains("2") || sub.contains("3") || sub.contains("4") || sub.contains("5") || sub.contains("6") || sub.contains("7") 
					|| sub.contains("8") || sub.contains("9") || sub.contains("0"))) {
				Game.money += Integer.parseInt(sub);
				Log.out(command);
			} else {
				Log.warning("Usage: " + commandsUse.get(1));
			}
			break;
		case 2:
			if(!(sub.isEmpty()) && (sub.contains("1") || sub.contains("2") || sub.contains("3") || sub.contains("4") || sub.contains("5") || sub.contains("6") || sub.contains("7") 
					|| sub.contains("8") || sub.contains("9") || sub.contains("0"))) {
				Game.research += Integer.parseInt(sub);
				Log.out(command);
			} else {
				Log.warning("Usage: " + commandsUse.get(2));
			}
			break;
		case 3:
			if(!(sub.isEmpty()) && (sub.contains("1") || sub.contains("2") || sub.contains("3") || sub.contains("4") || sub.contains("5") || sub.contains("6") || sub.contains("7") 
					|| sub.contains("8") || sub.contains("9") || sub.contains("0"))) {
				Game.air += Integer.parseInt(sub);
				Log.out(command);
			} else {
				Log.warning("Usage: " + commandsUse.get(3));
			}
			break;
		case 4:
			Log.out(command);
			LevelSaves.mapList();
			break;
		case 5:
			Log.out(command);
			EventHandler.restart();
			break;
		case 6:
			if(!(sub.isEmpty()) && (sub.contains("1") || sub.contains("2") || sub.contains("3") || sub.contains("4") || sub.contains("5") || sub.contains("6") || sub.contains("7") 
					|| sub.contains("8") || sub.contains("9") || sub.contains("0"))) {
				Render.tileX += Integer.parseInt(sub);
				Log.element("ge.gfx.Render", "tileX", "Changed to " + Render.tileX);
				Log.out(command);
			} else {
				Log.warning("Usage: " + commandsUse.get(6));
			}
			break;
		default:
			Log.warning("Unkown Command");
			break;
		}
	}
}
