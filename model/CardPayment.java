package model;

public class CardPayment extends Payment {
    public CardPayment(float amount) {
        super(amount);
    }

    @Override
    public float processPayment(float priceToBePaid) {
        // Assume card always succeeds for simulation
        return 0; // no change
    }
}