package com.fwd.gmall.service.impl;

import com.fwd.gmall.dao.CityMapper;
import com.fwd.gmall.entity.City;
import com.fwd.gmall.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* City数据服务
*/
@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    @Cacheable(cacheNames = "city", key = "#cityName")
    public List<City> selectCityByName(String cityName) {
        return cityMapper.selectCityByName(cityName);
    }

    @Override
    @CacheEvict(cacheNames = "city", key = "#cityName")
    public void clearCache(String cityName) {}
}
