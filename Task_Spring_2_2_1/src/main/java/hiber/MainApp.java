package hiber;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;
import java.sql.SQLException;
import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        Car car1 = new Car(4, "model1");
        user1.setCar(car1);
        
        userService.add(user1);
        
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        Car car2 = new Car(3, "model2");
        user2.setCar(car2);
        
        userService.add(user2);
        
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        Car car3 = new Car(2, "model3");
        user3.setCar(car3);
        
        userService.add(user3);
        
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        Car car4 = new Car(1, "model4");
        user4.setCar(car4);
        
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        
        List<Car> cars = userService.listCars();
        for (Car car : cars) {
            System.out.println("Id = " + car.getId());
            System.out.println("Model = " + car.getModel());
            System.out.println("Series = " + car.getSeries());
            System.out.println();
        }
        
        System.out.println(userService.getUserByCarSeriesModel(4, "model1"));

        context.close();
    }
}
