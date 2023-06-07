package com.sound;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class JavaSoundRecorder {
	
	static final long RECORD_TIME = 60000;
	
	Scanner scan = new Scanner(System.in);
	
	String nameOfFile = scan.nextLine();
	
	File wavFile = new File("D:/Documents/"+ nameOfFile +".wav");
	
	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	
	TargetDataLine line;
	AudioInputStream ais;
	
	AudioFormat getAudioFormat() {
		float sampleRate = 16000;
		int sampleSizeInBits = 8;
		int channels = 2;
		boolean signed = true;
		boolean bigEndian = true;
		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		return format;
	}
	
	void start() {
		try {
			AudioFormat format = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
			
			if (!AudioSystem.isLineSupported(info)) {
				System.out.println("Line not supported...");
				System.exit(0);
			}
			System.out.println("Name your record: ");
			System.out.println("Your file name is " + nameOfFile + ".wav" + " stored at " + wavFile);
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
			line.start();
			
			System.out.println("Capturing Sound...");
			
		    ais = new AudioInputStream(line);
			
			System.out.println("Started Redcording...");
			System.out.println("Type 'stop' to quit the recording.");
			
			AudioSystem.write(ais, fileType, wavFile);
//			String stopped = scan.nextLine();
//			
//			System.out.println("Finished Recording...");
//			System.exit(0);
			
		} catch(LineUnavailableException ex) {
			ex.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Name your record: ");
		
		final JavaSoundRecorder recorder = new JavaSoundRecorder();
		
//		Thread stopper = new Thread(new Runnable() {
//			public void run() {
//				try {
//					Thread.sleep(RECORD_TIME);
//				} catch(InterruptedException ex) {
//					ex.printStackTrace();
//				}
//			}
//		});
//		
//		stopper.start();
		
		recorder.start();

	}

}
