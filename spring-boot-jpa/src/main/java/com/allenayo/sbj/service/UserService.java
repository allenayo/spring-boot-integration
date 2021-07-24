package com.allenayo.sbj.service;

import com.allenayo.sbj.dao.UserRepository;
import com.allenayo.sbj.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userDao;

    public UserService(UserRepository userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.save(user);
    }

    public User findById(long id) {
        return userDao.findById(id).orElse(null);
    }

    public void deleteById(long id) {
        userDao.deleteById(id);
    }
}