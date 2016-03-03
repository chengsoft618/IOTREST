/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.cjtblog.domain;

import javax.persistence.Entity;
@Entity
public class Humidity extends SensorData{
    public static final String UNIT = "%RH";
    private double value;
    public double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
}
