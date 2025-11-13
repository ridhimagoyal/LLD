package inventory;

import inventory.productFactory.ProductFactory;
import inventory.replenishshmentStrategy.* ;
import java.util.* ;

import inventory.inventoryObserver.InventoryObserver;
import inventory.productFactory.Product ;

    

public class InventoryManager {
    
    private List<Warehouse> warehouses ;
    private ProductFactory productFactory ;
    private List<InventoryObserver> observers ;
    private ReplenishmentStrategy replenishmentStrategy ;

    private static InventoryManager instance ;

    private InventoryManager() {
        observers = new ArrayList<>() ;
        warehouses = new ArrayList<>() ;
        productFactory = new ProductFactory() ;
    }

    public void addObserver( InventoryObserver observer ) {
        observers.add( observer ) ;
    }

    public void removeObserver( InventoryObserver observer ) {
        observers.remove( observer ) ;
    }

    public void notifyObservers(Product product) {
        for (InventoryObserver observer : observers) {
        observer.update(product);
        }
    }


    public void setReplenishmentStrategy( ReplenishmentStrategy strategy ) {
        this.replenishmentStrategy = strategy ;
    }

    public static synchronized InventoryManager getInstance() {
        if ( InventoryManager.instance == null ) {
            InventoryManager.instance = new InventoryManager() ;
        }
        return InventoryManager.instance ;
    }

    public void addWarehouse( Warehouse warehouse ) {
        warehouses.add( warehouse ) ;
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
    }


    public Warehouse getWarehouseById( int warehouseId ) {
        for ( Warehouse warehouse : warehouses ) {
            if ( warehouse.getWarehouseId() == warehouseId ) {
                return warehouse ;
            }
        }
        return null ;
    }

    public Product getProductBySku( String sku ) {
        for ( Warehouse warehouse : warehouses ) {
            Map<String, Product> products = warehouse.getProducts() ;
            if ( products.containsKey( sku ) ) {
                return products.get( sku ) ;
            }
        }
        return null ;
    }

    public void checkAndReplenish( String sku ) {
        Product product = getProductBySku(sku) ;
        if ( product == null ) {
            System.out.println("Product with SKU: " + sku + " not found in inventory.") ;
            return ;
        }
        if ( product.getQuantity() < product.getThresholdQuantity() ) {
            System.out.println("Product " + product.getName() + " (SKU: " + sku + ") is below threshold. Initiating replenishment.") ;
            replenishmentStrategy.replenish( product ) ;
        } else {
            System.out.println("Product " + product.getName() + " (SKU: " + sku + ") has sufficient stock.") ;
        }
    } 

    public void performInventoryCheck() {
        for ( Warehouse warehouse : warehouses ) {
            Map<String, Product> products = warehouse.getProducts() ;
            for ( Product product : products.values() ) {
                checkAndReplenish( product.getId() ) ;
            }
        }
    }


}
