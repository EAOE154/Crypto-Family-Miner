package Main;
import java.awt.Desktop;
import java.net.URL;

import javax.swing.JOptionPane;

public class dev {


	public static final String version = "1.3.0";

public static boolean checkForUpdate() {
	if(web.read("https://cryptofamily3.github.io/webpage/cfminerversion.html").equalsIgnoreCase(version)) { System.out.println("False");return false;}//no update
	else { System.out.println("true");return true;}//there is an update 
}

}