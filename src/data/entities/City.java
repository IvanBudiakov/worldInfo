package data.entities;

import java.util.List;

import data.daos.Dao;

public class City implements Dao<City>{

    Integer ID; // PK
    String Name;
    String CountryCode;
    String District;
    Integer Population;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public Integer getPopulation() {
        return Population;
    }

    public void setPopulation(Integer population) {
        Population = population;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "City [CountryCode=" + CountryCode + ", District=" + District + ", ID=" + ID + ", Name=" + Name
                + ", Population=" + Population + "]";
    }





    @Override
    public boolean delete(Integer pk) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public City findById(Integer pk) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(City item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Boolean update(City item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<City> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
