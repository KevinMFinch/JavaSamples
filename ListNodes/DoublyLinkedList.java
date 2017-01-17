import java.util.*;

public class DoublyLinkedList
{
	public DoublyLinkedList()
	{
		int firstValue = (int)(Math.random()*1000)+1;
		ListNode node = new ListNode(new Integer(firstValue),null,null);
		for(int x=0;x<29;x++)
		{
			ListNode newOne = new ListNode(new Integer((int)(Math.random()*1000)+1),null,null);
			node.append(newOne);
			if(x==29)
			{
				newOne.setNext(node.get(0));
			}
		}
		output(node);
		//output(node);
		//outputReverse(node);
		//System.out.println("Sum: "+findSum(node));
		//System.out.println("Sum of even indeces: "+findEvenSum(node));
		//System.out.println("Sum of odd indeces: "+findOddSum(node));
		//duplicateEven(node);
		//removeMultiplesOfThree(node);
		//node.insert(3,new ListNode(new Integer((int)55555),null,null));
		//output(node);
		System.out.println(findMedian(node));
	}

	public void output(ListNode a)
	{
		ListNode nodes=a;
		int count=0;
		for(int x=0;x<a.size()-1;x++)
		{
			System.out.println(a.get(x).getValue());
			count++;
		}
		System.out.println("===================================");
	}

	public void duplicateEven(ListNode a)
	{
		ArrayList<ListNode> nodes = new ArrayList<ListNode>();
		for(int x=0;x<a.size();x++)
		{
			if((int)a.get(x).getValue() %2==0)
			{
				int y= (int)a.get(x).getValue();
				ListNode newOne = new ListNode(new Integer(y),null,null) ;
				nodes.add(newOne);
			}
		}
		for(int x=0;x<nodes.size();x++)
		{
			a.append(nodes.get(x));
		}
	}

	public void removeMultiplesOfThree(ListNode a)
	{
		for(int x=0;x<a.size();x++)
		{
			if((int)a.get(x).getValue() % 3==0)
			{
				a.remove(x) ;
			}
		}
	}

	public int findMedian(ListNode node)
	{
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int x=0;x<node.size();x++)
		{
			a.add((int)node.get(x).getValue());
		}

		if(a.size() % 2==1)
		{
			return a.get(a.size() /2) ;
		}
		else
		{
			return a.get(a.size()/2 -1);
		}
	}

	public void outputReverse(ListNode a)
	{
		for(ListNode node = a.get(a.size()-1);node !=null;node=node.getPrevious())
		{
			System.out.println(node.getValue());
		}
		System.out.println("===================================");
	}

	public int findSum(ListNode node)
	{
		int sum=0;
		for(int x=0;x<node.size();x++)
		{
			sum += (int)node.get(x).getValue();
		}
		return sum;
	}

	public int findEvenSum(ListNode node)
	{
		int sum=0;
		for(int x=0;x<node.size();x++)
		{
			if(x%2==0)
			{
				sum+= (int)node.get(x).getValue();
			}
		}
		return sum;
	}

	public int findOddSum(ListNode node)
		{
			int sum=0;
			for(int x=0;x<node.size();x++)
			{
				if(x%2==1)
				{
					sum+= (int)node.get(x).getValue();
				}
			}
			return sum;
	}


	public static void main(String args[])
	{
		DoublyLinkedList app=new DoublyLinkedList();
	}

}