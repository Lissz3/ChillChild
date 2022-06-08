
package com.chillchild;

import javax.swing.JFrame;

public class Application {
	public static void main(String[] args) {
		
			MainScreen ms = new MainScreen();
			
			ms.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ms.setResizable(false);
			ms.setTitle("Chill child");
			ms.setLocationRelativeTo(null);
			ms.setVisible(true);

	}
}
