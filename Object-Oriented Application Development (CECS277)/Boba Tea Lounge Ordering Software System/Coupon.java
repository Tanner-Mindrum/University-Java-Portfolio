public class Coupon {

    private String itemType;
    private double percentDiscount;
    private boolean isValid;

    /**
     * Constructs an invalid coupon with no discount
     * **/
    public Coupon() {
        itemType = "Not a valid coupon.";
        percentDiscount = 0;
        isValid = false;
    }

    public String getType () {
        return this.itemType;
    }

    /**
     * creates a coupon with type of item it is applicable to,
     * and a percent discount
     * @param item - type of item
     * @param discount - the discount as a decimal percent
     * **/
    public Coupon(String item, double discount) {
        itemType = item;
        percentDiscount = discount;
        isValid = true;
    }

    /**
     * gets the discount of this coupon
     * @return the discount as a percent decimal
     * **/
    public double getDiscount() {
        return percentDiscount;
    }


    /**
     * returns a string representation of this coupon that reads
     * 'This coupon is valid for <discount amount>% off any <item type> time.'
     * @return the Coupon object as a string
     * **/
    public String toString() {
        if(isValid) {
            return String.format("This coupon is valid for %1.0f%% off any %s item.",
                    percentDiscount * 100, itemType) ;}
        else { return "Invalid coupon."; }
    }
}
