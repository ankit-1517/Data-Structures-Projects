public class Position{
	
	private PageEntry p;
	private int wordIndex;
	
	public Position(PageEntry p,int wordIndex){
		this.p=p;
		this.wordIndex=wordIndex;
	}
	
	public PageEntry getPageEntry(){
		return p;
	}
	
	public int getWordIndex(){
		return wordIndex;
	}
	
}
