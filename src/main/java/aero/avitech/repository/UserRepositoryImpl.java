package aero.avitech.repository;

import aero.avitech.entity.User;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("Select u From User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAllUsers() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("Delete From User").executeUpdate();
        entityManager.getTransaction().commit();
    }
}