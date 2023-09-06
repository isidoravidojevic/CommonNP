package napredno.programiranje.domain;

/**
 * Enumeracija koja predstavlja razlicite nacine prijema fakture.
 * 
 * @author Isidora Vidojevic
 * 
 */

public enum InvoiceReceptionMeans {
	
	/**
     * Elektronski nacin prijema fakture.
     */
	ELEKTRONSKI,
	
	/**
     * Licno preuzimanje fakture.
     */
	LICNO,
	
	/**
     * Prijem fakture putem poste.
     */
	POSTA;
}
