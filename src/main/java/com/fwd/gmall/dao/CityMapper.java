package com.fwd.gmall.dao;

import com.fwd.gmall.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* city mapper
*/
@Mapper
public interface CityMapper {

    List<City> selectCityByName(@Param("name") String name);

}
