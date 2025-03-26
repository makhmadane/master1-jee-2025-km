package src.main.dao;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import src.main.database.Db;
import src.main.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserRepository implements Repository<User> {

    private  static final  Logger LOGGER = Logger.getLogger(UserRepository.class.getName());
    @Override
    public void insert(User user) {
        Db db = new Db();
        //Start logger
        LOGGER.info("Start Insertation user "+ user);
        String sql = "INSERT INTO \"user\"(nom, prenom, age) VALUES (?, ?, ?)";
        System.out.println(sql);
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,user.getNom());
            preparedStatement.setString(2,user.getPrenom());
            preparedStatement.setInt(3,user.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //End logger
        LOGGER.info("End Insertation user "+user);
    }

    @Override
    public boolean delete(int id) {
        Db db = new Db();
        String sql ="DELETE from \"user\" where id = ? ";
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            return true ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        //Start logger
        Db db = new Db();
        List<User> users = new ArrayList<>();
        String sql ="SELECT * from \"user\" u ";
        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(sql);
            ResultSet rs=  preparedStatement.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(1));
                User user =  User.builder()
                        .id(rs.getInt(1))
                        .nom(rs.getString(2))
                        .prenom(rs.getString(3))
                        .age(rs.getInt(4))
                        .build();

                users.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //End logger
        return users;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getById(int id) {
        return null;
    }
}
