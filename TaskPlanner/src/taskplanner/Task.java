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
    private final String name;
    private final Date beforeDate;
    private final int priority;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * In this constructor Task will initialize all the given parameters.
     *
     * @param name is the name of the Task
     * @param beforeDate is the Date before which the task needs to be
     * complected
     * @param priority is the priority of the task (higher is more important)
     */
    public Task(String name, Date beforeDate, int priority) {
        this.name = name;
        this.beforeDate = beforeDate;
        this.priority = priority;
    }
    //</editor-fold>

    //<editor-fold desc="Functions">
    //<editor-fold defaultstate="collapsed" desc="Save">
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
    //</editor-fold>
}
