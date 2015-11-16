package misc;

/**
 *
 * @author 6523617
 */
public class InvalidAttributesException extends Exception {
    public InvalidAttributesException() {
        super("All attributes need to be continuous");
    }
}
