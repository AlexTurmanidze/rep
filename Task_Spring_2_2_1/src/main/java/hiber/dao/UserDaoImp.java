package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        try {
            sessionFactory.getCurrentSession().persist(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }
    
    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }
    
    @Override
    public User getUserByCarSeriesModel(Integer series, String model) {
        Session session = sessionFactory.getCurrentSession();

        TypedQuery<User> query = session.createQuery("from User u join fetch u.car where u.car.series = :series and u.car.model = :model");
        query.setParameter("series", series);
        query.setParameter("model", model);

        return query.getSingleResult();
    }
}
