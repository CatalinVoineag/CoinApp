package catalin.coinnews.models;

import java.util.Date;

/**
 * Created by catalin on 17/12/17.
 */

public class Holding {

    private float quantity;
    private float tradePrice;
    private Date tradeDate;
    private String notes;

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(float tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
