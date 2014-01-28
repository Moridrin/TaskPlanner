//<editor-fold defaultstate="collapsed" desc="Jibberish">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskplanner;
//</editor-fold>

import Calculations.Convert;
import connections.MySQL;
import exceptions.UnsupportedType;
import gui.MainFrame;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeroen
 */
public class TaskList {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private final ArrayList<Task> tasks;

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    //</editor-fold>

    //<editor-fold desc="Functions">
    //<editor-fold defaultstate="collapsed" desc="Update">
    /**
     * This method will update the list of Tasks. First it cleans the current
     * list, then it prepares the variable needed for the SQL statement, then it
     * calls the DBCC with the parameters, and finally puts the result into the
     * list.
     *
     * @param conn is the connection you want to use to get the update.
     */
    public void getUpdate(MySQL conn) {
        ArrayList<String[]> tasksStrings = null;
        try {
            ArrayList<String> columns = new ArrayList<>();
            ArrayList<Object> parameters = new ArrayList<>();
            columns.add("ToDoName");
            columns.add("ToDoBefore");
            columns.add("ToDoPriority");
            tasksStrings = conn.executeSQL("SELECT * FROM ToDo;", columns, parameters);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedType ex) {
            Logger.getLogger(TaskList.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String[] s : tasksStrings) {
            try {
                tasks.add(new Task(s[0], Convert.toDate(s[1]), Convert.toInt(s[2])));
            } catch (ParseException ex) {
                Logger.getLogger(TaskList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //</editor-fold>

    //</editor-fold>
}
