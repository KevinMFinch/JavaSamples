import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.Collections ;
import java.util.ArrayList ;


public class problem4
{
	public static void main(String args[])
	{
		File name = new File("problem4.txt");

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text,output="";
			int y = 1 ;
			while( (text=input.readLine())!= null)
			{
				ArrayList<Integer> numbers = new ArrayList<Integer>() ;
				int num = Integer.parseInt(text) ;
				while(num>0)
				{
					numbers.add(num%10) ;
					num /= 10 ;

				}
				Collections.sort(numbers) ;
				System.out.print("Output #"+y+" ") ;
				for(int x=numbers.size()-1;x>=0;x--)
				{
					System.out.print(numbers.get(x)) ;
				}
				System.out.println() ;
				y++ ;
			}
		}
		catch (IOException io)
		{
			System.err.println("File error");
		}

	}
}
