import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.text.DecimalFormat ;
import java.util.ArrayList ;


public class problem2
{
	public static void main(String args[])
	{
		File name = new File("problem2.txt");

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text,output="";
			double startingY = 0.00 ;
			double startingX = 0.00 ;
			DecimalFormat fourDigits = new DecimalFormat("0.0000") ;
			while( (text=input.readLine())!= null)
			{
				ArrayList<Double> list = new ArrayList<Double>() ;
				startingY = Double.parseDouble(text) ;
				startingX = Math.round(startingY/2)+0.0 ;
				list.add(startingX) ;
				double newOne ;
				for(int i=1;i<7;i++)
				{
					newOne =((0.5)*((startingY/list.get(i-1))+list.get(i-1))) + 0.0 ;
					list.add(newOne) ;
				}
				for(int x=0;x<7;x++)
				{
					System.out.println("After iteration "+(x+1)+" : "+fourDigits.format(list.get(x))) ;
				}
				System.out.println("Square root of "+Double.parseDouble(text)+" is "+fourDigits.format(list.get(list.size()-1))+" after 7 iterations.") ;
				System.out.println("") ;
			}

		}
		catch (IOException io)
		{
			System.err.println("File error");
		}

	}
}