package inventory.inventoryObserver;

import inventory.productFactory.Product ;
public interface InventoryObserver {
    void update ( Product product ) ;
}
