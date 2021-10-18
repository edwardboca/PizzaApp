package model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Order implements Serializable {
    private int clientNumber;
    private String pizzaType;
    private int nbSlices;

    public Order(int clientNumber, String pizzaType, int nbSlices) {
        this.clientNumber = clientNumber;
        this.pizzaType = pizzaType;
        this.nbSlices = nbSlices;
    }

    @NonNull
    @Override
    public String toString() {
        String order = "Client number" + clientNumber + " PizzaType: " + pizzaType+ "Nb slices " +nbSlices+ " Amount: " + getAmount();
        return order;
    }

    public float getAmount() {
        float amount = 0;
        if (pizzaType.equals("Vegetarian"))
            amount = nbSlices * 2.5f;
        else if (pizzaType.equals("Cheese"))
            amount = nbSlices * 2.0f;
        else if (pizzaType.equals("Mexican"))
            amount = nbSlices * 2.4f;


        return amount;

    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public int getNbSlices() {
        return nbSlices;
    }

    public void setNbSlices(int nbSlices) {

        this.nbSlices = nbSlices;
    }
}
