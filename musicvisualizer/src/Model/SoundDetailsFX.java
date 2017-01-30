package Model;

import java.util.Observable;
import java.util.Observer;

public class SoundDetailsFX implements Observer {
	private SoundDetails soundDetails = null;
	
	public SoundDetailsFX(SoundDetails soundDetails)
	   {
	      this.soundDetails = soundDetails;
	   }
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(soundDetails.getAmount());
	}

}
