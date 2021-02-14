package com.doroshenko.serhey.person.domain.core.api;

import java.time.LocalDateTime;

public interface Auditable extends Identifiable {

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

}
