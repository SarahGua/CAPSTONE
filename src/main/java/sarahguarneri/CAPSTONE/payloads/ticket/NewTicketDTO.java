package sarahguarneri.CAPSTONE.payloads.ticket;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewTicketDTO(
        @NotEmpty(message = "The field cost must be filled")
        Double cost,

        @NotEmpty(message = "The field number of people must be filled")
        Integer maxPeople
) {
}
