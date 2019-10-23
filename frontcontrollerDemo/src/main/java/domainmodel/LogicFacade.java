package domainmodel;

import persistence.DBFacade;

/**
 * The purpose of LogicFacade is to keep low coupling between presentation layer
 * and domainmodel layer by being the first and only object beyond the
 * presentation layer to receive and coordinate a system operation (the
 * application workflow logic) Implements patterns: Facade and Controller (aka
 * Service Layer)
 *
 * @author tine
 */
public class LogicFacade {

    public static User login(String email, String password) throws LoginException {
        return DBFacade.login(email, password);
    }

    public static User createUser(String email, String password) throws LoginException {
        User user = new User(email, password, "customer");
        DBFacade.createUser(user);
        return user;
    }
}
