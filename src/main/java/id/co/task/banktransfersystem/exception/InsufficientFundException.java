package id.co.task.banktransfersystem.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsufficientFundException extends Exception {
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public InsufficientFundException(String message) {
        super(message);
        logger.error(this.getMessage());
    }
}
