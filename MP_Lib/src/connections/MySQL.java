//<editor-fold defaultstate="collapsed" desc="Jibberish">
package connections;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.*;
//</editor-fold>

/**
 * This class contains all information and functions needed for MySQL. //CHECK
 *
 * @author jeroen
 */
public class MySQL implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private Connection con;
    private String url;
    private String user;
    private String password;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * In this Constructor the required variables for the connection with the
     * database will be initiated. * @param server
     *
     * @param port is the port where the server listens to for Database
     * Connections.
     * @param database is the name of the database.
     * @param user is the user that is used to login.
     * @param password the password that is associated with the user.
     */
    public MySQL(String server, int port, String database, String user, String password) {
        this.url = "jdbc:mysql://" + server + ":" + port + "/" + database;
        this.user = user;
        this.password = password;
    }
    //</editor-fold>

    //<editor-fold desc="Functions">
    //<editor-fold defaultstate="collapsed" desc="Set SQL">
    /**
     * In this method the statement will be executed on the server.
     *
     * @param sql is the statement that the server must execute.
     * @param parameters contains a list with parameters that are send to the
     * server along side the statement.
     * @throws SQLException it throws this exception instead of catching it
     * here, so that the class calling this function knows what the problem is.
     */
    public void setSQL(String sql, ArrayList<String> parameters) throws SQLException {
        PreparedStatement pst = null;
        con = DriverManager.getConnection(url, user, password);
        pst = con.prepareStatement(sql);
        for (int i = 1; i <= parameters.size(); i++) {
            pst.setString(i, parameters.get(i - 1));
        }
        pst.executeUpdate();
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get SQL">
    /**
     * This sends an SELECT statement to the Database and returns whatever comes
     * back.
     *
     * @param sql is the statement that will be send to the Database. Use "?" for 
     * @param columns contains a lost with the names of the columns that you ask
     * for in the SQL Statement.
     * @throws SQLException it throws this exception instead of catching it
     * here, so that the class calling this function knows what the problem is.
     * @return is a List of String Arrays so the multiple columns can be set in
     * the Array, and the rows can be set as items in the List.
     */
    public ArrayList<String[]> getSQL(String sql, ArrayList<String> columns) throws SQLException {
        ArrayList<String[]> returner = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet resultSet;
        con = DriverManager.getConnection(url, user, password);
        for (int i = 0; i < columns.size(); i++) {
            //String replaceString = "?" + i;
            //sql.replace(replaceString, columns.get(i));
            sql = sql.replaceFirst("\\?", columns.get(i));
        }
        pst = con.prepareStatement(sql);
        resultSet = pst.executeQuery();
        while (resultSet.next()) {
            String[] row = new String[columns.size()];
            for (int i = 0; i < columns.size(); i++) {
                row[i] = resultSet.getString(columns.get(i));
            }
            returner.add(row);
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return returner;
    }
    //</editor-fold>
    //</editor-fold>
}
