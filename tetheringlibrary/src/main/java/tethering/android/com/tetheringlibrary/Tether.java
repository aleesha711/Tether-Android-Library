package tethering.android.com.tetheringlibrary;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * Created by Aleesha Kanwal on 10/10/2018.
 */
public class Tether {

    private boolean isTetheringEnabled(Context context) {

        boolean isWifiAPenabled = false;

        final WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        Method[] wmMethods = wifi.getClass().getDeclaredMethods();
        for (Method method : wmMethods) {
            if (method.getName().equals("isWifiApEnabled")) {
                try {
                    isWifiAPenabled = (boolean) method.invoke(wifi);
                    Log.e("Tethering", String.valueOf(isWifiAPenabled));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return isWifiAPenabled;
    }

    public List<Client> getTetheringClientsList(Context context) {

        List<Client> result = new ArrayList<>();

        if (!isTetheringEnabled(context)) {
            return null;
        }


        // Basic sanity checks
        Pattern macPattern = Pattern.compile("..:..:..:..:..:..");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/proc/net/arp"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" +");
                if (parts.length < 6) {
                    continue;
                }

                String ipAddr = parts[0];
                String hwAddr = parts[3];
                String device = parts[5];

                String mac = parts[3];

                System.out.println("ip:" + ipAddr + " mac : " + hwAddr);


                if (!macPattern.matcher(parts[3]).find()) {
                    continue;
                }

                if (mac.matches("..:..:..:..:..:..")) {

                    boolean isReachable = InetAddress.getByName(parts[0]).isReachable(300);

                    if (InetAddress.getByName(parts[0]).isReachable(300)) {

                        System.out.println("Mac = " + parts[3] + " IP = " + parts[0] + " is Reachable = " + isReachable);
                        result.add(new Client(ipAddr, hwAddr));

                        Set<Client> withoutDuplicates = new LinkedHashSet<Client>(result);
                        result.clear(); // copying elements but without any duplicates primes.addAll(withoutDuplicates);

                        result.addAll(withoutDuplicates);
                    }
                    else {
                        Log.e("ip =" + parts[0],"disconnected");
                    }
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "", e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "", e);
            }
        }

        return result;
    }
}
