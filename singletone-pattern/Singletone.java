public class Singletone {

    private static Singletone singletone;

    private Singletone() {

        // Private constructor to prevent instantiation
        // if (singletone != null) {
        //     throw new IllegalStateException("Singleton instance already created.");
        // }
    }

    public static Singletone getSingleton() {

        if (singletone == null) {
            singletone = new Singletone();
        }

        return singletone;

    }
}