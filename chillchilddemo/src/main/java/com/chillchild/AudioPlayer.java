package com.chillchild;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
	Long currentFrame;
	Clip clip;
	String status;
	AudioInputStream audioInputStream;
	String filePath;

	public AudioPlayer(String path) throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		filePath = "G:\\Mi unidad\\ChillChild\\chillchilddemo\\src\\main\\java\\Resources\\Song\\Ode.wav";
		path=path.substring(6);
		audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());

		clip = AudioSystem.getClip();

		clip.open(audioInputStream);

		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void play() {
		clip.start();
		status = "play";
	}

	public void pause() {

		if (status.equals("paused")) {
			return;
		}

		this.currentFrame = this.clip.getMicrosecondPosition();
		clip.stop();
		status = "paused";
	}

	public void resumeAudio() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {

		if (status.equals("play")) {
			return;
		}

		clip.close();
		resetAudioStream();
		clip.setMicrosecondPosition(currentFrame);
		this.play();
	}

	public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
			LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(
				new File(filePath).getAbsoluteFile());
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}

}
