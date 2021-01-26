//it stores MobilePhone in Myset
public class MobilePhoneSet{
	
	private Myset mobileSet=new Myset();
	
	public MobilePhoneSet(){
		
	}
	
	public void resetMobileSet(Myset a){
		this.mobileSet=a;
	}
	
	public void addMobile(MobilePhone a){
		mobileSet.Insert(a);
	}
	
	public void deleteMobile(MobilePhone a){//bcoz switched off
		mobileSet.Delete(a);
	}
	
	public Myset getSet(){
		return mobileSet;
	}
}
