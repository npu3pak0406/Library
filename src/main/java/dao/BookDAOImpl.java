package dao;

import entity.Book;
import org.hibernate.*;
import utils.HibernateUtil;

import java.util.List;

public class BookDAOImpl implements BookDAO<Book, Integer> {

    private Session session;
    private Transaction tx;

    public void save(Book entity) {
        try {
            startOperation();
            session.save(entity);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void update(Book entity) {
        try {
            startOperation();
            session.update(entity);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Book entity) {
        try {
            startOperation();
            session.delete(entity);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Book> findAll() {
        List<Book> books = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + Book.class.getName());
            books = query.list();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return books;
    }

    @Override
    public List<Book> findByName(String name) {
        List<Book> books = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + Book.class.getName() + "where name =:name");
            query.setParameter("name", name);
            books = query.list();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return books;
    }

    private void startOperation() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }
}
