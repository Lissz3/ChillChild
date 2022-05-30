
package com.chillchild;

import javax.swing.JFrame;

public class Application {
	public static void main(String[] args) {
		Game g = new Game();
		g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.setResizable(false);
		g.setLocationRelativeTo(null);
		g.setVisible(true);

	}
}
