package info.androidhive.firebasenotifications.app;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ravi Tamada on 28/09/16.
 * www.androidhive.info
 */

public class Config {
    //Random rand = new Random();
    public static final int n = 0;
    //public static final int x = n;
    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    private final static AtomicInteger c = new AtomicInteger(0);
    public static int getID() {
        return c.incrementAndGet();
    }
    public static int getID1(){
        c.incrementAndGet();
        return c.incrementAndGet();

    }

    // id to handle the notification in the notification tray
   // public static final int NOTIFICATION_ID = n;
   // public static final int NOTIFICATION_ID_BIG_IMAGE = n+1;
   // n = n+1;

    public static final String SHARED_PREF = "ah_firebase";
}
