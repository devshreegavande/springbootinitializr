package com.devdemo.angular.angulardemo;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue
    private Long id;

    @CsvBindByPosition(position = 0)
    private @NonNull String name;

    @CsvBindByPosition(position = 1)
    private @NonNull Integer year;
}
