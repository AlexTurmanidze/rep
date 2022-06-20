package web.dao;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import web.model.Car;

@Repository
public class CarDaoImpl implements CarDao {

    @Override
    public List<Car> list() {
        List<Car> listCars = new ArrayList<>();
        listCars.add(new Car(1, 1, "model1"));
        listCars.add(new Car(2, 2, "model2"));
        listCars.add(new Car(3, 3, "model3"));
        listCars.add(new Car(4, 4, "model4"));
        listCars.add(new Car(5, 5, "model5"));
        
        return listCars;
    }
}
