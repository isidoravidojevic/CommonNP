package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Predstavlja proizvod sa informacijama o identifikaciji, nazivu, kolicini, jedinici mere,
 * nabavnoj i prodajnoj ceni i proizvodjacu. 
 * Implementira GenericEntity interfejs za rad sa bazom podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */
public class Product implements GenericEntity {

	/**
	 * Identifikator proizvoda kao long.
	 */
	private long productID;
	
	/**
	 * Naziv proizvoda kao String.
	 */
    private String productName;
    
    /**
	 * Kolicina proizvoda na zalihama kao int.
	 */
    private int quantity;
    
    /**
	 * Jedinica mere proizvoda kao String.
	 */
    private String measurementUnit;
    
    /**
	 * Nabavna cena proizvoda kao double.
	 */
    private double purchasePrice;
    
    /**
	 * Prodajna cena proizvoda kao double.
	 */
    private double sellingPrice;
    
    /**
	 * Proizvodjac proizvoda kao Producer.
	 */
    private Producer producer;
    
    /**
     * Konstruktor bez parametara koji inicijalizuje objekat klase Product.
     */
    public Product() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat klase Product sa zadatim vrednostima.
     *
     * @param productID Identifikacija proizvoda
     * @param productName Naziv proizvoda
     * @param quantity Kolicina proizvoda
     * @param measurementUnit Jedinica mere
     * @param purchasePrice Nabavna cena proizvoda
     * @param sellingPrice Prodajna cena proizvoda
     * @param producer Proizvodjac proizvoda
     */
	public Product(long productID, String productName, int quantity, String measurementUnit, double purchasePrice,
			double sellingPrice, Producer producer) {
		setProductID(productID);
		setProductName(productName);
		setQuantity(quantity);
		setMeasurementUnit(measurementUnit);
		setPurchasePrice(purchasePrice);
		setSellingPrice(sellingPrice);
		setProducer(producer);
	}

	/**
	 * Vraca identifikator proizvoda.
	 *
	 * @return Identifikator proizvoda
	 */
	public long getProductID() {
		return productID;
	}

	/**
	 * Postavlja identifikator proizvoda na zadatu vrednost.
	 *
	 * @param productID Identifikator proizvoda
	 */
	public void setProductID(long productID) {
		this.productID = productID;
	}

	/**
	 * Vraca naziv proizvoda.
	 *
	 * @return Naziv proizvoda
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Postavlja naziv proizvoda na zadatu vrednost.
	 *
	 * @param productName Naziv proizvoda
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Vraca kolicinu proizvoda.
	 *
	 * @return Kolicina proizvoda
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Postavlja kolicinu proizvoda na zadatu vrednost.
	 *
	 * @param quantity Kolicina proizvoda
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Vraca jedinicu mere proizvoda.
	 *
	 * @return Jedinica mere proizvoda
	 */
	public String getMeasurementUnit() {
		return measurementUnit;
	}

	/**
	 * Postavlja jedinicu mere proizvoda na zadatu vrednost.
	 *
	 * @param measurementUnit Jedinica mere proizvoda
	 */
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	/**
	 * Vraca nabavnu cenu proizvoda.
	 *
	 * @return Nabavna cena proizvoda
	 */
	public double getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * Postavlja nabavnu cenu proizvoda na zadatu vrednost.
	 *
	 * @param purchasePrice Nabavna cena proizvoda
	 */
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * Vraca prodajnu proizvoda.
	 *
	 * @return Prodajna cena proizvoda
	 */
	public double getSellingPrice() {
		return sellingPrice;
	}

	/**
	 * Postavlja prodajnu cenu proizvoda na zadatu vrednost.
	 *
	 * @param sellingPrice Prodajna cena proizvoda
	 */
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/**
	 * Vraca proizvodjaca proizvoda.
	 *
	 * @return Proizvodjac proizvoda
	 */
	public Producer getProducer() {
		return producer;
	}

	/**
	 * Postavlja proizvodjaca proizvoda na zadatu vrednost.
	 *
	 * @param producer Proizvodjac proizvoda
	 */
	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	/**
     * {@inheritDoc}
     */
	@Override
    public String getTableName() {
        return "product";
    }

	/**
     * {@inheritDoc}
     */
    @Override
    public String getInsertColumns() {
        return "productName, quantity, measurementUnit, purchasePrice, sellingPrice, producerID";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInsertValues() {
        return "'" + productName + "'," + quantity + ",'" + measurementUnit + "'," + purchasePrice + "," + sellingPrice + "," + producer.getProducerID();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(long id) {
        productID = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUpdateValues() {
        return "productName= '" + productName + "',quantity= " + quantity + ",measurementUnit= '" + measurementUnit + "',purchasePrice= " + purchasePrice
                + ",sellingPrice= " + sellingPrice + ",producerID= " + producer.getProducerID();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getJoinText() {
        return " JOIN producer ON product.producerID = producer.producerID";
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
        return "product.productID=" + productID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericEntity getEntity(ResultSet rs) throws SQLException {
    	producer = new Producer();
        return new Product(rs.getLong(getTableName() + ".productID"), rs.getString(getTableName() + ".productName"),
                rs.getInt(getTableName() + ".quantity"), rs.getString(getTableName() + ".measurementUnit"),
                rs.getDouble(getTableName() + ".purchasePrice"), rs.getDouble(getTableName() + ".sellingPrice"), (Producer) producer.getEntity(rs));
    }

}
