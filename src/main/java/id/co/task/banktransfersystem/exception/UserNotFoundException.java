package id.co.task.banktransfersystem.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public UserNotFoundException(String username) {
        super(String.format(username, "is not found"));
        logger.error(this.getMessage());
    }
}
