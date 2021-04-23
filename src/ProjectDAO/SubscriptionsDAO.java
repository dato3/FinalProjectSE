package ProjectDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DataBeans.Subscription;

public class SubscriptionsDAO {
    private List<Connection> connectionPool = new ArrayList<Connection>();

    private String jdbcDriver;
    private String jdbcURL;
    private String tableName;

    public SubscriptionsDAO(String jdbcDriver, String jdbcURL, String tableName)
            throws MyDAOException {
        this.jdbcDriver = jdbcDriver;
        this.jdbcURL = jdbcURL;
        this.tableName = tableName;
    }

    private synchronized Connection getConnection() throws MyDAOException {
        if (connectionPool.size() > 0) {
            return connectionPool.remove(connectionPool.size() - 1);
        }

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            throw new MyDAOException(e);
        }

        try {
            return DriverManager.getConnection(jdbcURL, "system", "oracle");
        } catch (SQLException e) {
            throw new MyDAOException(e);
        }
    }

    private synchronized void releaseConnection(Connection con) {
        connectionPool.add(con);
    }
    
    public Subscription[] readSubscriptionsByName(String userName) throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName + " WHERE userName=? ORDER BY date_subscribed");
            
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            List<Subscription> list = new ArrayList<Subscription>();
            while (rs.next()) {
                Subscription bean = new Subscription();
                bean.setUserName(rs.getString("userName"));
                bean.setPeriodicalName(rs.getString("periodical_name"));
                bean.setDateSubscribed(rs.getDate("date_subscribed"));
                bean.setExpirationDate(rs.getDate("expiration_date"));
                bean.setAvailability(rs.getString("availability"));
                bean.setStatus(rs.getString("status"));
                list.add(bean);
            }
            pstmt.close();
            releaseConnection(con);

            return list.toArray(new Subscription[list.size()]);
        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
    public Subscription[] readSubscriptionsByPeriodical(String periodicalName) throws MyDAOException {
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName + " WHERE periodical_name=? ORDER BY date_subscribed");
            
            pstmt.setString(1, periodicalName);
            ResultSet rs = pstmt.executeQuery();
            List<Subscription> list = new ArrayList<Subscription>();
            while (rs.next()) {
                Subscription bean = new Subscription();
                bean.setUserName(rs.getString("userName"));
                bean.setPeriodicalName(rs.getString("periodical_name"));
                bean.setDateSubscribed(rs.getDate("date_subscribed"));
                bean.setExpirationDate(rs.getDate("expiration_date"));
                bean.setAvailability(rs.getString("availability"));
                bean.setStatus(rs.getString("status"));
                list.add(bean);
            }
            pstmt.close();
            releaseConnection(con);

            return list.toArray(new Subscription[list.size()]);
        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
    public Subscription[] read() throws MyDAOException {
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName);
            ResultSet rs = pstmt.executeQuery();
            List<Subscription> list = new ArrayList<Subscription>();
            while (rs.next()) {
                Subscription bean = new Subscription();
                bean.setUserName(rs.getString("userName"));
                bean.setPeriodicalName(rs.getString("periodical_name"));
                bean.setDateSubscribed(rs.getDate("date_subscribed"));
                bean.setExpirationDate(rs.getDate("expiration_date"));
                bean.setAvailability(rs.getString("availability"));
                bean.setStatus(rs.getString("status"));
                list.add(bean);
            }
            pstmt.close();
            releaseConnection(con);

            return list.toArray(new Subscription[list.size()]);
        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
    public void subscriptionInsert(String userName, String periodicalName, String status) throws MyDAOException {
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO "+tableName+" VALUES(?, ?, SYSDATE, NULL, 'AVAILABLE', ?)");
            pstmt.setString(1, userName);
            pstmt.setString(2, periodicalName);
            pstmt.setString(3, status);
            ResultSet rs = pstmt.executeQuery();
            rs.close();
            pstmt.close();
            releaseConnection(con);
        } catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
    public void subscriptionUpdate(String userName, String periodicalName) throws MyDAOException {
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("UPDATE "+tableName+" SET expiration_date = SYSDATE + 3, status = 'Received' WHERE userName = ? AND periodical_name = ?");
            pstmt.setString(1, userName);
            pstmt.setString(2, periodicalName);
            ResultSet rs = pstmt.executeQuery();
            rs.close();
            pstmt.close();
            releaseConnection(con);
        } catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
    public void subscription2ndUpdate(String periodicalName) throws MyDAOException {
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("UPDATE "+tableName+" SET status = 'Not Received' WHERE periodical_name = ? AND status = 'Waiting'");
            pstmt.setString(1, periodicalName);
            ResultSet rs = pstmt.executeQuery();
            rs.close();
            pstmt.close();
            releaseConnection(con);
        } catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
    public void subscriptionDelete(String userName, String periodicalName) throws MyDAOException {
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("DELETE FROM "+tableName+" WHERE userName = ? AND periodical_name = ?");
            pstmt.setString(1, userName);
            pstmt.setString(2, periodicalName);
            ResultSet rs = pstmt.executeQuery();
            rs.close();
            pstmt.close();
            releaseConnection(con);
        } catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
    public Subscription[] readByDates() throws MyDAOException {
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName + " WHERE expiration_date <= SYSDATE");
            ResultSet rs = pstmt.executeQuery();
            List<Subscription> list = new ArrayList<Subscription>();
            while (rs.next()) {
                Subscription bean = new Subscription();
                bean.setUserName(rs.getString("userName"));
                bean.setPeriodicalName(rs.getString("periodical_name"));
                bean.setDateSubscribed(rs.getDate("date_subscribed"));
                bean.setExpirationDate(rs.getDate("expiration_date"));
                bean.setAvailability(rs.getString("availability"));
                bean.setStatus(rs.getString("status"));
                list.add(bean);
            }
            pstmt.close();
            releaseConnection(con);

            return list.toArray(new Subscription[list.size()]);
        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
}