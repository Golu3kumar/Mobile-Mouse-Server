package sample;

public class HistoryDevices {

    private String deviceName;
    private String lastConnected;
    private String ipAddress;

    public HistoryDevices() {
    }

    public HistoryDevices(String deviceName, String lastConnected, String ipAddress) {
        this.deviceName = deviceName;
        this.lastConnected = lastConnected;
        this.ipAddress = ipAddress;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getLastConnected() {
        return lastConnected;
    }

    public void setLastConnected(String lastConnected) {
        this.lastConnected = lastConnected;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return deviceName + "\t\t" + lastConnected + "\t\t" + ipAddress;
    }
}
