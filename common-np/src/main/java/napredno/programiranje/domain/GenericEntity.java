package napredno.programiranje.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ovaj interfejs predstavlja genericki entitet koji se koristi za rad sa bazom podataka.
 * Implementacija ovog interfejsa omogucava upravljanje informacijama o entitetu, kao sto su tabela,
 * kolone za unos i vrednosti za unos, a takodje pruza metode za rad sa identifikatorom entiteta i
 * za izvlacenje entiteta iz rezultata SQL upita.
 * 
 * Entiteti koji implementiraju ovaj interfejs treba da obezbede konkretne implementacije
 * svih navedenih metoda kako bi omogucili integraciju sa bazom podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public interface GenericEntity extends Serializable {

	/**
     * Vraca ime tabele u bazi podataka koju entitet predstavlja.
     *
     * @return Ime tabele
     */
	String getTableName();
    
	/**
     * Vraca string koji sadrzi nazive kolona u tabeli za unos podataka.
     *
     * @return String sa nazivima kolona za unos
     */
    String getInsertColumns();
    
    /**
     * Vraca string koji sadrzi vrednosti koje treba uneti u odgovarajuce kolone za unos.
     *
     * @return String sa vrednostima za unos
     */
    String getInsertValues();
    
    /**
     * Postavlja ID entiteta na zadatu vrednost.
     *
     * @param id Vrednost identifikatora entiteta
     */
    void setId(long id);
    
    /**
     * Vraca string sa vrednostima koje treba azurirati u bazi podataka za entitet.
     *
     * @return String sa vrednostima za azuriranje
     */
    String getUpdateValues();
    
    /**
     * Vraca string sa tekstom za spajanje (JOIN) tabele sa drugim tabelama u SQL upitu.
     *
     * @return String sa tekstom za spajanje
     */
    String getJoinText();
    
    /**
     * Vraca string sa tekstom koji definise selekciju entiteta u SQL upitu.
     *
     * @return String sa tekstom selekcije
     */
    String getSelectedText();
    
    /**
     * Vraca string koji predstavlja uslov za identifikaciju entiteta u SQL upitu.
     *
     * @return String sa uslovom identifikacije
     */
    String getID();
    
    /**
     * Kreira i vraca instancu generickog entiteta na osnovu podataka iz ResultSet objekta.
     * 
     * @param rs ResultSet objekat sa podacima
     * @return Instanca generickog entiteta sa podacima iz ResultSet-a
     * @throws SQLException ako nastane problem pri citanju podataka iz ResultSet-a
     */
    GenericEntity getEntity(ResultSet rs) throws SQLException;
}
