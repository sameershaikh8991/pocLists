import java.io.Serializable;

public class SerializedSingleton implements Serializable {
    private static final long serialVersionUID = 1L;
    private static SerializedSingleton instance;

    private SerializedSingleton() {

    }

    public static SerializedSingleton getInstance() {
        if (instance == null) {
            // synchronized (SerializedSingleton.class) {
            //     if (instance == null) {
                    instance = new SerializedSingleton();
                // }
            // }
        }
        return instance;
    }

    protected Object readResolve() {

        return getInstance();
    }
}
