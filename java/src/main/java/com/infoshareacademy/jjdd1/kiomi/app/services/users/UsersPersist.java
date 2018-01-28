package com.infoshareacademy.jjdd1.kiomi.app.services.users;

import com.infoshareacademy.jjdd1.kiomi.app.model.cars.GoogleUser;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by marcin on 28.05.17.
 */
public class UsersPersist implements IUsers {


    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("database-autoparts");
    private EntityManager entityManager = emf.createEntityManager();

    @Override
    @Transactional
    public void addUser(User userToAdd) {
            entityManager.getTransaction().begin();
            entityManager.persist(userToAdd);
            entityManager.getTransaction().commit();

    }


    @Override
    @Transactional
    public void removeUser(String emailOfUserToRemove) {


            Query q = entityManager.
                    createQuery("DELETE FROM User u WHERE u.email = :email").
                    setParameter("email", emailOfUserToRemove);
            entityManager.getTransaction().begin();
            q.executeUpdate();
            entityManager.getTransaction().commit();

    }

    public User findUserInDatabase(GoogleUser googleUser) {

        User user = entityManager.
                createQuery("SELECT m FROM  User m WHERE m.email = :email " +
                        "ORDER BY m.email", User.class)
                .setParameter("email", googleUser.getEmail()).getSingleResult();
        return user;

    }


    @Override
    @Transactional
    public List<User> getAllUsers() {

        TypedQuery<User> typedQuery = entityManager
                .createQuery("SELECT u FROM User u", User.class);

        return typedQuery.getResultList();
    }

}
