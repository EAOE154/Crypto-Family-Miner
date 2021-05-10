package Main;

import java.text.DecimalFormat;

public class NF {
	
	private static boolean contains(String string, char character) {
		for(int t=0; t<string.length();++t) {
			char c = string.charAt(t);
			if(c == character) {return true;}
			}
		return false;
	}
	
public static String toFTdecimals(String amount) {

	int position = amount.length();
	for(int t=0; t < amount.length(); ++t) {
		char c = '.';
		char f = amount.charAt(t);
		if(f == c) {position = t; break;}	
	}
	
	String body = "";
	String decimals = "";
	
	for(int t= 0; t < position; ++t) {
		body += amount.charAt(t);
	}
	for(int t = position + 1; t < position + 11; ++t) {
		try{decimals += amount.charAt(t);}catch(StringIndexOutOfBoundsException e) {decimals += 0;}
	}
	
	String finalOutput = body;
	int f = 0;
	while(f < decimals.length()) {
		if(decimals.charAt(decimals.length() - 1 -f) == '0') {++f;}
		else {break;}
	}
	
	for (int t = 0; t < decimals.length() - f; ++t) {
		if(t == 0) {finalOutput += ".";}
		finalOutput += decimals.charAt(t);
	}
	return finalOutput;
}	
public static String format(String number) {

	if(!contains(number+"", '.')) {return number + "";}
	System.out.println("ok");
	DecimalFormat df = new DecimalFormat("0.00000000");
	String text = df.format(Double.parseDouble(number));
	String zero = "0";
	String hold = "";
	String output = "";
	int length = text.length();
//	System.out.println(length);
	
	char e, e1, e2, e3, e4, e5, e6, e7, e8, e9;
	if(length >= 1) { e = text.charAt(0);} else {e = zero.charAt(0);}
	if(length >= 2) { e1 = text.charAt(1); }else {e1 = zero.charAt(0);}
	if(length >= 3) { e2 = text.charAt(2);} else {e2 = zero.charAt(0);}
	if(length >= 4) { e3 = text.charAt(3);} else {e3 = zero.charAt(0);}
	if(length >= 5) { e4 = text.charAt(4);} else {e4 = zero.charAt(0);}
	if(length >= 6) { e5 = text.charAt(5);} else {e5 = zero.charAt(0);}
	if(length >= 7) { e6 = text.charAt(6);} else {e6 = zero.charAt(0);}
	if(length >= 8) { e7 = text.charAt(7);} else {e7 = zero.charAt(0);}
	if(length >= 9) { e8 = text.charAt(8);} else {e8 = zero.charAt(0);}
	if(length >= 10) { e9 = text.charAt(9);} else {e9 = zero.charAt(0);}
	
	if(Character.toString(e9).equalsIgnoreCase("0")) {
		if(Character.toString(e8).equalsIgnoreCase("0")) {
			if(Character.toString(e7).equalsIgnoreCase("0")) {
				if(Character.toString(e6).equalsIgnoreCase("0")) {
					if(Character.toString(e5).equalsIgnoreCase("0")) {
						if(Character.toString(e4).equalsIgnoreCase("0")) {
							if(Character.toString(e3).equalsIgnoreCase("0")) {
								if(Character.toString(e2).equalsIgnoreCase("0")) {
									hold = Character.toString(e)  + Character.toString(e1);
								}else {hold = Character.toString(e) + Character.toString(e1) + Character.toString(e2);}
							}else {hold = Character.toString(e) + Character.toString(e1) + Character.toString(e2) + Character.toString(e3);}
						}else {hold = Character.toString(e) + Character.toString(e1) + Character.toString(e2) + Character.toString(e3) + Character.toString(e4);}
					}else {hold = Character.toString(e) + Character.toString(e1) + Character.toString(e2) + Character.toString(e3) + Character.toString(e4) + Character.toString(e5);}
				}else {hold = Character.toString(e) + Character.toString(e1) + Character.toString(e2) + Character.toString(e3) + Character.toString(e4) + Character.toString(e5) + Character.toString(e6) ;}
			}else {hold = Character.toString(e) + Character.toString(e1) + Character.toString(e2) + Character.toString(e3) + Character.toString(e4) + Character.toString(e5) + Character.toString(e6) + Character.toString(e7)  ;}
		}else {hold = Character.toString(e) + Character.toString(e1) + Character.toString(e2) + Character.toString(e3) + Character.toString(e4) + Character.toString(e5) + Character.toString(e6) + Character.toString(e7) + Character.toString(e8);}
	}else {hold = Character.toString(e) + Character.toString(e1) + Character.toString(e2) + Character.toString(e3) + Character.toString(e4) + Character.toString(e5) + Character.toString(e6) + Character.toString(e7) + Character.toString(e8) + Character.toString(e9);}
		
	char p = hold.charAt(hold.length()-1);
	if(p == '.') {
	 for(int t1 = 0 ; t1 < hold.length()-1; ++t1) {
		 char m = hold.charAt(t1);
		 output += m;
	 }
	}
	else {output = hold;}
		
	return output;
}

}
