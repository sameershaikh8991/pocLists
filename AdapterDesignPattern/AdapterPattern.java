
public class AdapterPattern {

    public static void main(String[] args) {
        System.out.println("Adapter Design Pattern");

        // IphoneCharderABC abc = new IphoneCharderABC();
        AndroidXYZ androidXYZ = new AndroidXYZ();
        AdapterCharger adapterCharger = new AdapterCharger(new AndroidXYZ()); 
        Iphone iphone = new Iphone(adapterCharger);
        iphone.chargeIphone();
    }
}
