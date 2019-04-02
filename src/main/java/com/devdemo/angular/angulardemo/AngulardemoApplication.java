package com.devdemo.angular.angulardemo;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class AngulardemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AngulardemoApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CarRepository repository) {
        return args -> {
                List<Car> carList = repository.findAll();
                if (carList != null && !carList.isEmpty()){
                    repository.deleteAll();
                    System.out.println("Deleted all rows..");
                }

                Reader reader = Files.newBufferedReader(Paths.get("/Users/devshreeg/angularApps/Cars.csv"));
                CsvToBean<Car> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Car.class)
                        .withSkipLines(1)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                Iterator<Car> csvIterator = csvToBean.iterator();

                while (csvIterator.hasNext()) {
                    Car car = csvIterator.next();
//                    System.out.println("Name : " + car.getName());
//                    System.out.println("Year : " + car.getYear().toString());
//                    System.out.println("==========================");
                    repository.save(car);
                }
                repository.findAll().forEach(System.out::println);
                reader.close();
        };
    }



}

