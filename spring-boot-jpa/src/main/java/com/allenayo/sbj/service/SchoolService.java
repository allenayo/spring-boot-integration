package com.allenayo.sbj.service;

import com.allenayo.sbj.dao.SchoolRepository;
import com.allenayo.sbj.domain.School;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    private final SchoolRepository schoolDao;

    public SchoolService(SchoolRepository schoolDao) {
        this.schoolDao = schoolDao;
    }

    public void save(School school) {
        schoolDao.save(school);
    }

    public void update(School school) {
        schoolDao.save(school);
    }

    public School findById(long id) {
        return schoolDao.findById(id).orElse(null);
    }

    public void deleteById(long id) {
        schoolDao.deleteById(id);
    }
}