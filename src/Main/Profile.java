package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

import DataBeans.Client;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;
import ProjectDAO.SubscriptionsDAO;

public class Profile extends Action {
	private ClientDAO clientDAO;
	private SubscriptionsDAO subscriptionsDAO;
	
	public Profile(Model model) {
		clientDAO = model.getClientDAO();
		subscriptionsDAO = model.getSubscriptionsDAO();
		
    }
	
	public String getName() {
        return "profile.do";
    }

    public String performGet(HttpServletRequest request) {
    	return performPost(request);
    }
    
    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	Client client = (Client) session.getAttribute("client");
    	if(request.getParameter("fullName") != null) {
    		String fullName = request.getParameter("fullName");
    		try {
    			request.setAttribute("employee", clientDAO.readByFullName(fullName));
    			request.setAttribute("subscriptions", subscriptionsDAO.readSubscriptionsByName(clientDAO.readByFullName(fullName).getUserName()));
    		} catch (MyDAOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		return "profile2.jsp";
    		
    	}
    	else {
    		if (client == null) {
                return "login.do";
            }
            try {
    			request.setAttribute("subscriptions", subscriptionsDAO.readSubscriptionsByName(client.getUserName()));
    		} catch (MyDAOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            return ("profile.jsp");
    	}
    }
}