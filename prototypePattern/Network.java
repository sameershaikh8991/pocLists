import java.util.ArrayList;
import java.util.List;

public class Network implements Cloneable {

    private String ip;
    private String impData;

    private List<String> deomain = new ArrayList<>();

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getImpData() {
        return impData;
    }

    public void setImpData(String impData) {
        this.impData = impData;
    }

    public void loadImpdata() throws InterruptedException {
        this.impData = "very very imp data";
        deomain.add("google.com");
        deomain.add("fb.com");
        deomain.add("gmail.com");
        Thread.sleep(5000);
    }

    @Override
    public String toString() {
        return "Network ip=" + this.ip + ", impData=" + this.impData + " " + this.deomain;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        // for deep copy need to write a login in clone methods
        Network network = new Network();
        network.setIp(this.getIp());
        network.setImpData(this.impData);
        for (String d : this.deomain) {
            network.getDeomain().add(d);
        }
        return network;
    }

    public List<String> getDeomain() {
        return deomain;
    }

    public void setDeomain(List<String> deomain) {
        this.deomain = deomain;
    }

}
