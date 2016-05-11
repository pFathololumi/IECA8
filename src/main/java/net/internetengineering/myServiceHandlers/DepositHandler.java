package net.internetengineering.myServiceHandlers;

import net.internetengineering.domain.dealing.TransactionType;
import net.internetengineering.exception.DataIllegalException;
import net.internetengineering.server.StockMarket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deposit")
public class DepositHandler extends MyHttpServlet{

	public void doMyPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String id = request.getParameter("id");
			Long amount = Long.parseLong(request.getParameter("amount"));
			if (id == null || id.isEmpty() || amount == null)
				throw new DataIllegalException("Mismatched Parameters");
			if (StockMarket.getInstance().containCustomer(id)){
				StockMarket.getInstance().executeFinancialTransaction(id, TransactionType.DEPOSIT,amount);
				out.println("Successful");
			}
			else {
				throw new DataIllegalException("Unknown user id");
			}
//			request.setAttribute("successes", logger.getAndFlushMyLogger());
			StockMarket.getInstance().getDepositRequests().remove(id);
		}catch (DataIllegalException ex){
			out.println(ex.getMessage());
//			request.setAttribute("errors", logger.getAndFlushMyLogger());
		}catch (Exception e){
			out.println("Mismatched Parameters");
//			request.setAttribute("errors", logger.getAndFlushMyLogger());
		}
//		request.getRequestDispatcher("show-info.jsp").forward(request, response);

	}

    public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doMyPost(request,response);
    }

}