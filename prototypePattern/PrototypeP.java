public class PrototypeP {
    public static void main(String[] args) throws InterruptedException {

        Network network = new Network();
        network.setIp("192.11.11.1");
        network.loadImpdata();

        try {
            Network network1 = (Network) network.clone();
            // shallow copy
            System.out.println(network);
            network.getDeomain().remove(0);

            System.out.println(network1);
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
