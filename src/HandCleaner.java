public class HandCleaner {
    private String brand;
    private int quantity, quantityRemaining;
    private double alcoholPercentage;
    private double price;

    public HandCleaner(String brand, int quantity, double alcoholPercentage, double price) {
        setBrand(brand);
        setQuantity(quantity);
        setQuantityRemaining(quantity);
        setAlcoholPercentage(alcoholPercentage);
        setPrice(price);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand.trim().isEmpty())
            throw new IllegalArgumentException("brand cannot be blank");
        else
            this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity>0 && quantity<=5000)
            this.quantity = quantity;
        else
            throw new IllegalArgumentException("quantity must be >0 and <= 5000");
    }

    public int getQuantityRemaining() {
        return quantityRemaining;
    }

    public void setQuantityRemaining(int quantityRemaining) {
        if (quantityRemaining>=0 && quantityRemaining <= quantity)
            this.quantityRemaining = quantityRemaining;
        else
            throw new IllegalArgumentException("quantity remaining must be 0-"+quantity);
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        if (alcoholPercentage>=40 && alcoholPercentage<=100)
            this.alcoholPercentage = alcoholPercentage;
        else
            throw new IllegalArgumentException("alcohol level must be 40-100");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price>=0 && price<=50)
            this.price = price;
        else
            throw new IllegalArgumentException("price must be 0-50");
    }

    /**
     * This method will return the volume dispensed (or pumped) with 1 push of the
     * lever. It is assumed that each pump is 10 ml.
     * The method will update the volume remaining in the bottle
     */
    public int pump()
    {
        //check if there is any liquid left in the bottle
        if (quantityRemaining>=10)
        {
            quantityRemaining -= 10;
            return 10;
        }
        int pumpAmount = quantityRemaining;
        quantityRemaining=0;
        return pumpAmount;
    }

    public String toString()
    {
        return String.format("%s, bottle volume: %d ml, amount remaining: %d ml,  price: $%.2f",
                brand, quantity, quantityRemaining, price);
    }
}
