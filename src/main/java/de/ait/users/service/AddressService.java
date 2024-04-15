package de.ait.users.service;

import de.ait.users.model.Address;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressService {
    List<Address> findAllAddresses();

    Address findById(Long id);

    List<Address> getAddressesByCountry(String country);

    List<Address> getAddressesByCityAndStreet(String city, String street);

    Address createAddress(Address address);

    void deleteAddress(Long id);

    Address updateAddress(Long id, Address address);
}
