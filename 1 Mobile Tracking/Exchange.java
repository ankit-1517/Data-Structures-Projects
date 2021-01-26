//exchange will form nodes of the routing map structure
public class Exchange{
	
	private Integer number;//unique identifier for an exchange, its like a station number
	private Exchange parent = null;
    private final Boolean b1=new Boolean("True");
	private final Boolean b2=new Boolean("False");
	private ExchangeList children=new ExchangeList();
	private MobilePhoneSet residentMobiles=new MobilePhoneSet();
    
	public Exchange(int number){
		this.number=new Integer(number);
	}
	
	public Exchange getParent(){
		return this.parent;
	}
	
	public int numChildren(){
		return this.children.numChildren(); 
	}
	
	public void addChild(int number){
		Exchange child=new Exchange(number);
		this.children.add(child);
		child.setParent(this);
	}
	
	public void setParent(Exchange parent){
		this.parent=parent;
	}
	
	public ExchangeList getChildren() {
        return children;
    }
	
	public Exchange child(Integer i){//returns the i-th child
		return this.children.iChild(i);
	}
	
	public Integer getNumber(){
		return this.number;
	}
	
	public Boolean isRoot(){
		return this.parent==null;
	}
	
	public void addMobile(MobilePhone m){
		residentMobiles.addMobile(m);
	}
	
	public RoutingMapTree subTree(Integer i){//returns the i-th subtree
		RoutingMapTree subTree=new RoutingMapTree();
		subTree.root=this.child(i);
		return subTree;
	}
	
	public void updateResidentSet(MobilePhoneSet a){
		this.residentMobiles=a;
	}
	
	public MobilePhoneSet residentSet(){
		return residentMobiles;
	}
}
