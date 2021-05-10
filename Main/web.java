package Main;

import java.net.URL;
import java.util.Scanner;


public class web {

	public static String read(String  url) {
		try {
		 //Instantiating the URL class
	      URL url1 = null;
			url1 = new URL(url);
	      //Retrieving the contents of the specified page
	      Scanner sc = null;
			sc = new Scanner(url1.openStream());
	      //Instantiating the StringBuffer class to hold the result
	      StringBuffer sb = new StringBuffer();
	      while(sc.hasNext()) {
	         sb.append(sc.next());
	         //System.out.println(sc.next());
	      }
	      //Retrieving the String from the String Buffer object
	      String result = sb.toString();
	      //Removing the HTML tags
	      result = result.replaceAll("<[^>]*>", "");
	      return result;
		}catch(Exception e) {return "Exception";}
	}
}
