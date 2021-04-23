package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import DataBeans.Client;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;
import ProjectDAO.PeriodicalsDAO;
import ProjectDAO.SubscriptionsDAO;

public class changeAvailability extends Action {
	private ClientDAO clientDAO;
	private PeriodicalsDAO periodicalDAO;
	private SubscriptionsDAO subscriptionsDAO;
	
	public changeAvailability(Model model) {
		clientDAO = model.getClientDAO();
		periodicalDAO = model.getPeriodicalsDAO();
		subscriptionsDAO = model.getSubscriptionsDAO();
    }
	
	public String getName() {
        return "changeAvailability.do";
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
        String type = request.getParameter("changeType");
        try {
        	if(type.equals("Available")) {
        		periodicalDAO.periodicalUpdate(periodicalName, "Not Available");        		
        	}
        	else {
        		periodicalDAO.periodicalUpdate(periodicalName, "Available");    
        	}
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "main2.do";
    }
}