import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.ArrayList ;


public class problem1
{
	public problem1()
	{
		ArrayList<Integer> list = new ArrayList<Integer>() ;
		File name = new File("problem1.txt");
		int x = 2 ;
		while(list.size() < 1000)
		{
			int counter=0 ;
			for(int y=1;y<=x;y++)
			{
				if(x%y==0)
				{
					counter++ ;
				}
			}
			if(counter <= 2)
			{
				list.add(x) ;
			}
			x++ ;
		}
		//System.out.println(list) ;

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text,output="";
			String secondSentence ="The " ;
			int numCount = 0;
			ArrayList<Integer> indexNums = new ArrayList<Integer>() ;
			ArrayList<Integer> storeEm = new ArrayList<Integer>() ;
			while( (text=input.readLine())!= null)
			{
				int index = Integer.parseInt(text) ;
				if(index != 0)
				{
					indexNums.add(Integer.parseInt(text)) ;
				}
				if(index!=0)
				{
					output+= list.get(index-1)+"\n" ;
					storeEm.add(list.get(index-1)) ;
				}
			}
			for(int j=0;j<indexNums.size()-2;j++)
			{
				int index = indexNums.get(j) ;
				if(index % 10==0 && index!=0)
				{
					secondSentence +=index+"-th, " ;
				}
				else if(isItATeen(index))
				{
					secondSentence += index+"-th, " ;
				}
				else if(index % 10==1)
				{
					if(index % 100 == 11)
					{
						secondSentence += index+"-th, " ;
					}
					else
						secondSentence +=index+"-st, " ;
				}
				else if(index % 10==2)
				{
					secondSentence +=index+"-nd, " ;
				}
				else if(index % 10==3)
				{
					secondSentence +=index+"-rd, " ;
				}
				else
				{
					secondSentence +=index+"-th, " ;
				}
			}

			int index = indexNums.get(indexNums.size()-2) ;
			if(index % 10==0 && index!=0)
			{
				secondSentence +=index+"-th " ;
			}
			else if(isItATeen(index))
			{
				secondSentence += index+"-th " ;
			}
			else if(index % 10==1)
			{
				if(index % 100 ==11)
				{
					secondSentence += index+"-th " ;
				}
				else
					secondSentence +=index+"-st " ;
			}
			else if(index % 10==2)
			{
				secondSentence +=index+"-nd " ;
			}
			else if(index % 10==3)
			{
				secondSentence +=index+"-rd " ;
			}
			else
			{
				secondSentence +=index+"-th " ;
			}

			secondSentence+="and "+indexNums.get(indexNums.size()-1) ;
			index = indexNums.get(indexNums.size()-1) ;
			if(index % 10==0 && index!=0)
			{
				secondSentence +="-th" ;
			}
			else if(isItATeen(index))
			{
				secondSentence += index+"-th" ;
			}
			else if(index % 10==1)
			{
				if(index % 100 ==11)
				{
					secondSentence += index+"-th" ;
				}
				else
					secondSentence +="-st" ;
			}
			else if(index % 10==2)
			{
				secondSentence +="-nd" ;
			}
			else if(index % 10==3)
			{
				secondSentence +="-rd" ;
			}
			else
			{
				secondSentence +="-th" ;
			}
			output+=secondSentence+" prime numbers are " ;
			for(int z=0;z<storeEm.size()-1;z++)
			{
				output+=storeEm.get(z)+", " ;
			}
			output+="and "+storeEm.get(storeEm.size()-1)+" respectively." ;

			System.out.println(output);

		}
		catch (IOException io)
		{
			System.err.println("File error");
		}

	}

	public boolean isItATeen(int num)
	{
		int temp = num / 10 ;
		if (temp % 10 == 1)
		{
			return true ;
		}
		else
		{
			return false ;
		}
	}

	public static void main(String args[])
	{
		problem1 app = new problem1() ;
	}
}
