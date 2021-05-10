package wallet;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.Random;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

public class CFMwallet {

	  public static Web3j web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/fa471aa86d124cde87836175143616f6"));
	  public static Web3j web3b = Web3j.build(new HttpService("https://bsc-dataseed1.binance.org:443"));

	  public static String address = "0x";
	  public static String password = null;
	  private static String rando = null;
	  
	  public static BigInteger GAS_LIMIT = new BigInteger("21000");
	  public static BigInteger ETH_GAS_PRICE = new BigInteger("50000000000");
	  public static BigInteger BNB_GAS_PRICE = new BigInteger("20000000000");
	  
	  private static BigDecimal multiplier = new BigDecimal("1000000000000000000");
	  private static BigDecimal FTmultiplier = new BigDecimal("10000000000");//1000000000000000000
	  
	  public static BigDecimal FTbalance = new BigDecimal("-1");
	  public static BigDecimal BNBbalance;
	  public static BigDecimal ETHbalance;
	  
	  public static BigDecimal FTstaked; // amount of FT staked 
	  public static BigDecimal FTrewards; //rewards from FT staking
	  public static BigInteger block; // block staked at
	  
	  private static String contractAddress = "0x155488A3C962E052c15F9dE0f8ee2AAe51515747";
	  
	  private static String Key() {
		  File CFMwalletData = new File("C:\\CF\\CFMwallet\\CFMwallet.dat");
		  String encryption = file.readAll(CFMwalletData).replace(" ", "");
		  
		  String pass = AES256.decrypt(password, rando);
		  pass = AES256.decrypt(pass, "scritority");
		  pass = AES256.decrypt(pass, "scritority");
		  
		  encryption = AES256.decrypt(encryption, pass);
		  
		  return encryption;
	  }
	  
	  private static void passowrdThread() {
		  Thread t = new Thread() {
			  public void run() {
				  while(true) {
					  password = AES256.encrypt(password, time.currentTime());
					  try {Thread.sleep(1000);} catch (InterruptedException e) {}
				  }
			  }
		  };t.start();
	  }
	  public static BigDecimal total(BigInteger gas, BigInteger gasPrice) {
		  return new BigDecimal(gas.multiply(gasPrice)).divide(multiplier);
	  }
	  private static String remove(char characterToBeremoved, String sentence) {
		  String output = "";
		  for(int t = 0 ; t < sentence.length() ; ++t) {
			  char f = sentence.charAt(t);
			  if(f != characterToBeremoved) {output += f;}
		  }
		  return output;
	  }
	  
	  public static double BNB(BigInteger price, BigInteger limit) {
		  BigDecimal str = new BigDecimal(price.multiply(limit));
		  return Double.parseDouble(str.divide(multiplier) + "");
	  }
	  public static BigInteger hexToDecimal(String hex) {

		  String _decimal = "";
		  for(int t=0; t < hex.length(); ++t) {
			  if(t == 0 || t == 1) {}
			  else {
				  char c = hex.charAt(t);
				  _decimal += c;
			  }
		  }
		  BigInteger bigInt = new BigInteger(_decimal, 16);
		  return bigInt;
	  }
	  
	  public static BigDecimal Format(BigInteger value) {
		  String str = value.toString();
		  BigDecimal _value = new BigDecimal(str);
		  return _value.divide(FTmultiplier);
	  }
	  
	  public static String Format(String value) {
		  BigDecimal _value = new BigDecimal(value);
		  BigDecimal output = _value.divide(FTmultiplier);
		  return output + "";
		  }
	  
	  public static String addressFromKey(String key) {
		  BigInteger _key = new BigInteger(key.replace(" ", ""));
		  ECKeyPair ecKeyPair  = ECKeyPair.create(_key);

         WalletFile aWallet = null;
		try {
			aWallet = Wallet.createLight("n", ecKeyPair);
		} catch (CipherException e) {e.printStackTrace();}
          String sAddress = aWallet.getAddress();
          
          return sAddress;
	  } 
	  
	  public static void createWallet(String password){
		  ECKeyPair ecKeyPair  = null;
		try {
			ecKeyPair = Keys.createEcKeyPair();
		} catch (InvalidAlgorithmParameterException e) {e.printStackTrace();} catch (NoSuchAlgorithmException e) {e.printStackTrace();} catch (NoSuchProviderException e) {e.printStackTrace();}
		BigInteger privateKey = ecKeyPair.getPrivateKey();

          WalletFile aWallet = null;
		try {
			aWallet = Wallet.createLight("n", ecKeyPair);
		} catch (CipherException e) {e.printStackTrace();}
          String sAddress = aWallet.getAddress();
          address += sAddress;
          File CFMwallet = new File("C:\\CF\\CFMwallet");
          File CFMwalletData = new File("C:\\CF\\CFMwallet\\CFMwallet.dat");
          file.newFolder(CFMwallet);
          file.newFile(CFMwalletData, AES256.encrypt(privateKey.toString(), password));
	  }
	  
	  public static boolean unlockWallet(String password) {
		  File CFMwalletData = new File("C:\\CF\\CFMwallet\\CFMwallet.dat");
		  if(AES256.decrypt(file.readAll(CFMwalletData).replace(" ", ""), password.replace(" ", "")) != null) {
			  String key = AES256.decrypt(file.readAll(CFMwalletData).replace(" ", ""), password.replace(" ", "")).replace(" ", "");
			  address = getAddress(key);
			  CFMwallet.password = AES256.encrypt(password, "scritority");
			  CFMwallet.password = AES256.encrypt(CFMwallet.password, "scritority");
			  Random rand = new Random();
			  int i = rand.nextInt(999999999);
			  CFMwallet.password = AES256.encrypt(CFMwallet.password, i + "");
			  rando = i + "";
			  return true;}
		  return false;
	  }
	  
	  public static String getAddress(String key) {

          return "0x" + addressFromKey(key);
		  
	  }
	  
	  public static void loadBalances() {
		  FTbalance = getFTbalance();
		  BNBbalance = getBNBbalance();
		  ETHbalance = getETHbalance();
	  }
	  
	  public static BigDecimal getETHbalance() {
		  BigInteger bal = new BigInteger("0");
		  BigDecimal balance = null;
			try {
				EthGetBalance ethGetBalance = web3j
						  .ethGetBalance(address, DefaultBlockParameterName.LATEST)
						  .sendAsync()
						  .get();

					 bal = ethGetBalance.getBalance();
					 balance = new BigDecimal(bal);
					 balance = balance.divide(multiplier);
			} catch (Exception e) {System.out.println("Error");e.printStackTrace();}
			System.out.println("ETHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH : " + balance);
			return balance;
	  }
	  
	  public  static BigDecimal getBNBbalance() {
		  BigInteger bal = new BigInteger("0");
		  BigDecimal balance = null;
			try {
				EthGetBalance ethGetBalance = web3b
						  .ethGetBalance(address, DefaultBlockParameterName.LATEST)
						  .sendAsync()
						  .get();

					 bal = ethGetBalance.getBalance();
					 balance = new BigDecimal(bal);
					 balance = balance.divide(multiplier);
			} catch (Exception e) {System.out.println("Error");e.printStackTrace();}
			return balance;
	  }
	  
	  public static BigDecimal getFTbalance() {
		    Function function = new Function(
		            "balanceOf",  // function we're calling
		            Arrays.asList(new Address(address)),   // Parameters to pass as Solidity Types
		            Arrays.asList(new TypeReference<Uint256>() {}));
		    String encodedFunction = FunctionEncoder.encode(function);
		    EthCall ethCall = null;
			try {ethCall = web3b.ethCall(Transaction.createEthCallTransaction(address, contractAddress, encodedFunction),DefaultBlockParameterName.LATEST).send();} catch (IOException e) {e.printStackTrace(); }
			return  Format(hexToDecimal(ethCall.getValue()));
	  }
	  
	  public static double getBalance(String crypto) {
		  if(crypto.equalsIgnoreCase("ft")) {return Double.parseDouble(FTbalance + "");}
		  else if(crypto.equalsIgnoreCase("bnb")) {return Double.parseDouble(BNBbalance.toString());}
		  else if(crypto.equalsIgnoreCase("eth")) {return Double.parseDouble(ETHbalance.toString());}
		  else {return -1;}
		  
	  }

	  public static String[] getStakingStats() {
		    Function function = new Function(
		            "stakingStats",  // function we're calling
		            Arrays.asList(new Address(address)),   // Parameters to pass as Solidity Types
		            Arrays.asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
		    String encodedFunction = FunctionEncoder.encode(function);
		    EthCall ethCall = null;
		    try { ethCall = web3b.ethCall(Transaction.createEthCallTransaction(address, contractAddress, encodedFunction),DefaultBlockParameterName.LATEST).send();} catch (IOException e) {}
		    String output = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters()).toString();
		    String[] data = remove(']',remove('[', output)).split(",");
		    return data;
		    
	  }
	  
	  public static void loadStakingSats() {
		  String[] data = getStakingStats();
		    FTstaked = new BigDecimal(hexToDecimal(data[0].replace(" ", "")) + "").divide(multiplier);
		    block = hexToDecimal(data[1].replace(" ", ""));
		    FTrewards = new BigDecimal(hexToDecimal(data[2].replace(" ", "")) + "").divide(multiplier);
	  }
	  
	  public static String makeETHTransaction(String to_address, BigDecimal amount_ether) throws Exception {
			TransactionManager tm = new RawTransactionManager(web3j, getCredentialsFromPrivateKey());
			Transfer transfer = new Transfer(web3j, tm);
			TransactionReceipt tr = transfer.sendFunds(to_address, amount_ether, Convert.Unit.ETHER, ETH_GAS_PRICE, GAS_LIMIT).send();

			return tr.getTransactionHash();
		}
		
	  public static String makeBNBTransaction(String to_address, BigDecimal amount_ether) throws Exception {
			TransactionManager tm = new RawTransactionManager(web3b, getCredentialsFromPrivateKey());
			Transfer transfer = new Transfer(web3b, tm);
			TransactionReceipt tr = transfer.sendFunds(to_address, amount_ether, Convert.Unit.ETHER, BNB_GAS_PRICE, GAS_LIMIT).send();
			return tr.getTransactionHash();
		}
		
	  public static String makeFTtransaction(String to_address, BigDecimal amountFT) throws Exception {
			BigInteger amount = amountFT.multiply(FTmultiplier).toBigInteger();
			System.out.println(amount);
		    Function function = new Function(
		            "transfer",  // function we're calling
		            Arrays.asList(new Address(to_address), new Uint256(amount)),   // Parameters to pass as Solidity Types
		            Arrays.asList(new TypeReference<Bool>() {}));
		    String encodedFunction = FunctionEncoder.encode(function);
			return functionExecute(contractAddress, encodedFunction,  BNB_GAS_PRICE, new BigInteger("80000"));
		}
		
	  public static String makeTransaction(String crypto, String to_address, BigDecimal amountFT) throws Exception {
		  if(crypto.equalsIgnoreCase("ft")) {return makeFTtransaction(to_address, amountFT);}
		  else if(crypto.equalsIgnoreCase("bnb")){return makeBNBTransaction(to_address, amountFT);}
		  else if(crypto.equalsIgnoreCase("eth")){return makeETHTransaction(to_address, amountFT);}
		  else {return "invalid";}
	  }
	  public static String stakeFT(BigDecimal amountFT){
		  BigInteger amount = amountFT.multiply(FTmultiplier).toBigInteger();
		    Function function = new Function(
		    		"stakeFT",  // function we're calling
		            Arrays.asList(new Uint256(amount)),   // Parameters to pass as Solidity Types
		            Arrays.asList(new TypeReference<Bool>() {}));
		    String encodedFunction = FunctionEncoder.encode(function);
		    
		return functionExecute(contractAddress, encodedFunction,  BNB_GAS_PRICE, new BigInteger("76000"));
	  }
	  
	  public static String unstakeFT(double amountFT) {
		  BigInteger amount = new BigDecimal(amountFT).multiply(FTmultiplier).toBigInteger();
		    Function function = new Function(
		    		"unstakeFT",  // function we're calling
		            Arrays.asList(new Uint256(amount)),   // Parameters to pass as Solidity Types
		            Arrays.asList(new TypeReference<Bool>() {}));
		    String encodedFunction = FunctionEncoder.encode(function);
		    
		  return  functionExecute(contractAddress, encodedFunction,  BNB_GAS_PRICE, new BigInteger("70000"));
	  }
	  
	  public static String unstakeAllFT() {
		    Function function = new Function(
		    		"unstakeAll",  // function we're calling
		            Arrays.asList(),   // Parameters to pass as Solidity Types
		            Arrays.asList(new TypeReference<Bool>() {}));
		    String encodedFunction = FunctionEncoder.encode(function);
		    
		  return functionExecute(contractAddress, encodedFunction,  BNB_GAS_PRICE, new BigInteger("56000"));
	  }
	  
	  public static String refreshStaking() {
		    Function function = new Function(
		    		"refreshStaking",  // function we're calling
		            Arrays.asList(),   // Parameters to pass as Solidity Types
		            Arrays.asList(new TypeReference<Bool>() {}));
		    String encodedFunction = FunctionEncoder.encode(function);
		    
		  return functionExecute(contractAddress, encodedFunction,  BNB_GAS_PRICE, new BigInteger("40000"));
	  }
	  
	  public static Credentials getCredentialsFromPrivateKey() {
			BigInteger Key = new BigInteger(Key());
			ECKeyPair ecKeyPair = ECKeyPair.create(Key);
			return Credentials.create(ecKeyPair);
		}
		
	  private static String functionExecute(String contractAddress, String encodedFunction, BigInteger GAS_PRICE, BigInteger GAS_LIMIT){
		    Credentials creds = getCredentialsFromPrivateKey();
		    RawTransactionManager manager = new RawTransactionManager(web3b, creds);
		    EthSendTransaction transaction = null;
			try {
				transaction = manager.sendTransaction(GAS_PRICE, GAS_LIMIT, contractAddress, encodedFunction, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			  String pass = AES256.decrypt(password, rando);
			  pass = AES256.decrypt(pass, "scritority");
			  pass = AES256.decrypt(pass, "scritority");
			  
			  pass = AES256.encrypt(pass, "scritority");
			  pass = AES256.encrypt(pass, "scritority");
			  Random rand = new Random();
			  int i = rand.nextInt(999999999);
			  CFMwallet.password = AES256.encrypt(pass, i + "");
			  
			  rando = i + "";
			  
		    return transaction.getTransactionHash();
		}

}
