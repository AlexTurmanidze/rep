package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        
        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 20);
        userService.saveUser("Петр", "Петров", (byte) 32);
        userService.saveUser("Сидр", "Сидоров", (byte) 45);
        userService.saveUser("Владимир", "Владимиров", (byte) 58);
        
        userService.getAllUsers().forEach(System.out::println);
        
//        userService.removeUserById(-934513269);
//        userService.getAllUsers().forEach(System.out::println);
        
        userService.cleanUsersTable();
        userService.getAllUsers().forEach(System.out::println);
        
        userService.dropUsersTable();
        
        userService.close();
    }
}
