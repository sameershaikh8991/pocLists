import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
        System.out.println("hello");

        Singletone singletone = Singletone.getSingleton();
        System.out.println("hash code :" + singletone.hashCode());

        // Singletone singletone1 = Singletone.getSingleton();
        // System.out.println("hash code :" + singletone1.hashCode());

        Class<Singletone> singletonClass = (Class<Singletone>) Class.forName("Singletone");

        // Get the constructor of the Singleton class
        Constructor<?> constructor = singletonClass.getDeclaredConstructor();

        // Make the constructor accessible
        constructor.setAccessible(true);

        Singletone singleton1 = (Singletone) constructor.newInstance();
        System.out.println("hash code :" + singleton1.hashCode());

        // SingletonEnum singleton = SingletonEnum.INSTANCE;

        // singleton.setValue(2);
        // System.out.println(singleton.getValue());
    }
}
