package Main;

import java.util.Random;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.formbeanfactory.FormBeanFactory;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;
import DataBeans.Client;
import FormBeans.LoginForm;

public class Login extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = new FormBeanFactory<>(LoginForm.class);
    private ClientDAO clientDAO;
    
    public Login(Model model) {
    	clientDAO = model.getClientDAO();
    }
    
    public String getName() {
        return "login.do";
    }  
    
    public String performGet(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("client") != null) {
            return "main.do";
        }
        request.setAttribute("form", new LoginForm());
        return "login.jsp";
    }

    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        if (session.getAttribute("client") != null) {
            return "main.do";
        } 
        try {
            LoginForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "login.jsp";
            }

            // Look up the user
            Client client = clientDAO.read(form.getUserName());
            if (client == null) {
                form.addFieldError("userName", "User Name not found");
                return "login.jsp";
            }
            
            if (!client.getPassword().equals(form.getPassword())) {
                form.addFieldError("password", "Incorrect password");
                return "login.jsp";
            }
            
            if (client.getPosition().equals("librarian")) {
            	String veriftext = rand6char();
            	String text = "Hey, "+client.getFirstName()+" "+client.getLastName()+"!\nYour verification code is: "+veriftext;
            	LoginEmailSender t1 = new LoginEmailSender(text);
            	t1.start();
            	session.setAttribute("genString", veriftext);
            	session.setAttribute("client", client);
            	return "login2.do";
            }
            
            else {
            	session.setAttribute("client", client);
            	return "main.do";
            }
        } catch (MyDAOException e) {
            session.setAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            return "error.jsp";
        }
    }
    
    public String rand6char() {
    	int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();

        return(generatedString);
    }
    
}

class LoginEmailSender extends Thread{  
	private String text;
	LoginEmailSender(String t) {
		text = t;
	}
	
	public void run(){  
		try {
			JavaMailUtil.sendMail("voinbaha@gmail.com", text, "verification");
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
	}  
	
}