package ADBExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ADBExecutor {

    public String udid() {
        String s;
        Process p;
        String finalUDID;

        try {
            p = Runtime.getRuntime().exec("adb devices");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            List<String> data = new ArrayList<>();
            while ((s = stdInput.readLine()) != null) {
                data.add(s);
            }
            String newUdId = data.get(1);
            StringTokenizer st = new StringTokenizer(newUdId);
            List<String> ud = new ArrayList<>();
            while (st.hasMoreTokens()) {
                ud.add(st.nextToken());
            }
            finalUDID = ud.get(0);
            return finalUDID;

        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            finalUDID = "Failed";
            return finalUDID;
        }
    }

    public String getDeviceInformation(String valuetoFetch) {
        ADBExecutor adbCommand = new ADBExecutor();
        String udidOfConnectedDevice = adbCommand.udid();
        String execCommand = ("adb -s " + udidOfConnectedDevice + " shell getprop | grep " + valuetoFetch);
        Process p;
        String[] splittedValue;
        try {
            p = Runtime.getRuntime().exec(execCommand);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String value = stdInput.readLine();
            List<String> newArray = new ArrayList<>();
            if (value != null) {
                splittedValue = value.split(":");
                newArray.add(splittedValue[1].replace("[", "").replace("]", "").trim());
            }

            return newArray.get(0);
        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            return  "Failed";
        }
    }

    public void enableGPSGlobally() {
        Process p;
        try {
            p = Runtime.getRuntime().exec("adb shell getprop ro.build.version.sdk");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int value = Integer.parseInt(stdInput.readLine());
            if (value < 29) {
                try {
                    p = Runtime.getRuntime().exec("adb shell settings put secure location_providers_allowed +gps,network");
                } catch (IOException e) {
                    System.out.println("exception happened - here's what I know: ");
                    e.printStackTrace();
                }
            } else {
                try {
                    p = Runtime.getRuntime().exec("adb shell settings put secure location_mode 3");
                } catch (IOException e) {
                    System.out.println("exception happened - here's what I know: ");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
        }
    }
}
