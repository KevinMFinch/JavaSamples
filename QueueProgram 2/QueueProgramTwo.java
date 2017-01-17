

import java.io.*;

import java.util.*;

public class QueueProgramTwo
{

    public static void main(String[] args)
    {
        LinkedList<Passenger> que = new LinkedList<>();
        PriorityQueue<Passenger> pq = new PriorityQueue<>();
        File name = new File("PassengerInfo.txt");
        try
        {
            BufferedReader input = new BufferedReader(new FileReader(name));
            String text = "";
            int x = 1;
            String[] names;
            String fN = "";
            String lN = "";
            String dest = "";
            int time = 0;
            while ((text = input.readLine()) != null)
            {
                if (x == 3)
                {
                    String[] timeSplit = text.split(" ");
                    switch (timeSplit[1])
                    {
                        case "AM":
                        {
                            String[] temp = timeSplit[0].split(":");
                            String combine = temp[0] + temp[1];
                            time = Integer.parseInt(combine);
                            break;
                        }
                        case "PM":
                        {
                            String[] temp = timeSplit[0].split(":");
                            String combine = temp[0] + temp[1];
                            int tempInt;
                            if (temp[0].equals("12"))
                            {
                                tempInt = Integer.parseInt(combine);
                            } else
                            {
                                tempInt = Integer.parseInt(combine);
                                tempInt += 1200;
                            }
                            time = tempInt;
                            break;
                        }
                    }
                    Passenger ps = new Passenger(fN, lN, dest, text, time);
                    que.add(ps);
                }
                if (x == 2)
                {
                    dest = text;
                }
                if (x == 1)
                {
                    names = text.split(" ");
                    fN = names[0];
                    lN = names[1];
                }
                if (x < 3)
                {
                    x++;
                } else
                {
                    x = 1;
                }
            }
        } catch (IOException io)
        {
            System.err.println("File error");
        }

        while (que.peek() != null)
        {
            Passenger ps = (Passenger) que.remove();
            if(ps.etdHour() ==0)
            {
                pq.add(ps);
            }
            System.out.println(ps.toString());
        }
        System.out.println();
        while (pq.peek() != null)
        {

            Passenger ps = (Passenger) pq.remove();
            System.out.println(ps.toString());
        }
    }
}
