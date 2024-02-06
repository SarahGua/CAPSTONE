package sarahguarneri.CAPSTONE.payloads.users;

import java.util.UUID;

public record UserLoginResponseDTO(
        String token,
        UUID id,
        String name,
        String role,
        String surname,
        String img
) {
}
