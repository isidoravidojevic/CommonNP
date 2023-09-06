package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Predstavlja korisnika aplikacije (knjigovodju). Korisnik ima ime, prezime, 
 * korisnicko ime i sifru.
 * Klasa implementira GenericEntity interfejs i omogucava rad sa bazom podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class User implements GenericEntity{

	/**
	 * Ime korisnika kao String.
	 */
	private String firstName;
	
	/**
	 * Prezime korisnika kao String.
	 */
    private String lastName;
    
    /**
	 * Korisnicko ime kao String.
	 */
    private String username;
    
    /**
	 * Sifra kao String.
	 */
    private String password;

    /**
     * Konstruktor bez parametara koji inicijalizuje objekat klase User.
     */
    public User() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat klase User sa zadatim vrednostima.
     *
     * @param firstName Ime korisnika
     * @param lastName Prezime korisnika
     * @param username Korisnicko ime 
     * @param password Sifra
     * 
     */
    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
	
    /**
     * Vraca ime korisnika.
     *
     * @return Ime korisnika
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Vraca prezime korisnika.
     *
     * @return Prezime korisnika
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Vraca korisnicko ime korisnika.
     *
     * @return Korisnicko ime korisnika
     */
    public String getUsername() {
        return username;
    }

    /**
     * Vraca sifru korisnika.
     *
     * @return Sifra korisnika
     */
    public String getPassword() {
        return password;
    }

    /**
     * Postavlja ime korisnika na zadatu vrednost.
     *
     * @param firstName Ime korisnika
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Postavlja prezime korisnika na zadatu vrednost.
     *
     * @param lastName Prezime korisnika
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Postavlja korisnicko ime korisnika na zadatu vrednost.
     *
     * @param username Korisnicko ime korisnika
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Postavlja sifru korisnika na zadatu vrednost.
     *
     * @param password Sifra korisnika
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public String getTableName() {
		return "user";
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public String getInsertColumns() {
		return "firstName, lastName, username, password";
	}

	/**
     * {@inheritDoc}
     */
	@Override
    public String getInsertValues() {
        return "'" + firstName + "', '" + lastName + "', '" + username + "', '" + password + "'";
    }

	/**
     * {@inheritDoc}
     */
    @Override
    public String getUpdateValues() {
        return "";
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
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericEntity getEntity(ResultSet rs) throws SQLException {
        return new User(rs.getString(getTableName()+".firstName"), rs.getString(getTableName()+".lastName"), rs.getString(getTableName()+".username"), rs.getString(getTableName()+".password"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(long id) {
        
    }

}
