package de.ait.users.controller;

import de.ait.users.model.Address;
import de.ait.users.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Реализовать в проекте User класс Address {Long id, String country, String zipCode, String city, String
street, String house } Пока этот класс ни как не связан с User! Реализовать обработку следующих endpoint-ов

GET \addresses - вывести все адреса
GET \addresses\id - вывести адресс с заданным id
GET \addresses?country=usa - вывести все адресса в заданной стране (в нащем примере usa)
GET \addresses?city=berlin - вывести все адресса в заданном городе (в нащем примере berlin)
GET \addresses?city=berlin&&street=potsda - вывести все адресса в заданном городе на улицах, которые содержат заданную подстроку
POST\address - создать новый адресс
DELETE\address\id - удалить адрес
PUT\address\id - изменить адрес
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
//@Tags(value = {
//        @Tag(address = "Адресса", description = "Операции с адрессами"),
//        @Tag(address = "Addresses", description = "Addresses operation")
//})
public class AddressController {

    private final AddressService service;

    @Operation(summary = "Получение списка адрессов",
            description = "Список всех адрессов. Доступно администратору")
    @GetMapping
    public List<Address> getAllAddresses() {
        return service.findAllAddresses();
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping(params = "country")
    public List<Address> getAddressesByCountry(@RequestParam("country") String country) {
        return service.getAddressesByCountry(country);
    }

    @GetMapping(params = {"city", "street"})
    public List<Address> getAddressesByCityAndStreet(@RequestParam("city") String city,
                                                     @RequestParam("street") String street) {
        return service.getAddressesByCityAndStreet(city, street);
    }

    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return service.createAddress(address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        service.deleteAddress(id);

    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address address) {
        return service.updateAddress(id, address);
    }
}
