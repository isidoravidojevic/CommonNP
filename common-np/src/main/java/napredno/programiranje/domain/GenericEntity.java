package napredno.programiranje.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface GenericEntity extends Serializable {

	String getTableName();
    
    String getInsertColumns();
    
    String getInsertValues();
    
    void setId(long id);
    
    String getUpdateValues();
    
    String getJoinText();
    
    String getSelectedText();
    
    String getID();
    
    GenericEntity getEntity(ResultSet rs) throws SQLException;
}
