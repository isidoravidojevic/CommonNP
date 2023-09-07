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
     */
    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    /**
     * Postavlja naziv kupca na zadatu vrednost.
     *
     * @param customerName Naziv kupca
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Postavlja adresu kupca na zadatu vrednost.
     *
     * @param address Adresa kupca
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Postavlja PIB (Poreski identifikacioni broj) kupca na zadatu vrednost.
     *
     * @param VATnumber PIB kupca
     */
    public void setVATnumber(String VATnumber) {
        this.VATnumber = VATnumber;
    }

    /**
     * Postavlja maticni broj preduzeca kupca na zadatu vrednost.
     *
     * @param companyNumber Maticni broj preduzeca kupca
     */
    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
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
     */
	public void setCity(City city) {
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
