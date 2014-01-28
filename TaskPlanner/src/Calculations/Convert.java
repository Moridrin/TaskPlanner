//<editor-fold defaultstate="collapsed" desc="Jibberish">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//</editor-fold>

/**
 *
 * @author jeroen
 */
public class Convert {

    public static String toString(Date date) {
        String returner = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
        returner = sdf.format(date);
        return returner;
    }

    public static Date toDate(String string) throws ParseException {
        Date returner = null;
        returner = new SimpleDateFormat("yy/MM/dd").parse(string);
        return returner;
    }

    public static int toInt(String string) throws NumberFormatException {
        int returner = 0;
        returner = Integer.parseInt(string);
        return returner;
    }

    public static Date currentDate() {
        Date returner = null;
        returner = new Date();
        return returner;
    }
}
