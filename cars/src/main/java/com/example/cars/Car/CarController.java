package com.example.cars.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }
    @PostMapping("/post")
    public Car addNewCar(@RequestBody Car car){
        System.out.println(car.toString());
       return carService.addNewCar(car);
    }
    @GetMapping("/getCar")
    public List<Car> searchCar(@RequestBody Car car){
        System.out.println(car.toString());
        return carService.searchCar(car);
    }
    @PutMapping("/carSale/{amount}")
    public ResponseEntity <String> carSale(@PathVariable String amount,@RequestBody Car car){
        System.out.println(car.toString());
        System.out.println(amount);
        return carService.carSale(amount,car);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String>deleteOldCar(){
        return carService.deleteOldCar();
    }




}
