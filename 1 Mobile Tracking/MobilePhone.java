public class MobilePhone implements Runnable{
	
	private Integer number;
	private boolean switchOn;
	private final Boolean b1=new Boolean("True");
	private final Boolean b2=new Boolean("False");
	private Exchange station=null;
	private boolean busy;
	public Thread t;
	
	public MobilePhone(int number,Exchange a){
		this.number=new Integer(number);
		switchOn=b1;
		this.station=a;
		t=new Thread(this,"ThreadMobile "+number);
	}
	
	public void run(){
	
	}
	
	public void changeBusy(boolean t){
		busy=t;
	}
	
	public boolean isBusy(){
		return busy;
	}
	
	public Integer number(){
		return number;
	}
	
	public Boolean status(){
		return switchOn;
	}
	
	public void switchOff(){
		switchOn=b2;
	}
	
	public void switchOn(){
		switchOn=b1;
	}
	
	public void setStation(Exchange a){
		this.station=a;
	}
	
	public Exchange location(){
		return station;
	}
}
