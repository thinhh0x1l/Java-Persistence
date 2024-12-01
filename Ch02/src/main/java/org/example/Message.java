package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static void main(String[] args) {
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
            messages.forEach(m -> System.out.println(m.getText()));
            em.close();
        } finally {
            emf.close();
        }
    }
}
