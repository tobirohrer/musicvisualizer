package Model;

import java.util.Observable;

public class SoundDetails extends Observable implements Runnable {

	private byte[] data;
	
	public SoundDetails(byte[] data){
		this.data = data;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				Thread.sleep(20);
				long amount = 0;
				for(int i = 0; i <4096;i++){
					amount = amount+data[i];
				}

				System.out.println(amount);
					
			}catch(Exception e){
				
			}
		}
	}
}
