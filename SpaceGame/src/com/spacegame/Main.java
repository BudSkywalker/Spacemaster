package com.spacegame;

import com.spacegame.ge.Game;

public class Main {	
	static Runnable console = new Console();
	
	static Game game = new Game();
	
	public static void main(String[] args) {
		game.initialize();
		Log.reset("latest.log");
		addConfig();
		Log.out("Configuration loaded");
		addCommands();
		Log.out("Commands loaded");
		if(Config.get("displayconsole").equals("true")) {
			console.run();
			Log.element("Console", "frmConsole", "Displayed");
		} else if(Config.get("displayconsole").equals("false")) {
		} else {
			Log.warning("Unknow argument " + Config.get("displayconsole") + " in config.properties. Must be true or false");
		}
		game.start();
	}
	
	public static void addCommands() {
		Commands.add("help", "help", "Displays all commands");
		Log.out("Help command added");
		Commands.add("money", "money <int>", "Adds money");
		Log.out("Money command added");
		Commands.add("research", "research <int>", "Adds research");
		Log.out("Research command added");
		Commands.add("air", "air <int>", "Adds air");
		Log.out("Air command added");
		Commands.add("scan", "scan", "Rescans map");
		Log.out("Scan command added");
		Commands.add("restart", "restart", "Restarts game");
		Log.out("Restart command added");
		Commands.add("moveTile", "moveTile <int>", "Moves Tile overlay");
		Log.out("Move Tile command added");
	}
	
	public static void addConfig() {
		Config.add("keeplogs", "true");
		Config.add("quicklaunch", "true");
		Config.add("displayconsole", "false");
		Config.add("showfps", "true");
		Config.store();
	}
}
