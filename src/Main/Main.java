package Main;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import DataBeans.Client;
import DataBeans.Subscription;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;
import ProjectDAO.PeriodicalsDAO;
import ProjectDAO.SubscriptionsDAO;

public class Main extends Action {
	private ClientDAO clientDAO;
	private PeriodicalsDAO periodicalDAO;
	private SubscriptionsDAO subscriptionsDAO;
	
	public Main(Model model) {
		clientDAO = model.getClientDAO();
		periodicalDAO = model.getPeriodicalsDAO();
		subscriptionsDAO = model.getSubscriptionsDAO();
    }
	
	public String getName() {
        return "main.do";
    }

    public String performGet(HttpServletRequest request) {
    	return performPost(request);
    }
    
    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("client");
        int currentPage;
        ExpirationCheck t1 = new ExpirationCheck(subscriptionsDAO, client);  
        t1.start(); 
        if(request.getParameter("currentPage") == null) {
        	currentPage = 1;
        }
        else {
        	currentPage = Integer.parseInt(request.getParameter("currentPage"));        	
        }
        int recordsPerPage = 6;
        int rows;
        try {
        	rows = periodicalDAO.getNumberOfRows();
			int nOfPages = rows / recordsPerPage;
			if (rows % recordsPerPage != 0) {
				nOfPages++;
			}
			try {
				request.setAttribute("periodicals", periodicalDAO.getPeriodicalsById(currentPage));
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

class ExpirationCheck extends Thread{  
	private SubscriptionsDAO subscriptionsDAO;
	private Client client;
	ExpirationCheck(SubscriptionsDAO s, Client c) {
		subscriptionsDAO = s;
		client = c;
	}
	
	public void run(){  
		try {
			Subscription[] arr = subscriptionsDAO.readByDates();
			Calendar calendar = Calendar.getInstance();
			Date today = calendar.getTime();
			for(Subscription s: arr) {
				if(today.after(s.getExpirationDate())) {
					String text = "Hey, "+client.getFirstName()+" "+client.getLastName()+"!\nYou expired the periodical pass date, please sent the periodical to the mail as soon as possible\nAlso do not forget to confirm that you sent the periodical in your profile page !";
					try {
		    			JavaMailUtil.sendMail("voinbaha@gmail.com", text, "expiration");
		    		} catch (MessagingException e1) {
		    			e1.printStackTrace();
		    		}
				}
			}
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  
	
}