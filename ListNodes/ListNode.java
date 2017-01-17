public class ListNode
{
	private Object value;
	private ListNode next;
	private ListNode previous;

	public ListNode(Object initValue, ListNode initNext,ListNode initPrev)
	{
		value=initValue;
		next=initNext;
		previous = initPrev;
	}

	public Object getValue()
	{
		return value;
	}

	public ListNode get(int index)
	{
		int countDex=0;
		for(ListNode node=this;countDex <= this.size();node=node.getNext())
		{
			if(countDex==index)
			{
				return node;
			}
			countDex++;
		}
		throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
	}

	public ListNode getNext()
	{
		return next;
	}

	public void setValue(Object theNewValue)
	{
		value=theNewValue;
	}

	public void setNext(ListNode theNewNext)
	{
		next=theNewNext;
	}

	public void setPrevious(ListNode theNewPrev)
	{
		previous=theNewPrev;
	}

	public ListNode getPrevious()
	{
		return previous;
	}

	public void append(ListNode node)
	{
		ListNode x = this.get(this.size()-1);
		x.setNext(node);
		node.setPrevious(x);
	}

	public void insert(int index,ListNode node)
	{
		int countDex=0;
		for(ListNode dex=this;dex.getNext() !=null;dex=dex.getNext())
		{
			if(dex.getNext() !=null)
			{
				countDex++;
			}
			if(countDex==index)
			{
				node.setNext(dex.getNext());
				node.getNext().setPrevious(node);
				dex.setNext(node);
				return;
			}
		}
	}

	public void remove(int index)
	{
		int countDex=0;
		int baseSize = this.size();
		ListNode initial = this;

		if(index==0)
		{
			this.getNext().setPrevious(null);
			this.setNext(null);
			return;
		}

		for(ListNode node = this;countDex <=this.size();node=node.getNext())
		{
			if(countDex==size()-1)
			{
				node.getPrevious().setNext(null);
				node.setPrevious(null);
				return;
			}
			if(countDex==index)
			{
				node.getNext().setPrevious(node.getPrevious());
				node.getPrevious().setNext(node.getNext());
				node.setNext(null);
				node.setPrevious(null);
				return;
			}
			countDex++;
		}
	}

	public int size()
	{
		int count=0;
		for(ListNode x=this;x!=null;x=x.getNext())
		{
			if(x.getNext() != this)
				count++;
			else break;
		}
		return count;
	}
}