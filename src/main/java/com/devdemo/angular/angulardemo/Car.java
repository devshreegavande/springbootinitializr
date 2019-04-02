package com.devdemo.angular.angulardemo;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="cars")
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @CsvBindByPosition(position = 0)
    @Column(name="name")
    private @NonNull String name;

    @CsvBindByPosition(position = 1)
    @Column(name="year")
    private @NonNull Integer year;
}
