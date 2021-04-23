package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DataBeans.Client;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;
import ProjectDAO.PeriodicalsDAO;

public class DeletePeriodical extends Action {
	private PeriodicalsDAO periodicalDAO;
	
	public DeletePeriodical(Model model) {
		periodicalDAO = model.getPeriodicalsDAO();
    }
	
	public String getName() {
        return "deletePeriodical.do";
    }

    public String performGet(HttpServletRequest request) {
    	return performPost(request);
    }
    
    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("client");
        if(client == null) {
        	return "login.do";
        }
        String periodicalName = request.getParameter("periodicalName");
        try {
        	periodicalDAO.periodicalDelete(periodicalName);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "main2.do";
    }
}