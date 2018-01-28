package com.infoshareacademy.jjdd1.kiomi.app.services.users;

import java.util.List;

/**
 * Created by marcin on 28.05.17.
 */
public interface IUsers {

    void addUser(User userToAdd);

    void removeUser(String emailOfUserToRemove);


    List<User> getAllUsers();

}
