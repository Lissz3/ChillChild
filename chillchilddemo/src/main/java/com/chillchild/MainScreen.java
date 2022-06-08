package com.chillchild;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainScreen extends JFrame {
	JLabel lblStartingScreen;
	AudioPlayer audio;

	Intro intro;
	Game game;
	

	final int GAME_WIDTH = 1430;
	final int GAME_HEIGHT = 800;

	public MainScreen() {
		this.setSize(GAME_WIDTH, GAME_HEIGHT);

		String audioURL = "" + MainScreen.class.getResource("/Resources/Song/Ode.wav");
		String myAudio = java.net.URLDecoder.decode(audioURL, StandardCharsets.UTF_8);

		try {
			audio = new AudioPlayer(myAudio);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}

		audio.play();

		game = new Game(this);
		
		intro = new Intro(this);
		
		lblStartingScreen = new JLabel(intro.starting);
		lblStartingScreen.setLocation(0, 0);
		add(lblStartingScreen);
	}
}
