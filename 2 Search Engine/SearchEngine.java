import java.util.*;

public class SearchEngine{
	
	private InvertedPageIndex ipIndex;
	private String[] connectors={"a","an","the","they","these","this","for","is","are","was","of","or","and","does","will","whose"};//size 16
	private char[] punctuations={'{','}','[',']','<','>','=','(',')','.',',',';','\'','"','?','#','!','-',':'};//size 19
	private String[] plurals={"stacks","structures","applications"};//size 3
	
	public SearchEngine(){
		ipIndex=new InvertedPageIndex();
	}
	
	public String change(String s){
		//change capital to small
		s=s.toLowerCase();
		s=s.trim();
		int len=s.length();
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
		for(int i=0;i<3;i++){
			if(s.equals(plurals[i])){
				StringBuilder builder = new StringBuilder(s);
				builder.deleteCharAt(s.length()- 1);
				s=builder.toString();
			}
		}
		return s;
	}
	
	public void performAction(String actionMessage){
		try{
		String temp[] = actionMessage.split(" ");
		temp[1]=change(temp[1]);
		if(temp[0].equals("addPage")){
			if(ipIndex.searchPage(temp[1])!=null){
				System.out.println("Webpage "+temp[1]+" already exists");
				return;
			}
			try{
				PageEntry page=new PageEntry(temp[1]);
				page.f();
				ipIndex.addPage(page);
			}
			catch(Exception e){}
		}
		else if(temp[0].equals("queryFindPagesWhichContainWord")){
			MySet<PageEntry> pages=ipIndex.getPagesWhichContainWord(temp[1]);
			if(pages==null){
				System.out.println("No webpage contains word "+temp[1]);
				return;
			}
			setNode<PageEntry> tempNode=pages.getHead();
			for(;tempNode!=null;tempNode=tempNode.next){
				System.out.print(tempNode.data.getPageName());
				if(tempNode.next!=null)
					System.out.print(", ");
			}
			System.out.println();
		}
		else if(temp[0].equals("queryFindPositionsOfWordInAPage")){
			PageEntry page=ipIndex.searchPage(temp[2]);
			if(page==null){
				System.out.println("No webpage "+temp[2]+" found");
				return;
			}
			String s=ipIndex.findWordIndices(temp[1],temp[2]);
			if(s==null){
				System.out.println("Webpage "+temp[2]+" does not contain word "+temp[1]);
				return;
			}
			System.out.println(s);
		}
		else{
			System.out.println("Query not recognised");
		}}
		catch(Exception e){System.out.println("Query not recognised");}
	}
	
	
}
