package parking;

import parking.paymentMethod.PaymentMethod;

public class Payment {

    private double amount ;
    private PaymentMethod paymentMethod ;

    public Payment(double amount, PaymentMethod paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }
    // Process the payment using the assigned strategy
    public void processPayment() {
        if (amount > 0) {
            paymentMethod.processPayment(amount);  // Delegating to strategy
        } else {
            System.out.println("Invalid payment amount.");
        }
    }

    

}
