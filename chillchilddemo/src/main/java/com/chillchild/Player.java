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
	Image map = new ImageIcon(Game.class.getResource("/Resources/Levels/preview.png")).getImage();

	KbHandler kh;

	int limitX;
	int limitY;

	public Player(Game g) {
		limitX = 0;
		limitY = 0;
		x = 170;
		y = 150;
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
				xv = -2;
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				animation = walkRight;
				animation.start();
				xv = 2;
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				animation = shooting;
				animation.start();
				xv = 0;
				yv = 0;
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				animation = walkForward;
				animation.start();
				yv = -2;
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				animation = walkBack;
				animation.start();
				yv = 2;
			}
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == 65 || e.getKeyCode() == 68) {
				xv = 0;
			} else if (e.getKeyCode() == 87 || e.getKeyCode() == 83) {
				yv = 0;
			}
			animation.stop();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		animation.update();
		repaint();
		move();
		if (y > 470 && limitY != 2) {
			if (limitY == 0) {
				map = new ImageIcon(Game.class.getResource("/Resources/Levels/preview2.png")).getImage();
				y = 0;
				limitY++;
			} else {
				map = new ImageIcon(Game.class.getResource("/Resources/Levels/preview3.png")).getImage();
				y = 0;
				limitY++;
			}
		} else if (y < 0 && limitY != 0) {
			if (limitY == 1) {
				map = new ImageIcon(Game.class.getResource("/Resources/Levels/preview.png")).getImage();
				y = 470;
				limitY--;
			} else {
				map = new ImageIcon(Game.class.getResource("/Resources/Levels/preview2.png")).getImage();
				y = 470;
				limitY--;
			}
		}
	}

	private void move() {
		x = x + xv;
		y = y + yv;
	}

}
