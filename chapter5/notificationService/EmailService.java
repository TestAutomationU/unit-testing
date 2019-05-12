package notificationService;

/**
 * Wraps an external service used for sending notifications to customers via e-mail.
 * @author Tariq King
 */
public class EmailService implements Notification {

    private boolean connected;

    public EmailService()
    {
        this.connected=false;
    }

    @Override
    public void notify(String message, String recipient) {
        // Send message call to external service
    }

    public void connect() { connected=true; };

    public void disconnect () { connected = false; }

    public boolean isConnected() { return this.connected; }

}