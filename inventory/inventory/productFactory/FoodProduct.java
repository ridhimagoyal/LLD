package inventory.productFactory;

public class FoodProduct extends Product {
    
    private String expirationDate ;
    private String brand ;

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public FoodProduct( String id , String name, double price, int quantity, int thresholdQuantity ) {
        super() ;
        setId(id);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setThresholdQuantity(thresholdQuantity);
        setProductCategory(inventory.productFactory.ProductCategory.FOOD) ;
    }
    
}
