package sarahguarneri.CAPSTONE.payloads.ticket;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record NewTicketDTO(

        @NotNull
        UUID clientId,
        Integer quantity

) {
}
