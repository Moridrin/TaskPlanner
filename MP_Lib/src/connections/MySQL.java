//<editor-fold defaultstate="collapsed" desc="Jibberish">
package connections;

import java.awt.geom.Line2D;
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
     * @param table
     * @param columns
     * @param values
     *
     * @throws SQLException it throws this exception instead of catching it
     * here, so that the class calling this function knows what the problem is.
     */
    public void setInsertInto(String table, ArrayList<String> columns, ArrayList<String> values) throws SQLException {
        int parameterIndex = 1;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO " + table + " (");
        for (int i = 0; i < columns.size(); i++) {
            if (i == columns.size()-1) {
                sqlBuilder.append(columns.get(i));
            } else {
                sqlBuilder.append(columns.get(i));
                sqlBuilder.append(", ");
            }
        }
        sqlBuilder.append(") VALUES (");
        for (int i = 1; i <= values.size(); i++) {
            if (i == values.size()) {
                sqlBuilder.append("?");
            } else {
                sqlBuilder.append("?, ");
            }
        }
        sqlBuilder.append(");");
        String sql = sqlBuilder.toString();
        PreparedStatement pst = null;
        con = DriverManager.getConnection(url, user, password);
        pst = con.prepareStatement(sql);
        for (int i = 0; i < values.size(); i++) {
            pst.setString(parameterIndex, values.get(i));
            parameterIndex++;
        }
        try {
            pst.executeUpdate();
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
     * @param sql is the statement that will be send to the Database. Use "?"
     * for
     * @param columns contains a lost with the names of the columns that you ask
     * for in the SQL Statement.
     *
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
