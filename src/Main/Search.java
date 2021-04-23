package Main;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ProjectDAO.ClientDAO;
import ProjectDAO.PeriodicalsDAO;
import DataBeans.Client;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;

public class Search extends Action {
	private ClientDAO clientDAO;
	private PeriodicalsDAO periodicalDAO;
	
	public Search(Model model) {
		clientDAO = model.getClientDAO();
		periodicalDAO = model.getPeriodicalsDAO();
    }
	
	public String getName() {
        return "search.do";
    }

    public String performGet(HttpServletRequest request) {
    	return performPost(request);
    }
    
    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("client");
        int currentPage;
        if(request.getParameter("currentPage") == null) {
        	currentPage = 1;
        }
        else {
        	currentPage = Integer.parseInt(request.getParameter("currentPage"));        	
        }
        String txt = request.getParameter("search");
        int recordsPerPage = 6;
        int rows;
        try {
        	rows = periodicalDAO.getNumberOfRowsByName(txt.toLowerCase());
			int nOfPages = rows / recordsPerPage;
			if (rows % recordsPerPage != 0) {
				nOfPages++;
			}
			request.setAttribute("inputTxt", txt);
			try {
				request.setAttribute("periodicals", periodicalDAO.readPeriodicalsByName(txt.toLowerCase(),currentPage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("noOfPages", nOfPages);
	        request.setAttribute("currentPage", currentPage);
		} catch (MyDAOException e) {
			e.printStackTrace();
			return "error.jsp";
		}
        return ("main.jsp");
    }
}