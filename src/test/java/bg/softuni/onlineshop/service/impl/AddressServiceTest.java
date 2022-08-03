package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.model.entity.AddressEntity;
import bg.softuni.onlineshop.model.entity.ProductEntity;
import bg.softuni.onlineshop.model.enums.CountryEnum;
import bg.softuni.onlineshop.repository.AddressRepository;
import bg.softuni.onlineshop.service.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    private AddressService addressService;

    @BeforeEach
    void setUp() {
        this.addressService = new AddressServiceImpl(addressRepository);
    }

    @Test
    void testSaveAddress() {

        AddressEntity addressToSave = new AddressEntity(CountryEnum.ANGOLA, "test",
                "tst", "ts");

        AddressEntity expected = new AddressEntity(CountryEnum.ANGOLA, "test",
                "tst", "ts");

        when(addressRepository.save(Mockito.any(AddressEntity.class))).thenReturn(expected);
        AddressEntity returned = this.addressService.save(addressToSave);

        Assertions.assertEquals(returned.getCountry().name(), expected.getCountry().name());
        Assertions.assertEquals(returned.getCity(), expected.getCity());
        Assertions.assertEquals(returned.getZipCode(), expected.getZipCode());
        Assertions.assertEquals(returned.getStreet(), expected.getStreet());
    }
}
