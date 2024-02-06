package sarahguarneri.CAPSTONE.payloads.field;

import jakarta.validation.constraints.NotEmpty;

public record NewFieldDTO(
        @NotEmpty(message = "The field description must be filled")
        String description
) {
}
