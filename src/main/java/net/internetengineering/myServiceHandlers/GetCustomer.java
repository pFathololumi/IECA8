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
@WebServlet("/getcustomer")
public class GetCustomer extends MyHttpServlet{

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
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("id", c.getId());
                map.put("name", c.getName());
                map.put("money", c.getMoney());
                JsonBuilder.writeToJSON(map, response);
            }catch (DataIllegalException ex){
                    out.println(ex.getMessage());
            }
    }
    
}
