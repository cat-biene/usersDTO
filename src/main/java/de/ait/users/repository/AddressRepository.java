package de.ait.users.repository;

import de.ait.users.model.Address;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    List<Address> findAll();

    Address findById(Long id);

    List<Address> findByCountry(String country);

    List<Address> findByCityAndStreetContaining(String city, String street);

    Address save(Address address);

    void deleteById(Long id);

    Address updateAddress(Long id, Address address);
}
