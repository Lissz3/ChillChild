package com.chillchild;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Game extends JLayeredPane implements ActionListener {
	AudioPlayer audio;

	JLabel lblPlay;
	JLabel lblCredits;
	JLabel lblQuit;
	JLabel lblMusic;
	JLabel lblDisclaim;
	JLabel lblMenu;

	Player player;

	Map map;
	Map mapOver;

	Timer check;

	MainScreen ms;

	final int GAME_WIDTH = 1430;
	final int GAME_HEIGHT = 800;

	public Game(MainScreen mainScreen) {
		ms = mainScreen;
		this.setSize(GAME_WIDTH, GAME_HEIGHT);

		map = new Map(this, "/map.png");
		mapOver = new Map(this, "/arboles.png");
		
		player = new Player(this);

		add(player, JLayeredPane.PALETTE_LAYER);
		add(map, JLayeredPane.DEFAULT_LAYER);
		add(mapOver, JLayeredPane.MODAL_LAYER);

		check = new Timer(20, this);
		check.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		map.update(Game.this, player.playerX, player.playerY);
		if (map.mapChanged) {
			player.setPosition(map.newPos);
		}
	}
}
