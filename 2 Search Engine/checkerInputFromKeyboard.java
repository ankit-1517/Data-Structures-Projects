import java.util.*;
import java.io.*;

public class checkerInputFromKeyboard{
	public static void main(String[] args){
		Scanner ak=new Scanner(System.in);
		SearchEngine se=new SearchEngine();
		String s;
		System.out.println("Anytime, type \"end\" to stop.");
		for(;;){
			s=ak.nextLine();
			if(s.equals("end"))
				break;
			se.performAction(s);
		}
	}
}
