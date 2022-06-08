package com.chillchild;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

public class Player extends JLabel {
	private Animation walkLeft = new Animation("Char.png", 9, 0, 9, 7);
	private Animation walkRight = new Animation("Char.png", 9, 0, 11, 7);

	private Animation walkForward = new Animation("Char.png", 9, 0, 8, 5);
	private Animation walkBack = new Animation("Char.png", 9, 0, 10, 5);

	private Animation stdg = new Animation("Char.png", 2, 0, 2, 10);
	private Animation shooting = new Animation("Char.png", 13, 0, 19, 5);

	public Animation animation = stdg;

	Rectangle collision;

	int xv = 0;
	int yv = 0;

	int playerX;
	int playerY;

	KbHandler kh;

	String moving;

	boolean running;

	public Player(Game g) {
		setSize(g.GAME_WIDTH, g.GAME_HEIGHT);

		playerX = 1000;
		playerY = 180;

		kh = new KbHandler();
		g.addKeyListener(kh);
		g.setFocusable(true);

		animation.start();
		running = false;
	}

	private class KbHandler extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			if (e.isShiftDown() && (xv != 0 || yv != 0) && !running) {
				xv *= 2;
				yv *= 2;
				running = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_A) {
				animation = walkLeft;
				animation.start();
				xv = -2;
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
			if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
				xv = 0;
			} else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
				yv = 0;
			} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				yv = yv / 2;
				xv = xv / 2;
				running = false;
			}

			if (e.getKeyCode() != KeyEvent.VK_SHIFT) {
				animation.restart();
				animation.stop();
			}
		}
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(animation.getSprite(), playerX, playerY, null);
	}


	public void update() {
		animation.update();
		repaint();
		move();
	}

	private void move() {
		playerX += xv;
		playerY += yv;
	}

	public void setPosition (int[] positions){
		playerX = positions[0];
		playerY = positions[1];
	}

}
