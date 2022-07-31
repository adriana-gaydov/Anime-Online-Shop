package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.model.entity.AddressEntity;
import bg.softuni.onlineshop.repository.AddressRepository;
import bg.softuni.onlineshop.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void save(AddressEntity address) {
        this.addressRepository.save(address);
    }

    @Override
    public AddressEntity findById(Long addressId) {

        return this.addressRepository.findById(addressId)
                .orElse(null);
    }
}
