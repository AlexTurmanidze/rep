package web.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

@Repository("UserRepository")
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            return entityManager.merge(user);
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
