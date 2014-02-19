/**********************\
* Moridrin Productions *
* Created by:          *
*      Jeroen Berkvens *
\**********************/
//<editor-fold defaultstate="collapsed" desc="Jibberish">
package formatting;

import exceptions.*;
//</editor-fold>

/**
 * This class contains the Metric Prefix enums, and all Metric functions you
 * could need.
 *
 * @author jeroen
 */
public class Metric {

    //<editor-fold desc="Enums">
    //<editor-fold defaultstate="collapsed" desc="Metric Prefix">
    public enum MetricPrefix {

        Kilo,
        Mega,
        Giga,
        Tera,
        Peta;

        //<editor-fold desc="Operations">
        //<editor-fold defaultstate="collapsed" desc="next">
        /**
         * This operation returns the next enum value.
         *
         * @return the next enum.
         * @throws UnknownLargeMetricPrefix if there is no known next prefix
         * (currently Peta is the highest prefix).
         */
        public MetricPrefix next() throws UnknownLargeMetricPrefix {
            MetricPrefix prefixes[] = MetricPrefix.values();
            int ordinal = this.ordinal() + 1;
            if (ordinal >= ShortMetricPrefix.values().length) {
                throw new UnknownLargeMetricPrefix();
            }
            return prefixes[ordinal];
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="previous">
        /**
         * This operation returns the previous enum value.
         *
         * @return the previous enum.
         * @throws UnknownSmallMetricPrefix if there is no known previous prefix
         * (currently Kilo is the lowest prefix).
         */
        public MetricPrefix previous() throws UnknownSmallMetricPrefix {
            MetricPrefix prefixes[] = MetricPrefix.values();
            int ordinal = this.ordinal() + 1;
            if (ordinal >= ShortMetricPrefix.values().length) {
                throw new UnknownSmallMetricPrefix();
            }
            return prefixes[ordinal];
        }
        //</editor-fold>
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ShortMetricPrefix">
    public enum ShortMetricPrefix {

        K,
        M,
        G,
        T,
        P;

        //<editor-fold desc="Operations">
        //<editor-fold defaultstate="collapsed" desc="next">
        /**
         * This operation returns the next enum value.
         *
         * @return the next enum.
         * @throws UnknownLargeMetricPrefix if there is no known next prefix
         * (currently Peta is the highest prefix).
         */
        public ShortMetricPrefix next() throws UnknownLargeMetricPrefix {
            ShortMetricPrefix prefixes[] = ShortMetricPrefix.values();
            int ordinal = this.ordinal() + 1;
            if (ordinal >= ShortMetricPrefix.values().length) {
                throw new UnknownLargeMetricPrefix();
            }
            return prefixes[ordinal];
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="previous">
        /**
         * This operation returns the previous enum value.
         *
         * @return the previous enum.
         * @throws UnknownSmallMetricPrefix if there is no known previous prefix
         * (currently Kilo is the lowest prefix).
         */
        public MetricPrefix previous() throws UnknownSmallMetricPrefix {
            MetricPrefix prefixes[] = MetricPrefix.values();
            int ordinal = this.ordinal() + 1;
            if (ordinal >= ShortMetricPrefix.values().length) {
                throw new UnknownSmallMetricPrefix();
            }
            return prefixes[ordinal];
        }
        //</editor-fold>
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="nextShortMetricPrefix">
    /**
     * This returns a string of the next short metric prefix. Example: K > M.
     *
     * @param prefixString is the current prefix (example: K). If there is not
     * yet a prefix due to having the default (example: Meter doesn't have a
     * prefix) it'll give K back.
     * @return a string of the next metric prefix in short notation (example:
     * Kilo = K).
     * @throws UnknownLargeMetricPrefix if there is no known next prefix
     * (currently Peta is the highest prefix).
     */
    public static String nextShortMetricPrefix(String prefixString) throws UnknownLargeMetricPrefix {
        if (prefixString.isEmpty()) {
            return ShortMetricPrefix.K.toString();
        } else {
            ShortMetricPrefix prefix = ShortMetricPrefix.valueOf(prefixString);
            prefix = prefix.next();
            return prefix.toString();
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="nextMetricPrefix">
    /**
     * This returns a string of the next metric prefix. Example: Kilo > Mega.
     *
     * @param prefixString is the current prefix (example: Kilo). If there is
     * not yet a prefix due to having the default (example: Meter doesn't have a
     * prefix) it'll give Kilo back.
     * @return a string of the next metric prefix.
     * @throws UnknownLargeMetricPrefix if there is no known next prefix
     * (currently Peta is the highest prefix).
     */
    public static String nextMetricPrefix(String prefixString) throws UnknownLargeMetricPrefix {
        if (prefixString.isEmpty()) {
            return MetricPrefix.Kilo.toString();
        } else {
            MetricPrefix prefix = MetricPrefix.valueOf(prefixString);
            prefix = prefix.next();
            return prefix.toString();
        }
    }
    //</editor-fold>
    //</editor-fold>
}
