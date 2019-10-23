package persistence;

import domainmodel.LoginException;
import domainmodel.User;

/**
 * The purpose of IUserMapper is to provide a unique way for different mapper implementation,
 * for instance storing data in database or text file
 *
 * @author tine
 */
public interface IUserMapper {

    public void createUser(User user) throws LoginException;

    public User login(String email, String password) throws LoginException;
}
