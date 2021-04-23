package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;
import ProjectDAO.PeriodicalsDAO;
import ProjectDAO.SubscriptionsDAO;
import DataBeans.Client;
import DataBeans.Subscription;

public class Periodical extends Action {
    private ClientDAO clientDAO;
    private PeriodicalsDAO periodicalDAO;
    private SubscriptionsDAO subscriptionsDAO;
    
    public Periodical(Model model) {
    	clientDAO = model.getClientDAO();
    	periodicalDAO = model.getPeriodicalsDAO();
    	subscriptionsDAO = model.getSubscriptionsDAO();
    }
    
    public String getName() {
        return "periodical.do";
    }  
    
    public String performGet(HttpServletRequest request) {
    	return performPost(request);
    }

    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession(true);
        Client client = (Client) session.getAttribute("client");
    	if (client == null) {
            return "login.do";
        }
        String periodicalName = request.getParameter("periodicalName");
        try {
			DataBeans.Periodical periodical = periodicalDAO.read(periodicalName);
			if(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName).length == 0) {
				request.setAttribute("currentReader", "None");
				request.setAttribute("nextReader", "None");
			}
			else if(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName).length == 1) {
				request.setAttribute("currentReader", clientDAO.read(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName)[0].getUserName()).getFirstName() + " " + clientDAO.read(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName)[0].getUserName()).getLastName());
				request.setAttribute("nextReader", "None");
			}
			else if(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName).length == 2) {
				request.setAttribute("currentReader", clientDAO.read(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName)[0].getUserName()).getFirstName() + " " + clientDAO.read(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName)[0].getUserName()).getLastName());
				request.setAttribute("nextReader", clientDAO.read(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName)[1].getUserName()).getFirstName() + " " + clientDAO.read(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName)[1].getUserName()).getLastName());
			}
			else {
				System.out.println("Error");
				request.setAttribute("currentReader", "None");
				request.setAttribute("nextReader", "None");
			}
			request.setAttribute("periodical", periodical);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "periodicalpage.jsp";
    }
}