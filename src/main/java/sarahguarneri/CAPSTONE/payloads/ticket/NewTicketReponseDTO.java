package sarahguarneri.CAPSTONE.payloads.ticket;

import java.util.UUID;

public record NewTicketReponseDTO(UUID id, int availableQuantity) {
}
