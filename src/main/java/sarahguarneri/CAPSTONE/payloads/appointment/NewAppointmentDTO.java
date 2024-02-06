package sarahguarneri.CAPSTONE.payloads.appointment;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record NewAppointmentDTO(
        @NotEmpty(message = "The field date must be filled")
        LocalDate date
) {
}
