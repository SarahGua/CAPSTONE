package sarahguarneri.CAPSTONE.payloads.users.exhibitor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        String name,
        String surname,
        @Email
        @NotEmpty(message = "The field email must be filled")
        String email,
        @NotEmpty(message = "The field password must be filled")
        @Size(min = 6, message = "Password must be at least 6 characters!")
        String password,
        String phone_number,
        @NotEmpty(message = "Role must be one of these: admin, client or exhibitor")
        String role
//        String company_name,
//
//        String company_email,
//        String company_phone_number,
//        String address,
//        Long VAT
//        Integer idField
) {
}
