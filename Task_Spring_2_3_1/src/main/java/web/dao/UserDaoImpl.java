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
        List<User> listUser = findByEmail(user.getEmail());
        if (user.getId() == null && listUser.isEmpty()) {
            entityManager.persist(user);
            return user;
        } else if (user.getId() != null && (listUser.isEmpty() || user.getId().compareTo(listUser.get(0).getId()) == 0)) {
            return entityManager.merge(user);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }
    
    @Transactional(readOnly = true)
    private List<User> findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
