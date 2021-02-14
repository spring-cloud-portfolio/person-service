package com.doroshenko.serhey.person.domain.core.base;

import com.doroshenko.serhey.person.domain.core.api.Identifiable;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class BaseIdentifiable extends AbstractPersistable<Long> implements Identifiable {

    @Version
    private long version;

    /* Getter */
    public long getVersion() {
        return version;
    }

}
