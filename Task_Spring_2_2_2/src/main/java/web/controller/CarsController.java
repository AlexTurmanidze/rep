package web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;

@Controller
@RequestMapping("/cars")
public class CarsController {

    @GetMapping
    public String list(@RequestParam(value = "count", required = false) String count, ModelMap model) {
        List<Car> listCars = new ArrayList<>();
        listCars.add(new Car(1, 1, "model1"));
        listCars.add(new Car(2, 2, "model2"));
        listCars.add(new Car(3, 3, "model3"));
        listCars.add(new Car(4, 4, "model4"));
        listCars.add(new Car(5, 5, "model5"));
        
        if (count != null) {
            if (!count.isEmpty()) {
                int num = Integer.parseInt(count);
                if (num < listCars.size()) {
                    listCars = listCars.subList(0, num);
                }
            }
        }
                
        model.addAttribute("cars", listCars);
        
        return "carslist";
    }
}
