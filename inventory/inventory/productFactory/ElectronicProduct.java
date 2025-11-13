package inventory.productFactory;

public class ElectronicProduct extends Product {
    
    private String brand ;
    private String warranty ;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public ElectronicProduct( String id , String name, double price, int quantity, int thresholdQuantity) {
        super() ;
        setId(id);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setThresholdQuantity(thresholdQuantity);
        setProductCategory(inventory.productFactory.ProductCategory.ELECTRONICS )  ;
    }

    
}
