public class WordEntry{
	
	private String word;
	private MyLinkedList<Position> positionList=new MyLinkedList<Position>();
	
	public WordEntry(String word){
		this.word=word;
	}
	
	public String getWord(){
		return word;
	}
	
	public void addPosition(Position position){
		positionList.add(position);
	}
	
	public void addPositions(MyLinkedList<Position> positions){
		positionList.addMany(positions);
	}
	
	public void addPositionsWithChecking(MyLinkedList<Position> positions){
		positionList.addManyWithChecking(positions);
	}
	
	public MyLinkedList<Position> getAllPositionsForThisWord(){
		return positionList;
	}
	
	public float getTermFrequency(String word){
		Node<Position> temp= positionList.getHead();
		int i=0,totalWords=1;
		for(;temp!=null;temp=temp.next){
			if(temp.data.getPageEntry().getPageName().equals(word)){
				totalWords=temp.data.getPageEntry().getTotalWords();
				i++;
			}
		}
		if(i==0)
			return -1;
		return i/totalWords;
	}
	
}
