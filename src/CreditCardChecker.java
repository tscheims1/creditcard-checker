import java.io.InputStreamReader;
import java.util.Scanner;

import org.luhn.Luhn;


public class CreditCardChecker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));

        do {
        	System.out.println("Enter your credit card number or q to exit application: ");
        	String creditcardnumber = scanner.nextLine().trim();

            if(creditcardnumber.equals("q"))
            	break;
            
            try {
            	System.out.printf("Number %s is %s.", creditcardnumber, (Luhn.validate(creditcardnumber) ? "valid" : "invalid")+"\n");
            }
            catch(NumberFormatException ex) {
            	System.out.println("You've wrote an incorrect credit card number. Number should have the format: 6 digits, up to 12 alphanumerical signs, single digiti.");
            }
            
        } while (true);
	}
}
