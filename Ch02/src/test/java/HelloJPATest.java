import org.example.Message;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloJPATest {
    @Test
    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02");
        try{
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            Message msg = new Message();
            msg.setText("Hello World");
            em.persist(msg);
            em.getTransaction().commit();

            em.getTransaction().begin();
            List<Message> messages =
                    em.createQuery("select m from Message m", Message.class)
                            .getResultList();
            messages.get(messages.size()-1).setText("Hello World from JPA");

            em.getTransaction().commit();

            assertAll(
                    () -> assertEquals(1,messages.size())
            );
            em.close();
        } finally {
            emf.close();
        }
    }

}
