package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ProjectDAO.ClientDAO;
import ProjectDAO.Model;
import FormBeans.LoginForm;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class Logout extends Action {
	private ClientDAO clientDAO;
	
	public Logout(Model model) {
		clientDAO = model.getClientDAO();
	}

    public String getName() {
        return "logout.do";
    }
    
    public String performGet(HttpServletRequest request) {
    	return performPost(request);
    }

    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("client", null);
        request.setAttribute("form", new LoginForm());
        return "login.do";
    }
}
