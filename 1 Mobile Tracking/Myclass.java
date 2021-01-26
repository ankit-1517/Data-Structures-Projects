import java.util.*;
import java.io.*;

public class Myclass{
	
	public static Scanner ak=new Scanner(System.in);
	public static String s;
	public static RoutingMapTree tree=new RoutingMapTree();
	public static Thread main=Thread.currentThread();
	public static RoutingMapTree r = new RoutingMapTree();
	public static Thread central=new Thread(r,"Central Server");
			
	public static void main(String args[]){
		System.out.println("In a new line, type end to stop giving more input");
		System.out.println("To generate random sequence of requests, press r");
		System.out.println("To give input from file, press f");
		System.out.println("To give input from keyboard, press k");
		s=ak.nextLine();
		if(s.equals("end"))
			return;
		else if(s.equals("r"))
			random();
		else if(s.equals("f"))
			file();
		else if(s.equals("k"))
			keyBoard();
		else
			System.out.println("Query not recognised, ending program...");
	}
	
	public static void random(){
		System.out.print("Time (integer, and in milliseconds) for this simulation: ");
		int i;
		try{
			i=ak.nextInt();
		}catch(InputMismatchException e){
			System.out.println("Given input is not integer");
			return;
		}
		System.out.print(i);
	}
	
	public static void keyBoard(){
		for(;;){
				s=ak.nextLine();
				if(s.equals("end"))
					break;
				System.out.println(tree.performAction(s));
		}
	}
	
	public static void file()
	{
		BufferedReader br = null;
		try {
			String actionString;
			br = new BufferedReader(new FileReader("actions1.txt"));

			while ((actionString = br.readLine()) != null) {
				r.performAction(actionString);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
