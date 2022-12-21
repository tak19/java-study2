package tv;

public class TV {
	private int channel; // 1~ 255 255보다 크면 1 , 1보다 낮으면 255로
	private int volume;  // 0~100
	private boolean power;

	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}

	public void power(boolean on) {

		if(on) {
			this.power = true;
		}else {
			this.power = false;
		}
		
	}

	public void channel(int channel) {
		if(!this.power) {
			return;
		}

		ckchannel(channel);
	}

	public void channel(boolean up) {
		if(!this.power) {
			return;
		}

		if(up) {
			ckchannel(++this.channel);
			
		}else {
			ckchannel(--this.channel);
		}

	}
	

	public void volume(int volume) {
		if(!this.power) {
			return;
		}

		ckvolume(volume);
	}

	public void volume(boolean up) {
		if(!this.power) {
			return;
		}
		
		if(up) {
			ckvolume(++this.volume);
			
		}else {
			ckvolume(--this.volume);
		}
	}
	
	public void ckchannel(int channel) {
		if(channel > 255) {
			this.channel = 1;
		}else if(channel < 1) {
			this.channel = 255;
		}else {
			this.channel = channel;
		}
	}

	public void ckvolume(int volume) {
		if(volume > 100) {
			this.volume = 0;
		}else if(volume < 1) {
			this.volume = 100;
		}else {
			this.volume = volume;
		}
	}
	
	public void status() {
		System.out.println("TV[power=" + (power ? "on" : "off") + ", " + " channel =" +  channel + ", " + " volume=" + volume + "]"  );
	}

}
