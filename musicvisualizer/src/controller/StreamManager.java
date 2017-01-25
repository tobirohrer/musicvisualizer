package controller;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import Model.PlayAudio;

public class StreamManager {
	public void playClip(String audioFile) {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(this.getClass().getResourceAsStream(audioFile));
			
			AudioFormat originalFormat = audioInputStream.getFormat();
			
			//Todo: Umwaldung von input in decoded Stream, falls inputformat nicht "das Richtige" ist.
			
			AudioInputStream decodedStream = audioInputStream;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioInputStream.getFormat());
			
			SourceDataLine sourceDataLine = (SourceDataLine)AudioSystem.getLine(info);
			
			sourceDataLine.open(originalFormat);
			sourceDataLine.start();
				
			Thread playAudio = new Thread(new PlayAudio(sourceDataLine, decodedStream));
			playAudio.start();
			
			System.out.println(sourceDataLine.isActive());		
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
