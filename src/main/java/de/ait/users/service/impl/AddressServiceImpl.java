package de.ait.users.service.impl;

import de.ait.users.model.Address;
import de.ait.users.repository.AddressRepository;
import de.ait.users.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    @Override
    public List<Address> findAllAddresses() {
        return repository.findAll();
    }

    @Override
    public Address findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Address> getAddressesByCountry(String country) {
        return repository.findByCountry(country);
    }

    @Override
    public List<Address> getAddressesByCityAndStreet(String city, String street) {
        return repository.findByCityAndStreetContaining(city, street);
    }

    @Override
    public Address createAddress(Address address) {
        return repository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Address updateAddress(Long id, Address address) {
        return repository.updateAddress(id, address);
    }
}
