package ProjectDAO;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import DataBeans.Periodical;

public class PeriodicalsDAO {
    private List<Connection> connectionPool = new ArrayList<Connection>();

    private String jdbcDriver;
    private String jdbcURL;
    private String tableName;

    public PeriodicalsDAO(String jdbcDriver, String jdbcURL, String tableName)
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

    public Periodical read(String periodicalName) throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName + " WHERE periodical_name=?");
            pstmt.setString(1, periodicalName);
            ResultSet rs = pstmt.executeQuery();

            Periodical periodical;
            if (!rs.next()) {
            	periodical = null;
            } else {
            	periodical = new Periodical();
            	periodical.setPeriodicalName(rs.getString("periodical_name"));
            	periodical.setTitle(rs.getString("title"));
            	InputStream inputStream = (rs.getBlob("image")).getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                periodical.setImage(base64Image);
                periodical.setPostDate(rs.getDate("post_date"));
                periodical.setAvailability(rs.getString("availability"));
            }

            rs.close();
            pstmt.close();
            releaseConnection(con);
            return periodical;

        } catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }

    private boolean tableExists() throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName);
            ResultSet rs = pstmt.executeQuery();
            boolean answer = rs.next();
            rs.close();
            releaseConnection(con);

            return answer;

        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
    public Periodical[] getPeriodicals() throws MyDAOException, IOException {
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName + " ORDER BY post_date");
          
            ResultSet rs = pstmt.executeQuery();

            List<Periodical> list = new ArrayList<Periodical>();
            while (rs.next()) {
                Periodical periodical = new Periodical();
                periodical.setPeriodicalName(rs.getString("periodical_name"));
                periodical.setTitle(rs.getString("title"));
                InputStream inputStream = (rs.getBlob("image")).getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                periodical.setImage(base64Image);
                periodical.setPostDate(rs.getDate("post_date"));
                periodical.setAvailability(rs.getString("availability"));
                list.add(periodical);
            }
            pstmt.close();
            releaseConnection(con);

            return list.toArray(new Periodical[list.size()]);
        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
    public void periodicalInsert(String name, String title, InputStream image) throws MyDAOException {
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO "+tableName+" VALUES(?, ?, ?, SYSDATE, 'Available')");
            pstmt.setString(1, name);
            pstmt.setString(2, title);
            pstmt.setBlob(3, image);
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
    
    public int getNumberOfRows() throws MyDAOException{
    	int numOfRows;
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(*) FROM "+tableName);
          
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
            	numOfRows = rs.getInt("count(*)");
            } else {
            	numOfRows = 0;
            }
            pstmt.close();
            releaseConnection(con);
            return numOfRows;
        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }

    }
    
    public Periodical[] getPeriodicalsById(int currentPage) throws MyDAOException, IOException {
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName + " ORDER BY post_date OFFSET "+(currentPage-1)*6+" ROWS FETCH FIRST 6 ROWS ONLY");
          
            ResultSet rs = pstmt.executeQuery();

            List<Periodical> list = new ArrayList<Periodical>();
            while (rs.next()) {
                Periodical bean = new Periodical();
                bean.setPeriodicalName(rs.getString("periodical_name"));
                bean.setTitle(rs.getString("title"));
                InputStream inputStream = (rs.getBlob("image")).getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                bean.setImage(base64Image);
                bean.setPostDate(rs.getDate("post_date"));
                bean.setAvailability(rs.getString("availability"));
                list.add(bean);
            }
            pstmt.close();
            releaseConnection(con);

            return list.toArray(new Periodical[list.size()]);
        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    public int getNumberOfRowsByName(String periodicalName) throws MyDAOException{
    	int numOfRows;
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(*) FROM "+tableName+" WHERE LOWER(periodical_name) LIKE ?");
            pstmt.setString(1, periodicalName+"%");
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
            	numOfRows = rs.getInt("count(*)");
            } else {
            	numOfRows = 0;
            }
            pstmt.close();
            releaseConnection(con);
            return numOfRows;
        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }

    }
    public Periodical[] readPeriodicalsByName(String periodicalName, int currentPage) throws MyDAOException, IOException {
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName + " WHERE LOWER(periodical_name) LIKE ? OFFSET "+(currentPage-1)*6+" ROWS FETCH FIRST 6 ROWS ONLY");
            
            pstmt.setString(1, periodicalName+"%");
            ResultSet rs = pstmt.executeQuery();
            List<Periodical> list = new ArrayList<Periodical>();
            while (rs.next()) {
                Periodical bean = new Periodical();
                bean.setPeriodicalName(rs.getString("periodical_name"));
                bean.setTitle(rs.getString("title"));
                InputStream inputStream = (rs.getBlob("image")).getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                bean.setImage(base64Image);
                bean.setPostDate(rs.getDate("post_date"));
                bean.setAvailability(rs.getString("availability"));
                list.add(bean);
            }
            pstmt.close();
            releaseConnection(con);

            return list.toArray(new Periodical[list.size()]);
        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
    public void periodicalUpdate(String periodicalName, String type) throws MyDAOException {
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("UPDATE "+tableName+" SET availability = ? WHERE periodical_name = ?");
            pstmt.setString(1, type);
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
    
    public void periodicalDelete(String periodicalName) throws MyDAOException {
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("DELETE FROM "+tableName+" WHERE periodical_name = ?");
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
}