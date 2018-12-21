package com.mk.dao;

import com.mk.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Student> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Student> query =
                session.createQuery("from Student", Student.class);

        List<Student> students = query.getResultList();
        return students;
    }
}
