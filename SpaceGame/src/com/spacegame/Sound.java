package com.spacegame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	
	public static boolean doSound = true;
	public static boolean doMusic = true;
	
	private static Clip clip;
	private static Clip music;
	
	public static long musicPosition = 0;
	
	public static void play(String fileName) {
		if(doSound == true) {
			try {
				clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File("resources/sounds/" + fileName)));
				clip.start();
				Log.element("Sound", fileName, "Sound played");
			} catch(UnsupportedAudioFileException e) {
				Log.error(e);
			} catch(LineUnavailableException e) {
				Log.error(e);
			} catch(IOException e) {
				Log.error(e);
			} catch(Exception e) {
				Log.error(e);
			}
		}
	}
	
	public static void stop() {
		clip.stop();
		clip.close();
		Log.out("Sound Stopped");
	}
	
	public static void music() {
		if(music == null) {
			try {
				music = AudioSystem.getClip();
				music.open(AudioSystem.getAudioInputStream(new File("resources/sounds/import.wav")));
			} catch(UnsupportedAudioFileException e) {
				Log.error(e);
			} catch(LineUnavailableException e) {
				Log.error(e);
			} catch(IOException e) {
				Log.error(e);
			} catch(Exception e) {
				Log.error(e);
			}
		}
		
		if(doMusic == true) {
			try {
				music = AudioSystem.getClip();
				music.open(AudioSystem.getAudioInputStream(new File("resources/sounds/music.wav")));
				music.start();
				music.setMicrosecondPosition(musicPosition);
				music.loop(Clip.LOOP_CONTINUOUSLY);
			} catch(UnsupportedAudioFileException e) {
				Log.error(e);
			} catch(LineUnavailableException e) {
				Log.error(e);
			} catch(IOException e) {
				Log.error(e);
			} catch(Exception e) {
				Log.error(e);
			}
		}
		if(doMusic == false) {
			musicPosition = music.getMicrosecondPosition();
			music.stop();
			music.close();
		}
	}
}
