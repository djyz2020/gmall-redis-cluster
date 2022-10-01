package com.fwd.gmall.controller;

import com.fwd.gmall.entity.City;
import com.fwd.gmall.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private ICityService iCityService;

    @GetMapping("/city/{name}")
    public List<City> selectCityByName(@PathVariable("name") String cityName) {
        return iCityService.selectCityByName(cityName);
    }

    @GetMapping("/city/{name}/clearCache")
    public String clearCache(@PathVariable("name") String cityName) {
        iCityService.clearCache(cityName);
        return "SUCCESS";
    }

}
