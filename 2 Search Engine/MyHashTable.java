import java.util.*;

public class MyHashTable{
	
	private static MyLinkedList<WordEntry>[] arr=new MyLinkedList[1000];
	//ASCII code for 0->48 9->57 , a->97 z->122
	//store 0-9 in 0-9 and a-z in 10-35
	Node<WordEntry> tempNode;
	String tempString;
	
	static{
		for(int i=0;i<1000;i++)
			arr[i]=new MyLinkedList<WordEntry>();
	}
	
	private int getHashIndex(String str){
		if(str.length()==0)
			return -1;
		int ans=0;
		for(int i=0;i<str.length();i++){
			ans+=(i+1)*str.charAt(i);
		}
		return ans%1000;
	}
	
	public void addPositionsForWord(WordEntry w){
		tempString=w.getWord();
		int index=getHashIndex(tempString);
		if(index==-1)
			return;
		tempNode=arr[index].getHead();
		for(;tempNode!=null;tempNode=tempNode.next){
			//modify wordentry if already +nt
			if(tempNode.data.getWord().equals(tempString)){
				tempNode.data.addPositionsWithChecking(w.getAllPositionsForThisWord());
				return;
			}
		}
		//not present, hence make new one
		if(tempNode==null){
			arr[index].add(w);
			return;
		}
		tempNode.data=w;
		tempNode.next=null;
		return;
	}
	
	public MySet<PageEntry> searchWord(String str){
		int i=getHashIndex(str);
		if(i==-1)
			return null;
		MySet<PageEntry> pageSet=new MySet<>();
		tempNode=arr[i].getHead();
		for(;tempNode!=null;tempNode=tempNode.next){
			if(tempNode.data.getWord().equals(str)){
				MyLinkedList<Position> positionList=tempNode.data.getAllPositionsForThisWord();
				Node<Position> tempNodePosition=positionList.getHead();
				for(;tempNodePosition!=null;tempNodePosition=tempNodePosition.next){
					pageSet.Insert(tempNodePosition.data.getPageEntry());
				}
				pageSet=pageSet.deleteDuplicates(pageSet);
				return pageSet;				
			}
		}
		//if temp hits null=> string not +nt in any page
		return null;
	}
	
	public String wordIndices(String word,String page){
		String s=new String();
		int i=getHashIndex(word),j=0;
		if(i==-1)
			return null;
		tempNode=arr[i].getHead();
		for(;tempNode!=null;tempNode=tempNode.next){
			if(tempNode.data.getWord().equals(word)){
				Node<Position> positions=tempNode.data.getAllPositionsForThisWord().getHead();
				for(;positions!=null;positions=positions.next){
					if(positions.data.getPageEntry().getPageName().equals(page)){
						if(j!=0)
							s+=", ";
						j++;
						s+=positions.data.getWordIndex();
					}
				}
				return s;
			}
		}
		return null;
	}
	
}
