package sarahguarneri.CAPSTONE.payloads.stand;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewStandDTO(
        @NotEmpty(message = "The field cost must be filled")
        Double cost,
        @NotEmpty(message = "The field dimensions must be filled")
        String dimensions
) {
}
