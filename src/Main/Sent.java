package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import DataBeans.Client;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;
import ProjectDAO.PeriodicalsDAO;
import ProjectDAO.SubscriptionsDAO;

public class Sent extends Action {
	private ClientDAO clientDAO;
	private PeriodicalsDAO periodicalDAO;
	private SubscriptionsDAO subscriptionsDAO;
	
	public Sent(Model model) {
		clientDAO = model.getClientDAO();
		periodicalDAO = model.getPeriodicalsDAO();
		subscriptionsDAO = model.getSubscriptionsDAO();
    }
	
	public String getName() {
        return "sent.do";
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
			subscriptionsDAO.subscriptionDelete(client.getUserName(), periodicalName);
			subscriptionsDAO.subscription2ndUpdate(periodicalName);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "profile.do";
    }
}