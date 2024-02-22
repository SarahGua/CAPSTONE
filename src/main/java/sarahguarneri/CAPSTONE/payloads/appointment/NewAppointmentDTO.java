package sarahguarneri.CAPSTONE.payloads.appointment;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record NewAppointmentDTO(
        @NotEmpty(message = "The field date must be filled")
        String date,
        String time
) {
}
