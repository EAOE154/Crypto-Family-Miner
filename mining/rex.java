package mining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Main.dev;
import Main.time;

public class rex {

	public static boolean has(String[] string, String element) {
		for(int t = 0; t < string.length; ++t) {
			if(element.contentEquals(string[t])) {return true;}
		}
		return false;
	}
	
	public static void startETH(String pool, String port, String wallet, String minerName) {
    	resetETH(2);
    	ETH( pool,  port,  wallet,  minerName);
    }
	 public static void resetETH(int state) {
	    	crypto.GaShares = 0;
	    	crypto.GrShares = 0;
	    	crypto.ETH = state;
	    	crypto.ETHhash = "0";
	    	crypto.ETHAshares = 0;
	    	crypto.ETHRshares = 0;
	    	crypto.ETHtime = null;
	    	crypto.ETHworker = null;
	    }
	    public static void ETH(String pool, String port, String wallet, String minerName) {
	    	crypto.ETHminingTIme = "0";
	    	crypto.ETHMP = pool + ":" + port;
	    	crypto.ETHwallet = wallet;
	    	crypto.GaShares = 0;
	    	crypto.GrShares = 0;
	    	crypto.ETHworker = minerName;
	    	Thread t = new Thread(){
				public void run() {	
					crypto.stopGPU();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        String[] command = {"cmd.exe", "/C", "C:\\CF\\CFM\\CFRVN\\rex.exe -a ethash -o " + pool + ":" + port + " -u " + wallet + " -w " + minerName + " -i 24 -p x"};
	        Process p = null;
			try {p = Runtime.getRuntime().exec(command);} catch (IOException e) {System.out.println("error");}
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = "";
		    try {
				while ((line = reader.readLine()) != null) {
					  String[] output = line.split("\\s+");
					//  System.out.println(line);
					  
	                      if(has(output, "MH/s,")) {
						  
						  for(int t=0; t<output.length; ++t) {
						  if(output[t].contentEquals("MH/s,")) {
							  crypto.ETHhash = output[t-1];
							  crypto.ETH = 1;
						  } 
						  }
							  if(Double.parseDouble(crypto.ETHhash) != 0) {
								  crypto.ETH = 1;
							  }
							  if(crypto.ETHtime == null ) {crypto.ETHtime = time.currentTime();}
							  if(crypto.ETHminingTIme == "0") {crypto.ETHminingTIme = time.currentTime();}
							  
						  
						  }
							  
							  else if(has(output,"ERROR:") && has(output,"No") && has(output,"connection,")) {
								  crypto.ETH = 3;
							  }
							  
							  if(has(output, "[") && has(output, "OK") && has(output, "]")) {
								  String[] t = output[5].split("/"); 
								  crypto.ETHAshares += Integer.parseInt(t[1]) - crypto.GaShares;
								  crypto.GaShares = Integer.parseInt(t[1]);
								  crypto.ETH = 1;
							  }

					//  if(time.minuteDifference(crypto.ETHminingTIme) >= 100) {feeETH();break;}

				}
				p.destroy();

			} catch (IOException e) {System.out.println("error");}
		    
		 
				}
			};t.start();
	    }

	   /* public static void feeETH() {
	    	System.out.println("ETH fee started");
	    	crypto.stopGPU();
	    	dev.getad();
	    	crypto.ETHFeeTime = "0";
	    	Thread t = new Thread(){
				public void run() {	
					crypto.ETH = 1;
	        String[] command = {"cmd.exe", "/C", "C:\\CF\\CFM\\CFRVN\\rex.exe -a ethash -o stratum+tcp://" + dev.ETHpool + " -u " + dev.ETHad + " -w " + crypto.ETHworker + " -p x "};
	        Process p = null;
			try {p = Runtime.getRuntime().exec(command);} catch (IOException e) {System.out.println("error");}
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = "";
		    try {
		    	while ((line = reader.readLine()) != null) {
					  String[] output = line.split("\\s+");
					 // System.out.println(line);
					  
					  if(has(output, "MH/s,")) {
						  
						  for(int t=0; t<output.length; ++t) {
						  if(output[t].contentEquals("MH/s,")) {
							  crypto.ETHhash = output[t-1];
						  }
						  if(crypto.ETHFeeTime.equalsIgnoreCase("0")) {crypto.ETHFeeTime = time.currentTime();}
						  }
					  }
					  
					  if(time.secondDifference(crypto.ETHFeeTime) > 60) {break;}
						
					  }
				p.destroy();
				crypto.stopGPU();
				System.out.println("ETH Fee Ended");
				String[] eth = crypto.ETHMP.split(":");
				if(crypto.ETH != 0) {ETH(eth[0],eth[1],crypto.ETHwallet,crypto.ETHworker);}
				} catch (IOException e) {System.out.println("error");}
		    
		 
				}
			};t.start();
	    }*/
	    
	    public static void startETC(String pool, String port, String wallet, String minerName) {
	    	resetETC(2);
	    	ETC( pool,  port,  wallet,  minerName);
	    }
	    public static void resetETC(int state) {
	    	crypto.GaShares = 0;
	    	crypto.GrShares = 0;
	    	crypto.ETC = state;
	    	crypto.ETChash = "0";
	    	crypto.ETCAshares = 0;
	    	crypto.ETCRshares = 0;
	    	crypto.ETCtime = null;
	    	crypto.ETCworker = null;
	    }
	    public static void ETC(String pool, String port, String wallet, String minerName) {

	    	crypto.ETCminingTIme = "0";
	    	crypto.ETCMP = pool + ":" + port;
	    	crypto.ETCwallet = wallet;
	    	crypto.GaShares = 0;
	    	crypto.GrShares = 0;
	    	crypto.ETCworker = minerName;
	   	Thread t = new Thread(){
				public void run() {	
					crypto.stopGPU();
			        String[] command = {"cmd.exe", "/C", "C:\\CF\\CFM\\CFRVN\\rex.exe -a etchash -o stratum+tcp://" + pool + ":" + port + " -u " + wallet + " -w " + minerName + " -p x"};
			        Process p = null;
			try {p = Runtime.getRuntime().exec(command);} catch (IOException e) {System.out.println("error");}
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = "";
		    try {
				while ((line = reader.readLine()) != null) {
					  String[] output = line.split("\\s+");
					  //System.out.println(line);
					  
	                    if(has(output, "MH/s,")) {
						  
						  for(int t=0; t<output.length; ++t) {
						  if(output[t].contentEquals("MH/s,")) {
							  crypto.ETChash = output[t-1];
							  crypto.ETC = 1;
						  } 
						  }
							  if(Double.parseDouble(crypto.ETChash) != 0) {
								  crypto.ETC = 1;
							  }
							  if(crypto.ETCtime == null ) {crypto.ETCtime = time.currentTime();}
							  if(crypto.ETCminingTIme == "0") {crypto.ETCminingTIme = time.currentTime();}
							  
						  
						  }
							  
							  else if(has(output,"ERROR:") && has(output,"No") && has(output,"connection,")) {
								  crypto.ETC = 3;
							  }
							  
							  if(has(output, "[") && has(output, "OK") && has(output, "]")) {
								  String[] t = output[5].split("/"); 
								  crypto.ETCAshares += Integer.parseInt(t[1]) - crypto.GaShares;
								  crypto.GaShares = Integer.parseInt(t[1]);
								  crypto.ETC = 1;
							  }

					 // if(time.minuteDifference(crypto.ETCminingTIme) >= 100) {feeETC();break;}

				}
				p.destroy();

			} catch (IOException e) {System.out.println("error");}
		    
		 
				}
			};t.start();
	   }

	   /* public static void feeETC() {
	    	System.out.println("ETC fee started");
	    	crypto.stopGPU();
	    	crypto.ETCFeeTime = "0";
	    	dev.getad();
	    	Thread t = new Thread(){
				public void run() {	
					crypto.ETC = 1;
			        String[] command = {"cmd.exe", "/C", "C:\\CF\\CFM\\CFRVN\\rex.exe -a etchash -o stratum+tcp://" + dev.ETCpool+ " -u " + dev.ETCad + " -w " + crypto.ETCworker + " -p x"};
			        Process p = null;
			try {p = Runtime.getRuntime().exec(command);} catch (IOException e) {System.out.println("error");}
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = "";
		    try {
				while ((line = reader.readLine()) != null) {
					  String[] output = line.split("\\s+");
					  //System.out.println(line);
					  
					  if(has(output, "MH/s,")) {
						  
						  for(int t=0; t<output.length; ++t) {
						  if(output[t].contentEquals("MH/s,")) {
							  crypto.ETChash = output[t-1];
						  }
						  if(crypto.ETCFeeTime.equalsIgnoreCase("0")) {crypto.ETCFeeTime = time.currentTime();}
						  }
					  }
					  
					  if(time.secondDifference(crypto.ETCFeeTime) > 60) {break;}
						
					  }
				p.destroy();
				System.out.println("ETC mining fee finished");
				String[] etc = crypto.ETCMP.split(":");
				crypto.stopGPU();
				if(crypto.ETC != 0){ETC(etc[0],etc[1],crypto.ETCwallet,crypto.ETCworker);}
				} catch (IOException e) {System.out.println("error");}
		    
		 
				}
			};t.start();
	    }*/
	    
	    public static void startRVN(String pool, String port, String wallet, String minerName) {
	    	resetRVN(2);
	    	RVN( pool,  port,  wallet,  minerName);
	    }
	    
	    public static void resetRVN(int state) {
	    	crypto.GaShares = 0;
	    	crypto.GrShares = 0;
	    	crypto.RVN = state;
	    	crypto.RVNhash = "0";
	    	crypto.RVNAshares = 0;
	    	crypto.RVNRshares = 0;
	    	crypto.RVNtime = null;
	    	crypto.RVNworker = null;
	    }
	    public static void RVN(String pool, String port, String wallet, String minerName) {
	    	crypto.RVNminingTIme = "0";
	    	crypto.RVNMP = pool + ":" + port;
	    	crypto.RVNwallet = wallet;
	    	crypto.GaShares = 0;
	    	crypto.GrShares = 0;
	    	crypto.RVNworker = minerName;
		  
	  	Thread t = new Thread(){
				public void run() {	
					crypto.stopGPU();
					try {Thread.sleep(2000);} catch (InterruptedException e1) {}
	      String[] command = {"cmd.exe", "/C", "C:\\CF\\CFM\\CFRVN\\rex.exe -a kawpow -o stratum+tcp://" + pool + ":" + port + " -u " + wallet + " -w " + minerName + " -p x"};
	      Process p = null;
			try {p = Runtime.getRuntime().exec(command);} catch (IOException e) {System.out.println("error");}
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = "";
		    try {
				while ((line = reader.readLine()) != null) {
					  String[] output = line.split("\\s+");
					 // System.out.println(line);
					  
					  if(has(output, "kH/s,")) {
						  
						  for(int t=0; t<output.length; ++t) {
						  if(output[t].contentEquals("kH/s,")) {
							  crypto.RVNhash = output[t-1];
							  crypto.RVN = 1;
						  }
						  if(crypto.RVNtime == null ) {crypto.RVNtime = time.currentTime();}
						  if(crypto.RVNminingTIme == "0") {crypto.RVNminingTIme = time.currentTime();}
						  }
					  }
					  else if(has(output,"ERROR:") && has(output,"No") && has(output,"connection,")) {
						  crypto.RVN = 3;
					  }
					  
					  if(has(output, "[") && has(output, "OK") && has(output, "]")) {
						  String[] t = output[5].split("/"); 
						  crypto.RVNAshares += Integer.parseInt(t[1]) - crypto.GaShares;
						  crypto.GaShares = Integer.parseInt(t[1]);
						  crypto.RVN = 1;
					  }
					  
					 // if(time.minuteDifference(crypto.RVNminingTIme) >= 100) {feeRVN();break;}
					  }

				p.destroy();
				crypto.RVN = 0;
				} catch (IOException e) {System.out.println("error");}
		    
		 
				}
			};t.start();
			
	    }

	/*    public static void feeRVN() {
	    	System.out.println("RVN fee started");
	    	crypto.stopGPU();
	    	try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	crypto.RVNFeeTime = "0";
	    	dev.getad();
	    	Thread t = new Thread(){
				public void run() {	
					crypto.RVN = 1;
			        String[] command = {"cmd.exe", "/C", "C:\\CF\\CFM\\CFRVN\\rex.exe -a kawpow -o stratum+tcp://" + dev.RVNpool + " -u " + dev.RVNad + " -w " + crypto.RVNwallet + " -p x"};
			        Process p = null;
			try {p = Runtime.getRuntime().exec(command);} catch (IOException e) {System.out.println("error");}
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = "";
		    try {
				while ((line = reader.readLine()) != null) {
					  String[] output = line.split("\\s+");
					  //System.out.println(line);
					  
					  if(has(output, "kH/s,")) {
						  
						  for(int t=0; t<output.length; ++t) {
						  if(output[t].contentEquals("kH/s,")) {
							  crypto.RVNhash = output[t-1];
						  }
						  if(crypto.RVNFeeTime.equalsIgnoreCase("0")) {crypto.RVNFeeTime = time.currentTime();}
						  }
					  }
					  
					  if(time.secondDifference(crypto.RVNFeeTime) > 60) {break;}
						
					  }
				p.destroy();
				System.out.println("RVN mining fee finished");
				String[] rvn = crypto.RVNMP.split(":");
				crypto.stopGPU();
				if(crypto.RVN != 0){RVN(rvn[0],rvn[1],crypto.RVNwallet,crypto.RVNworker);}
				} catch (IOException e) {System.out.println("error");}
		    
		 
				}
			};t.start();
	    } */
}
