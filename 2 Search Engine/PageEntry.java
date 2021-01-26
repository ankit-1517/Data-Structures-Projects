import java.io.*;
import java.util.*;

public class PageEntry{
	
	private String pageName;
	private PageIndex pageIndex=new PageIndex();
	private String[] connectors={"a","an","the","they","these","this","for","is","are","was","of","or","and","does","will","whose"};//size 16
	private char[] punctuations={'{','}','[',']','<','>','=','(',')','.',',',';','\'','"','?','#','!','-',':'};//size 19
	private String[] plurals={"stacks","structures","applications"};//size 3
	private int totalWords=0,len;
	private String[] temp;
	private MyHashTable tableClass=new MyHashTable();
	int indexUptoPrevLine=0,indexUptoPrevLine2=0;
	
	public PageEntry(String pageName){
		this.pageName=pageName;
	}
	
	public void f() throws Exception{		
		readFile();
	}
	
	public int getTotalWords(){
		return totalWords;
	}
	
	public String getPageName(){
		return pageName;
	}
	
	public void readFile() throws Exception{
		BufferedReader br = null;
		String s;
		try {
			br = new BufferedReader(new FileReader("webpages\\"+pageName));
			while ((s = br.readLine()) != null) {
				if(s.length()==0)
					continue;
				//change capital to small
				s=s.toLowerCase();
				s=s.trim();
				len=s.length();
				//change punctuations to spaces
				for(int i=0;i<len;i++){
					for(int j=0;j<19;j++){
						if(s.charAt(i)==punctuations[j]){
							StringBuilder newS = new StringBuilder(s);
							newS.setCharAt(i,' ');
							s=newS.toString();
							break;
						}
					}
				}
				//change plurals to singulars
				temp=s.split("\\s+");
				indexUptoPrevLine=indexUptoPrevLine2+temp.length;
				len=temp.length;
				for(int i=0;i<len;i++){
					for(int j=0;j<3;j++){
						if(temp[i].equals(plurals[j])){
							StringBuilder builder = new StringBuilder(temp[i]);
							builder.deleteCharAt(temp[i].length()- 1);
							temp[i]=builder.toString();
							break;
						}
					}
				}
				//replace connectors by space
				totalWords+=len;
				for(int i=0;i<len;i++){
					for(int j=0;j<16;j++){
						if(temp[i].equals(connectors[j])){
							temp[i]=" ";
							totalWords--;
							break;
						}
					}
				}
				createPageIndex();
			}
		} catch (IOException e) {
			System.out.println("Webpage "+pageName+" not found in webpages folder");
			throw new Exception(e);
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void createPageIndex(){
		for(int i=0;i<len;i++){
			if(!temp[i].equals(" ")){
				pageIndex.addPositionForWord(temp[i],new Position(this,indexUptoPrevLine2+i+1));
			}
		}
		MyLinkedList<WordEntry> wordsInThisPage=pageIndex.getWordEntries();
		Node<WordEntry> tempNode=wordsInThisPage.getHead();
		for(;tempNode!=null;tempNode=tempNode.next){
			tableClass.addPositionsForWord(tempNode.data);	
		}
		indexUptoPrevLine2=indexUptoPrevLine;
	}
	
	public PageIndex getPageIndex(){
		return pageIndex;
	}
	
}
