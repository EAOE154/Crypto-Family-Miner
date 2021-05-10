package mining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Main.dev;
import Main.time;

public class phx {

	public static String config = "\r\n"
			+ "setx GPU_FORCE_64BIT_PTR 0\r\n"
			+ "setx GPU_MAX_HEAP_SIZE 100\r\n"
			+ "setx GPU_USE_SYNC_OBJECTS 1\r\n"
			+ "setx GPU_MAX_ALLOC_PERCENT 100\r\n"
			+ "setx GPU_SINGLE_ALLOC_PERCENT 100\r\n";
	
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
					try {Thread.sleep(2000);} catch (InterruptedException e1) {}
					////////////////////////////////////////C:\CF\CFM\PhoenixMiner_5.4c
			        String[] command = {"cmd.exe", "/C", " C:\\CF\\CFM\\PhoenixMiner_5.4c\\phx.exe -pool  " + pool + ":" + port + " -wal " + wallet + " -worker " + minerName + " -dagrestart 1 -rvram -1 -eres 0 -acm -mi 14 -log 0"};
			        String[] command1 = {"cmd.exe", "/C", "setx GPU_FORCE_64BIT_PTR 0"};
			        String[] command2 = {"cmd.exe", "/C", "setx GPU_MAX_HEAP_SIZE 100"};
			        String[] command3 = {"cmd.exe", "/C", "setx GPU_USE_SYNC_OBJECTS 1"};
			        String[] command4 = {"cmd.exe", "/C", "setx GPU_MAX_ALLOC_PERCENT 100"};
			        String[] command5 = {"cmd.exe", "/C", "setx GPU_SINGLE_ALLOC_PERCENT 100"};
			        
	        Process p = null;
			try {p = Runtime.getRuntime().exec(command1);} catch (IOException e) {System.out.println("error");}
			try {p = Runtime.getRuntime().exec(command2);} catch (IOException e) {System.out.println("error");}
			try {p = Runtime.getRuntime().exec(command3);} catch (IOException e) {System.out.println("error");}
			try {p = Runtime.getRuntime().exec(command4);} catch (IOException e) {System.out.println("error");}
			try {p = Runtime.getRuntime().exec(command5);} catch (IOException e) {System.out.println("error");}
			try {p = Runtime.getRuntime().exec(command);} catch (IOException e) {System.out.println("error");}
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = "";
		    try {
				while ((line = reader.readLine()) != null) {
					  String[] output = line.split("\\s+");
					  System.out.println(line);
					  
					  if(has(output, "Eth") && has(output, "speed:")) {
						  String[] shares = output[5].split("/");
						  if(Double.parseDouble(output[2]) > 0) {crypto.ETHhash = output[2];}
						  crypto.ETHAshares += Integer.parseInt(shares[0]) - crypto.GaShares;
						  crypto.ETHRshares += Integer.parseInt(shares[1]) - crypto.GrShares;
						  
						  crypto.GaShares = Integer.parseInt(shares[0]);
						  crypto.GrShares = Integer.parseInt(shares[1]);
						  
						  if(Double.parseDouble(crypto.ETHhash) != 0) {
							  crypto.ETH = 1;
						  if(crypto.ETHtime == null ) {crypto.ETHtime = time.currentTime();}
						  if(crypto.ETHminingTIme == "0") {crypto.ETHminingTIme = time.currentTime();}
						  }
						  
					  }
					  else if(has(output,"Eth:") && has(output,"Can't") && has(output,"resolve") && has(output,"host")) {
						  crypto.ETH = 3;
					  }
					  else if(line.equalsIgnoreCase("Pool login failed: Invalid user provided")) {
						  crypto.ETH = 4;
					  }
					  else if(has(output, "out") && has(output, "of") && has(output, "memory")) {
						  crypto.ETH = 5;
					  }
					  else {
						  for(int t = 1; t<21;++t) {
							  if(has(output,"GPU"+t)) {crypto.GPUtemp[t] = Double.parseDouble(output[1]); crypto.GPUpower[t] = Double.parseDouble(output[2]);}
						  }
					  }
					 // if(time.minuteDifference(crypto.ETHminingTIme) >= 100) {feeETH();break;}

				}

			} catch (IOException e) {System.out.println("error");}
		    
		 
				}
			};t.start();
	    }
	    /*public static void feeETH() {
	    	System.out.println("ETH fee started");
	    	crypto.stopGPU();
			try {Thread.sleep(2000);} catch (InterruptedException e1) {}
	    	dev.getad();
	    	crypto.ETHFeeTime = "0";
	    	Thread t = new Thread(){
				public void run() {	
					crypto.ETH = 1;
			        String[] command = {"cmd.exe", "/C", "C:\\CF\\CFM\\PhoenixMiner_5.4c\\phx.exe -pool  " + dev.ETHpool + " -wal " + dev.ETHad + " -worker " + crypto.ETHworker + " -dagrestart 1 -rvram -1 -eres 0 -acm  -mi 14 -log 0"};
			        String[] command1 = {"cmd.exe", "/C", "setx GPU_FORCE_64BIT_PTR 0"};
			        String[] command2 = {"cmd.exe", "/C", "setx GPU_MAX_HEAP_SIZE 100"};
			        String[] command3 = {"cmd.exe", "/C", "setx GPU_USE_SYNC_OBJECTS 1"};
			        String[] command4 = {"cmd.exe", "/C", "setx GPU_MAX_ALLOC_PERCENT 100"};
			        String[] command5 = {"cmd.exe", "/C", "setx GPU_SINGLE_ALLOC_PERCENT 100"};
			        
			         Process p = null;
						try {p = Runtime.getRuntime().exec(command1);} catch (IOException e) {System.out.println("error");}
						try {p = Runtime.getRuntime().exec(command2);} catch (IOException e) {System.out.println("error");}
						try {p = Runtime.getRuntime().exec(command3);} catch (IOException e) {System.out.println("error");}
						try {p = Runtime.getRuntime().exec(command4);} catch (IOException e) {System.out.println("error");}
						try {p = Runtime.getRuntime().exec(command5);} catch (IOException e) {System.out.println("error");}
						try {p = Runtime.getRuntime().exec(command);} catch (IOException e) {System.out.println("error");}
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = "";
		    try {
		    	while ((line = reader.readLine()) != null) {
					  String[] output = line.split("\\s+");
					  System.out.println(line);
					  
					  if(has(output, "Eth") && has(output, "speed:")) {
						  if(Double.parseDouble(output[2]) != 0) {if(crypto.ETHFeeTime == "0") {crypto.ETHFeeTime = time.currentTime();}}  
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
					try {Thread.sleep(2000);} catch (InterruptedException e1) {}
			        String[] command = {"cmd.exe", "/C", "C:\\CF\\CFM\\PhoenixMiner_5.4c\\phx.exe -pool  " + pool + ":" + port + " -wal " + wallet + " -worker " + minerName + " -dagrestart 1 -rvram -1 -eres 0 -acm  -mi 14 -log 0"};
			        String[] command1 = {"cmd.exe", "/C", "setx GPU_FORCE_64BIT_PTR 0"};
			        String[] command2 = {"cmd.exe", "/C", "setx GPU_MAX_HEAP_SIZE 100"};
			        String[] command3 = {"cmd.exe", "/C", "setx GPU_USE_SYNC_OBJECTS 1"};
			        String[] command4 = {"cmd.exe", "/C", "setx GPU_MAX_ALLOC_PERCENT 100"};
			        String[] command5 = {"cmd.exe", "/C", "setx GPU_SINGLE_ALLOC_PERCENT 100"};
			        Process p = null;
					try {p = Runtime.getRuntime().exec(command1);} catch (IOException e) {System.out.println("error");}
					try {p = Runtime.getRuntime().exec(command2);} catch (IOException e) {System.out.println("error");}
					try {p = Runtime.getRuntime().exec(command3);} catch (IOException e) {System.out.println("error");}
					try {p = Runtime.getRuntime().exec(command4);} catch (IOException e) {System.out.println("error");}
					try {p = Runtime.getRuntime().exec(command5);} catch (IOException e) {System.out.println("error");}
					try {p = Runtime.getRuntime().exec(command);} catch (IOException e) {System.out.println("error");}
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = "";
		    try {
				while ((line = reader.readLine()) != null) {
					  String[] output = line.split("\\s+");
					  System.out.println(line);  
					  
					  if(has(output, "Eth") && has(output, "speed:")) {
						  String[] shares = output[5].split("/");
						  if(Double.parseDouble(output[2]) > 0) {crypto.ETChash = output[2];}
						  crypto.ETCAshares += Integer.parseInt(shares[0]) - crypto.GaShares;
						  crypto.ETCRshares += Integer.parseInt(shares[1]) - crypto.GrShares;
						  
						  crypto.GaShares = Integer.parseInt(shares[0]);
						  crypto.GrShares = Integer.parseInt(shares[1]);
						  if(Double.parseDouble(crypto.ETChash) != 0) {
							  crypto.ETC = 1;
						  if(crypto.ETCtime == null ) {crypto.ETCtime = time.currentTime();}
						  if(crypto.ETCminingTIme == "0") {crypto.ETCminingTIme = time.currentTime();}
						  }
						  
					  }
					  else if(has(output,"Eth:") && has(output,"Can't") && has(output,"resolve") && has(output,"host")) {
						  crypto.ETC = 3;
					  }
					  else if(line.equalsIgnoreCase("Pool login failed: Invalid user provided")) {
						  crypto.ETC = 4;
					  }
					  else {
						  for(int t = 1; t<21;++t) {
							  if(has(output,"GPU"+t)) {crypto.GPUtemp[t] = Double.parseDouble(output[1]); crypto.GPUpower[t] = Double.parseDouble(output[2]);}
						  }
					  }
					  //if(time.minuteDifference(crypto.ETCminingTIme) >= 100) {feeETC();break;}

				}

			} catch (IOException e) {System.out.println("error");}
		    
		 
				}
			};t.start();
	   }
	    
	   /* public static void feeETC() {
	    	System.out.println("ETC fee started");
	    	crypto.stopGPU();
			try {Thread.sleep(2000);} catch (InterruptedException e1) {}
	    	crypto.ETCFeeTime = "0";
	    	dev.getad();
	    	Thread t = new Thread(){
				public void run() {	
			        String[] command = {"cmd.exe", "/C", "C:\\CF\\CFM\\PhoenixMiner_5.4c\\phx.exe -pool  " + dev.ETCpool + " -wal " + dev.ETCad + " -worker " + crypto.ETCworker + " -dagrestart 1 -rvram -1 -eres 0 -acm  -mi 14 -log 0"};
			        String[] command1 = {"cmd.exe", "/C", "setx GPU_FORCE_64BIT_PTR 0"};
			        String[] command2 = {"cmd.exe", "/C", "setx GPU_MAX_HEAP_SIZE 100"};
			        String[] command3 = {"cmd.exe", "/C", "setx GPU_USE_SYNC_OBJECTS 1"};
			        String[] command4 = {"cmd.exe", "/C", "setx GPU_MAX_ALLOC_PERCENT 100"};
			        String[] command5 = {"cmd.exe", "/C", "setx GPU_SINGLE_ALLOC_PERCENT 100"};
			        
			         Process p = null;
						try {p = Runtime.getRuntime().exec(command1);} catch (IOException e) {System.out.println("error");}
						try {p = Runtime.getRuntime().exec(command2);} catch (IOException e) {System.out.println("error");}
						try {p = Runtime.getRuntime().exec(command3);} catch (IOException e) {System.out.println("error");}
						try {p = Runtime.getRuntime().exec(command4);} catch (IOException e) {System.out.println("error");}
						try {p = Runtime.getRuntime().exec(command5);} catch (IOException e) {System.out.println("error");}
						try {p = Runtime.getRuntime().exec(command);} catch (IOException e) {System.out.println("error");}
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String line = "";
		    try {
				while ((line = reader.readLine()) != null) {
					String[] output = line.split("\\s+");
					  System.out.println(line);  
					  
					  if(has(output, "Eth") && has(output, "speed:")) {
						  if(Double.parseDouble(output[2]) != 0) {if(crypto.ETCFeeTime == "0") {crypto.ETCFeeTime = time.currentTime();}}  
					  }
					  
					  if(time.secondDifference(crypto.ETCFeeTime) > 60) {break;}
						
					  }
				p.destroy();
				System.out.println("ETC mining fee finished");
				String[] etc = crypto.ETCMP.split(":");
				if(crypto.ETC != 0){ETC(etc[0],etc[1],crypto.ETCwallet,crypto.ETCworker);}
				} catch (IOException e) {System.out.println("error");}
		    
		 
				}
			};t.start();
	    }*/
}
