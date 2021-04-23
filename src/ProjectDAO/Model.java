package ProjectDAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Model {
    private ClientDAO clientDAO;
    private PeriodicalsDAO periodicalDAO;
    private SubscriptionsDAO subscriptionsDAO;

    public Model(ServletConfig config) throws ServletException {
        try {
            String jdbcDriver = config.getInitParameter("jdbcDriverName");
            String jdbcURL = config.getInitParameter("jdbcURL");
            clientDAO = new ClientDAO(jdbcDriver, jdbcURL, "clients");
            periodicalDAO = new PeriodicalsDAO(jdbcDriver, jdbcURL, "periodicals");
            subscriptionsDAO = new SubscriptionsDAO(jdbcDriver, jdbcURL, "subscriptions");
        } catch (MyDAOException e) {
            throw new ServletException(e);
        }
    }
    
    public ClientDAO getClientDAO() {
    	return clientDAO;
    }
    
    public PeriodicalsDAO getPeriodicalsDAO() {
    	return periodicalDAO;
    }
    
    public SubscriptionsDAO getSubscriptionsDAO() {
    	return subscriptionsDAO;
    }
}
