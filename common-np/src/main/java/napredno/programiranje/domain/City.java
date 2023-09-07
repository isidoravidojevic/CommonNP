package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Predstavlja grad iz koga je kupac. Grad ima identifikator, naziv i postanski broj.
 * Klasa implementira GenericEntity interfejs i omogucava rad sa bazom podataka.
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
	 * Postanski broj grada kao String.
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
     * @param cityID ID grada
     * @param cityName naziv grada
     * @param postalCode postanski broj grada
     */
	public City(long cityID, String cityName, String postalCode) {
		setCityID(cityID);
		setCityName(cityName);
		setPostalCode(postalCode);
	}
	
	/**
     * Vraca ID grada.
     *
     * @return ID grada kao long
     */
	public long getCityID() {
		return cityID;
	}

	/**
     * Postavlja ID grada na zadatu vrednost.
     *
     * @param cityID ID grada
     * @throws IllegalArgumentException Ako je cityID manji ili jednak 0.
     */
	public void setCityID(long cityID) {
		if (cityID > 0) {
	        this.cityID = cityID;
	    } else {
	        throw new IllegalArgumentException("City ID mora biti broj veci od 0.");
	    }
	}

	/**
     * Vraca naziv grada.
     *
     * @return naziv grada
     */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Postavlja naziv grada.
	 * 
	 * @param cityName Naziv grada
	 * @throws IllegalArgumentException Ako je cityName null ili prazan string.
	 */
	public void setCityName(String cityName) {
	    if (cityName != null && !cityName.isEmpty()) {
	        this.cityName = cityName;
	    } else {
	        throw new IllegalArgumentException("Naziv grada ne može biti null ili prazan string.");
	    }
	}


	/**
     * Vraca postanski broj grada.
     *
     * @return postanski broj grada
     */
	public String getPostalCode() {
		return postalCode;
	}

	/**
     * Postavlja postanski broj grada na zadatu vrednost.
     *
     * @param postalCode postanski broj grada
     * @throws IllegalArgumentException Ukoliko je zadati postanski broj null ili nema tacno 5 karaktera.
     */
	public void setPostalCode(String postalCode) {
		if (postalCode != null && postalCode.length() == 5) {
	        this.postalCode = postalCode;
	    } else {
	        throw new IllegalArgumentException("Neispravan format poštanskog broja.");
	    }
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
