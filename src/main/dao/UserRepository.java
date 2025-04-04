package src.main.dao;

import src.main.database.JPAUtil;
import src.main.entity.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

public class UserRepository implements Repository<User> {

    private  static final  Logger LOGGER = Logger.getLogger(UserRepository.class.getName());
    private EntityManager entityManager;

    public UserRepository() {
        this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    }

    @Override
    public void insert(User user) {
        LOGGER.info("Start Insertation user "+ user);
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        //End logger
        LOGGER.info("End Insertation user "+user);
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Start Delete user "+ id);
        entityManager.getTransaction().begin();
        User user = getById(id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        LOGGER.info("End Delete user "+ id);
        return true;
    }

    @Override
    public List<User> getAll() {
        //Start logger
        LOGGER.info("Start GET ALL USERS  ");
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("FROM User",User.class).getResultList();
        entityManager.getTransaction().commit();
        LOGGER.info("End GET ALL USERS  ");
        return users;
    }

    @Override
    public void update(User user) {
        LOGGER.info("Start Udpate user "+ user);
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        LOGGER.info("End Udpate user "+ user);
    }

    @Override
    public User getById(int id) {
        //Start logger
        LOGGER.info("Start get by user "+ id);
        User user = entityManager.find(User.class,id);
        LOGGER.info("End get by user "+ id);
        return user;
    }
}
