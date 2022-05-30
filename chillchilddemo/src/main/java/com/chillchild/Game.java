package com.chillchild;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame {
	AudioPlayer audio;

	JLabel lblPlay;
	JLabel lblCredits;
	JLabel lblQuit;
	JLabel lblMusic;
	JLabel lblDisclaim;
	JLabel lblMenu;

	MouseHandler myMouse;

	Player player;

	public Game() {
		super("Chill, child");
		setLayout(null);
		setSize(960, 540);

		ImageIcon starting = new ImageIcon(Game.class.getResource("/Resources/StartingScreen/starting.gif"));
		this.setContentPane(new JLabel(starting));

		String audioURL = "" + Game.class.getResource("/Resources/Song/Ode.wav");
		String myAudio = java.net.URLDecoder.decode(audioURL, StandardCharsets.UTF_8);

		try {
			audio = new AudioPlayer(myAudio);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}

		audio.play();

		myMouse = new MouseHandler();

		intro();
	}

	private void intro() {
		lblPlay = new JLabel(new ImageIcon(Game.class.getResource("/Resources/Buttons/Start/start.png")));
		lblPlay.setSize(lblPlay.getIcon().getIconWidth(), lblPlay.getIcon().getIconHeight());
		lblPlay.addMouseListener(myMouse);

		lblCredits = new JLabel(new ImageIcon(Game.class.getResource("/Resources/Buttons/Credits/credits.png")));
		lblCredits.setSize(lblCredits.getIcon().getIconWidth(), lblCredits.getIcon().getIconHeight());
		lblCredits.addMouseListener(myMouse);

		lblQuit = new JLabel(new ImageIcon(Game.class.getResource("/Resources/Buttons/Exit/exit.png")));
		lblQuit.setSize(lblQuit.getIcon().getIconWidth(), lblQuit.getIcon().getIconHeight());
		lblQuit.addMouseListener(myMouse);

		lblCredits.setLocation(Game.this.getWidth() / 2 - lblCredits.getWidth() / 2, 300);
		add(lblCredits);
		lblPlay.setLocation(lblCredits.getX() - lblPlay.getWidth() - 20, 300);
		add(lblPlay);
		lblQuit.setLocation(lblCredits.getX() + lblCredits.getWidth() + 20, 300);
		add(lblQuit);

		lblMusic = new JLabel(new ImageIcon(Game.class.getResource("/Resources/Buttons/Music/music.png")));
		lblMusic.setSize(lblMusic.getIcon().getIconWidth(), lblMusic.getIcon().getIconHeight());
		lblMusic.setLocation(this.getWidth() - (lblMusic.getWidth() + 20), 5);
		lblMusic.setName("music");
		lblMusic.addMouseListener(myMouse);
		add(lblMusic);
	}

	private class MouseHandler extends MouseAdapter {
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == lblPlay) {
				lblPlay.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Start/starthover.png")));
			} else if (e.getSource() == lblCredits) {
				lblCredits
						.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Credits/creditshover.png")));
			} else if (e.getSource() == lblQuit) {
				lblQuit.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Exit/exithover.png")));
			} else if (e.getSource() == lblMusic) {
				lblMusic.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Music/musichover.png")));
			} else if (e.getSource() == lblMenu) {
				lblMenu.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Menu/menuhover.png")));
			}
		}

		public void mouseExited(MouseEvent e) {
			if (e.getSource() == lblPlay) {
				lblPlay.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Start/start.png")));
			} else if (e.getSource() == lblCredits) {
				lblCredits.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Credits/credits.png")));
			} else if (e.getSource() == lblQuit) {
				lblQuit.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Exit/exit.png")));
			} else if (e.getSource() == lblMusic) {
				lblMusic.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Music/music.png")));
			} else if (e.getSource() == lblMenu) {
				lblMenu.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Menu/menu.png")));
			}
		}

		public void mousePressed(MouseEvent e) {
			if (e.getSource() == lblPlay) {
				lblPlay.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Start/startclicked.png")));

			} else if (e.getSource() == lblCredits) {
				lblCredits.setIcon(
						new ImageIcon(Game.class.getResource("/Resources/Buttons/Credits/creditsclicked.png")));
			} else if (e.getSource() == lblQuit) {
				lblQuit.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Exit/exitclicked.png")));
			} else if (e.getSource() == lblMusic) {
				lblMusic.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Music/musicclicked.png")));
			} else if (e.getSource() == lblMenu) {
				lblMusic.setIcon(new ImageIcon(Game.class.getResource("/Resources/Buttons/Music/musicclicked.png")));
			}
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == lblPlay) {
				player = new Player(Game.this);
				for (Component c : Game.this.getContentPane().getComponents()) {
						remove(c);
				}
				Game.this.setContentPane(player);
				

			} else if (e.getSource() == lblMusic) {
				if (audio.status.equals("play")) {
					audio.pause();
					lblMusic.setEnabled(false);
					lblMusic.setDisabledIcon(
							new ImageIcon(Game.class.getResource("/Resources/Buttons/Music/musicclicked.png")));
				} else {
					audio.play();
					lblMusic.setEnabled(true);
				}
			} else if (e.getSource() == lblCredits) {

				for (Component c : Game.this.getContentPane().getComponents()) {
					remove(c);
				}

				lblDisclaim = new JLabel(new ImageIcon(Game.class.getResource("/Resources/Disclaimer.png")));
				lblDisclaim.setSize(lblDisclaim.getIcon().getIconWidth(), lblDisclaim.getIcon().getIconHeight());
				lblDisclaim.setLocation(0, 0);

				lblMenu = new JLabel(new ImageIcon(Game.class.getResource("/Resources/Buttons/Menu/menu.png")));
				lblMenu.setSize(lblMenu.getIcon().getIconWidth(), lblCredits.getIcon().getIconHeight());
				lblMenu.setLocation(Game.this.getWidth() / 2 - lblMenu.getWidth() / 2, 325);
				lblMenu.addMouseListener(myMouse);

				add(lblMenu);
				add(lblDisclaim);

			} else if (e.getSource() == lblMenu) {
				for (Component c : Game.this.getContentPane().getComponents()) {
					remove(c);
				}

				intro();
			}
		}
	}
}
