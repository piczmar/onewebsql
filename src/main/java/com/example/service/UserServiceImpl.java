package com.example.service;

import com.generated.onewebsql.User;
import com.generated.onewebsql.UserDAO;
import com.onewebsql.query.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService implementation using OneWebSQL API
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public List<User> getUsersByLogin(String login) {
        SelectQuery selectQuery = new SelectQuery(UserDAO.TABLE_EXPRESSION);

        selectQuery.setWhere(
                UserDAO.LOGIN.eq(login)
                //.and(UserDAO.PASSWORD).eq(password)
        );
        return userDAO.getUserList(selectQuery);
    }

    @Override
    public User save(User user) {
        if (user.getId() != null) {
            userDAO.update(user);
        } else {
            userDAO.insert(user);
        }
        return userDAO.getByPK(user.getId());
    }
}
