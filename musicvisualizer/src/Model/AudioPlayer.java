package Model;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer implements Runnable {

	private SourceDataLine sourceDataLine;
	private AudioInputStream decodedStream;
	private SoundDetails soundDetails;

	public AudioPlayer(SourceDataLine sourceDataLine, AudioInputStream decodedStream, SoundDetails soundDetails) {
		this.sourceDataLine = sourceDataLine;
		this.decodedStream = decodedStream;
		this.soundDetails = soundDetails;
	}

	@Override
	public void run() {
		byte[] data = new byte[4096];
		
		//wire AudioPlayer and AudioDetails together.
		soundDetails.setSoundData(data);
		
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
