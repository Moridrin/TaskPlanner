//<editor-fold defaultstate="collapsed" desc="Jibberish">
package taskplanner;

import connections.MySQL;
import exceptions.UnsupportedType;
import gui.AddTaskPanel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//</editor-fold>

/**
 * This class contains all information and functions needed for Task. //CHECK
 *
 * @author jeroen
 */
public class Task {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private String name;
    private Date beforeDate;
    private int priority;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * In this class //TODO
     */
    public Task(String name, Date beforeDate, int priority) {
        this.name = name;
        this.beforeDate = beforeDate;
        this.priority = priority;
    }
    //</editor-fold>

    //<editor-fold desc="Functions">
    public void Save(MySQL connectionSettings) {
        String sql = "INSERT INTO ToDo (ToDoName, ToDoBefore, ToDoPriority) VALUES (?, ?, ?);";
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(name);
        parameters.add(beforeDate.toString());
        parameters.add(String.valueOf(priority));
        try {
            connectionSettings.executeSQL(sql, parameters);
        } catch (SQLException ex) {
            Logger.getLogger(AddTaskPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedType ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>
}
