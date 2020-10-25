package recruitment.assignment.recruitment.util;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicIdCounter {

    public static synchronized Long getRandomUID() {
        AtomicLong counter = new AtomicLong(System.currentTimeMillis());
        Random r = new Random();
        Long value = (counter.incrementAndGet() + r.nextInt());
        return value;
    }
}
