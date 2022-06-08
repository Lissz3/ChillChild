package com.chillchild;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Intro {

	ImageIcon starting;

	JLabel lblPlay;
	JLabel lblCredits;
	JLabel lblQuit;
	JLabel lblMusic;
	JLabel lblDisclaim;
	JLabel lblMenu;

	MouseHandler myMouse;

	MainScreen ms;

	public Intro(MainScreen MainScreen) {
		ms = MainScreen;

		ms.audio.play();

		starting = new ImageIcon(Intro.class.getResource("/Resources/StartingScreen/starting.png"));

		myMouse = new MouseHandler();

		lblPlay = new JLabel(new ImageIcon(Intro.class.getResource("/Resources/Buttons/play.png")));
		lblPlay.setSize(lblPlay.getIcon().getIconWidth(), lblPlay.getIcon().getIconHeight());
		lblPlay.addMouseListener(myMouse);

		lblCredits = new JLabel(new ImageIcon(Intro.class.getResource("/Resources/Buttons/help.png")));
		lblCredits.setSize(lblCredits.getIcon().getIconWidth(), lblCredits.getIcon().getIconHeight());
		lblCredits.addMouseListener(myMouse);

		lblQuit = new JLabel(new ImageIcon(Intro.class.getResource("/Resources/Buttons/about.png")));
		lblQuit.setSize(lblQuit.getIcon().getIconWidth(), lblQuit.getIcon().getIconHeight());
		lblQuit.addMouseListener(myMouse);

		lblCredits.setLocation(ms.getWidth() / 2 - lblCredits.getWidth() / 2, 600);
		ms.add(lblCredits);
		lblPlay.setLocation(lblCredits.getX() - lblPlay.getWidth() - 100, 600);
		ms.add(lblPlay);
		lblQuit.setLocation(lblCredits.getX() + lblCredits.getWidth() + 100, 600);
		ms.add(lblQuit);

		lblMusic = new JLabel(new ImageIcon(Intro.class.getResource("/Resources/Buttons/music.png")));
		lblMusic.setSize(lblMusic.getIcon().getIconWidth(), lblMusic.getIcon().getIconHeight());
		lblMusic.setLocation(ms.getWidth() - (lblMusic.getWidth() + 40), 20);
		lblMusic.addMouseListener(myMouse);
		ms.add(lblMusic);
	}

	private class MouseHandler extends MouseAdapter {
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == lblPlay) {
				lblPlay.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/playmo.png")));
			} else if (e.getSource() == lblCredits) {
				lblCredits
						.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/helpmo.png")));
			} else if (e.getSource() == lblQuit) {
				lblQuit.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/aboutmo.png")));
			} else if (e.getSource() == lblMusic) {
				lblMusic.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/musicmo.png")));
			} else if (e.getSource() == lblMenu) {
				//lblMenu.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/.png")));
			}
		}

		public void mouseExited(MouseEvent e) {
			if (e.getSource() == lblPlay) {
				lblPlay.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/play.png")));
			} else if (e.getSource() == lblCredits) {
				lblCredits.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/help.png")));
			} else if (e.getSource() == lblQuit) {
				lblQuit.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/about.png")));
			} else if (e.getSource() == lblMusic) {
				lblMusic.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/music.png")));
			} else if (e.getSource() == lblMenu) {
				//lblMenu.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Menu/menu.png")));
			}
		}

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == lblPlay) {
				lblPlay.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/playc.png")));

			} else if (e.getSource() == lblCredits) {
				lblCredits.setIcon(
						new ImageIcon(Game.class.getResource("/Resources/Buttons/helpc.png")));
			} else if (e.getSource() == lblQuit) {
				lblQuit.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/aboutc.png")));
			} else if (e.getSource() == lblMusic) {
				lblMusic.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/musicc.png")));
			} else if (e.getSource() == lblMenu) {
				lblMusic.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/musicc.png")));
			}
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == lblPlay) {
				ms.getContentPane().removeAll();
				ms.validate();
				ms.add(ms.game);
				ms.addKeyListener(ms.game.player.kh);
				ms.setFocusable(true);
				ms.validate();
			} else if (e.getSource() == lblMusic) {
				if (ms.audio.status.equals("play")) {
					ms.audio.pause();
					lblMusic.setEnabled(false);
					lblMusic.setDisabledIcon(
							new ImageIcon(Game.class.getResource("/Resources/Buttons/musicc.png")));
				} else {
					ms.audio.play();
					lblMusic.setEnabled(true);
				}
			} else if (e.getSource() == lblCredits) {

				lblDisclaim = new JLabel(new ImageIcon(Game.class.getResource("/Resources/Disclaimer.png")));
				lblDisclaim.setSize(lblDisclaim.getIcon().getIconWidth(), lblDisclaim.getIcon().getIconHeight());
				lblDisclaim.setLocation(0, 0);

				//lblMenu = new JLabel(new ImageIcon(Game.class.getResource("/Resources/Buttons/Menu/menu.png")));
				lblMenu.setSize(lblMenu.getIcon().getIconWidth(), lblCredits.getIcon().getIconHeight());
				lblMenu.setLocation(ms.getWidth() / 2 - lblMenu.getWidth() / 2, 325);
				lblMenu.addMouseListener(myMouse);

				ms.add(lblMenu);
				ms.add(lblDisclaim);

			} else if (e.getSource() == lblMenu) {

			}
		}
	}
}
