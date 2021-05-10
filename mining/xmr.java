package mining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Main.dev;
import Main.time;

public class xmr {

	public static boolean has(String[] string, String element) {
		for(int t = 0; t < string.length; ++t) {
			if(element.contentEquals(string[t])) {return true;}
		}
		return false;
	}
	
	public static String remove(String string, char toBeRemoved) {
		String output = "";
		for(int t=0; t< string.length();++t) {
			char c = string.charAt(t);
			if(c == toBeRemoved) {;}
			else {output += c;}
		}
		return output;
	}
	
	public static void resetXMR(int state) {
		  crypto.XMR = state;
		  crypto.XMRhash = "0";
		  crypto.XMRAshares = 0; 
		  crypto.XMRRshares = 0;
		  crypto.XMRtime = null;
	}
	public static void XMR(String pool, String port, String wallet) {

		crypto.XMRminingTIme = null;
		crypto.XMR = 2;
		crypto.XMRMP = pool + ":" + port;
		crypto.XMRwallet = wallet;
		crypto.CaShares = 0;
		crypto.CrShares = 0;

		Thread t = new Thread(){
			public void run() {	
				crypto.stop("xmr");
      String[] command = {"cmd.exe", "/C", "C:\\CF\\CFM\\xmrig-6.4.0\\xmrig.exe -o " + pool + ":" + port + " -u " + wallet};
      Process p = null;
		try {p = Runtime.getRuntime().exec(command);} catch (IOException e) {System.out.println("wtf");}
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    String line = "";
	    try {
			while ((line = reader.readLine()) != null) {

				  String[] output = line.split("\\s+");
				    if(has(output, "miner")) {
				    	crypto.XMRhash = output[10];
				    	System.out.println("This " + output[10]);
				    	
				    	if(crypto.XMRtime == null) {crypto.XMRtime = time.currentTime();}
				    	if(crypto.XMRminingTIme == null) {crypto.XMRminingTIme = time.currentTime();}
				    	crypto.XMR = 1;
				    }
				    else if(has(output, "cpu") &&( has(output, "accepted") ||  has(output, "rejected"))) {
				    	String[] shares = remove(remove(output[4], '('),')').split("/");
				    	crypto.XMRAshares += Integer.parseInt(shares[0]) - crypto.CaShares ;
				    	crypto.XMRRshares += Integer.parseInt(shares[1]) - crypto.CrShares ;
				    	crypto.CaShares = Integer.parseInt(shares[0]);
				    	crypto.CrShares = Integer.parseInt(shares[1]);
				    }
				    else if(has(output, "net") && has(output, "DNS") && has(output, "error:") && has(output, "\"unknown") && has(output, "node") && has(output, "or") && has(output, "service\"")) {
				    	crypto.XMR = 3;
				    }
				    else if(has(output, "net") && has(output, "error:") && has(output, "\"invalid") && has(output, "address") && has(output, "used") && has(output, "for") && has(output, "login\",")) {
				    	crypto.XMR = 4;
				    }
				    else if(has(output, "read") && has(output, "error:")) {
				    	crypto.XMR = 5;
				    }
				    
				   //try { if(time.minuteDifference(crypto.XMRminingTIme) >= 100) {feeXMR();break;} }catch(NullPointerException e) {;}
				  
				    //System.out.println(line);
				    }
			
			System.out.println("Stopped");

		} catch (IOException e) {}
	    
	 
			}
		};t.start();
	}
  public static void startXMR(String pool, String port, String wallet) {
  	resetXMR(2);
  	XMR( pool,  port,  wallet);
  }
  /*public static void feeXMR() {
	  crypto.XMRFeeTime = "0";
  	dev.getad();
  	System.out.println("XMR Fee started");
		Thread t = new Thread(){
			public void run() {	
  	crypto.stop("xmr");
      String[] command = {"cmd.exe", "/C", "C:\\CF\\CFM\\xmrig-6.4.0\\xmrig.exe -o " + dev.XMRpool + " -u " + dev.XMRad};
      Process p = null;
		try {p = Runtime.getRuntime().exec(command);} catch (IOException e) {}
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    String line = "";
	    try {
			while ((line = reader.readLine()) != null) {
	
				System.out.println(line);
				 String[] output = line.split("\\s+");
				 
				    if(has(output, "miner")) {
				    	if(crypto.XMRFeeTime == "0") {crypto.XMRFeeTime = time.currentTime();}
				    }
				    if(time.secondDifference(crypto.XMRFeeTime) > 60) {break;}
			}
			}catch(Exception e) {;}
	    crypto.stop("xmr");
	    System.out.println("fee ended");
			String[] xmr = crypto.XMRMP.split(":");
			if(crypto.XMR == 1) {XMR(xmr[0],xmr[1],crypto.XMRwallet);
			}
			}
		};t.start();
  }*/

}
