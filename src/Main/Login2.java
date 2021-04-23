package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import DataBeans.Client;
import FormBeans.LoginForm;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;

public class Login2 extends Action {
    private ClientDAO clientDAO;
    
    public Login2(Model model) {
    	clientDAO = model.getClientDAO();
    }
    
    public String getName() {
        return "login2.do";
    }  
    
    public String performGet(HttpServletRequest request) {
        return "login2.jsp";
    }

    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	Client client = (Client) session.getAttribute("client");
        String veriftext = request.getParameter("veriftext");
        if(session.getAttribute("genString").equals(veriftext)) {
        	session.removeAttribute("genString");
        	return "main2.do";
        }
        else {
        	request.setAttribute("incpsw", "Invalid Verification code !");
        	return "login2.jsp";
        }
    }
}