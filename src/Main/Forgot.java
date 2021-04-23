package Main;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;
import DataBeans.Client;
import FormBeans.LoginForm;

public class Forgot extends Action {
    private ClientDAO clientDAO;
    
    public Forgot(Model model) {
    	clientDAO = model.getClientDAO();
    }
    
    public String getName() {
        return "forgot.do";
    }  
    
    public String performGet(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("client") != null) {
            return "main.do";
        }
        String text = "";
        String email = request.getParameter("emailAddress");
        try {
        	LoginForm form = new LoginForm();
        	request.setAttribute("form", form);
        	Client client = clientDAO.read(email);
			if (client == null) {
				form.addFieldError("userName", "User Name not found");
                return "login.jsp";
            }
			else {
				text = "Hey, "+client.getFirstName()+" "+client.getLastName()+"!\nYour password is: "+client.getPassword()+"\nYou can change your password at any time in your profile page.";
			}
		} catch (MyDAOException e) {
			session.setAttribute("error", e.getMessage());
            return "error.jsp";
		}
        ForgotEmailSender t1 = new ForgotEmailSender(text);
        t1.start();
        return "login.jsp";
    }

    public String performPost(HttpServletRequest request) {
    	return performGet(request);
    }
}

class ForgotEmailSender extends Thread{  
	private String text;
	ForgotEmailSender(String t) {
		text = t;
	}
	
	public void run(){  
		try {
			JavaMailUtil.sendMail("voinbaha@gmail.com", text, "password");
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
	}  
	
}