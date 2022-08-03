package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.model.entity.AddressEntity;

public interface AddressService {
    AddressEntity save(AddressEntity address);

    AddressEntity findById(Long addressId);
}
