package net.internetengineering.myServiceHandlers;

import net.internetengineering.domain.Customer;
import net.internetengineering.exception.DataIllegalException;
import net.internetengineering.server.StockMarket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add")
public class AddCustomer extends MyHttpServlet{
        @Override
	public void doMyPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String family = request.getParameter("family");
			if (id == null || id.isEmpty() || name == null || name.isEmpty()
					|| family == null || family.isEmpty()) {
				throw new DataIllegalException("Mismatched Parameters");
			}
			if (StockMarket.getInstance().containCustomer(id)) {
				throw new DataIllegalException("Repeated id");

			} else {
				StockMarket.getInstance().addNewCustomer(new Customer(id, name, family));
				out.println("New user is added");
//				request.setAttribute("successes", logger.getAndFlushMyLogger());
			}
		}catch (DataIllegalException ex){
			out.println(ex.getMessage());
//			request.setAttribute("errors", logger.getAndFlushMyLogger());
		}
//		request.getRequestDispatcher("show-info.jsp").forward(request, response);
	}
        @Override
	public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
	}
}