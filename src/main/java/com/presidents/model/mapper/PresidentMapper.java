package com.presidents.model.mapper;

import com.presidents.model.dto.PresidentDto;
import com.presidents.model.entity.President;
import org.springframework.stereotype.Component;

public interface PresidentMapper {

    static PresidentDto toDto(President president) {
        return PresidentDto.builder()
                .id(president.getId())
                .name(president.getName())
                .surname(president.getSurname())
                .termFrom(president.getTermFrom())
                .termTo(president.getTermTo())
                .politicalParty(president.getPoliticalParty())
                .build();
    }

    static President toEntity(PresidentDto presidentDto) {
        return President.builder()
                .id(presidentDto.getId())
                .name(presidentDto.getName())
                .surname(presidentDto.getSurname())
                .termFrom(presidentDto.getTermFrom())
                .termTo(presidentDto.getTermTo())
                .politicalParty(presidentDto.getPoliticalParty())
                .build();
    }
}
