

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class QueueProgram
{

    public static void main(String[] args)
    {
        LinkedList<Word> que = new LinkedList<>();
        PriorityQueue<Word> pQue = new PriorityQueue<>() ;
        File name = new File("speech.txt");
        try
        {
            BufferedReader input = new BufferedReader(new FileReader(name));
            String line, text = "";
            while ((text = input.readLine()) != null)
            {
                String[] words = text.split(" ") ;
                for(int x =0;x<words.length;x++)
                {
                    Word word = new Word(words[x]) ;
                    que.add(word) ;
                    pQue.add(word) ;
                }
            }

        } catch (IOException io)
        {
            System.err.println("File error");
        }

        while(que.peek() != null)
        {
            Word word = (Word)que.remove() ;
            System.out.println(word.toString()) ;
        }
        System.out.println() ;
        System.out.println() ;

        while(pQue.peek() != null)
        {
            Word word = (Word)pQue.remove() ;
            System.out.println(word.toString());
        }
    }

}
