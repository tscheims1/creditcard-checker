
public class IssuerIdentification {
	private int m_Bin;
	private String m_Brand;
	private String m_CountryCode;
	private String m_CountryName;
	private String m_Bank;
	private String m_CardType;
	private String m_CardCategory;

	public int getBin() {
		return m_Bin;
	}
	public void setBin(int m_Bin) {
		this.m_Bin = m_Bin;
	}
	public String getBrand() {
		return m_Brand;
	}
	public void setBrand(String m_Brand) {
		this.m_Brand = m_Brand;
	}
	public String getCountryCode() {
		return m_CountryCode;
	}
	public void setCountryCode(String m_CountryCode) {
		this.m_CountryCode = m_CountryCode;
	}
	public String getCountryName() {
		return m_CountryName;
	}
	public void setCountryName(String m_CountryName) {
		this.m_CountryName = m_CountryName;
	}
	public String getBank() {
		return m_Bank;
	}
	public void setBank(String m_Bank) {
		this.m_Bank = m_Bank;
	}
	public String getCardType() {
		return m_CardType;
	}
	public void setCardType(String m_CardType) {
		this.m_CardType = m_CardType;
	}
	public String getCardCategory() {
		return m_CardCategory;
	}
	public void setCardCategory(String m_CardCategory) {
		this.m_CardCategory = m_CardCategory;
	}

	public IssuerIdentification(int bin, String brand, String countryCode, String countryName, String bank, String cardType, String cardCategory) {
		this.m_Bin = bin;
		this.m_Brand = brand;
		this.m_CountryCode  = countryCode;
		this.m_CountryName = countryName;
		this.m_Bank = bank;
		this.m_CardType = cardType;
		this.m_CardCategory = cardCategory;
	}
	public IssuerIdentification() {
	}
}	
