package Model;

import java.util.Observable;

public class SoundDetails extends Observable implements Runnable{

	private byte[] data;
	long amount = 0;

	public void setSoundData(byte[] data){
		this.data = data;
	}
	
	public long getAmount(){
		return amount;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{		
				Thread.sleep(20);
				amount = 0;
				for(int i = 0; i <4096;i++){
					amount = amount+data[i];
				}
				
				System.out.println(amount);

			}catch(Exception e){
				
			}
		}
	}

}
