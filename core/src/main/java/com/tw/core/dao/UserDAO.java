package com.tw.core.dao;

import com.tw.core.User;
import com.tw.core.service.PasswordService;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by twer on 7/17/14.
 */
@Repository
@Transactional(readOnly = true)
public class UserDAO {

    private SessionFactory sessionFactory;
    private PasswordService passwordService;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.passwordService = new PasswordService();
    }

    public List<User> listUser() {
        return sessionFactory.getCurrentSession().createQuery("from User")
                .list();
    }

    public void addUser(User user) {
        user.setPassword(passwordService.encrypt(user.getPassword()));
        sessionFactory.getCurrentSession().save(user);
    }

    public User findUserById(long id) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
        return user;
    }

    public User findUserByNameAndPassword(User user) {
        User rUser;
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("name", user.getName()));
        criteria.add(Restrictions.eq("password", passwordService.encrypt(user.getPassword())));
        criteria.setFirstResult(0);
        criteria.setMaxResults(1);

        if (criteria.list().size() > 0) {
            rUser = (User) criteria.list().get(0);
        } else {
            rUser = new User();
        }
        return rUser;
    }

    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public void deleteUser(long id) {
        User user = findUserById(id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    public void deleteUserList(long[] ids) {
        for (int index = 0; index < ids.length; index++) {
            deleteUser(ids[index]);
        }
    }
}
