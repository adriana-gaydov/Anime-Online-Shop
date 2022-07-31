package bg.softuni.onlineshop.model.dto;

import bg.softuni.onlineshop.model.enums.CountryEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddressDTO {

    @NotNull(message = "Country must not be null!")
    private CountryEnum country;
    @NotBlank(message = "City must not be empty!")
    private String city;

    @NotBlank(message = "Zip code must not be empty!")
    private String zipCode;

    @NotBlank(message = "Street must not be empty!")
    private String street;

    private Long address;

    public AddressDTO() {
    }

    public CountryEnum getCountry() {
        return country;
    }

    public AddressDTO setCountry(CountryEnum country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public AddressDTO setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public Long getAddress() {
        return address;
    }

    public AddressDTO setAddress(Long address) {
        this.address = address;
        return this;
    }
}
