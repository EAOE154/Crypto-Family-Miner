package mining;

import java.io.IOException;

import Main.time;

public class crypto {

	public static int XMR = 0;
	public static String XMRhash = "0";
	public static int XMRAshares = 0; //XMR accepted shares
	public static int XMRRshares =0; //XMR rejected shares
	public static String XMRtime = null; //The time the XMR miner has been on (this timer resets every time the user runs XMR mining)
	public static String XMRminingTIme = null;// the time the XMR miner has been on since last fee (this timer resets only after a fee)
	public static String XMRMP; //XMR mining pool
	public static String XMRwallet; //XMR wallet being mined to 
	public static String XMRFeeTime = null;// XMR fee time (the time for how long the fee has been running)
	
	public static int ETH = 0;
	public static String ETHhash = "0";
	public static int ETHAshares = 0; //ETH accepted shares
	public static int ETHRshares = 0; //ETH rejected shares
	public static String ETHtime = null; //The time the ETH miner has been on 
	public static String ETHminingTIme = null;// the time the ETH miner has been on since last fee (this timer resets only after a fee)
	public static String ETHMP; //ETH mining pool 
	public static String ETHwallet; //ETH wallet being mined to
	public static String ETHworker;
	public static String ETHFeeTime = null;// ETH fee time (the time for how long the fee has been running)
	
	public static int ETC = 0;
	public static String ETChash = "0";
	public static int ETCAshares = 0; //ETC accepted shares
	public static int ETCRshares = 0; //ETC rejected shares
	public static String ETCtime = null; //The time the ETC miner has been on 
	public static String ETCminingTIme = null;// the time the ETC miner has been on since last fee (this timer resets only after a fee)
	public static String ETCMP; //ETC mining pool 
	public static String ETCwallet; //ETC wallet being mined to
	public static String ETCworker;
	public static String ETCFeeTime = null;// ETC fee time (the time for how long the fee has been running)
	
	public static int RVN = 0;
	public static String RVNhash = "0";
	public static int RVNAshares = 0; //RVN accepted shares
	public static int RVNRshares = 0; //RVN rejected shares
	public static String RVNtime = null; //The time the RVN miner has been on 
	public static String RVNminingTIme = null;// the time the RVN miner has been on since last fee (this timer resets only after a fee)
	public static String RVNMP; //RVN mining pool 
	public static String RVNwallet; //RVN wallet being mined to
	public static String RVNworker;
	public static String RVNFeeTime = null;// RVN fee time (the time for how long the fee has been running)
	
	public static int GaShares = 0; //gpu accepted shares
	public static int GrShares = 0; //gpu reected shares 
	public static int CaShares = 0; //cpu accepted shares
	public static int CrShares = 0; //cpu reected shares
	
	public static double[] GPUtemp = new double[20] ; //gpu temp of gpus
	public static double[] GPUpower = new double[20] ; //power usage of gpus
	
	public static String[] Scryptos = {"xmr", "eth", "etc", "rvn"};//supported cryptos

	public static void stopGPU() {
		String[] command1 = {"cmd.exe", "/C", "taskkill /IM rex.exe  /F"};
		String[] command2 = {"cmd.exe", "/C", "taskkill /IM phx.exe  /F"};
        try {Process p1 = Runtime.getRuntime().exec(command1);} catch (IOException e) {}
        try {Process p2 = Runtime.getRuntime().exec(command2);} catch (IOException e) {}
	}
	
	public static boolean has(String[] string, String element) {
		for(int t = 0; t < string.length; ++t) {
			if(element.contentEquals(string[t])) {return true;}
		}
		return false;
	}
	
	public static String time(String Time) {
		int sec = 0;
	try {	 sec = (int)time.secondDifference(Time);}catch(NullPointerException e) {}
		int min = 0;
		int hr = 0;
		while(sec >= 60) {
			++min;
			sec -= 60;
		}
		while(min >= 60) {
			++hr;
			min -= 60;
		}
		return hr + ": " + min + ": " + sec;
	}
	
	public static boolean gpu() { //returns if gpu is being used for mining or not 
		if(ETH != 0) {return true;}
		else if(ETC != 0) {return true;}
		else if(RVN != 0) {return true;}
		else {return false;}
	}
	
	public static void stop(String crypto) {
		if(crypto.equalsIgnoreCase("xmr")) {
		   	 String[] command = {"cmd.exe", "/C", "taskkill /IM xmrig.exe /F"};
		     try {Process p1 = Runtime.getRuntime().exec(command);} catch (IOException e) {}
		}
		else if(crypto.equalsIgnoreCase("eth") || crypto.equalsIgnoreCase("etc")) {
			String[] command1 = {"cmd.exe", "/C", "taskkill /IM phx.exe  /F"};
	         try {Process p1 = Runtime.getRuntime().exec(command1);} catch (IOException e) {}
		}
		else if(crypto.equalsIgnoreCase("rvn")) {
			String[] command1 = {"cmd.exe", "/C", "taskkill /IM rex.exe  /F"};
	         try {Process p1 = Runtime.getRuntime().exec(command1);} catch (IOException e) {}
		}
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
	}

}
