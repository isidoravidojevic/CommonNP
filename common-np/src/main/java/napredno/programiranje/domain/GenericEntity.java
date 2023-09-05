package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GenericEntity {

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
