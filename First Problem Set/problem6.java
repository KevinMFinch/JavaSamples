import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.ArrayList ;
import java.util.Collections ;


public class problem6
{
	public static void main(String args[])
	{
		problem6 app=new problem6();
	}

	public int decode(String st)
	{
		if(st.equals("A"))
		{
			return 14 ;
		}
		else if(st.equals("K"))
		{
			return 13 ;
		}
		else if(st.equals("Q"))
		{
			return 12 ;
		}
		else if(st.equals("J"))
		{
			return 11 ;
		}
		else if(st.equals("0"))
		{
			return 10 ;
		}
		else
		{
			return Integer.parseInt(st) ;
		}
	}

	public problem6()
	{
		File name = new File("problem6.txt") ;
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text="";
			while( (text=input.readLine())!= null)
			{
				int winner = 0 ;
				ArrayList<Integer> simNums = new ArrayList<Integer>() ;
				ArrayList<String> hands = new ArrayList<String>() ;
				ArrayList<Integer> hand1 = new ArrayList<Integer>() ;
				ArrayList<Integer> hand2 = new ArrayList<Integer>() ;
				ArrayList<Integer> handType = new ArrayList<Integer>() ;
				String firstHand="" ;
				String secondHand="" ;
				if(text.length() != 1)
				{
					hands.add(text) ;
					int spaceDex = text.indexOf(" ") ;
					for(int x=0;x<spaceDex;x++)
					{
						hand1.add(decode(text.substring(x,x+1))) ;
					}
					for(int x=spaceDex+1;x<text.length();x++)
					{
						hand2.add(decode(text.substring(x,x+1))) ;
					}
					Collections.sort(hand1) ;
					Collections.sort(hand2) ;

					Collections.reverse(hand1) ;
					Collections.reverse(hand2) ;
					handType.add(7) ;
					handType.add(7) ;
					//Classify hand types
					//Hand1
					if((hand1.get(0) == hand1.get(1) && hand1.get(0) == hand1.get(2) && hand1.get(0) == hand1.get(3)) ||
					   (hand1.get(1) == hand1.get(2) && hand1.get(1) == hand1.get(3) && hand1.get(1) == hand1.get(4))) //First hand 40fKind
					{
						handType.set(0,1) ;
						simNums.add(hand1.get(1)) ;
					}
					else if((hand1.get(0) == hand1.get(1) && hand1.get(0) == hand1.get(2)) && (hand1.get(3) == hand1.get(4))) //full house
					{
						handType.set(0,2) ;
						simNums.add(hand1.get(0)) ;
						simNums.add(hand1.get(3)) ;
					}
					else if((hand1.get(0) == hand1.get(1) && hand1.get(2) == hand1.get(3) && hand1.get(2) == hand1.get(4)))
					{
						handType.set(0,2) ;
						simNums.add(hand1.get(0)) ;
						simNums.add(hand1.get(2)) ;
					}
					else if((hand1.get(0) == (hand1.get(1) +1) && hand1.get(0) == (hand1.get(2)+2) && hand1.get(0) == (hand1.get(3)+3) && hand1.get(0) == (hand1.get(4)+4)) ||
							(hand1.get(4) == (hand1.get(3) +1) && hand1.get(4) == (hand1.get(2)+2) && hand1.get(4) == (hand1.get(1)+3) && hand1.get(4) == (hand1.get(0)+4)))
					{
						handType.set(0,3) ;
						simNums.add(hand1.get(0)) ;
					}
					else if(hand1.get(0) == hand1.get(1) && hand1.get(0) == hand1.get(2))
					{
						handType.set(0,4) ;
						simNums.add(hand1.get(0)) ;
					}
					else if(hand1.get(1) == hand1.get(2) && hand1.get(1) == hand1.get(3))
					{
						handType.set(0,4) ;
						simNums.add(hand1.get(1)) ;
					}
					else if(hand1.get(2) == hand1.get(3) && hand1.get(2) == hand1.get(4))
					{
						handType.set(0,4) ;
						simNums.add(hand1.get(2)) ;
					}
					else if(hand1.get(0) == hand1.get(1) && hand1.get(2) == hand1.get(3))
					{
						handType.set(0,5) ;
						simNums.add(hand1.get(0)) ;
						simNums.add(hand1.get(2)) ;
					}
					else if(hand1.get(1) == hand1.get(2) && hand1.get(3) == hand1.get(4))
					{
						handType.set(0,5) ;
						simNums.add(hand1.get(1)) ;
						simNums.add(hand1.get(3)) ;
					}
					else if(hand1.get(0) == hand1.get(1) && hand1.get(3) == hand1.get(4))
					{
						handType.set(0,5) ;
						simNums.add(hand1.get(0)) ;
						simNums.add(hand1.get(3)) ;
					}
					else if(hand1.get(0) == hand1.get(1))
					{
						handType.set(0,6) ;
						simNums.add(hand1.get(0)) ;
					}
					else if(hand1.get(1) == hand1.get(2))
					{
						handType.set(0,6) ;
						simNums.add(hand1.get(1)) ;
					}
					else if(hand1.get(2) == hand1.get(3))
					{
						handType.set(0,6) ;
						simNums.add(hand1.get(2)) ;
					}
					else if(hand1.get(3) == hand1.get(4))
					{
						handType.set(0,6) ;
						simNums.add(hand1.get(3)) ;
					}

					//Hand2
					if((hand2.get(0) == hand2.get(1) && hand2.get(0) == hand2.get(2) && hand2.get(0) == hand2.get(3)) ||
					   (hand2.get(1) == hand2.get(2) && hand2.get(1) == hand2.get(3) && hand2.get(1) == hand2.get(4))) //First hand 40fKind
					{
						handType.set(1,1) ;
						simNums.add(hand2.get(1)) ;
					}
					else if((hand2.get(0) == hand2.get(1) && hand2.get(0) == hand2.get(2)) && (hand2.get(3) == hand2.get(4))) //full house
					{
						handType.set(1,2) ;
						simNums.add(hand2.get(0)) ;
						simNums.add(hand2.get(3)) ;
					}
					else if((hand2.get(0) == hand2.get(1) && hand2.get(2) == hand2.get(3) && hand2.get(2) == hand2.get(4)))
					{
						handType.set(1,2) ;
						simNums.add(hand2.get(0)) ;
						simNums.add(hand2.get(2)) ;
					}
					else if((hand2.get(0) == (hand2.get(1) +1) && hand2.get(0) == (hand2.get(2)+2) && hand2.get(0) == (hand2.get(3)+3) && hand2.get(0) == (hand2.get(4)+4)) ||
							(hand2.get(4) == (hand2.get(3) +1) && hand2.get(4) == (hand2.get(2)+2) && hand2.get(4) == (hand2.get(1)+3) && hand2.get(4) == (hand2.get(0)+4)))
					{
						handType.set(1,3) ;
						simNums.add(0) ;
					}
					else if(hand2.get(0) == hand2.get(1) && hand2.get(0) == hand2.get(2))
					{
						handType.set(1,4) ;
						simNums.add(hand2.get(0)) ;
					}
					else if(hand2.get(1) == hand2.get(2) && hand2.get(1) == hand2.get(3))
					{
						handType.set(1,4) ;
						simNums.add(hand2.get(1)) ;
					}
					else if(hand2.get(2) == hand2.get(3) && hand2.get(2) == hand2.get(4))
					{
						handType.set(1,4) ;
						simNums.add(hand2.get(2)) ;
					}
					else if(hand2.get(0) == hand2.get(1) && hand2.get(2) == hand2.get(3))
					{
						handType.set(1,5) ;
						simNums.add(hand2.get(0)) ;
						simNums.add(hand2.get(2)) ;
					}
					else if(hand2.get(1) == hand2.get(2) && hand2.get(3) == hand2.get(4))
					{
						handType.set(1,5) ;
						simNums.add(hand2.get(1)) ;
						simNums.add(hand2.get(3)) ;
					}
					else if(hand2.get(0) == hand2.get(1) && hand2.get(3) == hand2.get(4))
					{
						handType.set(1,5) ;
						simNums.add(hand2.get(0)) ;
						simNums.add(hand2.get(3)) ;
					}
					else if(hand2.get(0) == hand2.get(1))
					{
						handType.set(1,6) ;
						simNums.add(hand2.get(0)) ;
					}
					else if(hand2.get(1) == hand2.get(2))
					{
						handType.set(1,6) ;
						simNums.add(hand2.get(1)) ;
					}
					else if(hand2.get(2) == hand2.get(3))
					{
						handType.set(1,6) ;
						simNums.add(hand2.get(2)) ;
					}
					else if(hand2.get(3) == hand2.get(4))
					{
						handType.set(1,6) ;
						simNums.add(hand2.get(3)) ;
					}

					//Who wins?
					if(handType.get(0) < handType.get(1))
					{
						winner = 1 ;
					}
					else if(handType.get(1) < handType.get(0))
					{
						winner = 2 ;
					}
					else
					{
						if(handType.get(0) == 1)
						{
							if(hand1.get(1) > hand2.get(1))
							{
								winner = 1 ;
							}
							else if(hand1.get(1) > hand2.get(1))
							{
								winner = 2 ;
							}
						}
						else if(handType.get(0) == 2)
						{
							if(simNums.get(0) > simNums.get(1))
							{
								winner = 1 ;
							}
							else if(simNums.get(1) > simNums.get(0))
							{
								winner = 2 ;
							}
						}
						else if(handType.get(0) == 3)
						{
							if(simNums.get(0) > simNums.get(1))
							{
								winner = 1 ;
							}
							if(simNums.get(1) > simNums.get(0))
							{
								winner = 2 ;
							}
						}
						else if(handType.get(0) == 4)
						{
							if(simNums.get(0) > simNums.get(1))
							{
								winner = 1 ;
							}
							if(simNums.get(1) > simNums.get(0))
							{
								winner = 2 ;
							}
						}
						else if(handType.get(0) == 5)
						{
							if(simNums.get(0) > simNums.get(2))
							{
								winner = 1 ;
							}
							else if(simNums.get(2) > simNums.get(0))
							{
								winner = 2 ;
							}
							else if(simNums.get(0) == simNums.get(2))
							{
								if(simNums.get(1) > simNums.get(3))
								{
									winner = 1 ;
								}
								else if(simNums.get(3) > simNums.get(1))
								{
									winner = 2 ;
								}
							}
						}
						else if(handType.get(0) == 6)
						{
							if(simNums.get(0) > simNums.get(1))
							{
								winner = 1 ;
							}
							if(simNums.get(1) > simNums.get(0))
							{
								winner = 2 ;
							}
						}
					}
					System.out.println(hands.get(0)+"\t"+winner) ;
				}

			}
		}

		catch (IOException io)
		{
			System.err.println("File error");
		}
	}
}