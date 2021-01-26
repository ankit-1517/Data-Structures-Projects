public class Myset{
	
	private setNode head=null; 
    
    public Myset(){
	}
	
	class setNode{
        MobilePhone data;
        setNode next;
        setNode(MobilePhone d){
            data = d;
            next = null;
        }
    }
	
	public Boolean IsEmpty(){
		return head==null;
	}
	
	public Boolean IsMember(Object o){
		MobilePhone m=(MobilePhone)o;
		if (head==null)
			return false;
        setNode curr = head;
		for(;curr!=null;curr=curr.next)
			if(curr.data.number().equals(m.number()))
				return true;
		return false;
	} 
	
	public void Insert(Object o){
		setNode new_node = new setNode((MobilePhone)o);
        if(head==null){
			head=new_node;
			return;
		}
		setNode curr=head;
		while(curr.next!=null)
			curr=curr.next;
		curr.next=new_node;
		return;
	}
	
	public void Delete(Object o)
    {
		MobilePhone m=(MobilePhone)o;
		if (head==null){
			//System.out.print("Can't delete from empty list");
			return;
		}
		if(head.data.number().equals(m.number())){
			head=head.next;
			return;
		}
		setNode curr = head,temp=null;
        for(;curr!=null;curr=curr.next){
			if(curr.data.number().equals(m.number())){
				temp.next=curr.next;
				return;
			}
			temp=curr;
		}
		RoutingMapTree.s=" Error - No exchange with identifier "+m.number()+" found in the network";
		return;
    }
	
	public Myset Union(Myset a){
		Myset union=new Myset();
		setNode curr=this.head;
		for(;curr!=null;curr=curr.next)
			union.Insert(curr.data);
		curr=a.head;
		for(;curr!=null;curr=curr.next)
			if(!union.IsMember(curr.data))
				union.Insert(curr.data);
		return union;
	}
	
	public Myset Intersection(Myset a){
		Myset union=new Myset();
		setNode curr=this.head;
		for(;curr!=null;curr=curr.next)
			union.Insert(curr.data);
		curr=this.head;
		for(;curr!=null;curr=curr.next)
			if(!a.IsMember(curr.data))
				union.Delete(curr.data);
		return union;
	}
	
	public void printMobilePhoneSet(){
		setNode curr=head;
		for(;curr!=null;curr=curr.next)
			if(curr.data.status()==true){
				RoutingMapTree.s+=" "+curr.data.number();
				if(curr.next!=null)
					RoutingMapTree.s+=",";
			}
	}
	
	public MobilePhone getMobile(int a){
		setNode curr=this.head;
		for(;;curr=curr.next)
			if(curr.data.number().equals(a))
				return curr.data;
	}
}
