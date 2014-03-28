import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class BinlistClient {
	public static IssuerIdentification getIssuerIdentification(int issuerIdentificationNumber) {
		String response;
		try {
			response = executeGet("http://www.binlist.net/xml/"+issuerIdentificationNumber);

			Document dom = parseXmlFile(response);
			return parseDocument(dom);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (NoCardInformationFoundException e) {
			return null;
		}
	}

	private static String executeGet(String url) throws IOException, NoCardInformationFoundException
	{		 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		if(responseCode == 404)
			throw new NoCardInformationFoundException("");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

	private static Document parseXmlFile(String str){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			return db.parse(new ByteArrayInputStream(str.getBytes("UTF-8")));

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}

	private static IssuerIdentification parseDocument(Document dom){
		Element docEle = dom.getDocumentElement();

		int bin = getIntValue(docEle,"Bin");
		String brand = getTextValue(docEle,"Brand");
		String countryCode = getTextValue(docEle,"CountryCode");
		String countryName = getTextValue(docEle,"CountryName");
		String bank = getTextValue(docEle,"Bank");
		String cardType = getTextValue(docEle,"CardType");
		String cardCategory = getTextValue(docEle,"CardCategory");
		return new IssuerIdentification(bin, brand, countryCode, countryName, bank, cardType, cardCategory);
	}

	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = "";
			
			Node n = el.getFirstChild();
			if(n != null)
				textVal = n.getNodeValue();
		}

		return textVal;
	}

	/**
	 * Calls getTextValue and returns a int value
	 */
	private static int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}
}
