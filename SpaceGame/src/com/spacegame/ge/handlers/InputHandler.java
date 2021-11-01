package com.spacegame.ge.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.spacegame.Log;
import com.spacegame.ge.Game;

public class InputHandler implements KeyListener, MouseListener, MouseWheelListener {
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key draw = new Key();
	public Key escape = new Key();
	public Key shift = new Key();
	public Key rotate = new Key();
	
	public static int x;
	public static int y;
	
	public static boolean inMenu = false;
	public static boolean importMenu = false;
	public static boolean researchMenu = false;
	
	public static float mouseRatioX;
	public static float mouseRatioY;
	public static boolean tileOverlay;
	
	public InputHandler(Game game) {
		game.addKeyListener(this);
		game.addMouseListener(this);
		game.addMouseWheelListener(this);
		Log.out("Listening");
	}
	
	public InputHandler() {
	}

	public class Key {
		private int numTimesPressed = 0;
		private boolean pressed = false;
		
		public int getNumTimesPressed() {
			return numTimesPressed;
		}
		
		public boolean isPressed() {
			return pressed;
		}
		public void toggle(boolean isPressed) {
			pressed = isPressed;
			if(isPressed) {
				numTimesPressed++;
			}
		}
	}
	
	public void keyPressed(KeyEvent event) {
		toggleKey(event.getKeyCode(), true);
	}
	
	public void keyReleased(KeyEvent event) {
		toggleKey(event.getKeyCode(), false);
	}
	
	public void keyTyped(KeyEvent event) {
		//null
	}
	
	public void mouseClicked(MouseEvent event) {
		x = event.getX();
		y = event.getY();
		Log.element("System", "Mouse", "Location is " + x + ", " +y);
		
		mouseRatioX = (float) x / (float) Game.screenWidth;
		mouseRatioY = (float) y / (float) Game.screenHeight;
		
		Log.element("System", "Mouse", "Ratio is " + mouseRatioX + ", " + mouseRatioY);
		toggleKey(event.getButton(), event.isShiftDown());
	}
	
	public void mouseWheelMoved(MouseWheelEvent event) {
		
	}
	
	public void mouseEntered(MouseEvent event) {
	}

	public void mouseExited(MouseEvent event) {
	}

	public void mousePressed(MouseEvent event) {
	}

	public void mouseReleased(MouseEvent event) {
	}
	
	public void toggleKey(int keyCode, boolean isPressed) {
		if(Game.newSave == true) {
			if(keyCode == KeyEvent.VK_ESCAPE) {
				Game.newSave = false;
				Log.element("ge.Game", "newSave", "set to false");
				Game.homeMenu = true;
				Log.element("ge.Game", "homeMenu", "set to true");
			} else if(keyCode == KeyEvent.VK_BACK_SPACE) {
				EventHandler.saveName = EventHandler.saveName.substring(0, EventHandler.saveName.length() - 1);
			} else if ((keyCode == KeyEvent.VK_A ||
					   keyCode == KeyEvent.VK_B ||
					   keyCode == KeyEvent.VK_C ||
					   keyCode == KeyEvent.VK_D ||
					   keyCode == KeyEvent.VK_E ||
					   keyCode == KeyEvent.VK_F ||
					   keyCode == KeyEvent.VK_G ||
					   keyCode == KeyEvent.VK_H ||
					   keyCode == KeyEvent.VK_I ||
					   keyCode == KeyEvent.VK_J ||
					   keyCode == KeyEvent.VK_K ||
					   keyCode == KeyEvent.VK_L ||
					   keyCode == KeyEvent.VK_M ||
					   keyCode == KeyEvent.VK_N ||
					   keyCode == KeyEvent.VK_O ||
					   keyCode == KeyEvent.VK_P ||
					   keyCode == KeyEvent.VK_Q ||
					   keyCode == KeyEvent.VK_R ||
					   keyCode == KeyEvent.VK_S ||
					   keyCode == KeyEvent.VK_T ||
					   keyCode == KeyEvent.VK_U ||
					   keyCode == KeyEvent.VK_V ||
					   keyCode == KeyEvent.VK_W ||
					   keyCode == KeyEvent.VK_X ||
					   keyCode == KeyEvent.VK_Y ||
					   keyCode == KeyEvent.VK_Z ||
					   keyCode == KeyEvent.VK_0 ||
					   keyCode == KeyEvent.VK_1 ||
					   keyCode == KeyEvent.VK_2 ||
					   keyCode == KeyEvent.VK_3 ||
					   keyCode == KeyEvent.VK_4 ||
					   keyCode == KeyEvent.VK_5 ||
					   keyCode == KeyEvent.VK_6 ||
					   keyCode == KeyEvent.VK_7 ||
					   keyCode == KeyEvent.VK_8 ||
					   keyCode == KeyEvent.VK_9 ||
					   keyCode == KeyEvent.VK_MINUS) && isPressed == true){
				if(keyCode == KeyEvent.VK_MINUS && shift.isPressed() == true){
					EventHandler.saveName = EventHandler.saveName + "_";
				} else if(keyCode == KeyEvent.VK_MINUS && shift.isPressed() == false) {
					EventHandler.saveName = EventHandler.saveName + "-";
				} else {
					EventHandler.saveName = EventHandler.saveName + KeyEvent.getKeyText(keyCode);					
				}
			}
		} else {
			if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
				up.toggle(isPressed);
			}
			if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
				down.toggle(isPressed);
			}
			if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
				left.toggle(isPressed);
			}
			if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
				right.toggle(isPressed);
			}
			if(keyCode == KeyEvent.VK_R) {
				rotate.toggle(isPressed);
			}
			if(keyCode == KeyEvent.VK_ESCAPE) {
				escape.toggle(isPressed);
				if(Game.tile == true) {
					Game.tile = false;
					Log.element("ge.Game", "tile", "set to false");
					CursorHandler.setCursor(CursorHandler.getID(CursorHandler.DEFAULT));
				} else if(Game.homeMenu == true) {
				} else if(Game.loadSave == true) {
					Game.loadSave = false;
					Log.element("ge.Game", "loadSave", "set to false");
					Game.homeMenu = true;
					Log.element("ge.Game", "homeMenu", "set to true");
				} else if(importMenu == true) {
					importMenu = false;
					Log.element("ge.handlers.InputHandler", "importMenu", "set to false");
				} else if(researchMenu == true) {
					researchMenu = false;
					Log.element("ge.handlers.InputHandler", "researchMenu", "set to false");
				} else if(inMenu == true && isPressed == true) {
					inMenu = false;
					Log.element("ge.handlers.InputHandler", "inMenu", "set to false");
					Game.pause = false;
					Log.element("ge.Game", "pause", "set to false");
				} else if(inMenu == false && isPressed == true) {
					inMenu = true;
					Log.element("ge.handlers.InputHandler", "inMenu", "set to true");
					Game.pause = true;
					Log.element("ge.Game", "pause", "set to true");
				}
			}
		}
		
		if(keyCode == MouseEvent.BUTTON1) {
			EventHandler.clickEvent(isPressed);
		}
		if(keyCode == MouseEvent.BUTTON3) {
			if(Game.tile == true) {
				Game.tile = false;
				CursorHandler.setCursor(CursorHandler.getID(CursorHandler.DEFAULT));
			}
		}
	}
}
