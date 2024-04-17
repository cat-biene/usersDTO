package de.ait.users.repository.impl;

import de.ait.users.model.Address;
import de.ait.users.repository.AddressRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private List<Address> addresses = new ArrayList<>(List.of(
            new Address(1L, "Ukraine", "UA123", "Kyiv", "Street 1", "12"),
            new Address(2L, "Germany", "DE456", "Berlin", "Street 2", "24"),
            new Address(3L, "USA", "US789", "New York", "Street 3", "36"),
            new Address(4L, "France", "FR012", "Paris", "Street 4", "48"),
            new Address(5L, "Italy", "IT345", "Rome", "Street 5", "60"),
            new Address(6L, "Spain", "ES678", "Madrid", "Street 6", "72"),
            new Address(7L, "Japan", "JP901", "Tokyo", "Street 7", "84"),
            new Address(8L, "Brazil", "BR234", "Rio de Janeiro", "Street 8", "96"),
            new Address(9L, "Australia", "AU567", "Sydney", "Street 9", "108"),
            new Address(10L, "Canada", "CA890", "Toronto", "Street 10", "120")
    ));

    @Override
    public List<Address> findAll() {

        return addresses;
    }

    @Override
    public Address findById(Long id) {
        for (Address address : addresses) {
            if (address.getId().equals(id)) {
                return address;
            }
        }
        return null;
    }

    @Override
    public List<Address> findByCountry(String country) {
        List<Address> result = new ArrayList<>();
        for (Address address : addresses) {
            if (address.getCountry().equalsIgnoreCase(country)) {
                result.add(address);
            }
        }
        return result;
    }

    @Override
    public List<Address> findByCityAndStreetContaining(String city, String street) {
        List<Address> result = new ArrayList<>();
        for (Address address : addresses) {
            if (address.getCity().equalsIgnoreCase(city) && address.getStreet().contains(street)) {
                result.add(address);
            }
        }
        return result;
    }

    @Override
    public Address save(Address address) {
        address.setId((long) (addresses.size() + 1)); // Генерация нового уникального id
        addresses.add(address);
        return address;
    }

    @Override
    public void deleteById(Long id) {
        addresses.removeIf(address -> address.getId().equals(id));
    }

    @Override
    public Address updateAddress(Long id, Address address) {
        for (int i = 0; i < addresses.size(); i++) {
            if (addresses.get(i).getId().equals(id)) {
                address.setId(id);
                addresses.set(i, address);
                return address;
            }
        }
        return null;
    }
}
