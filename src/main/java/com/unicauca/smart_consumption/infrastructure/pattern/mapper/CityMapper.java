package com.unicauca.smart_consumption.infrastructure.pattern.mapper;

import com.unicauca.smart_consumption.domain.city.City;
import com.unicauca.smart_consumption.infrastructure.pattern.dto.CityDto;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link CityDto} and {@link City}.
 * <p>The implementation of this interface is automatically generated by MapStruct during the build process, ensuring
 * that the mappings are efficient and error-free.</p>
 *
 * @see CityDto
 * @see City
 * @see EntityMapper
 */

@Mapper(componentModel = "spring")
public interface CityMapper extends EntityMapper<CityDto, City> {
}