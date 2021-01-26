public class MySet<X>{
	
	private setNode head=null; 
    
    public setNode<X> getHead(){
		return head;
	}
	
	public Boolean IsEmpty(){
		return head==null;
	}
	
	public Boolean IsMember(X m){
		if (head==null)
			return false;
        setNode curr = head;
		for(;curr!=null;curr=curr.next)
			if(curr.data.equals(m))
				return true;
		return false;
	} 
	
	public void Insert(X o){
		setNode new_node = new setNode(o);
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
	
	public void Delete(X m){
		if (head==null){
			return;
		}
		if(head.data.equals(m)){
			head=head.next;
			return;
		}
		setNode curr = head,temp=null;
        for(;curr!=null;curr=curr.next){
			if(curr.data.equals(m)){
				temp.next=curr.next;
				return;
			}
			temp=curr;
		}
		return;
    }
	
	public MySet<X> union(MySet<X> a){
		MySet<X> Union=new MySet<X>();
		setNode<X> curr=this.head;
		for(;curr!=null;curr=curr.next)
			Union.Insert(curr.data);
		curr=a.head;
		for(;curr!=null;curr=curr.next)
			if(!Union.IsMember(curr.data))
				Union.Insert(curr.data);
		return Union;
	}
	
	public MySet<X> intersection(MySet<X> a){
		MySet<X> Union=new MySet<X>();
		setNode<X> curr=this.head;
		for(;curr!=null;curr=curr.next)
			Union.Insert(curr.data);
		curr=this.head;
		for(;curr!=null;curr=curr.next)
			if(!a.IsMember(curr.data))
				Union.Delete(curr.data);
		return Union;
	}
	
	public MySet<X> deleteDuplicates(MySet<X> a){
		setNode<X> tempNode=a.getHead();
		if(tempNode==null)
			return null;
		setNode<X> tempNode2=a.getHead();
		setNode<X> tempNode3=a.getHead();
		for(;tempNode!=null;tempNode=tempNode.next){
			for(tempNode2=tempNode.next,tempNode3=tempNode;tempNode2!=null;tempNode2=tempNode2.next){
				if(tempNode2.data.equals(tempNode.data)){
					if(tempNode2.next==null){
						tempNode3.next=null;
					}
					else{
						tempNode3.next=tempNode2.next;
					}tempNode2=tempNode3;
				}
				tempNode3=tempNode2;
			}
		}
		return a;
	}
}
