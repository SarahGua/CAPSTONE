package sarahguarneri.CAPSTONE.payloads.stand;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewStandDTO(
        @NotEmpty
        String cost,
        @NotEmpty(message = "The field dimensions must be filled")
        String dimensions,
        String position


) {
}
