package controllers.notifications;

/**
 * Created by quyet on 12/31/2016.
 */
public interface EventSubcriber {
    boolean onEvent(EventType eventType, Object o, int scope);

}
