package Main;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class time {


public static double secondDifference(String Date) {
		
		double difference = 2 ;
		
		//DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss"); 
				try {
					
					Date date1 = formatter.parse(Date);
			
				Date date2 = new Date();
				
				 difference = date2.getTime() - date1.getTime();
				 Date date3 = new Date();

				
	} catch (ParseException e) {}
				
				return difference/1000;
			
		}
	
public static double minuteDifference(String Date) {
		
	double difference = 2 ;
	
	//DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss"); 
			try {
				
				Date date1 = formatter.parse(Date);
		
			Date date2 = new Date();
			
			 difference = date2.getTime() - date1.getTime();
			
			 /*System.out.println(formatter.format(date1)); 
			System.out.println(date1);
			System.out.println(date2);
			System.out.println(difference);*/
			
} catch (ParseException e) {}
			
			return difference/60000;
		
	}

public static double hourDifference(String Date) {	
	
double difference = 2 ;
	
	//DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss"); 
			try {
				
				Date date1 = formatter.parse(Date);
		
			Date date2 = new Date();
			
			 difference = date2.getTime() - date1.getTime();
			
			 System.out.println(formatter.format(date1)); 
			System.out.println(date1);
			System.out.println(date2);
			System.out.println(difference);
			
} catch (ParseException e) {}
			
			return difference/3600000;
	
	}

public static double dayDifference(String Date) {
	
double difference = 2 ;
	
System.out.println(Date);
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss"); 
			try {
				
				Date date1 = formatter.parse(Date);
		
			Date date2 = new Date();
			
			 difference = date2.getTime() - date1.getTime();
			
			 System.out.println(formatter.format(date1)); 
			System.out.println(date1);
			System.out.println(date2);
			System.out.println(difference);
			
} catch (ParseException e) {System.out.println("Parsing Error");}
			
			return difference/86400000;

}

public static double weekDifference(String Date) {
	
	double output = dayDifference(Date)/7;
	
	return output;
}

public static double yearDifference(String Date) {
	
    double output = dayDifference(Date)/365;
	
	return output;
}

public static String currentTime(){

	 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss"); 
	 Date date = new Date();
	 String output = formatter.format(date);

	 return output;	
}
}