import java.util.Stack;
import java.io.* ;
import java.util.*;
import java.text.*;

public class StackProgram
{
	public StackProgram()
	{
		File file = new File("Top100SciFiGoodReads.txt") ;
		Stack<Book> st=new Stack<Book>();
		int rank=0;
		String title="";
		String firstName="";
		String lastName="" ;
		Double rating=0.00;
		int numRatings=0;
		int score=0;
		int numVotes=0;

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(file));
			String text = "";
			int lineNumber = 0 ;
			while ((text = input.readLine()) != null)
			{
				if(lineNumber ==0)
				{
					//Title, rank
					String parts[] = text.split(" by ");
					int spaceLoc = parts[0].indexOf(' ');
					rank = Integer.parseInt(parts[0].substring(0,spaceLoc));
					title = parts[0].substring(spaceLoc+1);
					//Author names
					firstName = parts[1].substring(0,parts[1].lastIndexOf(" ")) ;
					lastName = parts[1].substring(parts[1].lastIndexOf(" ")+1) ;
				}
				else if(lineNumber==1)
				{
					//RatingScore (stars), numRatings
					String parts[] = text.split(" stars ");
					rating = Double.parseDouble(parts[0].substring(0,parts[0].indexOf(' '))) ;
					String numString = parts[1].substring(0,parts[1].indexOf(' '));
					try
					{
						numRatings = (int)((long)NumberFormat.getNumberInstance(java.util.Locale.US).parse(numString));
					}catch(ParseException pe)
					{
					}
				}
				else if(lineNumber ==2)
				{
					//Score and numVotes
					String parts[] = text.split(", and ") ;
					String numString = parts[0].substring(parts[0].indexOf(' ')) ;
					try
					{
						score = (int)((long)NumberFormat.getNumberInstance(java.util.Locale.US).parse(numString));
					}catch(ParseException pe)
					{
					}
					numString = parts[1].substring(0,parts[1].indexOf(' ')) ;
					try
					{
						numVotes = (int)((long)NumberFormat.getNumberInstance(java.util.Locale.US).parse(numString));
					}catch(ParseException pe)
					{
					}
					Book book = new Book(firstName,lastName,title,rank,rating,numRatings,score,numVotes);
					st.push(book);
				}

				if(lineNumber == 2)
				{
					lineNumber=0;
				}
				else
				{
					lineNumber++;
				}

			}
		} catch (IOException io)
		{

        }
		PriorityQueue<Book> pq = new PriorityQueue<Book>();
		System.out.println("+++++++++");
		while(!st.empty())
		{
			//if(st.peek()%2==0)
				pq.add(st.pop());
			//else System.out.println(-st.pop());
		}
		while(pq.peek() != null)
		{
			System.out.println(pq.remove().getLastName());
		}
	}
	public static void main(String args[])
	{
		StackProgram app=new StackProgram();
	}
}