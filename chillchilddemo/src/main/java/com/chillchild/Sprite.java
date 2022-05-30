package com.chillchild;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Sprite {

	private static BufferedImage spriteSheet;
	private static final int X_TILE = 64;
	private static final int Y_TILE = 64;

	public static BufferedImage loadSprite(String file) {

		BufferedImage sprite = null;

		URL spriteURL = Game.class.getResource("/Resources/Sprites/" + file);

		try {
			sprite = ImageIO.read(spriteURL);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sprite;
	}

	public static BufferedImage getSprite(String file, int xGrid, int yGrid) {

		if (spriteSheet == null) {
			spriteSheet = loadSprite(file);
		}

		return spriteSheet.getSubimage(xGrid * X_TILE, yGrid * Y_TILE, X_TILE, Y_TILE);
	}

}
