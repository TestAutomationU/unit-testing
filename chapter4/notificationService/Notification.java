package notificationService;

/**
 * Interface specifying messaging capabilities.
 * @author Tariq King
 */
public interface Notification {

    public void notify(String message, String recipient);

    public void connect();

    public void disconnect();

    public boolean isConnected();

}