package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class User implements GenericEntity{

	private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
	
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.firstName);
        hash = 23 * hash + Objects.hashCode(this.lastName);
        hash = 23 * hash + Objects.hashCode(this.username);
        hash = 23 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        
        return Objects.equals(this.username, other.username);
    }
    
	@Override
	public String getTableName() {
		return "user";
	}

	@Override
	public String getInsertColumns() {
		return "firstName, lastName, username, password";
	}

	@Override
    public String getInsertValues() {
        return "'" + firstName + "', '" + lastName + "', '" + username + "', '" + password + "'";
    }

    @Override
    public String getUpdateValues() {
        return "";
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
        return "";
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws SQLException {
        return new User(rs.getString(getTableName()+".firstName"), rs.getString(getTableName()+".lastName"), rs.getString(getTableName()+".username"), rs.getString(getTableName()+".password"));
    }

    @Override
    public void setId(long id) {
        
    }

}
