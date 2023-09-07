package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Predstavlja korisnika aplikacije (knjigovodju). Korisnik ima ime, prezime,
 * korisnicko ime i sifru. Klasa implementira GenericEntity interfejs i
 * omogucava rad sa bazom podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class User implements GenericEntity {

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
	 * @param lastName  Prezime korisnika
	 * @param username  Korisnicko ime
	 * @param password  Sifra
	 * 
	 */
	public User(String firstName, String lastName, String username, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setPassword(password);
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
	 * @throws IllegalArgumentException Ako je firstName null ili prazan String
	 */
	public void setFirstName(String firstName) {
		if (firstName != null && !firstName.isEmpty()) {
			this.firstName = firstName;
		} else {
			throw new IllegalArgumentException("Ime korisnika ne sme biti null ili prazan String.");
		}
	}

	/**
	 * Postavlja prezime korisnika na zadatu vrednost.
	 *
	 * @param lastName Prezime korisnika
	 * @throws IllegalArgumentException Ako je lastName null ili prazan String
	 */
	public void setLastName(String lastName) {
		if (lastName != null && !lastName.isEmpty()) {
			this.lastName = lastName;
		} else {
			throw new IllegalArgumentException("Prezime korisnika ne sme biti null ili prazan String.");
		}
	}

	/**
	 * Vraca hash izracunat na osnovu sifre i korisnickog imena
	 * 
	 * @return hash code na osnovu sifre i korisnickog imena
	 */
    @Override
	public int hashCode() {
		return Objects.hash(password, username);
    }
    
	/**
	 * Postavlja korisnicko ime korisnika na zadatu vrednost.
	 *
	 * @param username Korisnicko ime korisnika
	 * @throws IllegalArgumentException Ako je username null ili prazan String
	 */
	public void setUsername(String username) {
		if (username != null && !username.isEmpty()) {
			this.username = username;
		} else {
			throw new IllegalArgumentException("Korisničko ime korisnika ne sme biti null ili prazan String.");
		}
	}

	/**
	 * Poredi dva korisnika po sifri i korisnickom imenu.
	 * 
	 * @param obj drugi korisnik.
	 * 
	 * @return true ako su password i username isti, a false inace
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	/**
	 * {@inheritDoc}
	 */
	/**
	 * Postavlja sifru korisnika na zadatu vrednost.
	 *
	 * @param password Sifra korisnika
	 * @throws IllegalArgumentException Ako je password null ili prazan String
	 */
	public void setPassword(String password) {
		if (password != null && !password.isEmpty()) {
			this.password = password;
		} else {
			throw new IllegalArgumentException("Šifra korisnika ne sme biti null ili prazan String.");
		}
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
		return new User(rs.getString(getTableName() + ".firstName"), rs.getString(getTableName() + ".lastName"),
				rs.getString(getTableName() + ".username"), rs.getString(getTableName() + ".password"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(long id) {

	}

}
