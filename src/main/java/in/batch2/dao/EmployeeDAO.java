
package in.batch2.dao;

import in.batch2.model.Employee;
import in.batch2.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAO {

    public List<Employee> findAll() {
        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "from Employee order by id",
                    Employee.class
            ).list();
        }
    }

    public Employee findById(long id) {
        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.find(Employee.class, id);
        }catch (Exception e){
            throw e;
        }
    }
    public void create(Employee employee){
       try(Session session = HibernateUtil.getSessionFactory().openSession()){
           Transaction transaction = session.beginTransaction();

           try{
               if(employee==null) return;
               session.persist(employee);
               transaction.commit();
           } catch (Exception e){
               transaction.rollback();
               throw e;
           }
       }catch (Exception e){
         throw e;
       }
    }

    public void delete(long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            try {
                Employee employee = session.find(Employee.class, id);
                if(employee==null) return;

                session.remove(employee);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }catch(Exception e){
            throw e;
        }
    }

    public void update(Employee employee){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                if(employee==null) return;
                session.merge(employee);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }catch(RuntimeException e){
            throw e;
        }
    }
}
