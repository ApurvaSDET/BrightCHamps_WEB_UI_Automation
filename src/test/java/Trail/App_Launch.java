package Trail;

import Base.BaseUtil;
import org.apache.commons.lang.StringUtils;
import java.sql.*;
import java.util.HashMap;


public class App_Launch extends BaseUtil {


    public static  String _converted_date(String date)

    {

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

        if(date.contains(","))
        {
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


        }

        else {

            //Separating month from original date
            String initial_date = StringUtils.substringBefore(StringUtils.substringAfter(date, " "), " ");


            //Converting month into numeric using HashMap
            String converted_month =  hash_map.get(initial_date);

            //Separating Year from original date
            String Year = StringUtils.substringAfter(StringUtils.substringAfter(date, " "), " ");

            //Separating DATE from original date
            String DATE = StringUtils.substringBefore(date, " ");

            if(DATE.length()==1)
            {
                DATE = "0"+DATE;
            }

            New_date_format = DATE+"-"+converted_month+"-"+Year;


        }


        return New_date_format;
    }



    public static int _currenttimestamp(){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String minute = StringUtils.substringBefore(StringUtils.substringAfter(timestamp.toString(), ":"), ":");

        System.out.println(Integer.parseInt(minute));
        return Integer.parseInt(minute);

    }


    public static void main (String []args) {

    }

    }


