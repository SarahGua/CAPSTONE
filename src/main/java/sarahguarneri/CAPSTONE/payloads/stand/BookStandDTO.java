package sarahguarneri.CAPSTONE.payloads.stand;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record BookStandDTO(
        @NotEmpty
        UUID userId,

        @NotEmpty
        UUID standId
) {
}
