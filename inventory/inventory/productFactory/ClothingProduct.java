package inventory.productFactory;

public class ClothingProduct extends Product {
    
    private String size ;
    private String material ;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public ClothingProduct( String id , String name, double price, int quantity, int thresholdQuantity ) {
        super() ;
        setId(id);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setThresholdQuantity(thresholdQuantity);
        setProductCategory(inventory.productFactory.ProductCategory.CLOTHING) ;
    }
    
}
