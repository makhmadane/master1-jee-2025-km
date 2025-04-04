package src.main.dao;

import src.main.database.JPAUtil;
import src.main.entity.Role;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

public class RoleRepository implements Repository<Role> {

    private  static final  Logger LOGGER = Logger.getLogger(RoleRepository.class.getName());
    private EntityManager entityManager;

    public RoleRepository() {
        this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    }

    @Override
    public void insert(Role role) {
        LOGGER.info("Start Insertation role "+ role);
        entityManager.getTransaction().begin();
        entityManager.persist(role);
        entityManager.getTransaction().commit();
        //End logger
        LOGGER.info("End Insertation role "+role);
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Start Delete role "+ id);
        entityManager.getTransaction().begin();
        Role role = getById(id);
        entityManager.remove(role);
        entityManager.getTransaction().commit();
        LOGGER.info("End Delete role "+ id);
        return true;
    }

    @Override
    public List<Role> getAll() {
        //Start logger
        LOGGER.info("Start GET ALL USERS  ");
        entityManager.getTransaction().begin();
        List<Role> roles = entityManager.createQuery("FROM Role",Role.class).getResultList();
        entityManager.getTransaction().commit();
        LOGGER.info("End GET ALL USERS  ");
        return roles;
    }

    @Override
    public void update(Role role) {
        LOGGER.info("Start Udpate role "+ role);
        entityManager.getTransaction().begin();
        entityManager.merge(role);
        entityManager.getTransaction().commit();
        LOGGER.info("End Udpate role "+ role);
    }

    @Override
    public Role getById(int id) {
        //Start logger
        LOGGER.info("Start get by role "+ id);
        Role role = entityManager.find(Role.class,id);
        LOGGER.info("End get by role "+ id);
        return role;
    }
}
