public class Singletone {

    private static Singletone singletone;

    private Singletone() {

    }

    public static Singletone getSingleton() {

        if (singletone == null) {
            singletone = new Singletone();
        }

        return singletone;

    }
}