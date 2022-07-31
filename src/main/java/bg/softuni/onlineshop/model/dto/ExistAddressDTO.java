package bg.softuni.onlineshop.model.dto;

import javax.validation.constraints.NotNull;

public class ExistAddressDTO {

    @NotNull(message = "Please select an address!")
    private Long address;

    public ExistAddressDTO() {
    }

    public Long getAddress() {
        return address;
    }

    public ExistAddressDTO setAddress(Long address) {
        this.address = address;
        return this;
    }
}
