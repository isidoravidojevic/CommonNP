package napredno.programiranje.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product implements GenericEntity {

	private long productID;
    private String productName;
    private int quantity;
    private String measurementUnit;
    private double purchasePrice;
    private double sellingPrice;
    private Producer producer;
    
    public Product() {
    }

	public Product(long productID, String productName, int quantity, String measurementUnit, double purchasePrice,
			double sellingPrice, Producer producer) {
		this.productID = productID;
		this.productName = productName;
		this.quantity = quantity;
		this.measurementUnit = measurementUnit;
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
		this.producer = producer;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	@Override
    public String getTableName() {
        return "product";
    }

    @Override
    public String getInsertColumns() {
        return "productName, quantity, measurementUnit, purchasePrice, sellingPrice, producerID";
    }

    @Override
    public String getInsertValues() {
        return "'" + productName + "'," + quantity + ",'" + measurementUnit + "'," + purchasePrice + "," + sellingPrice + "," + producer.getProducerID();
    }

    @Override
    public void setId(long id) {
        productID = id;
    }

    @Override
    public String getUpdateValues() {
        return "productName = '" + productName + "' , quantity= " + quantity + ", measurementUnit= '" + measurementUnit + "' ,purchasePrice=" + purchasePrice
                + ", sellingPrice= " + sellingPrice + ", producerID= " + producer.getProducerID();
    }

    @Override
    public String getJoinText() {
        return " JOIN producer ON product.producerID = producer.producerID";
    }

    @Override
    public String getSelectedText() {
        return "";
    }

    @Override
    public String getID() {
        return "product.productID=" + productID;
    }

    @Override
    public GenericEntity getEntity(ResultSet rs) throws SQLException {
    	producer = new Producer();
        return new Product(rs.getLong(getTableName() + ".productID"), rs.getString(getTableName() + ".productName"),
                rs.getInt(getTableName() + ".quantity"), rs.getString(getTableName() + ".measurementUnit"),
                rs.getDouble(getTableName() + ".purchasePrice"), rs.getDouble(getTableName() + ".sellingPrice"), (Producer) producer.getEntity(rs));
    }

}
