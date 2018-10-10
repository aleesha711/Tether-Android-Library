package tethering.android.com.tetheringlibrary;

/**
 * Created by Aleesha Kanwal on 14/04/2018.
 */

public class Client {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!ipAddr.equals(client.ipAddr)) return false;
        return hwAddr.equals(client.hwAddr);
    }

    @Override
    public int hashCode() {
        int result = ipAddr.hashCode();
        result = 31 * result + hwAddr.hashCode();
        return result;
    }

    // ipAddr is the raw string of the IP Address client
    public String ipAddr;

    // hwAddr is the raw string of the MAC of the client
    public String hwAddr;

    public Client(String ipAddr, String hwAddr) {
        this.ipAddr = ipAddr;
        this.hwAddr = hwAddr;
    }
}