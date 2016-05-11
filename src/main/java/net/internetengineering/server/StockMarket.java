package net.internetengineering.server;

import net.internetengineering.domain.Customer;
import net.internetengineering.domain.dealing.*;
import net.internetengineering.exception.DataIllegalException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hamed Ara on 2/18/2016.
 */
public class StockMarket {
    private static StockMarket stockMarket=null;
    private static List<Instrument> instruments;
    private static HashMap<String, Customer> customers;
    private static HashMap<String, String> depositRequests;

    private StockMarket(){
        instruments = new ArrayList<Instrument>();
        customers = new HashMap<String, Customer>();
        depositRequests = new HashMap<String, String>();
    }

    public static StockMarket getInstance(){
        if(stockMarket==null)
            stockMarket=new StockMarket();
        return stockMarket;
    }

    public void addNewCustomer(Customer newOne){
        customers.put(newOne.getId(), newOne);
    }

    public Boolean containCustomer(String id ){
        return customers.containsKey(id);
    }

    public void executeFinancialTransaction(String id, TransactionType type, Long amount){
        customers.get(id).executeTransaction(type, amount);
    }

    public Boolean customerHasEnoughMoney(String id,Long amount){
        return customers.get(id).hasEnoughMoney(amount);
    }

    public void executeSellingOffer(PrintWriter out, SellingOffer offer, String symbol) throws DataIllegalException{
        if(offer.isAdminOffer())
            addOrUpdateInstrumentByAdmin(symbol,offer);
            Instrument instrument = loadVerifiedParameters(offer,symbol);

            Customer customer = customers.get(offer.getID());
            if(!customer.hasEnoughStock(symbol, offer) && !offer.isAdminOffer()){
                throw new DataIllegalException("Not enough share");
            }

            instrument.executeSellingByType(out,offer);
    }

    public void executeBuyingOffer(PrintWriter out, BuyingOffer offer, String symbol) throws DataIllegalException{
        Customer customer = customers.get(offer.getID());
        if(!customer.hasEnoughMoney(offer.getPrice() * offer.getQuantity())){
            throw  new DataIllegalException("Not enough money");
        }
        if(offer.isAdminOffer())
            deleteOrUpdateInstrumentByAdmin(symbol,offer);
        else
            customer.executeTransaction(TransactionType.WITHDRAW, offer.getPrice()*offer.getQuantity());
        Instrument instrument = loadVerifiedParameters(offer, symbol);
        instrument.executeBuyingByType(out, offer);
    }

    private void addOrUpdateInstrumentByAdmin(String symbol,Offering offer) {
    	boolean flag = false;
    	for(Instrument i : instruments){
			if(i.symbolIsMatched(symbol)){
				i.changeQuantity("add", offer.getQuantity());
				flag = true;
				break;
			}
		}
    	if(!flag)
    		instruments.add(new Instrument(symbol, offer.getQuantity()));
    }
    private void deleteOrUpdateInstrumentByAdmin(String symbol,Offering offer) {
    	for(Instrument i : instruments){
			if(i.symbolIsMatched(symbol)){
				i.changeQuantity("delete", offer.getQuantity());
				return;
			}
		}
    }

    private Instrument loadVerifiedParameters(Offering offer, String symbol) throws DataIllegalException {
        Instrument instrument=null;
        if(!customerIsRegistered(offer))
            throw new DataIllegalException("Unknown user id");
        if((instrument= getSymbol(symbol))==null)
            throw new DataIllegalException("Invalid symbol id");
        
        return instrument;
    }


    private Boolean customerIsRegistered(Offering offer){
        if( customers.get(offer.getID())==null)
            return false;
        else
            return true;
    }

    public Customer getCustomer(String id){
        return customers.get(id);
    }

    private Instrument getSymbol(String inst){
        for(Instrument instrument : instruments)
            if( instrument.symbolIsMatched(inst))
                return instrument;
        return null;
    }
    public void initializeInstruments(){
        Instrument ins1 = new Instrument("Peugeot",242L);
        ins1.initialOfferLists();
        instruments.add(ins1);
        ins1 = new Instrument("Renault",8585L);
        ins1.initialOfferLists();
        instruments.add(ins1);
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public HashMap<String, String> getDepositRequests(){
        return depositRequests;
    }

    public void setDepositRequest(String id, String amount){
        depositRequests.put(id, amount);
    }

    public static void changeCustomerProperty(SellingOffer sOffer, BuyingOffer bOffer, Long price, Long count, String symbol){
        Customer seller = customers.get(sOffer.getID());
        Customer buyer = customers.get(bOffer.getID());

        seller.executeTransaction(TransactionType.DEPOSIT, price*count);
        seller.updateInstruments("delete", count, symbol);

//        buyer.executeTransaction(TransactionType.WITHDRAW, price*count);
        buyer.updateInstruments("add", count, symbol);

    }
}
