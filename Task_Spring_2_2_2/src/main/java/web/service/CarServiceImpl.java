package web.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import web.dao.CarDao;
import web.model.Car;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;
    
    @Override
    public List<Car> list() {
        return carDao.list();
    }
}
