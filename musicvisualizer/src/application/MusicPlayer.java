package application;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class MusicPlayer {
	public void playClip(String audioFile) {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(this.getClass().getResourceAsStream(audioFile));
			
			AudioFormat originalFormat = audioInputStream.getFormat();

			AudioInputStream decodedStream = audioInputStream;
			
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioInputStream.getFormat());
			
			SourceDataLine test = (SourceDataLine)AudioSystem.getLine(info);
			
			test.open(originalFormat);
			test.start();
			
			byte[] data = new byte[4096];
					int nBytesRead;
					while(true){
					nBytesRead = decodedStream.read(data,0,data.length);

					
					
					if(nBytesRead == -1)
						break;
					 test.write(data, 0, nBytesRead);
					}
					
								
			System.out.println(test.isActive());		
			/*
			Clip clip = (Clip) AudioSystem.getLine(info);
			
			clip.addLineListener(new LineListener() {
				public void update(LineEvent e) {
					if (e.getType() == LineEvent.Type.STOP) {
				;
						synchronized (clip) {
							clip.notify();
						}
					}
				}
			});
			clip.open(audioInputStream);
			
			clip.setFramePosition(0);
			clip.start();
			
			long frameLength = clip.getFrameLength();
			frameLength = frameLength -1000; 
			
			while(clip.getFramePosition() < frameLength){
				//doSomething
			}
			
			synchronized (clip) {
				clip.wait();
			}
			clip.drain();
			clip.close();
			*/
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
