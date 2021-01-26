//tree whose nodes are from exchange class
public class RoutingMapTree implements Runnable{
	
	public Exchange root=null;
	private boolean exchangeExist=false;
	private boolean mobileExist=false;
	public static String s="";
	
	public RoutingMapTree() {
		root=new Exchange(0);
	}
	
	class exchangeAbsent extends Exception{}
	class mobileAbsent extends Exception{}
	
	public void run(){
	
	}
	
	//a is mobile, b is exchange, to switchon a and add to b
	public void searchExchange(int a,int b,Exchange curr) throws exchangeAbsent{
		if(curr.getNumber()==b){
			try{
				exchangeExist=true;
				searchMobile(a,curr);
			}
			catch(mobileAbsent e){
				switchOn(new MobilePhone(a,curr),curr);
			}
			return;
		}
		else
			for(int i=0;i<curr.numChildren();i++)
				searchExchange(a,b,curr.child(i));
		if(curr.getNumber()==0)
			throw new exchangeAbsent();
	}
	//add b as child to a
	public void searchExchange(Exchange curr,int a,int b) throws exchangeAbsent{
		if(curr.getNumber()==a){
			exchangeExist=true;
			addExchange(curr,b);
			return;
		}
		else
			for(int i=0;i<curr.numChildren();i++)
				searchExchange(curr.child(i),a,b);
		if(curr.numChildren()==0&&curr.getNumber()==0)
			throw new exchangeAbsent();
	}
	//for query mobilephoneset and nthchild
	public Exchange searchExchange(int a) throws exchangeAbsent{
		if(this.root.getNumber()==a)
			return this.root;
		Exchange curr=null;
		int i=this.root.numChildren();
		if(i>0){
			i--;
			for(;i>-1&&curr==null;i--){
				curr=this.root.subTree(i).searchExchange(a);
			}
		}
		else{
			if(this.root.getNumber()==0)
				throw new exchangeAbsent();
			return null;
		}
		if(curr==null&&this.root.getNumber()==0)
			throw new exchangeAbsent();
		return curr;
	}
	//for switching on
	public void searchMobile(int a,Exchange b) throws mobileAbsent{
		Myset mps=this.root.residentSet().getSet();
		if(mps.IsEmpty()){
			throw new mobileAbsent();
		}
		else{
			if(mps.IsMember(new MobilePhone(a,null))){
				if(!mps.getMobile(a).status()==true)
					switchOn(mps.getMobile(a),b);
				return;
			}
			else
				throw new mobileAbsent();
		}
	}
	//for switching off
	public void searchMobile(int a) throws mobileAbsent{
		Myset mps=this.root.residentSet().getSet();
		if(mps.IsEmpty()){
			throw new mobileAbsent();
		}
		else{
			if(mps.IsMember(new MobilePhone(a,null))){
				if(mps.getMobile(a).status()==true)
					switchOff(mps.getMobile(a));
				return;
			}
			else
				throw new mobileAbsent();
		}
	}
	
	public void addExchange(Exchange a,int b){
		a.addChild(b);
	}
	
	public void switchOn(MobilePhone a,Exchange b){
		Exchange temp=a.location();
		//delete if it already was earlier on at some station but was then switched off
		if(temp!=null)
			temp.residentSet().getSet().Delete(a);
		b.addMobile(a);
		a.setStation(b);
		a.switchOn();
		updateTree(this.root);
		mobileExist=true;
	}
	
	public void switchOff(MobilePhone a){
		a.switchOff();
		//a.location().residentSet().deleteMobile(a);
		//updateTree(this.root);
		mobileExist=true;
	}
	//print identifier of exchange which is bth child of a
	public void queryNthChild(int a,int b){
		Exchange parent;
		try{
			parent=searchExchange(a);
		}
		catch(exchangeAbsent e){
			s=" Error - No exchange with identifier "+a+" found in the network";
			return;
		}
		exchangeExist=true;
		int i=parent.numChildren()-1;
		if(i<0){
			System.out.println("It has no children");
			return;
		}
		if(b<0||b>i){
			System.out.println(b+" doesn't lie in range of number of children of given exchange");
			return;
		}
		Exchange nthchild=parent.child(b);
		s=s+" "+(int)nthchild.getNumber();
	}
	
	public MobilePhoneSet updateTree(Exchange parent){
		int i=parent.numChildren(),j=0;
		if(i>0){
		Myset temp=new Myset();
		for(;j<i;j++){
			temp=temp.Union(updateTree(parent.child(j)).getSet());
		}
		parent.residentSet().resetMobileSet(temp);}
		return parent.residentSet();
	}
	//print all set of mobile in this exchange
	public void queryMobilePhoneSet(int a){
		Exchange parent;
		try{
			parent=searchExchange(a);
		}
		catch(exchangeAbsent e){
			s=" Error - No exchange with identifier "+a+" found in the network";
			return;
		}
		exchangeExist=true;
		Myset set=parent.residentSet().getSet();
		set.printMobilePhoneSet();
	}
	
	public boolean containsNode(Exchange a){
		if(this.root.getNumber()==a.getNumber())
			return true;
		boolean flag=false;
		int n=this.root.numChildren();
		for(int i=0;i<n&&flag==false;i++)
			flag=this.root.subTree(i).containsNode(a);
		return flag;
	}
	//for finding mobile for general operation
	public MobilePhone searchMobileGeneral(int a) throws mobileAbsent{
		Myset mps=this.root.residentSet().getSet();
		if(mps.IsEmpty()){
			throw new mobileAbsent();
		}
		else{
			if(mps.IsMember(new MobilePhone(a,null))){
				mobileExist=true;
				return mps.getMobile(a);
			}
			else
				throw new mobileAbsent();
		}
	}
	
	public Exchange findPhone(MobilePhone m) throws Exception{
		if(m.status()==false)
			throw new Exception();
		return m.location();
	}
	
	public Exchange lowestRouter(Exchange a,Exchange b){
		int n=this.root.numChildren(),i;
		for(i=0;i<n;i++)
			if(this.root.subTree(i).containsNode(a)==true&&this.root.subTree(i).containsNode(b)==true)
				return this.root.subTree(i).lowestRouter(a,b);
		return this.root;
	}
	
	public void movePhone(MobilePhone a,Exchange b){
		a.setStation(b);
		b.addMobile(a);
		updateTree(this.root);
	}
	
	public ExchangeList routeCall(MobilePhone a,MobilePhone b){
		ExchangeList list=new ExchangeList();
		ExchangeList temp=new ExchangeList();
		Exchange eA=a.location(),eB=b.location();
		Exchange commonParent=lowestRouter(eA,eB);
		Exchange curr=eA;
		for(;!curr.getNumber().equals(commonParent.getNumber());curr=curr.getParent())
			list.add(curr);
		list.add(curr);
		if(eA.getNumber().equals(eB.getNumber()))
			return list;
		for(curr=eB;!curr.getNumber().equals(commonParent.getNumber());curr=curr.getParent())
			temp.add(curr);
		//now i have to add temp to list in reverse order
		int i=temp.numChildren()-1;
		for(;i>=0;i--)
			list.add(temp.iChild(i));
		return list;
	}
	
	public String performAction(String actionMessage) {
		s+=actionMessage;
		String temp[] = actionMessage.split(" ");
		int a,b;
		
		if(temp[0].equals("addExchange")){
			if(temp.length!=3){
				s="";
				return "Invalid argument";
			}
			a=Integer.parseInt(temp[1]);
			b=Integer.parseInt(temp[2]);
			try{
				searchExchange(b);
				System.out.println("Exchange with identifier "+b+" already exists");
			}
			catch(exchangeAbsent e){
				try{
					searchExchange(this.root,a,b);
				}
				catch(exchangeAbsent f){
					if(exchangeExist==false)
						s=" Error - No exchange with identifier "+a+" found in the network";
				}
			}
			s="";			
		}
		else if(temp[0].equals("switchOnMobile")){
			if(temp.length!=3){
				s="";
				return "Invalid argument";
			}
			a=Integer.parseInt(temp[1]);
			b=Integer.parseInt(temp[2]);
			//a is mobile, b is exchange
			try{
				searchExchange(a,b,this.root);
			}
			catch(exchangeAbsent e){
				if(exchangeExist==false)
					s=" Error - No exchange with identifier "+b+" found in the network";
			}
			s="";
		}
		else if(temp[0].equals("switchOffMobile")){
			if(temp.length!=2){
				s="";
				return "Invalid argument";
			}
			a=Integer.parseInt(temp[1]);
			try{
				searchMobile(a);
			}
			catch(mobileAbsent e){
				if(mobileExist==false)
					s=" Error - No mobile phone with identifier "+a+" found in the network";
			}
			s="";
		}
		else if(temp[0].equals("queryNthChild")){
			if(temp.length!=3){
				s="";
				return "Invalid argument";
			}
			s+=":";
			a=Integer.parseInt(temp[1]);
			b=Integer.parseInt(temp[2]);
			queryNthChild(a,b);
		}
		else if(temp[0].equals("queryMobilePhoneSet")){
			if(temp.length!=2){
				s="";
				return "Invalid argument";
			}
			s+=":";
			a=Integer.parseInt(temp[1]);
			queryMobilePhoneSet(a);
		}
		else if(temp[0].equals("findPhone")){
			if(temp.length!=2){
				s="";
				return "Invalid argument";
			}
			a=Integer.parseInt(temp[1]);
			try{
				MobilePhone m=searchMobileGeneral(a);
				try{
					Exchange mExchange=findPhone(m);
					s="queryFindPhone "+a+": "+mExchange.getNumber();
				}
				catch(Exception e){
					s="";
					return "queryFindPhone "+a+": Error - Mobile phone with identifier "+a+" is currently switched off";
				}
			}
			catch(mobileAbsent e){
				if(mobileExist==false){
					s="";
					return "queryFindPhone "+a+": Error - No mobile phone with identifier "+a+" found in the network";
				}
			}
		}
		else if(temp[0].equals("lowestRouter")){
			if(temp.length!=3){
				s="";
				return "Invalid argument";
			}
			a=Integer.parseInt(temp[1]);
			b=Integer.parseInt(temp[2]);
			try{
				Exchange eA=searchExchange(a);
				try{
					exchangeExist=false;
					Exchange eB=searchExchange(b);
					Exchange eParent=lowestRouter(eA,eB);
					s="queryLowestRouter "+a+" "+b+": "+eParent.getNumber();
				}
				catch(exchangeAbsent f){
					if(exchangeExist==false){
						s="";
						return "Exchange with identifier "+b+" doesn't exist";
					}
				}
			}
			catch(exchangeAbsent e){
				if(exchangeExist==false){
					s="";
					return "Exchange with identifier "+a+" doesn't exist";
				}
			}
		}
		else if(temp[0].equals("findCallPath")){
			if(temp.length!=3){
				s="";
				return "Invalid argument";
			}
			a=Integer.parseInt(temp[1]);
			b=Integer.parseInt(temp[2]);
			try{
				MobilePhone mA=searchMobileGeneral(a);
				try{
					MobilePhone mB=searchMobileGeneral(b);
					if(mA.status()==false){
						s="";
						return "queryFindCallPath "+a+" "+b+": Error - Mobile phone with identifier "+a+" is currently switched off";
					}
					if(mB.status()==false){
						s="";
						return "queryFindCallPath "+a+" "+b+": Error - Mobile phone with identifier "+b+" is currently switched off";
					}
					ExchangeList list=routeCall(mA,mB);
					s="queryFindCallPath "+a+" "+b+":";
					int i=list.numChildren();
					if(i==1)
						s+=" "+list.iChild(0).getNumber();
					else{
						int j=0;
						for(;j<i-1;j++)
							s+=" "+list.iChild(j).getNumber()+",";
						s+=" "+list.iChild(j).getNumber();
					}						
				}
				catch(mobileAbsent f){
					if(mobileExist==false){
						s="";
						return "Mobile with identifier "+b+" doesn't exist";
					}
				}
			}
			catch(mobileAbsent e){
				if(mobileExist==false){
					s="";
					return "Mobile with identifier "+a+" doesn't exist";
				}
			}
		}
		else if(temp[0].equals("movePhone")){
			if(temp.length!=3){
				s="";
				return "Invalid argument";
			}
			a=Integer.parseInt(temp[1]);
			b=Integer.parseInt(temp[2]);
			Exchange ex;
			MobilePhone m;
			try{
				ex=searchExchange(b);
				m=searchMobileGeneral(a);
				if(m.status()==true){
					movePhone(m,ex);
				}
				else{
					s="";
					return "Mobile with identifier "+a+" is already on";
				}
			}
			catch(exchangeAbsent e){
				s="";
				return "Exchange with identifier "+b+" not found";
			}
			catch(mobileAbsent f){
				s="";
				return "Mobile with identifier "+a+" not found";
			}
			s="";
		}
		else{
			s="Query not recognised";
		}
		String tempString=s;
		s="";
		mobileExist=false;
		exchangeExist=false;
		return tempString;	
	}
}
