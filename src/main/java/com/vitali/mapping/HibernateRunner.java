package com.vitali.mapping;

import com.vitali.mapping.entity.Company;
import com.vitali.mapping.entity.User;
import com.vitali.mapping.util.HibernateUtil;

import javax.persistence.EntityManager;

public class HibernateRunner {
    public static void main(String[] args) {
        // прежде чем указать сущность company в билдере User, нужно ее создать
        Company company = Company.builder()
                .name("Apple")
                .build();

        User user = User.builder()
                .lastname("Jack")
                .firstname("Johnson")
                .username("jack")
                .company(company)
                .build();

        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();

        User user1 = em.find(User.class, 1);
//        em.persist(company);
//        em.persist(user);
        em.getTransaction().commit();

    }
}
