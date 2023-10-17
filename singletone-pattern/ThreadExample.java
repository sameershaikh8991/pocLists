import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SingletonThred implements Runnable {
    private String threadName;

    public SingletonThred(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        SerializedSingleton singleton = SerializedSingleton.getInstance();
        System.out.println(threadName + " - Singleton hash code: " + System.identityHashCode(singleton));
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        // Thread thread1 = new Thread(new SingletonThred("Thread 1"));
        // Thread thread2 = new Thread(new SingletonThred("Thread 2"));

        // // Start both threads
        // thread1.start();
        // thread2.start();

        int numberOfThreads = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(new SingletonThred("Thread " + (i + 1)));
        }

    }
}