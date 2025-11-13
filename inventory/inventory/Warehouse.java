package inventory;

import java.util.* ;

import inventory.productFactory.Product; 

public class Warehouse {

    private int warehouseId ;
    private String name ;
    private String location ;

    public int getWarehouseId() {
        return warehouseId;
    }
    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    private Map<String, Product> products ; 

    public Warehouse( int warehouseId, String name , String location ) {
        this.warehouseId = warehouseId ;
        this.name = name ;
        this.location = location ;
        this.products = new HashMap<>() ;
    }


    public void addProduct ( Product product , int quantity ) {
        String sku = product.getId();
        if (products.containsKey(sku)) {
            // Product exists, update quantity
            Product existingProduct = products.get(sku);
            existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
        } else {
            // New product, add to inventory
            product.setQuantity(quantity);
            products.put(sku, product);
        }
        System.out.println(quantity + " units of " + product.getName()
            + " (SKU: " + sku + ") added to " + name
            + ". New quantity: " + getAvailableQuantity(sku));
    }

    public void removeProduct( Product product , int quantity ) {

        String sku = product.getId() ;
        if ( products.containsKey(sku) ) {
            Product existingProduct = products.get(sku) ;
            int currentQuantity = existingProduct.getQuantity() ;
            if ( currentQuantity >= quantity ) {
                existingProduct.setQuantity( currentQuantity - quantity ) ;
                System.out.println( quantity + " units of " + product.getName()
                    + " (SKU: " + sku + ") removed from " + name
                    + ". New quantity: " + getAvailableQuantity(sku) ) ;
                if (product.getQuantity() == 0) {
                    // Remove products with zero quantity
                    products.remove(sku);
                    System.out.println("Product " + product.getName()
                        + " removed from inventory as quantity is now zero.");
                }
            } else {
                System.out.println("Insufficient stock to remove " + quantity + " units of " + product.getName()
                    + " (SKU: " + sku + "). Current stock: " + currentQuantity ) ;
            }
        } else {
            System.out.println("ERROR: Product " + product.getName() + 
            " (SKU: " + sku + ") not found in " + name ) ;
        }

    }

    public int getAvailableQuantity(String sku) {
        if (products.containsKey(sku)) {
        return products.get(sku).getQuantity();
        }
        return 0; // Product not found
    }


    public void infoWarehouse() {
        System.out.println("Warehouse ID: " + warehouseId + ", Location: " + location ) ;
        System.out.println("Product Stock: ") ;
        for( Map.Entry<String, Product> entry : products.entrySet() ) {
            System.out.println("Product ID: " + entry.getKey() + ", Quantity: " + entry.getValue().getQuantity() ) ;
        }
    }

    public Map<String, Product> getProducts() {
        return products;
    }


}
