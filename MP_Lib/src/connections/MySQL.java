/**********************\
* Moridrin Productions *
* Created by:          *
*      Jeroen Berkvens *
\**********************/
//<editor-fold defaultstate="collapsed" desc="Jibberish">
package connections;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;
import exceptions.UnsupportedType;
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
    //<editor-fold defaultstate="collapsed" desc="Get SQL">
    /**
     * This statement returns the result of the given SQL statement.
     *
     * @param sql this is the statement that will be executed.
     * @param columns this is a list of all the columns you want to select from
     * (this must be the same as in the SQL Statement).
     * @param parameters this is a list of parameters you want to add to the SQL
     * Statement. These parameters will be linked to "?" in the SQL Statement.
     * @return this is a list of arrays with every item in the list as a row in
     * the table, and the columns in the array.
     * @throws SQLException this exception is thrown because the specifics of
     * the error are useless in the library.
     * @throws exceptions.UnsupportedType this exception is thrown when one of
     * the parameters has a type that is not supported. (Supported types are:
     * String, int, Double, Date)
     */
    public ArrayList<String[]> executeSQL(String sql, ArrayList<String> columns, ArrayList<Object> parameters) throws SQLException, UnsupportedType {
        ArrayList<String[]> returner = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet resultSet;
        con = DriverManager.getConnection(url, user, password);
        pst = con.prepareStatement(sql);
        for (int i = 0; i < parameters.size(); i++) {
            String className = String.valueOf(parameters.get(i).getClass());
            switch (className) {
                case "String":
                    pst.setString(i, (String) parameters.get(i));
                    break;
                case "int":
                    pst.setInt(i, (int) parameters.get(i));
                    break;
                case "double":
                    pst.setDouble(i, (Double) parameters.get(i));
                    break;
                case "Date":
                    pst.setDate(i, (Date) parameters.get(i));
                    break;
                default:
                    throw new UnsupportedType(className);
            }
        }
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

    //<editor-fold defaultstate="collapsed" desc="Set SQL">
    /**
     * This statement returns the result of the given SQL statement.
     *
     * @param sql this is the statement that will be executed.
     * @param parameters this is a list of parameters you want to add to the SQL
     * Statement. These parameters will be linked to "?" in the SQL Statement.
     * @throws SQLException this exception is thrown because the specifics of
     * the error are useless in the library.
     * @throws exceptions.UnsupportedType this exception is thrown when one of
     * the parameters has a type that is not supported. (Supported types are:
     * String, int, Double, Date)
     */
    public void executeSQL(String sql, ArrayList<Object> parameters) throws SQLException, UnsupportedType {
        PreparedStatement pst = null;
        con = DriverManager.getConnection(url, user, password);
        pst = con.prepareStatement(sql);
        for (int i = 0; i < parameters.size(); i++) {
            String className = String.valueOf(parameters.get(i).getClass());
            switch (className) {
                case "String":
                    pst.setString(i, (String) parameters.get(i));
                    break;
                case "int":
                    pst.setInt(i, (int) parameters.get(i));
                    break;
                case "double":
                    pst.setDouble(i, (Double) parameters.get(i));
                    break;
                case "Date":
                    pst.setDate(i, (Date) parameters.get(i));
                    break;
                default:
                    throw new UnsupportedType(className);
            }
        }
        pst.executeQuery();
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
    //</editor-fold>
}
