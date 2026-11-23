package com.motupa.ticketing;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record EventRequest(@NotBlank @Size(max = 200) String title, @Size(max = 2000) String description,
        @NotBlank @Size(max = 200) String venue, @NotNull @Future Instant startTime, @Positive int capacity,
        @NotNull @PositiveOrZero BigDecimal price) {

}
