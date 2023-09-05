package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Producer implements GenericEntity {

	private long producerID;
	private String producerName;
	
	public Producer() {
	}

	public Producer(long producerID, String producerName) {
		this.producerID = producerID;
		this.producerName = producerName;
	}

	public long getProducerID() {
		return producerID;
	}

	public void setProducerID(long producerID) {
		this.producerID = producerID;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	@Override
	public String getTableName() {
		return "producer";
	}

	@Override
	public String getInsertColumns() {
		return "producerName";
	}

	@Override
	public String getInsertValues() {
		return "'" + producerName + "'";
	}

	@Override
	public void setId(long id) {
		producerID = id;

	}

	@Override
	public String getUpdateValues() {
		return "producerName = '" + producerName + "'";
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
		return "producer.producerID=" + producerID;
	}

	@Override
	public GenericEntity getEntity(ResultSet rs) throws SQLException {
		return new Producer(rs.getLong(getTableName() + ".producerID"), rs.getString(getTableName() + ".producerName"));
	}
}
