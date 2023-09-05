package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class City implements GenericEntity {

	private long cityID;
	private String cityName;
	private String postalCode;
	
	public City() {
	}

	public City(long cityID, String cityName, String postalCode) {
		this.cityID = cityID;
		this.cityName = cityName;
		this.postalCode = postalCode;
	}
	
	public long getCityID() {
		return cityID;
	}

	public void setCityID(long cityID) {
		this.cityID = cityID;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String getTableName() {
		return "city";
	}

	@Override
	public String getInsertColumns() {
		return "cityName, postalCode";
	}

	@Override
	public String getInsertValues() {
		return "'" + cityName + "', '" + postalCode + "'"; 
	}

	@Override
	public void setId(long id) {
		cityID = id;

	}

	@Override
	public String getUpdateValues() {
		return "cityName = '" + cityName + "', postalCode = '" + postalCode + "'";
	}

	@Override
	public String getJoinText() {
		return "";
	}

	@Override
	public String getSelectedText() {
		return "";
	}

	@Override
	public String getID() {
		return "city.cityID=" + cityID;
	}

	@Override
	public GenericEntity getEntity(ResultSet rs) throws SQLException {
		return new City(rs.getLong(getTableName() + ".cityID"), rs.getString(getTableName() + ".cityName"), 
				rs.getString(getTableName() + ".postalCode"));
	}

	
}
