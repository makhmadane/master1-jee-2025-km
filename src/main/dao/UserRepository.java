package src.main.dao;

import src.main.database.Db;
import src.main.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserRepository implements Repository<User> {

    private  static final  Logger LOGGER = Logger.getLogger(UserRepository.class.getName());
    private Connection  conn;

    public UserRepository() {
        this.conn =new Db().getConnection();
    }

    @Override
    public void insert(User user) {
        //Start logger
        LOGGER.info("Start Insertation user "+ user);
        String sql = "INSERT INTO \"user\"(nom, prenom, age) VALUES (?, ?, ?)";
        System.out.println(sql);
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
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
        LOGGER.info("Start Delete user "+ id);
        String sql ="DELETE from \"user\" where id = ? ";
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            LOGGER.info("End Delete user "+ id);
            return true ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getAll() {
        //Start logger
        LOGGER.info("Start GET ALL USERS  ");
        List<User> users = new ArrayList<>();
        String sql ="SELECT * from \"user\" u ";
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
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
        LOGGER.info("End GET ALL USERS  ");
        return users;
    }

    @Override
    public void update(User user) {
        LOGGER.info("Start Udpate user "+ user);
        //Start logger
        LOGGER.info("Start Insertation user "+ user);
        String sql = "UPDATE \"user\" set nom= ?, prenom = ?, age = ? where id = ?";
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getNom());
            preparedStatement.setString(2,user.getPrenom());
            preparedStatement.setInt(3,user.getAge());
            preparedStatement.setInt(4,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("End Udpate user "+ user);
    }

    @Override
    public User getById(int id) {
        //Start logger
        LOGGER.info("Start get by user "+ id);
        User user;
        String sql ="SELECT * from \"user\" u where id = ? ";
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet rs=  preparedStatement.executeQuery();
            rs.next();
            user =  User.builder()
                    .id(rs.getInt(1))
                    .nom(rs.getString(2))
                    .prenom(rs.getString(3))
                    .age(rs.getInt(4))
                    .build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("End get by user "+ id);
        return user;
    }
}
