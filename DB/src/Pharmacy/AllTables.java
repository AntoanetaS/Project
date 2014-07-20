package Pharmacy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ViewClients
 */
@WebServlet("/ViewClients")
public class AllTables extends HttpServlet {
    
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllTables() {
    	super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Set encoding support Bulgarian symbol
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Clients cl = new Clients();
		response.getWriter().println(cl.Search("88"));
		cl.Insert("Тошко","Namotkov","711212350");
		response.getWriter().println(cl.getTable());
		Medicament md = new Medicament();
		response.getWriter().println(md.getTable());
		response.getWriter().println(md.Search("Aspirin"));
		
		Orders or = new Orders();
		response.getWriter().println(or.getTable());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
