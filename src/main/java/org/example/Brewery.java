package org.example;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brewery {

    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String address;

    @CsvBindByPosition(position = 2)
    private String categories;

    @CsvBindByPosition(position = 3)
    private String city;

    @CsvBindByPosition(position = 4)
    private String country;

    @CsvBindByPosition(position = 5)
    private String hours;

    @CsvBindByPosition(position = 6)
    private String keys;

    @CsvBindByPosition(position = 7)
    private String latitude;

    @CsvBindByPosition(position = 8)
    private String longitude;

    @CsvBindByPosition(position = 9)
    private String menus;

    @CsvBindByPosition(position = 10)
    private String name;

    @CsvBindByPosition(position = 11)
    private String postalCode;

    @CsvBindByPosition(position = 12)
    private String province;

    @CsvBindByPosition(position = 13)
    private String twitter;

    @CsvBindByPosition(position = 14)
    private String websites;

}
