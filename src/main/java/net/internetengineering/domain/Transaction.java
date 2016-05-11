package net.internetengineering.domain;

/**
 * Created by Hamed Ara on 4/7/2016.
 */
public class Transaction {
    public String buyer;
    public String seller;
    public String instrument;
    public String typeOfTrade;
    public String quantity;
    public String buyerRemainedMoney;
    public String sellerCurrentMoney;

    public Transaction(String buyer, String seller, String instrument, String typeOfTrade, String quantity, String buyerRemainedMoney, String sellerCurrentMoney) {
        this.buyer = buyer;
        this.seller = seller;
        this.instrument = instrument;
        this.typeOfTrade = typeOfTrade;
        this.quantity = quantity;
        this.buyerRemainedMoney = buyerRemainedMoney;
        this.sellerCurrentMoney = sellerCurrentMoney;
    }
}
