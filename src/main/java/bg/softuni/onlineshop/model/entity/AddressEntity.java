package bg.softuni.onlineshop.model.entity;

import bg.softuni.onlineshop.model.enums.CountryEnum;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addresses")
public class AddressEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CountryEnum country;

    @Column(nullable = false)
    private String city;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @Column(nullable = false)
    private String street;

    public AddressEntity() {}

    public CountryEnum getCountry() {
        return country;
    }

    public AddressEntity setCountry(CountryEnum country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public AddressEntity setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return country == that.country && Objects.equals(city, that.city) && Objects.equals(zipCode, that.zipCode) && Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, zipCode, street);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", this.country, this.city, this.zipCode, this.street);
    }
}
