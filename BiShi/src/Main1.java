import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;


public class Main1 {
	public static void main(String[] args)
	{
		Scanner inScanner = new Scanner(System.in);
		
		while (inScanner.hasNext()) {
		 String string = inScanner.next();
		 int[] ids = new int[string.length() -2];
		  for(int i = 1;i < string.length() - 1;i++)
		  {
			  ids[i-1] = Integer.parseInt(string.substring(i, i));
			  System.out.println("ss" + ids[i-1]);
		  }
		}
		 
	}

}
