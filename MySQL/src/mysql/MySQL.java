//<editor-fold defaultstate="collapsed" desc="Jibberish">
package mysql;

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
public class MySQL{

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private Connection con;
    private String url;
    private String user;
    private String password;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * In this method the connection with the database will be initiated.
     */
    public MySQL(String server, int port, String database, String user, String password) {
        this.url = "jdbc:mysql://" + server + ":" + port + "/" + database;
        this.user = user;
        this.password = password;
    }
    //</editor-fold>

    //<editor-fold desc="Functions">
    /*
    //<editor-fold defaultstate="collapsed" desc="Add Observer">
    public void addObserver(Object observer){
        this.addObserver(observer);
    }
    //</editor-fold>
    */
    
    //<editor-fold defaultstate="collapsed" desc="Set SQL">
    /**
     * In this method the statement will be executed on the server.
     *
     * @param sql The statement that the server must execute.
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
        //notifyObservers();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get SQL">
    /**
     * This returned a list of ArratList with the data it gets back from the
     * deck.
     *
     * @param sql
     * @return
     */
    public ArrayList<String> getSQL(String sql) {
        ArrayList<String> returner = null;
        return returner;
    }
    //</editor-fold>
    //</editor-fold>
}
