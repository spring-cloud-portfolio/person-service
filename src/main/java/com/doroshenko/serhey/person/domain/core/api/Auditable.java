package com.doroshenko.serhey.person.domain.core.api;

import com.doroshenko.serhey.person.dto.core.api.Identifiable;

import java.time.LocalDateTime;

public interface Auditable extends Identifiable {

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

}
