package de.ait.users.dto;

import de.ait.users.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Schema(name = "AddressClass", description = "System address")

public class AddressClientResponseDTO {

        @Schema(description = "address country", example = "Deutschland")
        private String country;
        @Schema(description = "address zipCode", example = "123qwe")
        private String zipCode;
        @Schema(description = "address city", example = "Berlin")
        private String city;
        @Schema(description = "address id", example = "Berlin Street")
        private String street;
        @Schema(description = "address id", example = "12")
        private String house;

    public static AddressClientResponseDTO from(Address address) {
        return AddressClientResponseDTO.builder()
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .street(address.getStreet())
                .house(address.getHouse())
                .build();
    }

    public static List<AddressClientResponseDTO> from(List<Address> addresses) {
        return addresses.stream()
                .map(AddressClientResponseDTO::from)
                .collect(Collectors.toList());
    }

}
