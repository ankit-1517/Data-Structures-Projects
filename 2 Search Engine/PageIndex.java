public class PageIndex{
	
	private MyLinkedList<WordEntry> listWordEntry=new MyLinkedList<>();//list of all words stored in a webpage
	
	public void addPositionForWord(String str, Position p){
		Node<WordEntry> head=listWordEntry.getHead();
		if(head==null){
			WordEntry word=new WordEntry(str);
			word.addPosition(p);
			listWordEntry.add(word);
			return;
		}
		for(;head!=null;head=head.next){
			if(head.data.getWord().equals(str)){
				head.data.addPosition(p);
				return;
			}
		}
		WordEntry word=new WordEntry(str);
		word.addPosition(p);
		listWordEntry.add(word);
	}
	
	public MyLinkedList<WordEntry> getWordEntries(){
		return listWordEntry;
	}
	
	public void print(){
		Node<WordEntry> head=listWordEntry.getHead();
		for(;head!=null;head=head.next){System.out.print(head.data.getWord()+" ");}
	}
	
}
