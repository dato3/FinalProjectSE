package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import DataBeans.Client;
import DataBeans.LibrarianList;
import DataBeans.Periodical;
import DataBeans.Subscription;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;
import ProjectDAO.PeriodicalsDAO;
import ProjectDAO.SubscriptionsDAO;

public class Main2 extends Action {
	private ClientDAO clientDAO;
	private PeriodicalsDAO periodicalDAO;
	private SubscriptionsDAO subscriptionsDAO;
	
	public Main2(Model model) {
		clientDAO = model.getClientDAO();
		periodicalDAO = model.getPeriodicalsDAO();
		subscriptionsDAO = model.getSubscriptionsDAO();
    }
	
	public String getName() {
        return "main2.do";
    }

    public String performGet(HttpServletRequest request) {
    	return performPost(request);
    }
    
    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("client");
        List<LibrarianList> list = new ArrayList<LibrarianList>();
        if(client == null) {
        	return "login.do";
        }
        try {
        	try {
        		DataBeans.Periodical[] arr = periodicalDAO.getPeriodicals();
				for(DataBeans.Periodical p: arr) {
					LibrarianList temp = new LibrarianList();
					temp.setPeriodicalName(p.getPeriodicalName());
					temp.setAvailability(p.getAvailability());
					Subscription[] arr2 = subscriptionsDAO.readSubscriptionsByPeriodical(p.getPeriodicalName());
					if(arr2.length == 0) {
						temp.setCurrentReader("None");
						temp.setStatusCur("None");
						temp.setExpirationDateCurrent(null);
						temp.setNextReader("None");
						temp.setStatusNext("None");
						temp.setExpirationDateNext(null);
					}
					else if(arr2.length == 1) {
						temp.setCurrentReader(clientDAO.read(arr2[0].getUserName()).getFirstName()+" "+clientDAO.read(arr2[0].getUserName()).getLastName());
						temp.setStatusCur(arr2[0].getStatus());
						temp.setExpirationDateCurrent(arr2[0].getExpirationDate());
						temp.setNextReader("None");
						temp.setStatusNext("None");
						temp.setExpirationDateNext(null);
					}
					else if(arr2.length == 2) {
						temp.setCurrentReader(clientDAO.read(arr2[0].getUserName()).getFirstName()+" "+clientDAO.read(arr2[0].getUserName()).getLastName());
						temp.setStatusCur(arr2[0].getStatus());
						temp.setExpirationDateCurrent(arr2[0].getExpirationDate());
						temp.setNextReader(clientDAO.read(arr2[1].getUserName()).getFirstName()+" "+clientDAO.read(arr2[1].getUserName()).getLastName());
						temp.setStatusNext(arr2[1].getStatus());
						temp.setExpirationDateNext(arr2[1].getExpirationDate());
					}
					else {
						System.out.println("Error occured");
						return "error.jsp";
					}
					list.add(temp);
				}
				request.setAttribute("librarianList", list);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ("main2.jsp");
    }
}