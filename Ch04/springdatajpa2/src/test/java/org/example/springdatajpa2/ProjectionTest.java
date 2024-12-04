package org.example.springdatajpa2;

import org.example.springdatajpa2.model.Projection;
import org.example.springdatajpa2.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectionTest extends SpringDataJpaApplicationTests{

    @Test
    void testProjectionUsername(){
        List<Projection.UsernameOnly> users =
                userRepository.findByEmail("john@somedomain.com");

        users.forEach(System.out::println);
        assertAll(
                () -> assertEquals(1,users.size()),
                () -> assertEquals("john",users.getFirst().getInfo())
        );
    }

    @Test
    void testProjectionUserSummary(){
        List<Projection.UserSummary> users =
                userRepository.findByRegistrationDateAfter(
                        LocalDate.of(2021, Month.FEBRUARY,1));
        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("julius", users.get(0).getUsername()),
                () -> assertEquals("julius julius@someotherdomain.com",
                        users.get(0).getInfo())
        );
    }
    @Test
    void testDynamicProjection(){
        List<Projection.UsernameOnly> usernames =
                userRepository.findByEmail("katie@somedomain.com",Projection.UsernameOnly.class);
        usernames.forEach(System.out::println);
        List<User> users = userRepository.findByEmail("mike@somedomain.com",
                User.class);
        users.forEach(System.out::println);
        List<Projection.UsernameOnly> usersS =
                userRepository.findByLevelEquals(2,Projection.UsernameOnly.class);
        usersS.forEach(System.out::println);
        assertAll(
                () -> assertEquals(1, usernames.size()),
                () -> assertEquals(1, users.size()),
                () -> assertEquals("mike", users.get(0).getUsername())
        );
    }

}
