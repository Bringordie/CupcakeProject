package domainmodel;

/**
 * The purpose of LogicException is to turn Exceptions from the persistence
 * layer (for instance SQLException) into so-called business exception to keep
 * low coupling between the layers and keep the encapsulation of the persistence
 * layer
 *
 * @author tine
 */
public class LoginException extends Exception {

    public LoginException(String msg) {
        super(msg);
    }

}
