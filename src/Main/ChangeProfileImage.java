package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import DataBeans.Client;
import ProjectDAO.ClientDAO;
import ProjectDAO.Model;
import ProjectDAO.MyDAOException;

public class ChangeProfileImage extends Action {
    private ClientDAO clientDAO;
    
    public ChangeProfileImage(Model model) {
    	clientDAO = model.getClientDAO();
    }
    
    public String getName() {
        return "changeImage.do";
    }  
    
    public String performGet(HttpServletRequest request) {
        return performPost(request);
    }

    public String performPost(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	Client client = (Client) session.getAttribute("client");
    	JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getAbsolutePath());
        }
        try {
			clientDAO.changeProfileImage(client.getUserName(),chooser.getSelectedFile().getAbsolutePath());
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			client = clientDAO.read(client.getUserName());
			session.removeAttribute("client");
			session.setAttribute("client", client);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "profile.do";
    }
}