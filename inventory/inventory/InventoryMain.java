package inventory;

import inventory.productFactory.Product;
import inventory.productFactory.ProductFactory;
import inventory.replenishshmentStrategy.BulkOrderStrategy;
import inventory.replenishshmentStrategy.JustInTimeStrategy;

public class InventoryMain {
    public static void main(String[] args) {

        InventoryManager inventoryManager = InventoryManager.getInstance() ;

        Warehouse warehouse1 = new Warehouse( 1, "Warehouse A", "Location A" ) ;
        Warehouse warehouse2 = new Warehouse( 2, "Warehouse B", "Location B" ) ;

        inventoryManager.addWarehouse( warehouse1 ) ;
        inventoryManager.addWarehouse( warehouse2 ) ;

        ProductFactory productFactory = new ProductFactory() ;

        // create products using the correct factory signature: (ProductCategory, sku, name, price, quantity, reorderThreshold)
        Product product1 = productFactory.createProduct(inventory.productFactory.ProductCategory.ELECTRONICS, "SKU123", "Laptop", 999.99, 10, 2) ;
        Product product2 = productFactory.createProduct(inventory.productFactory.ProductCategory.CLOTHING, "SKU456", "Jeans", 49.99, 50, 10) ;
        warehouse1.addProduct( product1 , 5) ;
        warehouse2.addProduct( product2 , 10 ) ;

        inventoryManager.setReplenishmentStrategy( new JustInTimeStrategy() ) ;

        inventoryManager.performInventoryCheck();

        // Switch replenishment strategy to Bulk Order
        inventoryManager.setReplenishmentStrategy(new BulkOrderStrategy());

        // Replenish a specific product if needed
        inventoryManager.checkAndReplenish("SKU123");


    }
}
