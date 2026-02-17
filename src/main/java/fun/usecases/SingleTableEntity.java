package fun.usecases;

import java.util.UUID;

public interface SingleTableEntity {

    EntityType entityType();

    default String buildPk(UUID id) {
        return "#" + entityType().prefix() + "#" + id;
    }
}
