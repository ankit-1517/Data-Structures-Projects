public class InvertedPageIndex{
	
	private MySet<PageEntry> pageEntry=new MySet<>();
	private MyHashTable hashTable=new MyHashTable();
	
	public void addPage(PageEntry p){
		pageEntry.Insert(p);
	}
	
	public PageEntry searchPage(String str){
		setNode<PageEntry> tempNode=pageEntry.getHead();
		for(;tempNode!=null;tempNode=tempNode.next){
			if(tempNode.data.getPageName().equals(str)){
				return tempNode.data;
			}
		}
		return null;
	}
	
	public String findWordIndices(String word,String page){
		return hashTable.wordIndices(word,page);
	}
	
	public MySet<PageEntry> getPagesWhichContainWord(String str){
		return hashTable.searchWord(str);
	}
	
}
