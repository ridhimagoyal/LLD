package inventory.productFactory;

public abstract class Product {

    private String id ;
    private double price ;
    private int quantity ;
    private String name ;
    private int thresholdQuantity ;
    private ProductCategory category ;

    public void setId( String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setThresholdQuantity(int thresholdQuantity) {
        this.thresholdQuantity = thresholdQuantity;
    }

    public void setProductCategory(ProductCategory category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public int getThresholdQuantity() {
        return thresholdQuantity;
    }

    public ProductCategory getCategory() {
        return category;
    }    

}
