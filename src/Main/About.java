package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DataBeans.Client;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;

public class About extends Action {
	private ClientDAO clientDAO;
	
	public About(Model model) {
		clientDAO = model.getClientDAO();
    }
	
	public String getName() {
        return "about.do";
    }

    public String performGet(HttpServletRequest request) {
    	return performPost(request);
    }
    
    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	Client client = (Client) session.getAttribute("client");
        if (client == null) {
            return "login.do";
        }
        return ("about.jsp");
    }
}