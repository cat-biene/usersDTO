package de.ait.users.repository.impl;

import de.ait.users.model.Address;
import de.ait.users.model.User;
import de.ait.users.repository.AddressRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryJDBCTemplateImpl implements AddressRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String ID = "id";
    private static final String COUNTRY = "country";
    private static final String ZIPCODE = "zipCode";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE = "house";

    private static final RowMapper<Address> ADDRESS_ROW_MAPPER = (row, rowNum) -> {
        Long id = row.getLong(ID);
        String country = row.getString(COUNTRY);
        String zipcode = row.getString(ZIPCODE);
        String city = row.getString(CITY);
        String street = row.getString(STREET);
        String house = row.getString(HOUSE);
        return new Address(id, country, zipcode, city, street, house);
    };


    @Override
    public List<Address> findAll() {
        return jdbcTemplate.query("SELECT * FROM t_user", ADDRESS_ROW_MAPPER);
    }

    @Override
    public Address findById(Long id) {
        Address address = jdbcTemplate.queryForObject("SELECT * FROM t_user WHERE id = ?", ADDRESS_ROW_MAPPER, id);
    return address;
    }

    @Override
    public List<Address> findByCountry(String country) {
        return List.of();
    }

    @Override
    public List<Address> findByCityAndStreetContaining(String city, String street) {
        return List.of();
    }

    @Override
    public Address save(Address address) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Address updateAddress(Long id, Address address) {
        return null;
    }
}
