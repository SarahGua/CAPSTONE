package sarahguarneri.CAPSTONE.payloads.users.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserAdminDTO(
        @Email
        @NotEmpty(message = "The field email must be filled")
        String email,
        @NotEmpty(message = "The field password must be filled")
        @Size(min = 6, message = "Password must be at least 6 characters!")
        String password
//        @NotEmpty(message = "Role must be one of these: admin, client or exhibitor")
//        String role
) {
}
