package com.chillchild;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Player extends JPanel implements ActionListener {
	private Animation walkLeft = new Animation("Char.png", 9, 0, 9, 7);
	private Animation walkRight = new Animation("Char.png", 9, 0, 11, 7);

	private Animation walkForward = new Animation("Char.png", 9, 0, 8, 5);
	private Animation walkBack = new Animation("Char.png", 9, 0, 10, 5);

	private Animation stdg = new Animation("Char.png", 2, 0, 2, 10);
	private Animation shooting = new Animation("Char.png", 13, 0, 19, 5);

	private Animation animation = stdg;

	Timer timer;
	Rectangle collision;
	int xv = 0;
	int yv = 0;
	int x;
	int y;
	Image map = new ImageIcon(Game.class.getResource("/Resources/Levels/map1.png")).getImage();

	KbHandler kh;

	int mapX;
	int mapY;

	String moving;

	public Player(Game g) {
		mapX = 0;
		mapY = 0;
		x = 180;
		y = 180;
		this.setSize(g.getWidth(), g.getHeight());
		this.setBackground(Color.DARK_GRAY);
		timer = new Timer(25, this);
		timer.start();

		kh = new KbHandler();
		g.addKeyListener(kh);
		g.setFocusable(true);

		animation.start();

	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(map, 0, 0, null);
		g2d.drawImage(animation.getSprite(), x, y, null);
	}

	private class KbHandler extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_A) {
				animation = walkLeft;
				animation.start();
				moving = "A";
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				animation = walkRight;
				animation.start();
				xv = 2;
				moving = "D";
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				animation = shooting;
				animation.start();
				xv = 0;
				yv = 0;
				moving = "STD";
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				animation = walkForward;
				animation.start();
				yv = -2;
				moving = "W";
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				animation = walkBack;
				animation.start();
				yv = 2;
				moving = "S";
			}
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == 65 || e.getKeyCode() == 68) {
				xv = 0;
			} else if (e.getKeyCode() == 87 || e.getKeyCode() == 83) {
				yv = 0;
			}
			animation.restart();
			animation.stop();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		animation.update();
		repaint();
		move();
		changeMap();

		System.err.println(notEdge());
	}

	private void move() {
		if (notEdge()){
			x = x + xv;
			y = y + yv;
		}
	}

	private void changeMap() {
		if (x > 1380 && mapX == 0 && mapY == 0) {
			map = new ImageIcon(Game.class.getResource("/Resources/Levels/map2.png")).getImage();
			x = 0;
			mapX++;
		} else if (x < -10 && mapX == 1 && mapY == 0) {
			map = new ImageIcon(Game.class.getResource("/Resources/Levels/map1.png")).getImage();
			x = 1380;
			mapX--;
		} else if (x > 1380 && mapX == 0 && mapY == 1) {
			map = new ImageIcon(Game.class.getResource("/Resources/Levels/map4.png")).getImage();
			x = 0;
			mapX++;
		} else if (x < -10 && mapX == 1 && mapY == 1) {
			map = new ImageIcon(Game.class.getResource("/Resources/Levels/map3.png")).getImage();
			x = 1380;
			mapX--;
		} else if (y > 695 && mapY == 0 && mapX == 0) {
			map = new ImageIcon(Game.class.getResource("/Resources/Levels/map3.png")).getImage();
			y = 0;
			mapY++;
		} else if (y < -10 && mapY == 1 && mapX == 0) {
			map = new ImageIcon(Game.class.getResource("/Resources/Levels/map1.png")).getImage();
			y = 685;
			mapY--;
		} else if (y > 695 && mapY == 0 && mapX == 1) {
			map = new ImageIcon(Game.class.getResource("/Resources/Levels/map4.png")).getImage();
			y = 0;
			mapY++;
		} else if (y < -10 && mapY == 1 && mapX == 1) {
			map = new ImageIcon(Game.class.getResource("/Resources/Levels/map3.png")).getImage();
			y = 685;
			mapY--;
		}
	}

	private boolean notEdge() {
		if (mapX == 0 && mapY == 0 && this.x < 160 && moving.equals("A")){
			return false;
		}
		return true;
	}

}
