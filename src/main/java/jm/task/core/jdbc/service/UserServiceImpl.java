package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao hyber = new UserDaoHibernateImpl();

    public void createUsersTable() {
        hyber.createUsersTable();
    }

    public void dropUsersTable() {
        hyber.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        hyber.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        hyber.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return hyber.getAllUsers();
    }

    public void cleanUsersTable() {
        hyber.cleanUsersTable();
    }
}
