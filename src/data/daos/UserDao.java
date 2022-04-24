package data.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.sql.SQLException;

import data.entities.User;

public class UserDao implements Dao<User>{
    Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User findUserByName(String name){
        User user =  new User();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM User WHERE username=" + "\"" + name + "\"");
            while (result.next())
            {
                user.setID(result.getInt("id"));
                user.setUserName(result.getString("username"));
                user.setPwd(result.getString("enc_pwd"));
                
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            return null;
        }
        return user;
    }


    @Override
    public void insert(User user){
        try (Statement statement = connection.createStatement())
        {
            String insert = "INSERT INTO User VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, null);
            ps.setString(2,user.getUserName());
            ps.setString(3,user.getPwd());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next())
                user.setID(keys.getInt(1));

        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findById(Integer pk) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean update(User item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(Integer pk) {
        // TODO Auto-generated method stub
        return false;
    }

}
