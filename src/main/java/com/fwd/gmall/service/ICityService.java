package com.fwd.gmall.service;

import com.fwd.gmall.entity.City;

import java.util.List;

/**
*
*/
public interface ICityService {

    List<City> selectCityByName(String cityName);

    void clearCache(String cityName);

}
