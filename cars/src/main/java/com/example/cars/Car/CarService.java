package com.example.cars.Car;

import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.*;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addNewCar(Car car) {
        if ((car.getColor() != null) && (car.getModel() != null)
                && (car.getPrice() != 0) && (car.getYearsOfMake() != null)) {
            return carRepository.save(car);
        }
        return null;
    }

    public List<Car> searchCar(Car car) {
        int priceCar = car.getPrice();
        LocalDate years = car.getYearsOfMake();
        String color = car.getColor();
        return carRepository.findCar(color, priceCar, years);
    }


    public ResponseEntity<String> carSale(String amount, Car car1) {
        int amountCar = Integer.parseInt(amount);
        Long carID = car1.getId();
        Car car = carRepository.findById(carID).orElse(null);
        if (amountCar - car.getPrice() == 0) {
            return ResponseEntity.status(200).body("The car has been sold , Congratulation ");
        } else if (amountCar > car.getPrice()) {
            amountCar -= car.getPrice();
            return ResponseEntity.status(200).body("The car has been sold , The rest of the amount " + amountCar + "  Congratulation");
        } else if (amountCar < car.getPrice()) {
            return ResponseEntity.status(400).body("The amount is not enough for the price of the car " + car.getPrice() + " SR");
        }
        return ResponseEntity.status(400).body("error");
    }

    public ResponseEntity<String> deleteOldCar() {
      List <Car> car=carRepository.findAll();
      LocalDate currentDate = LocalDate.now();
        for (Car i :car){
            if (currentDate.getYear()-i.getYearsOfMake().getYear()>=5){
                System.out.println(currentDate.getYear()-i.getYearsOfMake().getYear()>=5);
                car.remove(i.getId());
                continue;
            }
            System.out.println(i.getYearsOfMake());
            carRepository.save(i);
            return ResponseEntity.status(200).body("the cars of year of make less then or equaled 5 years is deleted in the system  ");
        }
        return ResponseEntity.status(200).body("the cars of year of make more then or equaled 5 years  ");

    }
}
