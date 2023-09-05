package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Customer implements GenericEntity{

	private long customerID;
    private String customerName;
    private String address;
    private String VATnumber;
    private String companyNumber;
    private City city;

    public Customer() {
    }

    public Customer(long customerID, String customerName, String address, String VATnumber, String companyNumber, City city) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.VATnumber = VATnumber;
        this.companyNumber = companyNumber;
        this.city = city;
    }

    public long getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getVATnumber() {
        return VATnumber;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setVATnumber(String VATnumber) {
        this.VATnumber = VATnumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }
    
    public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

    @Override
	public int hashCode() {
		return Objects.hash(VATnumber, address, city, companyNumber, customerID, customerName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(VATnumber, other.VATnumber) && Objects.equals(address, other.address)
				&& Objects.equals(city, other.city) && Objects.equals(companyNumber, other.companyNumber)
				&& customerID == other.customerID && Objects.equals(customerName, other.customerName);
	}

    @Override
    public String getTableName() {
        return "customer";
    }

    @Override
    public String getInsertColumns() {
        return "customerName, address, VATnumber, companyNumber, cityID";
    }

    @Override
    public String getInsertValues() {
        return "'" + customerName + "','" + address + "','" + VATnumber + "','" + companyNumber + "', "+city.getCityID();
    }

    @Override
    public void setId(long id) {
        customerID = id;
    }

    @Override
    public String getUpdateValues() {
        return "customerName = '"+ customerName +"' , address= '"+address+"', VATnumber= '"+VATnumber+"' ,companyNumber= '"+companyNumber + "',cityID= " + city.getCityID();
    }

    @Override
    public String getJoinText() {
        return " JOIN city ON customer.cityID = city.cityID";
    }

    @Override
    public String getSelectedText() {
        return "";
    }

    @Override
    public String getID() {
        return "customer.customerID=" + customerID;
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws SQLException {
    	city = new City();
        return new Customer(rs.getLong(getTableName() + ".customerID"), rs.getString(getTableName() + ".customerName"),
            rs.getString(getTableName() + ".address"), rs.getString(getTableName() + ".VATnumber"), rs.getString(getTableName() + ".companyNumber"), (City) city.getEntity(rs));
    }
}
