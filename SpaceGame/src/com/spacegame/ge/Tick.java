package com.spacegame.ge;

import com.spacegame.Sound;
import com.spacegame.ge.levels.LevelSaves;
import com.spacegame.ge.levels.Tile;

public class Tick extends Game {
	private static final long serialVersionUID = 1L;
	
	public Tick() {
	}
	
	public void tick() {
		tickCount++;
		researchTick++;
		importTick++;
		foodTick++;
		airTick++;
		
		int researchTicks = 1500;
		int importTicks = 3000;
		int foodTicks = 1000;
		int airTicks = 100;
		
		if(airTick >= airTicks) {
			airTick();
		}
		
		if(researchTick >= researchTicks) {
			researchTick();
		}
		
		if(importTick >= importTicks) {
			importTick();
		}
		
		if(foodTick >= foodTicks) {
			foodTick();
		}
		if(air <= 0 || food <= 0) {
			gameOver = true;
		}
	}
	
	public void airTick() {
		air -= people;
		airTick = 0;
	}
	
	public void foodTick() {
		food -= people;
		foodTick = 0;
	}
	public void researchTick() {
		research += LevelSaves.researchTiles;
		air += LevelSaves.foodTiles * 20;
		food += LevelSaves.foodTiles * 2;		 
		researchTick = 0;
	}
	
	public void importTick() {
		Sound.play("import.wav");
		money += moneyImport * LevelSaves.importTiles;
		air += airImport * LevelSaves.importTiles;
		food += foodImport / 5 * LevelSaves.importTiles;
		importTick = 0;
	}
	
	public void renderTick(int x, int y) {
		int finX = x + 2;
		int finY = y + 2;
		for(y -= 1; y < finY; y++) {
			if(y < 0) y = 0;
			for(x -= 1; x < finX; x++) {
				if(x < 0) x = 0;
				
				//Ship	
				if(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.ROCKET) || 
					LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.LIMPORT) ||
					LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.DIMPORT) ||
					LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.RIMPORT) ||
					LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.UIMPORT)) {
					
					if(LevelSaves.getARGB(x - 1, y) == Tile.getMapColor(Tile.FLOOR)) {
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.LIMPORT));
					} else
					if(LevelSaves.getARGB(x, y + 1) == Tile.getMapColor(Tile.FLOOR)) {
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.DIMPORT));
					} else
					if(LevelSaves.getARGB(x + 1, y) == Tile.getMapColor(Tile.FLOOR)) {
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.RIMPORT));
					} else
					if(LevelSaves.getARGB(x, y - 1) == Tile.getMapColor(Tile.FLOOR)) {
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.UIMPORT));
					}
				} else
				
				//Tile
				if(LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.SPACE) || 
				   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.LTCORNER) ||
				   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.TWALL) ||
			       LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.RTCORNER) ||
			       LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.LWALL) ||
			       LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.RWALL) ||
				   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.LBCORNER) ||
				   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.BWALL) ||
				   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.RBCORNER) ||
				   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.RLWALL) ||
				   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.TBWALL) ||
				   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.TOPEN) ||
				   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.BOPEN) ||
				   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.LOPEN) ||
				   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.ROPEN) ||
				   LevelSaves.getARGB(x, y) == Tile.getMapColor(Tile.CLOSED)) {
					
					boolean top;
					boolean bottom;
					boolean left;
					boolean right;
					
					
					if(LevelSaves.getARGB(x + 1, y) == (Tile.getMapColor(Tile.FLOOR)) ||
					   LevelSaves.getARGB(x + 1, y) == (Tile.getMapColor(Tile.FOOD)) ||
					   LevelSaves.getARGB(x + 1, y) == (Tile.getMapColor(Tile.RESEARCH)) ||
					   LevelSaves.getARGB(x + 1, y) == (Tile.getMapColor(Tile.TDESK)) ||
					   LevelSaves.getARGB(x + 1, y) == (Tile.getMapColor(Tile.RDESK)) ||
					   LevelSaves.getARGB(x + 1, y) == (Tile.getMapColor(Tile.BDESK)) ||
					   LevelSaves.getARGB(x + 1, y) == (Tile.getMapColor(Tile.LDESK))) {
						right = true;
					} else {
						right = false;
					}
					
					if(x <= 0) {
						left = false;
					} else if(LevelSaves.getARGB(x - 1, y) == (Tile.getMapColor(Tile.FLOOR)) ||
					   LevelSaves.getARGB(x - 1, y) == (Tile.getMapColor(Tile.FOOD)) ||
					   LevelSaves.getARGB(x - 1, y) == (Tile.getMapColor(Tile.RESEARCH)) ||
					   LevelSaves.getARGB(x - 1, y) == (Tile.getMapColor(Tile.TDESK)) ||
					   LevelSaves.getARGB(x - 1, y) == (Tile.getMapColor(Tile.RDESK)) ||
					   LevelSaves.getARGB(x - 1, y) == (Tile.getMapColor(Tile.BDESK)) ||
					   LevelSaves.getARGB(x - 1, y) == (Tile.getMapColor(Tile.LDESK))) {
						left = true;
					} else {
						left = false;
					}
					
					if (LevelSaves.getARGB(x, y + 1) == (Tile.getMapColor(Tile.FLOOR)) ||
					    LevelSaves.getARGB(x, y + 1) == (Tile.getMapColor(Tile.FOOD)) ||
						LevelSaves.getARGB(x, y + 1) == (Tile.getMapColor(Tile.RESEARCH)) ||
						LevelSaves.getARGB(x, y + 1) == (Tile.getMapColor(Tile.TDESK)) ||
						LevelSaves.getARGB(x, y + 1) == (Tile.getMapColor(Tile.RDESK)) ||
						LevelSaves.getARGB(x, y + 1) == (Tile.getMapColor(Tile.BDESK)) ||
						LevelSaves.getARGB(x, y + 1) == (Tile.getMapColor(Tile.LDESK))) {
						bottom = true;
					} else {
						bottom = false;
					}
					
					if(y <= 0) {
						top = false;
					} else if (LevelSaves.getARGB(x, y - 1) == (Tile.getMapColor(Tile.FLOOR)) ||
					    LevelSaves.getARGB(x, y - 1) == (Tile.getMapColor(Tile.FOOD)) ||
						LevelSaves.getARGB(x, y - 1) == (Tile.getMapColor(Tile.RESEARCH)) ||
						LevelSaves.getARGB(x, y - 1) == (Tile.getMapColor(Tile.TDESK)) ||
						LevelSaves.getARGB(x, y - 1) == (Tile.getMapColor(Tile.RDESK)) ||
						LevelSaves.getARGB(x, y - 1) == (Tile.getMapColor(Tile.BDESK)) ||
						LevelSaves.getARGB(x, y - 1) == (Tile.getMapColor(Tile.LDESK))) {
						top = true;
					} else {
						top = false;
					}
					
					if(top == true &&
						bottom == true &&
						left == true &&
						right == true) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.CLOSED));
					} else
					if(top == true &&
						bottom == false &&
						left == true &&
						right == false) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.LTCORNER));
					} else
					if(top == true &&
						bottom == false &&
						left == false &&
						right == false) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.TWALL));
					} else
					if(top == true &&
						bottom == false &&
						left == false &&
						right == true) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.RTCORNER));
					} else
					if(top == false &&
						bottom == false &&
						left == true &&
						right == false) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.LWALL));
					} else
					if(top == false &&
						bottom == false &&
						left == false &&
						right == true) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.RWALL));
					} else
					if(top == false &&
						bottom == true &&
						left == true &&
						right == false) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.LBCORNER));
					} else
					if(top == false &&
						bottom == true &&
						left == false &&
						right == false) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.BWALL));
					} else
					if(top == false &&
						bottom == true &&
						left == false &&
						right == true) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.RBCORNER));
					} else
					if(top == true &&
						bottom == true &&
						left == false &&
						right == false) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.TBWALL));
					} else
					if(top == false &&
						bottom == false &&
						left == true &&
						right == true) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.RLWALL));
					} else
					if(top == false &&
						bottom == true &&
						left == true &&
						right == true) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.TOPEN));
					} else
					if(top == true &&
						bottom == false &&
						left == true &&
						right == true) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.BOPEN));
					} else
					if(top == true &&
						bottom == true &&
						left == false &&
						right == true) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.LOPEN));
					} else
					if(top == true &&
						bottom == true &&
						left == true &&
						right == false) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.ROPEN));
					} else
						if(top == false &&
						bottom == false &&
						left == false &&
						right == false) {
						
						LevelSaves.setMap(x, y, Tile.getMapColor(Tile.SPACE));
					}
				} 
			}
			x -= 2;
		}
	}
}
