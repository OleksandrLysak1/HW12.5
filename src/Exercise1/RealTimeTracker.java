package Exercise1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RealTimeTracker {
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
    private static volatile boolean isMyThread1Started = false;

    public static void main(String[] args) {

        executor.scheduleAtFixedRate(new TimePrinter(1), 0, 1, TimeUnit.SECONDS);

        executor.scheduleWithFixedDelay(new FiveSecondNotifier(2), 5, 5, TimeUnit.SECONDS);
    }

    public static void notifyMyThread1Started() {
        isMyThread1Started = true;
    }
}

class TimePrinter implements Runnable {
    private final LocalDateTime startTime;
    private final int threadId;

    public TimePrinter(int threadId) {
        this.startTime = LocalDateTime.now();
        this.threadId = threadId;
        RealTimeTracker.notifyMyThread1Started();
    }

    @Override
    public void run() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(startTime, now);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedNow = now.format(formatter);
        System.out.println("Thread " + threadId + " - Current Time: " + formattedNow + " | Elapsed Time: " + duration.getSeconds() + " seconds");
    }
}

class FiveSecondNotifier implements Runnable {
    private final int threadId;

    public FiveSecondNotifier(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        System.out.println("Thread " + threadId + " - 5 seconds have passed");
    }
}
