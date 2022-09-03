package com.presidents.exception.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PresidentsControllerExceptionMessages {

    ENTITY_FOR_PROVIDED_ID_NOT_EXIST("Entity for provided ID does not exist"),
    ENTITY_FOR_PROVIDED_PARAMETER_NOT_EXIST("Entity for provided parameter does not exist");

    private final String message;
}

