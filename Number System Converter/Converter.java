public class Converter{
	
	public Converter(){
		System.out.println(hexToBin("F"));
	}

	public String binToDec(String binString){
		String arr[] = binString.split("");
		int power=0;
		int decNum=0;
		for(int x=arr.length-1;x>=0;x--){
			decNum+= (int)Math.pow(2,power) * Integer.parseInt(arr[x]);
			power++;
		}
		return Integer.toString(decNum);
	}

	public String binToHex(String binString){
		return decToHex(binToDec(binString));
	}

	public String hexToBin(String hexString){
		return decToBin(hexToDec(hexString));
	}

	public String hexToDec(String hexString){
		String arr[] = hexString.split("");
		for(int i=0;i<arr.length;i++){
			arr[i] = mapNumber(arr[i]);
		}
		int power = 0;
		int decNum = 0;
		for(int x=arr.length-1;x>=0;x--){
			decNum+= (int)Math.pow(16,power) * Integer.parseInt(arr[x]);
			power++;
		}
		return Integer.toString(decNum);
	}

	public String mapNumber(String letter){
		switch(letter) {
			case "A": return Integer.toString(10);
			case "B": return Integer.toString(11);
			case "C": return Integer.toString(12);
			case "D": return Integer.toString(13);
			case "E": return Integer.toString(14);
			case "F": return Integer.toString(15);
		}
		return letter;
	}

	public int highestPower(int num, int base){
		int power = 0;
		int prev = 0;
		while((int)Math.pow(base,power) < num){
			prev = power;
			power++;
		}
		return (int)Math.pow(base,prev);	
	}

	public String mapLetter(int numTimes){
		switch(numTimes) {
			case 10: return "A";
			case 11: return "B";
			case 12: return "C";
			case 13: return "D";
			case 14: return "E";
			case 15: return "F";
		}
		return Integer.toString(numTimes);
	}

	public String decToHex(String str){
		int num = Integer.parseInt(str);
		int powSixteen = highestPower(num,16);
		String hexString = "";
		while(powSixteen > 0){
			if(num >= powSixteen){
				int numTimesAppears = num / powSixteen ;
				hexString+= mapLetter(numTimesAppears);
				num -= numTimesAppears * powSixteen;
				powSixteen /= 16;
			}
			else{
				hexString+="0";
				powSixteen /=16;
			}
		}
		return hexString;
	}

	public String decToBin(String str){
		int num = Integer.parseInt(str);
		int powTwo = highestPower(num,2);
		String binString = "";
		while(powTwo > 0){
			if(num >= powTwo){
				binString+="1";
				num -= powTwo;
				powTwo /= 2;
			}
			else{
				binString+="0";
				powTwo /=2;
			}
		}
		return binString;
	}

	public static void main(String args[]){
		Converter app = new Converter();
	}
}