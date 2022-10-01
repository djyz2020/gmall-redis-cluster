package com.fwd.gmall.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName city
 */
@Data
public class City implements Serializable {

    private Integer id;

    private String name;

    private String countrycode;

    private String district;

    private Integer population;

}