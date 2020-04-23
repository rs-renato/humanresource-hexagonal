package br.com.hrs.core.repository;

import br.com.hrs.core.model.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public interface LocationRepository {

    Logger logger = LogManager.getLogger(LocationRepository.class);

    Location find(Integer locationId);

    Integer save(Location location);

    boolean update(Location Location);

    Collection<Location> findAll();

    boolean delete(Integer locationId);
}
