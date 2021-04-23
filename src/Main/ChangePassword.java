package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import DataBeans.Client;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;

public class ChangePassword extends Action {
    private ClientDAO clientDAO;
    
    public ChangePassword(Model model) {
    	clientDAO = model.getClientDAO();
    }
    
    public String getName() {
        return "changePassword.do";
    }  
    
    public String performGet(HttpServletRequest request) {
        return "changePwrd.jsp";
    }

    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	Client client = (Client) session.getAttribute("client");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirmPassword");
        if(password.equals(confirm)) {
        	try {
				clientDAO.changePassword(client.getUserName(), password);
			} catch (MyDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return "profile.do";
        }
        else {
        	request.setAttribute("incpsw", "Password doesn't match !");
        	return "changePwrd.jsp";
        }
    }
}