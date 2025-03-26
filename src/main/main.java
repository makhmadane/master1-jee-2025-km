package src.main;

import src.main.dao.UserRepository;
import src.main.database.Db;
import src.main.entity.User;

public class main {

    public static void main(String[]args){


        UserRepository userRepository = new UserRepository();
        userRepository.insert(User.builder().nom("DIOP").prenom("DANE").age(18).build());

        //userRepository.getAll().forEach(a -> System.out.println(a));



    }
}
