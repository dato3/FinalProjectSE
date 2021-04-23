package Main;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import DataBeans.Client;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;
import ProjectDAO.PeriodicalsDAO;
import ProjectDAO.SubscriptionsDAO;

public class Subscribe extends Action {
	private ClientDAO clientDAO;
	private PeriodicalsDAO periodicalDAO;
	private SubscriptionsDAO subscriptionsDAO;
	
	public Subscribe(Model model) {
		clientDAO = model.getClientDAO();
		periodicalDAO = model.getPeriodicalsDAO();
		subscriptionsDAO = model.getSubscriptionsDAO();
    }
	
	public String getName() {
        return "subscribe.do";
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
			DataBeans.Periodical periodical = periodicalDAO.read(periodicalName);
			if(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName).length == 1) {
				subscriptionsDAO.subscriptionInsert(client.getUserName(), periodicalName, "Waiting");				
			}
			else {
				subscriptionsDAO.subscriptionInsert(client.getUserName(), periodicalName, "Not Received");				
			}
			if(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName).length == 0) {
				request.setAttribute("currentReader", "None");
				request.setAttribute("nextReader", "None");
			}
			else if(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName).length == 1) {
				request.setAttribute("currentReader", clientDAO.read(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName)[0].getUserName()).getFirstName()+" "+clientDAO.read(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName)[0].getUserName()).getLastName());
				request.setAttribute("nextReader", "None");
			}
			else if(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName).length == 2) {
				request.setAttribute("currentReader", clientDAO.read(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName)[0].getUserName()).getFirstName()+" "+clientDAO.read(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName)[0].getUserName()).getLastName());
				request.setAttribute("nextReader", clientDAO.read(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName)[1].getUserName()).getFirstName()+" "+clientDAO.read(subscriptionsDAO.readSubscriptionsByPeriodical(periodicalName)[1].getUserName()).getLastName());
			}
			else {
				System.out.println("Error");
				request.setAttribute("currentReader", "None");
				request.setAttribute("nextReader", "None");
			}
			String text = "Hey, "+client.getFirstName()+" "+client.getLastName()+"!\nYou recently subscribed for the "+ periodicalName+" periodical.\nPlease do not forget to acknowledge that you received and sent the periodical.\nYou can acknowledge it in your profile page !";
			SubscribeEmailSender t1 = new SubscribeEmailSender(text);
			t1.start();
			request.setAttribute("periodical", periodical);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "periodicalpage.jsp";
    }
}

class SubscribeEmailSender extends Thread{  
	private String text;
	SubscribeEmailSender(String t) {
		text = t;
	}
	
	public void run(){  
		try {
			JavaMailUtil.sendMail("voinbaha@gmail.com", text, "subscription");
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
	}  
	
}