package controllers.notifications;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by quyet on 12/31/2016.
 */
public class NotificationCenter {
    Vector<EventSubcriber> subcribers;

    public static NotificationCenter instance = new NotificationCenter();

    private NotificationCenter() {
        subcribers  = new Vector<>();
    }
    public void register(EventSubcriber s){
        subcribers.add(s);

    }
    public void unRegister(EventSubcriber e)
    {
        subcribers.remove(e);

    }
    public void onEvent(EventType eventType, Object param, int scope){
        Iterator<EventSubcriber> iterator = subcribers.iterator();
        while (iterator.hasNext())
        {
            EventSubcriber e = iterator.next();
            if (!e.onEvent(eventType, param, scope)){
                iterator.remove();
            }
        }

    }
}
