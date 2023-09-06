package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Predstavlja grad iz koga je kupac. Grad ima identifikator, naziv i poštanski broj.
 * Klasa implementira GenericEntity interfejs i omogućava rad sa bazom podataka.
 * 
 * @author Isidora Vidojevic
 */

public class City implements GenericEntity {

	/**
	 * Identifikator grada kao long.
	 */
	private long cityID;
	
	/**
	 * Naziv grada kao String.
	 */
	private String cityName;
	
	/**
	 * Poštanski broj grada kao String.
	 */
	private String postalCode;
	
	/**
     * Konstruktor bez parametara koji inicijalizuje objekat klase City.
     */
	public City() {
	}

	/**
     * Konstruktor koji inicijalizuje objekat klase City sa zadatim vrednostima.
     *
     * @param cityID, ID grada
     * @param cityName, naziv grada
     * @param postalCode, poštanski broj grada
     */
	public City(long cityID, String cityName, String postalCode) {
		this.cityID = cityID;
		this.cityName = cityName;
		this.postalCode = postalCode;
	}
	
	/**
     * Vraća ID grada.
     *
     * @return ID grada kao long
     */
	public long getCityID() {
		return cityID;
	}

	/**
     * Postavlja ID grada na zadatu vrednost.
     *
     * @param cityID, ID grada
     */
	public void setCityID(long cityID) {
		this.cityID = cityID;
	}

	/**
     * Vraća naziv grada.
     *
     * @return naziv grada
     */
	public String getCityName() {
		return cityName;
	}

	/**
     * Postavlja naziv grada na zadatu vrednost.
     *
     * @param cityName, naziv grada
     */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
     * Vraća poštanski broj grada.
     *
     * @return poštanski broj grada
     */
	public String getPostalCode() {
		return postalCode;
	}

	/**
     * Postavlja poštanski broj grada na zadatu vrednost.
     *
     * @param postalCode, poštanski broj grada
     */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getTableName() {
		return "city";
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getInsertColumns() {
		return "cityName, postalCode";
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getInsertValues() {
		return "'" + cityName + "', '" + postalCode + "'"; 
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void setId(long id) {
		cityID = id;

	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getUpdateValues() {
		return "cityName = '" + cityName + "', postalCode = '" + postalCode + "'";
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getJoinText() {
		return "";
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
		return "city.cityID=" + cityID;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public GenericEntity getEntity(ResultSet rs) throws SQLException {
		return new City(rs.getLong(getTableName() + ".cityID"), rs.getString(getTableName() + ".cityName"), 
				rs.getString(getTableName() + ".postalCode"));
	}

	
}
