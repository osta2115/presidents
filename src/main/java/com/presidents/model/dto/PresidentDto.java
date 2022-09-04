package com.presidents.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PresidentDto {

    private Long id;

    @NotNull(message = "Name is required")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    @NotNull(message = "Surname is required")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String surname;

    private Timestamp termFrom;

    private Timestamp termTo;

    @NotNull(message = "Political party is required")
    //@Pattern(regexp = "[Federalist]||[Unaffiliated]||[Democratic\\-Republican]",
           // message = "Political party should be one of: Federalist, Unaffiliated or Democratic-Republican")
    private String politicalParty;
}
