package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("select u from User u", User.class).list();
    }

    @Override
    public User findUserByCar(String model, int series) {
        return sessionFactory.getCurrentSession().createQuery(" select u from Car c join c.user u " +
                                                              "where c.model=:model and c.series=:series", User.class)
                .setParameter("model", model)
                .setParameter("series", series).getSingleResultOrNull();
    }
}
