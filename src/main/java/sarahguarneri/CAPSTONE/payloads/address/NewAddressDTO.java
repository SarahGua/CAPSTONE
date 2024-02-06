package sarahguarneri.CAPSTONE.payloads.address;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewAddressDTO(
        @NotEmpty(message = "The field street must be filled")
        @Size(min = 3, max = 30, message = "The street must be between 3 and 30 characters!")
        String street,
        @NotEmpty(message = "The field street number must be filled")
        @Size(min = 3, max = 30, message = "The street number can accept numbers and letters ")
        String street_number,
        @NotEmpty(message = "The field postal code must be filled")
        @Size(min = 3, max = 30, message = "The postal code must be between 3 and 10 characters!")
        Integer postal_code,
        @NotEmpty(message = "The field city must be filled")
        @Size(min = 3, max = 30, message = "The city must be between 3 and 10 characters!")
        String city,
        @NotEmpty(message = "The field state must be filled")
        @Size(min = 3, max = 30, message = "The state must be between 3 and 10 characters!")
        String state
) {
}
