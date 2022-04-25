package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import data.Database;
import data.daos.CityDao;
import data.daos.UserDao;
import data.entities.City;
import data.entities.User;

public class DataModel {

    public static String[] getHeaders(){
        String[] headers = null;
        headers = new String[5];

        headers[0] = "ID";
        headers[1] = "Name";
        headers[2] = "Country Code";
        headers[3] = "District";
        headers[4] = "Population";

        return headers;
    }

    public static void addUser(String username, String password){
        try(Connection connection = Database.getDatabaseConnection();){

            User user = new User();

            user.setID(null);
            user.setUserName(username);
            user.setPwd(password);

            UserDao newUser = new UserDao(connection);

            newUser.insert(user);
            System.out.println("insertion of " + user.getUserName() + " completed successfully");

        }catch(SQLException e){
            e.printStackTrace();
        }


    }

    public static User findUserByName(String username){
        User user = null;
        try(Connection connection = Database.getDatabaseConnection();) {
            UserDao theUser = new UserDao(connection);
            user = theUser.findUserByName(username);
            
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return user;
    }

    public  static String[][] getCityData()
    {
        String[][] cityData = null;

        try(Connection connection = Database.getDatabaseConnection();)
        {
            CityDao cityDao = new CityDao(connection);
            List<City> cities = cityDao.findAll();
            cityData = new String[cities.size()][5];
            int row = 0;
            for (City city : cities) {
                cityData[row][0] = city.getID().toString();
                cityData[row][1] = city.getName();
                cityData[row][2] = city.getCountryCode();
                cityData[row][3] = city.getDistrict();
                cityData[row][4] = city.getPopulation().toString();
                row++;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return cityData;
    }

    public static String[][] getData()
    {
        final int NCOLS = 4;
        Random rand = new Random();
        String[][] data;

        final int NROWS  = 10 + rand.nextInt(20);
        data = new String[NROWS][NCOLS];

        for (int row = 0 ; row < NROWS; row++)
        {
            for( int col = 0; col < NCOLS; col++)
            {
                data[row][col] = String.valueOf(rand.nextInt(1000));
            }
        }

        return data;
    }

    public static String[][] getCitiesByCountry(String country){
        String [][] countryCities = null;

        try(Connection connection = Database.getDatabaseConnection();)
        {
            CityDao cityDao = new CityDao(connection);
            List<City> cities = cityDao.findCitiesByCountry(country);
            countryCities = new String[cities.size()][5];
            int row = 0;
            for (City city : cities) {
                countryCities[row][0] = city.getID().toString();
                countryCities[row][1] = city.getName();
                countryCities[row][2] = city.getCountryCode();
                countryCities[row][3] = city.getDistrict();
                countryCities[row][4] = city.getPopulation().toString();
                row++;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }


        return countryCities;
    }

    public static String[][] getOver1M(){
        String [][] countryCities = null;

        try(Connection connection = Database.getDatabaseConnection();)
        {
            CityDao cityDao = new CityDao(connection);
            List<City> cities = cityDao.findCitiesByPopulation(1000000);
            countryCities = new String[cities.size()][5];
            int row = 0;
            for (City city : cities) {
                countryCities[row][0] = city.getID().toString();
                countryCities[row][1] = city.getName();
                countryCities[row][2] = city.getCountryCode();
                countryCities[row][3] = city.getDistrict();
                countryCities[row][4] = city.getPopulation().toString();
                row++;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }


        return countryCities;
    }

}
