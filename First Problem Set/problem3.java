import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;


public class problem3
{
	public static void main(String args[])
	{
		File name = new File("problem3.txt");

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text,output="";

			while( (text=input.readLine())!= null)
			{
				int num = Integer.parseInt(text) ;
				if(num!=0)
				{
					System.out.println(Integer.toBinaryString(num)) ;
				}
			}

			System.out.println(output);

		}
		catch (IOException io)
		{
			System.err.println("File error");
		}

	}
}
