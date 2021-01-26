public class ExchangeList{
	
    private Node head; 
    
    public ExchangeList(){
		head=null;
	}
 
    // Linked list Node
    class Node{
        Exchange data;
        Node next;
        Node(Exchange d){
            data = d;
            next = null;
        }
    }
 
    public void add(Exchange new_data){
        Node new_node = new Node(new_data);
        if(head==null){
			head=new_node;
			return;
		}
		
		Node curr=head;
		while(curr.next!=null)
			curr=curr.next;
		curr.next=new_node;
		return;
	}
 
	public int numChildren(){
		Node curr=head;
		int i=0;
		while(curr!=null){
			i++;
			curr=curr.next;
		}
		return i;
	}
	
	public Exchange iChild(int position){
		Node curr = head;
		//this will work almost like an array, like if he asks me to give 0th elemnet, i'll give head and so on
		//if there are n elements, i'll have to give n-1th element if he asks for the last one
        for (int i=0; curr!=null && i<position; i++)
            curr=curr.next;
		if(curr==null) return null;
        return curr.data;
	}
}
