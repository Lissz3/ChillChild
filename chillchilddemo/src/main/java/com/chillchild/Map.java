package com.chillchild;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Map extends JLabel {
	private BufferedImage[][] maps = new BufferedImage[2][2];

	BufferedImage map = null;

	URL mapURL;

	int mapX;
	int mapY;

	Player player;

	boolean mapChanged;

	int[] newPos;

	public Map(Game g, String file) {
		setSize(g.GAME_WIDTH, g.GAME_HEIGHT);

		mapURL = Game.class.getResource("/Resources/Levels/" + file);
		
		try {
			map = ImageIO.read(mapURL);
		} catch (IOException e) {
			e.printStackTrace();
		}

		maps[0][0] = map.getSubimage(0, 0, g.GAME_WIDTH, g.GAME_HEIGHT);
		maps[1][0] = map.getSubimage(g.GAME_WIDTH, 0, g.GAME_WIDTH, g.GAME_HEIGHT);
		maps[0][1] = map.getSubimage(0, g.GAME_HEIGHT, g.GAME_WIDTH, g.GAME_HEIGHT);
		maps[1][1] = map.getSubimage(g.GAME_WIDTH, g.GAME_HEIGHT, g.GAME_WIDTH, g.GAME_HEIGHT);
		
		mapX = 0;
		mapY = 0;

		map = maps[mapX][mapY];

		mapChanged = false;
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(map, 0, 0, null);
	}

	public void update(Game g, int playerX, int playerY){
		changeMap(g, playerX, playerY);
		repaint();
	}

	public void changeMap(Game g, int playerX, int playerY) {
		if (playerX < -10 && mapX == 1 && mapY == 0){
			mapX--;
			map = maps[mapX][mapY];
			newPos = new int[]{1380, playerY};
			mapChanged = true;
		} else if (playerX > 1380 && mapX == 0 && mapY == 0){
			mapX++;
			map = maps[mapX][mapY];
			newPos = new int[]{0, playerY};
			mapChanged = true;
		} else if (playerX > 1380 && mapX == 0 && mapY == 1) {
			mapX++;
			map = maps[mapX][mapY];
			newPos = new int[]{0, playerY};
			mapChanged = true;
		} else if (playerX < -10 && mapX == 1 && mapY == 1) {
			mapX--;
			map = maps[mapX][mapY];
			newPos = new int[]{1380, playerY};
			mapChanged = true;
		} else if (playerY > 740 && mapY == 0 && mapX == 0) {
			mapY++;
			map = maps[mapX][mapY];
			newPos = new int[]{playerX, 0};
			mapChanged = true;
		} else if (playerY < -10 && mapY == 1 && mapX == 0) {
			mapY--;
			map = maps[mapX][mapY];
			newPos = new int[]{playerX, 740};
			mapChanged = true;
		} else if (playerY > 740 && mapY == 0 && mapX == 1) {
			mapY++;
			map = maps[mapX][mapY];
			newPos = new int[]{playerX, 0};
			mapChanged = true;
		} else if (playerY < -10 && mapY == 1 && mapX == 1) {
			mapY--;
			map = maps[mapX][mapY];
			newPos = new int[]{playerX, 740};
			mapChanged = true;
		} else {
			mapChanged = false;
		}
	}

	public boolean edge(int playerX, int playerY, String direction) {
		if (mapX == 0 && mapY == 0 && playerX < 160 && direction.equals("A")) {
			return true;
		}
		return false;
	}



}
