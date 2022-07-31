package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.model.entity.AddressEntity;

public interface AddressService {
    void save(AddressEntity address);

    AddressEntity findById(Long addressId);
}
