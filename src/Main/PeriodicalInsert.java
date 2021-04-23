package Main;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DataBeans.Client;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;
import ProjectDAO.PeriodicalsDAO;

@MultipartConfig(maxFileSize = 16177215)
public class PeriodicalInsert extends Action {
	private PeriodicalsDAO periodicalDAO;
	
	public PeriodicalInsert(Model model) {
		periodicalDAO = model.getPeriodicalsDAO();
    }
	
	public String getName() {
        return "periodicalInsert.do";
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
        String periodicalTitle = request.getParameter("periodicalTitle");
        InputStream inputStream = null; 
        Part filePart;
		try {
			filePart = request.getPart("image");
			if (filePart != null) {
	            inputStream = filePart.getInputStream();
	            try {
		        	periodicalDAO.periodicalInsert(periodicalName, periodicalTitle, inputStream);
				} catch (MyDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		tr
        return "main2.do";
    }
}