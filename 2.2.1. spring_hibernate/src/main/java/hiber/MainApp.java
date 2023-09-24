package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CarService carService = context.getBean(CarService.class);

        carService.add(new Car("model_1", 1));
        carService.add(new Car("model_2", 2));
        carService.add(new Car("model_3", 3));
        carService.add(new Car("model_4", 4));

        List<Car> cars = carService.listCars();
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", cars.get(0)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", cars.get(1)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", cars.get(2)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", cars.get(3)));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }


        System.out.println(userService.findUserByCar(cars.get(0)));

        context.close();
    }
}
