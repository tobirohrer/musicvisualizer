package Model;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;

public class PlayAudio implements Runnable {

	private SourceDataLine sourceDataLine;
	private AudioInputStream decodedStream;

	public PlayAudio(SourceDataLine sourceDataLine, AudioInputStream decodedStream) {
		this.sourceDataLine = sourceDataLine;
		this.decodedStream = decodedStream;
	}

	@Override
	public void run() {
		byte[] data = new byte[4096];
		
		//wire SoundDetails and soundDEtailsFX together (Observer Observable)
		SoundDetails soundDetails = new SoundDetails(data);
		Thread soundDetailsThread = new Thread(soundDetails);	
		SoundDetailsFX soundDetailsFX = new SoundDetailsFX(soundDetails);
		soundDetails.addObserver(soundDetailsFX);
		
		soundDetailsThread.start();	
		
		int nBytesRead;
		while (true) {
			
			try {
				//read Data into the Buffer data.
				nBytesRead = decodedStream.read(data, 0, data.length);	
				
				if (nBytesRead == -1)
					break;
				sourceDataLine.write(data, 0, nBytesRead);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
