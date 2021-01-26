import java.util.*;
import java.io.*;

public class checkerInputFromFile{
	public static void main(String[] args){
		BufferedReader br = null;
		SearchEngine se=new SearchEngine();

		try {
			String actionString;
			br = new BufferedReader(new FileReader("actions.txt"));

			while ((actionString = br.readLine()) != null) {
				se.performAction(actionString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	
	}
}
