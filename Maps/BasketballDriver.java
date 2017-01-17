import java.util.* ;
import java.io.* ;

public class BasketballDriver
{
	BufferedReader input ;
	HashMap<String,ArrayList<BasketballPlayer>> hMap = new HashMap<String,ArrayList<BasketballPlayer>>() ;
	TreeMap<String,ArrayList<BasketballPlayer>> tMap = new TreeMap<String,ArrayList<BasketballPlayer>>() ;


	public BasketballDriver()
	{
		try
		{

			input = new BufferedReader(new FileReader(new File("BasketballPlayerList.txt")));
			String text = "";
			while ((text = input.readLine()) != null)
			{
				String parts[] = text.split("	") ;
				if (hMap.containsKey(parts[2]))
				{
					ArrayList<BasketballPlayer> pastValues = hMap.get(parts[2]);
					pastValues.add(new BasketballPlayer(parts[0],parts[1]));
					hMap.put(parts[2], pastValues);
				}
				else
				{
					ArrayList<BasketballPlayer> values = new ArrayList<BasketballPlayer>();
					values.add(new BasketballPlayer(parts[0],parts[1]));
					hMap.put(parts[2], values);
				}

				if (tMap.containsKey(parts[2]))
				{
					ArrayList<BasketballPlayer> pastValues = tMap.get(parts[2]);
					pastValues.add(new BasketballPlayer(parts[0],parts[1]));
					tMap.put(parts[2], pastValues);
				}
				else
				{
					ArrayList<BasketballPlayer> values = new ArrayList<BasketballPlayer>();
					values.add(new BasketballPlayer(parts[0],parts[1]));
					tMap.put(parts[2], values);
				}
			}

		} catch (IOException io)
		{

        }

        System.out.println("Iterating through the HashMap:") ;
        Iterator it=hMap.values().iterator() ;
        while(it.hasNext())
        {
			ArrayList<BasketballPlayer> players = (ArrayList<BasketballPlayer>)it.next() ;
        	for(int x = 0;x<players.size();x++)
        	{
				System.out.println(players.get(x)) ;
			}
		}

		System.out.println() ;
		System.out.println("Iterating through the TreeMap: ") ;
		it = tMap.values().iterator() ;
		while(it.hasNext())
		{
		ArrayList<BasketballPlayer> players = (ArrayList<BasketballPlayer>)it.next() ;
					for(int x = 0;x<players.size();x++)
					{
						System.out.println(players.get(x)) ;
			}
		}
	}

	public static void main(String args[])
	{
		BasketballDriver app = new BasketballDriver() ;

	}

}