package Trail;

import Base.BaseUtil;
import org.apache.commons.lang.StringUtils;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;


public class App_Launch extends BaseUtil {


    public static String _converted_date(String date) {

        String New_date_format = null;

        // Creating an empty HashMap
        HashMap<String, String> hash_map = new HashMap();

        // Mapping String values to String keys
        hash_map.put("Jan", "01");
        hash_map.put("Feb", "02");
        hash_map.put("Mar", "03");
        hash_map.put("Apr", "04");
        hash_map.put("May", "05");
        hash_map.put("Jun", "06");
        hash_map.put("Jul", "07");
        hash_map.put("Aug", "08");
        hash_map.put("Sep", "09");
        hash_map.put("Oct", "10");
        hash_map.put("Nov", "11");
        hash_map.put("Dec", "12");

        if (date.contains(",")) {
            //Seperating month from original date
            String initial_date = StringUtils.substringBefore(date, " ");

            //Converting month into numberic using HashMap
            String converted_month = hash_map.get(initial_date);


            //Seperating Year from original date
            String Year = StringUtils.substringAfter(date, ", ");

            //Seperating DATE from original date
            String DATE = StringUtils.substringBefore(StringUtils.substringAfter(date, " "), ",");

            if (DATE.length() == 1) {
                DATE = "0" + DATE;
            }

            New_date_format = DATE + "-" + converted_month + "-" + Year;


        } else {

            //Separating month from original date
            String initial_date = StringUtils.substringBefore(StringUtils.substringAfter(date, " "), " ");


            //Converting month into numeric using HashMap
            String converted_month = hash_map.get(initial_date);

            //Separating Year from original date
            String Year = StringUtils.substringAfter(StringUtils.substringAfter(date, " "), " ");

            //Separating DATE from original date
            String DATE = StringUtils.substringBefore(date, " ");

            if (DATE.length() == 1) {
                DATE = "0" + DATE;
            }

            New_date_format = DATE + "-" + converted_month + "-" + Year;


        }


        return New_date_format;
    }


    public static int _currenttimestamp() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String minute = StringUtils.substringBefore(StringUtils.substringAfter(timestamp.toString(), "-"), "-");

        System.out.println(Integer.parseInt(minute));
        return Integer.parseInt(minute);

    }


    public static void main(String[] args) throws ParseException {

       /* System.out.println(LocalTime.now());
        LocalDateTime date = LocalDateTime.now();
        int seconds = date.toLocalTime().toSecondOfDay();

        System.out.println(seconds);

        */

      /*  SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date = parseFormat.parse("00:30 AM");
        System.out.println(parseFormat.format(date) + " = " + displayFormat.format(date));



        String time = displayFormat.format(date); //HH:MM
        String[] units = time.split(":"); //will break the string up into an array
        int hours = Integer.parseInt(units[0]); //first element
        int minutes = Integer.parseInt(units[1]); //second element
        int duration = 3600 * hours + 60 * minutes; //add up our values

        System.out.println(duration);

       */


     /*   System.out.println(_get_current_time_in_sec());
        System.out.println(_get_provided_time_in_sec("12:00 PM "));

        String value = "12:00 PM - 1:00 PM";

        System.out.println(StringUtils.substringBefore(value, " -"));

      */



boolean abc = _get_provided_time_in_sec("17:30 PM - 18:30 PM")-600 <=
        _get_current_time_in_sec() && _get_provided_time_in_sec("12:00 PM - 1:00 PM")+600
        <=_get_current_time_in_sec();

//System.out.println(abc);

      int before =   _get_provided_time_in_sec("06:00 PM - 07:00 PM")-600;
      int after =  _get_provided_time_in_sec("06:00 PM - 07:00 PM")+600;
      System.out.println(before);
      System.out.println(after);
      System.out.println(_get_current_time_in_sec());


      boolean qwerty = _get_current_time_in_sec()>= before && _get_current_time_in_sec()<= after;

      System.out.println(qwerty);





}

    }


