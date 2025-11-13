package inventory.inventoryObserver;

public class SupplierNotifier implements InventoryObserver {
    private String supplierName ;
    private String contactInfo ;

    public SupplierNotifier(String supplierName, String contactInfo) {
        this.supplierName = supplierName;
        this.contactInfo = contactInfo;
    }

    @Override
    public void update( Product product) {
        if (product.getQuantity() < product.getThreshold()) {
            // Send email notification to supplier
            System.out.println("Notification sent to " + supplierName
                + " for low stock of " + product.getName());
        }
    }
}
