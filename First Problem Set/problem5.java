import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.Collections ;
import java.util.ArrayList ;


public class problem5
{
	public static void main(String args[])
	{
		File name = new File("problem5.txt");

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text,output="";
			ArrayList<String> list1 = new ArrayList<String>() ;
			ArrayList<Boolean> list2 = new ArrayList<Boolean>() ;
			ArrayList<Integer> allNums = new ArrayList<Integer>() ;
			int line= 0 ;
			while( (text=input.readLine())!= null)
			{
				if(line!=0)
				{
					ArrayList<Integer> numbers = new ArrayList<Integer>() ;
					int num = Integer.parseInt(text) ;
					allNums.add(num) ;
					while(num > 0)
					{
						numbers.add(num%10) ;
						num /= 10 ;
					}
					Collections.sort(numbers) ;
					String number ="" ;
					for(int x=0;x<numbers.size();x++)
					{
						number+= numbers.get(x) ;
					}
					list1.add(number) ;
					list2.add(false) ;
				}
				line++ ;
			}

			for(int x=0;x<list1.size()-1;x++)
			{
				for(int y=x+1;y<list1.size();y++)
				{
					if(list1.get(x).equals(list1.get(y)))
					{
						list2.set(y,true) ;
						list2.set(x,true) ;
					}
				}
			}

			for(int x=0;x<list2.size();x++)
			{
				if(list2.get(x) == false)
				{
					System.out.println(allNums.get(x)) ;
				}
			}
		}
		catch (IOException io)
		{
			System.err.println("File error");
		}

	}
}
