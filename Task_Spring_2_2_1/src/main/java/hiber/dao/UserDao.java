package hiber.dao;

import hiber.model.Car;
import java.util.List;
import hiber.model.User;

public interface UserDao {

    void add(User user);

    List<User> listUsers();

    List<Car> listCars();
    
    User getUserByCarSeriesModel(Integer series, String model);
}
