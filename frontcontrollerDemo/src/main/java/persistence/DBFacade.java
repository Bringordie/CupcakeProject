package persistence;

import domainmodel.LoginException;
import domainmodel.User;

/**
 * The purpose of DBFacade is to keep low coupling between domainmodel layer
 * and persistence layer by providing a unified interface to the persistence layer
 * It delegates database operation requests to mapper objects
 * Implements pattern: Facade
 *
 * @author tine
 */
public class DBFacade {

    private static IUserMapper userMapper = new UserMapper();

    public static User login(String email, String password) throws LoginException {
        return userMapper.login(email, password);
    }
    
    public static User createUser(User user) throws LoginException {
        userMapper.createUser(user);
        return user;
    }

}
