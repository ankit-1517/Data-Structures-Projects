public class MyLinkedList<X>{
	
	private Node<X> head; 
    
    public MyLinkedList(){
		head=null;
	}
 
    public Node<X> getHead(){
		return head;
	}
	
	public void updateHead(Node<X> head){
		this.head=head;
	}
	
	public boolean isPresent(X data){
		Node<X> temp=this.getHead();
		for(;temp!=null;temp=temp.next){
			if(temp.data.equals(data))
				return true;
		}
		return false;
	}
 
    public void add(X new_data){
        Node<X> new_node = new Node<X>(new_data);
        if(head==null){
			head=new_node;
			return;
		}
		
		Node<X> curr=head;
		while(curr.next!=null)
			curr=curr.next;
		curr.next=new_node;
		return;
	}
	
	public void addMany(MyLinkedList<X> list){
		Node<X> listHead=list.getHead();
		Node<X> temp=listHead;
		for(;temp!=null;temp=temp.next){
			add(temp.data);
		}
	}
	
	public void addManyWithChecking(MyLinkedList<X> list){
		Node<X> listHead=list.getHead();
		Node<X> temp=listHead;
		for(;temp!=null;temp=temp.next){
			if(this.isPresent(temp.data)==false)
				add(temp.data);
		}
	}
}

