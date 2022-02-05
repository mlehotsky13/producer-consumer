package aero.avitech.repository;

import aero.avitech.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteAllUsers();
}