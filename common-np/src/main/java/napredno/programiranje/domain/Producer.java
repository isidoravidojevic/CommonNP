package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Predstavlja proizvodjaca nekog proizvoda. Proizvodjac ima identifikator, 
 * i naziv.
 * Klasa implementira GenericEntity interfejs i omogucava rad sa bazom podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class Producer implements GenericEntity {

	/**
	 * Identifikator proizvodjaca kao long.
	 */
	private long producerID;
	
	/**
	 * Naziv proizvodjaca kao String.
	 */
	private String producerName;
	
	/**
     * Konstruktor bez parametara koji inicijalizuje objekat klase Producer.
     */
	public Producer() {
	}

	/**
     * Konstruktor koji inicijalizuje objekat klase Producer sa zadatim vrednostima.
     *
     * @param producerID ID proizvodjaca
     * @param producerName naziv proizvodjaca
     */
	public Producer(long producerID, String producerName) {
		setProducerID(producerID);
		setProducerName(producerName);
	}

	/**
     * Vraca ID proizvodjaca.
     *
     * @return ID proizvodjaca kao long
     */
	public long getProducerID() {
		return producerID;
	}

	/**
     * Postavlja ID proizvodjaca na zadatu vrednost.
     *
     * @param producerID ID proizvodjaca
     * @throws IllegalArgumentException Ako je customerID manji ili jednak 0
     */
	public void setProducerID(long producerID) {
		if (producerID > 0) {
	        this.producerID = producerID;
	    } else {
	        throw new IllegalArgumentException("ID proizvođača mora biti veći od nule.");
	    }
	}

	/**
     * Vraca naziv proizvodjaca.
     *
     * @return naziv proizvodjaca
     */
	public String getProducerName() {
		return producerName;
	}

	/**
     * Postavlja naziv proizvodjaca na zadatu vrednost.
     *
     * @param producerName naziv proizvodjaca
     * @throws IllegalArgumentException Ako je customerName null ili prazan String
     */
	public void setProducerName(String producerName) {
		if (producerName != null && !producerName.isEmpty()) {
	        this.producerName = producerName;
	    } else {
	        throw new IllegalArgumentException("Naziv proizvođača ne sme biti null ili prazan String.");
	    }
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getTableName() {
		return "producer";
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getInsertColumns() {
		return "producerName";
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getInsertValues() {
		return "'" + producerName + "'";
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void setId(long id) {
		producerID = id;

	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getUpdateValues() {
		return "producerName = '" + producerName + "'";
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
		return "producer.producerID=" + producerID;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public GenericEntity getEntity(ResultSet rs) throws SQLException {
		return new Producer(rs.getLong(getTableName() + ".producerID"), rs.getString(getTableName() + ".producerName"));
	}
}
