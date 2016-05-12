/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.internetengineering.myServiceHandlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.internetengineering.domain.Customer;
import net.internetengineering.exception.DataIllegalException;
import net.internetengineering.server.StockMarket;
import net.internetengineering.utils.JsonBuilder;

/**
 *
 * @author Hamed Ara
 */
@WebServlet("/getcustomerins")
public class GetCustomerIns extends MyHttpServlet{

    @Override
    public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out= response.getWriter();
            try {
                String id = request.getParameter("id");
                if (id == null || id.isEmpty()) {
                    throw new DataIllegalException("Mismatched Parameters");
                }
                if (!StockMarket.getInstance().containCustomer(id)) {
                    throw new DataIllegalException("Invalid ID");
                }
                Customer c = StockMarket.getInstance().getCustomer(id);

                JSONArray list = new JSONArray();
                for (int i = 0; i <  c.getInstruments().size(); i++ ) {
                    list.put(i,c.getInstruments().get(i).getSymbol());
                }
                
                
                response.getWriter().print(list);
                response.setContentType("application/json");
                
            }catch (DataIllegalException ex){
                    out.println(ex.getMessage());
            }
    }
    
}
