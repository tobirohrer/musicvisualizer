package Model;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;

public class PlayAudio implements Runnable {

	SourceDataLine sourceDataLine;
	AudioInputStream decodedStream;

	public PlayAudio(SourceDataLine sourceDataLine, AudioInputStream decodedStream) {
		this.sourceDataLine = sourceDataLine;
		this.decodedStream = decodedStream;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte[] data = new byte[4096];
		Thread soundDetails = new Thread(new SoundDetails(data));
		soundDetails.start();
		int nBytesRead;
		while (true) {
			// read Data into the Buffer data.
			try {
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
