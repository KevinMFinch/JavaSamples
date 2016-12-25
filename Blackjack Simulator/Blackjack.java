import java.util.*;

public class Blackjack{
	ArrayList<Integer> numList = new ArrayList<Integer>();
	ArrayList<ArrayList<Integer>> simList = new ArrayList<ArrayList<Integer>>();
	int bustCount = 0;


	public int softSum(ArrayList<Integer> hand){
		if(hand.isEmpty() == false){
			ArrayList<Integer> convertedHand = new ArrayList<Integer>();
			convertedHand = convertHand(hand);
			int sum =0;
			for(int x=0;x<convertedHand.size();x++){
				sum += convertedHand.get(x);
			}
			return sum;
		}
		else{
			return 0;
		}
	}

	public ArrayList<Integer> convertHand(ArrayList<Integer> hand){
		ArrayList<Integer> newHand = new ArrayList<Integer>();

		for(int x=0;x<hand.size();x++){
			newHand.add(hand.get(x));
		}
		for(int x=0;x<newHand.size();x++){
			for(int y=x+1;y<newHand.size();y++){
				if(newHand.get(x) == newHand.get(y)){
					newHand.set(y,-1);
				}
			}
		}
		
		for(int x =0;x<newHand.size();x++){
			if(newHand.get(x) >= 0){
				newHand.set(x,newHand.get(x) % 13);
				if(newHand.get(x) > 10){
					newHand.set(x,10);
				}
				if(newHand.get(x) == 0){
					newHand.set(x,10);
				}
				if(newHand.get(x) == 1){
					newHand.set(x,11);
				}
			}
			else{
				newHand.set(x,0);
			}

		}
		return newHand;
	}

	public ArrayList<String> symbolHand(ArrayList<Integer> hand){
		ArrayList<String> symbolHand = new ArrayList<String>();
		ArrayList<Integer> newHand = new ArrayList<Integer>();
		for(int x=0;x<hand.size();x++){
			newHand.add(hand.get(x));
		}
		for(int x=0;x<newHand.size();x++){
			for(int y=x+1;y<newHand.size();y++){
				if(newHand.get(x) == newHand.get(y)){
					newHand.set(y,-1);
				}
			}
		}
		for(int x =0;x<newHand.size();x++){
			if(newHand.get(x) >= 0){
				newHand.set(x,newHand.get(x) % 13);
				if(newHand.get(x) == 11){
					symbolHand.add("J");
				}
				else if(newHand.get(x) == 12){
					symbolHand.add("Q");
				}
				else if(newHand.get(x) == 0){
					symbolHand.add("K");
				}
				else if(newHand.get(x) == 1){
					symbolHand.add("A");
				}
				else{
					symbolHand.add(newHand.get(x).toString());
				}
			}
			else{
				symbolHand.add("X");
			}

		}
		return symbolHand;
	}

	public void runSim(int count){
		ArrayList<Integer> simulation = new ArrayList<Integer>();
		while(softSum(simulation) < 17){
			simulation.add(numList.remove(0));
		}
		String winSt ="";
		if(softSum(simulation) <= 21){
			winSt+="No bust";
		}
		else{
			winSt+="Bust";
			bustCount++;
		}
		System.out.println("Simulation "+count+ ": "+simulation+" "+
			symbolHand(simulation)+" "+softSum(simulation)+" "+winSt);
	}



	public Blackjack(){
		for(int x=0;x<10000000;x++){
			numList.add((int)(Math.random()*52)+1);
		}
		
		for(int x =0;x<10000;x++){
			runSim(x+1);
		}
		System.out.println("The dealer busted "+bustCount+" times.");
		
	}

	public static void main(String args[]){
		Blackjack app = new Blackjack();
	}
}