package parking.paymentMethod;

public class CardPaymentMethod implements PaymentMethod {
    public void processPayment ( double fee ) {
        System.out.println( " The payment of amount " + fee + " is processing!!");
    }
}
