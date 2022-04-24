package data.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.entities.City;

public class CityDao implements Dao<City>{

    Connection connection;

    public CityDao(Connection connection) {
        this.connection = connection;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM city");
            while (result.next())
            {
                City city =  new City();
                city.setCountryCode(result.getString("CountryCode"));
                city.setDistrict(result.getString("District"));
                city.setID(result.getInt("ID"));
                city.setName(result.getString("Name"));
                city.setPopulation(result.getInt("Population"));
                cities.add(city);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return cities;
    }

    public void insert (City city)
    {
        try (Statement statement = connection.createStatement())
        {
            String insert = "INSERT INTO city VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, null);
            ps.setString(2,city.getName());
            ps.setString(3,city.getCountryCode());
            ps.setString(4,city.getDistrict());
            ps.setInt(5, city.getPopulation());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next())
                city.setID(keys.getInt(1));

        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }

    }


     public Boolean update (City city)
     {
        Boolean success = true;
        String update = "UPDATE City SET population=? WHERE id=?";

        try  (PreparedStatement ps = connection.prepareStatement(update) ;)
        {
            ps.setInt(1, city.getPopulation());
            ps.setInt(2, city.getID());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            success = false;
        }
        return success;
     }

     /**
      * TODO - Add code to retrieve City object by name
      *
      * @param name -   Name of the City to be retrieved from database
      * @return     -   City object if found, otherwise null
      */

     public City findCityByName(String name, String country, String district)
     {
         Boolean success = false;
         City city =  new City();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM city WHERE Name = \"" + name
            + "\"AND CountryCode = \"" + country + "\" AND District = \"" + district +"\"");
            if(result.next()){             
                city.setCountryCode(result.getString("CountryCode"));
                city.setDistrict(result.getString("District"));
                city.setID(result.getInt("ID"));
                city.setName(result.getString("Name"));
                city.setPopulation(result.getInt("Population"));
                success = true; 
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.out.println("out");
        }
        if(success)
            return city;
        return null; 
     }

     /**
      * TODO - Add code to retrieve list of all cities in a given country.
      *
      * @param country 
      * @return     - list of cities, or null if country not found
      */
     public List<City> findCitiesByCountry(String country)
     {
        List<City> cities = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM city WHERE CountryCode = \"" + country + "\" ORDER BY District ASC");
            while (result.next())
            {
                City city =  new City();
                city.setCountryCode(result.getString("CountryCode"));
                city.setDistrict(result.getString("District"));
                city.setID(result.getInt("ID"));
                city.setName(result.getString("Name"));
                city.setPopulation(result.getInt("Population"));
                cities.add(city);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return cities;
     }
    
    
     /**
      * retrieves a list of all cities over a specific population.
      *
      * @param population
      * @return     - list of cities, or null
      */
      public List<City> findCitiesByPopulation(Integer population)
      {
         List<City> cities = new ArrayList<>();
 
         try (Statement statement = connection.createStatement()) {
             ResultSet result = statement.executeQuery("SELECT * FROM city WHERE Population > " + population);
             while (result.next())
             {
                 City city =  new City();
                 city.setCountryCode(result.getString("CountryCode"));
                 city.setDistrict(result.getString("District"));
                 city.setID(result.getInt("ID"));
                 city.setName(result.getString("Name"));
                 city.setPopulation(result.getInt("Population"));
                 cities.add(city);
             }
         } catch (SQLException e) {
             System.err.println(e.getMessage());
             return null;
         }
         return cities;
      }


    @Override
    public boolean delete(Integer pk) {
        Boolean success = true;
        String delete = "DELETE FROM City WHERE id=?";

        try  (PreparedStatement ps = connection.prepareStatement(delete) ;)
        {
            ps.setInt(1, pk);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            success = false;
        }
        return success;
    }

    @Override
    public City findById(Integer pk) { 
        
        City city =  new City();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM City WHERE ID=" + pk);
            while (result.next())
            {
                city.setCountryCode(result.getString("CountryCode"));
                city.setDistrict(result.getString("District"));
                city.setID(result.getInt("ID"));
                city.setName(result.getString("Name"));
                city.setPopulation(result.getInt("Population"));
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            return null;
        }
        return city;
    }

    

}
