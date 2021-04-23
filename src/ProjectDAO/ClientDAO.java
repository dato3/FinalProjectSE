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
import DataBeans.Client;

public class ClientDAO {
    private List<Connection> connectionPool = new ArrayList<Connection>();

    private String jdbcDriver;
    private String jdbcURL;
    private String tableName;

    public ClientDAO(String jdbcDriver, String jdbcURL, String tableName)
            throws MyDAOException {
        this.jdbcDriver = jdbcDriver;
        this.jdbcURL = jdbcURL;
        this.tableName = tableName;

        if (!tableExists()) {
        	System.out.println(tableName+" not exist");        	
        }
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

    public Client read(String userName) throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName + " WHERE userName=?");
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();

            Client client;
            if (!rs.next()) {
            	client = null;
            } else {
            	client = new Client();
            	client.setUserName(rs.getString("userName"));
            	client.setPosition(rs.getString("position"));
            	client.setPassword(rs.getString("password"));
            	client.setFirstName(rs.getString("firstName"));
            	client.setLastName(rs.getString("lastName"));
            	client.setDateOfBirth(rs.getDate("dateOfBirth"));
            	client.setMailCode(rs.getInt("mailCode"));
            	client.setPhoneNumber(rs.getString("phoneNumber"));
            	try {
            		InputStream inputStream = (rs.getBlob("image")).getBinaryStream();    
            		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                     
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);                  
                    }
                     
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    client.setImage(base64Image);
            	} catch(NullPointerException c) {
            		client.setImage(null);
            	}
            }

            rs.close();
            pstmt.close();
            releaseConnection(con);
            return client;

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
    
    public Client[] getClients() throws MyDAOException, IOException {
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName);
          
            ResultSet rs = pstmt.executeQuery();

            List<Client> list = new ArrayList<Client>();
            while (rs.next()) {
                Client client = new Client();
                client.setUserName(rs.getString("userName"));
                client.setPosition(rs.getString("position"));
                client.setPassword(rs.getString("password"));
                client.setFirstName(rs.getString("firstName"));
                client.setLastName(rs.getString("lastName"));
                client.setDateOfBirth(rs.getDate("dateOfBirth"));
                client.setMailCode(rs.getInt("mailCode"));
                client.setPhoneNumber(rs.getString("phoneNumber"));
                InputStream inputStream = (rs.getBlob("image")).getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            	client.setImage(base64Image);
                list.add(client);
            }
            pstmt.close();
            releaseConnection(con);

            return list.toArray(new Client[list.size()]);
        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
    public void changePassword(String userName, String password) throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("UPDATE "
                    + tableName + " SET password=? WHERE userName=?");
            pstmt.setString(1, password);
            pstmt.setString(2, userName);
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
    public void changeProfileImage(String userName, String imagePath) throws MyDAOException {
    	Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("UPDATE "+tableName+" SET image=? WHERE userName=?");
            InputStream in = new FileInputStream(imagePath);
            pstmt.setBlob(1, in);
            pstmt.setString(2, userName);
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
    
    public Client readByFullName(String fullName) throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName + " WHERE firstName=? AND lastName=?");
            String firstName = fullName.substring(0, fullName.indexOf(' '));
    		String lastName = fullName.substring(fullName.indexOf(' ')+1,fullName.length());
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            ResultSet rs = pstmt.executeQuery();

            Client client;
            if (!rs.next()) {
            	client = null;
            } else {
            	client = new Client();
            	client.setUserName(rs.getString("userName"));
            	client.setPosition(rs.getString("position"));
            	client.setPassword(rs.getString("password"));
            	client.setFirstName(rs.getString("firstName"));
            	client.setLastName(rs.getString("lastName"));
            	client.setDateOfBirth(rs.getDate("dateOfBirth"));
            	client.setMailCode(rs.getInt("mailCode"));
            	client.setPhoneNumber(rs.getString("phoneNumber"));
            	try {
            		InputStream inputStream = (rs.getBlob("image")).getBinaryStream();    
            		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                     
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);                  
                    }
                     
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    client.setImage(base64Image);
            	} catch(NullPointerException c) {
            		client.setImage(null);
            	}
            }

            rs.close();
            pstmt.close();
            releaseConnection(con);
            return client;

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