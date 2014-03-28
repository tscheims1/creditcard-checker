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
				boolean cardvalid = Luhn.validate(creditcardnumber);
				System.out.printf("Number %s is %s.\n", creditcardnumber, (cardvalid ? "valid" : "invalid"));

				int iid = Integer.parseInt(creditcardnumber.substring(0, 6));
				IssuerIdentification ii = BinlistClient.getIssuerIdentification(iid);
				
				if(ii != null)
					System.out.printf("Your Card is %s (%s, %s) from the issuer %s of %s.\n", ii.getBrand(), ii.getCardType(), ii.getCardType(), ii.getBank(), ii.getCountryName());
				else
					System.out.printf("No additional information found for IID: %d\n", iid);
			}
			catch(NumberFormatException ex) {
				System.out.println("You've wrote an incorrect credit card number. Number should have the format: 6 digits, up to 12 alphanumerical signs, single digiti.");
			}

		} while (true);
	}
}
