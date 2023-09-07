package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


/**
 * Predstavlja kupca koji je vezan za fakturu. Kupac ima identifikator, naziv kupca, adresu,
 * PIB (Poreski identifikacioni broj), maticni broj preduzeca i grad u kojem se nalazi.
 * Klasa implementira GenericEntity interfejs i omogucava rad sa bazom podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */
public class Customer implements GenericEntity{

	/**
	 * Identifikator kupca kao long.
	 */
	private long customerID;
	
	/**
	 * Naziv kupca kao String.
	 */
    private String customerName;
    
    /**
	 * Adresa kupca kao String.
	 */
    private String address;
    
    /**
	 * PIB kupca kao String.
	 */
    private String VATnumber;
    
    /**
	 * Maticni broj kupca (preduzeca) kao String.
	 */
    private String companyNumber;
    
    /**
	 * Grad iz kog je kupac kao City.
	 */
    private City city;

    
    /**
     * Konstruktor bez parametara koji inicijalizuje objekat klase Customer.
     */
    public Customer() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat klase Customer sa zadatim vrednostima.
     *
     * @param customerID ID kupca
     * @param customerName naziv kupca
     * @param address adresa kupca
     * @param VATnumber PIB kupca
     * @param companyNumber maticni broj preduzeca kupca
     * @param city grad u kojem se nalazi kupac
     */
    public Customer(long customerID, String customerName, String address, String VATnumber, String companyNumber, City city) {
        setCustomerID(customerID);
        setCustomerName(customerName);
        setAddress(address);
        setVATnumber(VATnumber);
        setCompanyNumber(companyNumber);
        setCity(city);
    }

    /**
     * Vraca ID kupca.
     *
     * @return ID kupca
     */
    public long getCustomerID() {
        return customerID;
    }

    /**
     * Vraca naziv kupca.
     *
     * @return Naziv kupca
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Vraca adresu kupca.
     *
     * @return Adresa kupca
     */
    public String getAddress() {
        return address;
    }

    /**
     * Vraca PIB kupca.
     *
     * @return PIB kupca
     */
    public String getVATnumber() {
        return VATnumber;
    }

    /**
     * Vraca maticni broj preduzeca kupca.
     *
     * @return Maticni broj preduzeca kupca
     */
    public String getCompanyNumber() {
        return companyNumber;
    }

    /**
     * Postavlja ID kupca na zadatu vrednost.
     *
     * @param customerID ID kupca
     * @throws IllegalArgumentException Ukoliko je zadati ID manji ili jednak 0.
     */
    public void setCustomerID(long customerID) {
    	if (customerID > 0) {
            this.customerID = customerID;
        } else {
            throw new IllegalArgumentException("Neispravan format ID-ja kupca.");
        }
    }

    /**
     * Postavlja naziv kupca na zadatu vrednost.
     *
     * @param customerName Naziv kupca
     * @throws IllegalArgumentException Ako je customerName null ili krace od 3 karaktera.
     */
    public void setCustomerName(String customerName) {
    	if (customerName != null && customerName.length() >= 3) {
            this.customerName = customerName;
        } else {
            throw new IllegalArgumentException("Neispravan format imena kupca.");
        }
    }

    /**
     * Postavlja adresu kupca na zadatu vrednost.
     *
     * @param address Adresa kupca
     * @throws IllegalArgumentException Ako je address null ili kraca od 5 karaktera.
     */
    public void setAddress(String address) {
    	if (address != null && address.length() >= 5) {
            this.address = address;
        } else {
            throw new IllegalArgumentException("Neispravan format adrese kupca.");
        }
    }

    /**
     * Postavlja PIB (Poreski identifikacioni broj) kupca na zadatu vrednost.
     *
     * @param VATnumber PIB kupca
     * @throws IllegalArgumentException Ako je VATnumber null, ne sadrzi tacno 9 karaktera i ne sadrzi samo cifre.
     */
    public void setVATnumber(String VATnumber) {
    	if (VATnumber != null && VATnumber.length() == 9 && VATnumber.matches("\\d+")) {
            this.VATnumber = VATnumber;
        } else {
            throw new IllegalArgumentException("Neispravan format PIB-a kupca.");
        }
    }

    /**
     * Postavlja maticni broj preduzeca kupca na zadatu vrednost.
     *
     * @param companyNumber Maticni broj preduzeca kupca
     * @throws IllegalArgumentException Ako je companyNumber null, ne sadrzi tacno 8 karaktera i ne sadrzi samo cifre.
     */
    public void setCompanyNumber(String companyNumber) {
    	if (companyNumber != null && companyNumber.length() == 8 && companyNumber.matches("\\d+")) {
            this.companyNumber = companyNumber;
        } else {
            throw new IllegalArgumentException("Neispravan format PDV broja kupca.");
        }
    }
    
    /**
     * Vraca grad iz kog je kupac.
     *
     * @return Grad iz kog je kupca
     */
    public City getCity() {
		return city;
	}

    /**
     * Postavlja grad za kupca.
     *
     * @param city Grad kupca
     * @throws IllegalArgumentException Ako je city null
     */
	public void setCity(City city) {
		if (city == null) {
	        throw new IllegalArgumentException("Grad ne sme imati null vrednost.");
	    }
	    this.city = city;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public String getTableName() {
        return "customer";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInsertColumns() {
        return "customerName, address, VATnumber, companyNumber, cityID";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInsertValues() {
        return "'" + customerName + "','" + address + "','" + VATnumber + "','" + companyNumber + "', "+city.getCityID();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(long id) {
        customerID = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUpdateValues() {
        return "customerName='"+ customerName +"',address='"+address+"',VATnumber='"+VATnumber+"',companyNumber='"+companyNumber + "',cityID=" + city.getCityID();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getJoinText() {
        return " JOIN city ON customer.cityID = city.cityID";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSelectedText() {
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getID() {
        return "customer.customerID=" + customerID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericEntity getEntity(ResultSet rs) throws SQLException {
    	city = new City();
        return new Customer(rs.getLong(getTableName() + ".customerID"), rs.getString(getTableName() + ".customerName"),
            rs.getString(getTableName() + ".address"), rs.getString(getTableName() + ".VATnumber"), rs.getString(getTableName() + ".companyNumber"), (City) city.getEntity(rs));
    }
}
