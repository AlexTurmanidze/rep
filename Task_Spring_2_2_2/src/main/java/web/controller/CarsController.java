package web.controller;

import java.util.List;
import web.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;

@Controller
@RequestMapping("/cars")
public class CarsController {
    @Autowired
    private CarService carService;
    
    @GetMapping
    public String list(@RequestParam(value = "count", required = false) String count, ModelMap model) {
        List<Car> listCars = carService.list();
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
