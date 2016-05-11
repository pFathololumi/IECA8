package net.internetengineering.myServiceHandlers;

import net.internetengineering.domain.Customer;
import net.internetengineering.server.StockMarket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hamed Ara on 4/8/2016.
 */
public class MyHttpServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!StockMarket.getInstance().containCustomer("1"))
            StockMarket.getInstance().addNewCustomer(new Customer("1","admin","admin"));
        doMyPost(request,response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!StockMarket.getInstance().containCustomer("1"))
            StockMarket.getInstance().addNewCustomer(new Customer("1","admin","admin"));
        doMyGet(request,response);
    }
    public void doMyPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
