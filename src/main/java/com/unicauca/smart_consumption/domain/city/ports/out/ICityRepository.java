package com.unicauca.smart_consumption.domain.city.ports.out;

import com.unicauca.smart_consumption.domain.city.City;
import java.util.Optional;

public interface ICityRepository {
    /**
     * Finds a city in the system by its ID.
     *
     * @param id The ID of the {@link City} to be retrieved.
     * @return An {@link Optional} containing the found {@link City} if it exists, or {@link Optional#empty()} if not found.
     */
    Optional<City> findCityById(String id);
}
