package com.unicauca.smart_consumption.infrastructure.modules.city.dataproviders.jpa;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.domain.city.ports.out.ICityRepository;
import com.unicauca.smart_consumption.infrastructure.pattern.mapper.CityJPAMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CityRepositoryAdapter implements ICityRepository {

    private final CityJPARepository cityJPARepository;
    private final CityJPAMapper cityJPAMapper;

    @Override
    public Optional<City> findCityById(String id) {
        return cityJPARepository.findById(id).map(cityJPAMapper::toDomain);
    }
}
