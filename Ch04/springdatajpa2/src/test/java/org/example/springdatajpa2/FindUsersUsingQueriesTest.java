package org.example.springdatajpa2;

import org.example.springdatajpa2.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class FindUsersUsingQueriesTest extends SpringDataJpaApplicationTests{
    @Test
    void testFindAll(){
        List<User> users = userRepository.findAll();
        assertEquals(10, users.size());
    }

    @Test
    void testFindUser(){
        User beth = userRepository.findByUsername("beth");
        assertEquals("beth", beth.getUsername());
    }

    @Test
    void testFindAllByOrderByUsernameAsc(){
        List<User> users = userRepository.findAllByOrderByUsernameAsc();
        assertAll(
                () -> assertEquals("beth", users.getFirst().getUsername()),
                () -> assertEquals("stephanie", users.getLast().getUsername())
        );
    }

    @Test
    void testFindByRegistrationDateBetween(){
        List<User> users = userRepository.findByRegistrationDateBetween(
                LocalDate.of(2020, Month.APRIL,1),
                LocalDate.of(2021, Month.JANUARY, 1));
        assertAll(
                () -> assertEquals(5,users.size()),
                () -> assertEquals("john",users.getFirst().getUsername()),
                () -> assertTrue(Objects.equals(users.getLast().getUsername(), "burk")),
                () -> assertFalse(Objects.equals(users.getLast().getUsername(), "beth"))
        );
    }

    @Test
    void testFindByUsernameAndEmail(){
        List<User> user1 = userRepository.findByUsernameAndEmail("john", "john@somedomain.com");
        List<User> user2 = userRepository.findByUsernameAndEmail("james","katie@somedomain.com");
        List<User> user3 = userRepository.findByUsernameAndEmail("julius","julius@someotherdomain.com");

        assertAll(
                () -> assertEquals(1, user1.getFirst().getLevel()),
                () -> assertTrue(user1.getFirst().isActive()),
                () -> assertFalse(!user2.isEmpty()),
                () -> assertEquals(4, user3.getFirst().getLevel()),
                () -> assertFalse(!user3.getFirst().isActive())
        );
    }

    @Test
    void testFindByUsernameIgnoreCase(){
        List<User> users = userRepository.findByUsernameIgnoreCase("sTePHAniE");
        assertAll(
                () -> assertFalse(users.isEmpty()),
                () -> assertTrue(users.getFirst().isActive()),
                () -> assertEquals(4,users.getFirst().getLevel())
        );
    }

    @Test
    void testFindByLevelOrderByUsernameDesc(){
        List<User> users = userRepository.findByLevelOrderByUsernameDesc(2);
        assertAll(
                () -> assertEquals(3, users.size()),
                () -> assertTrue(users.getFirst().getUsername().equals("marion")),
                () -> assertTrue(users.getLast().isActive(),() -> users.getLast().getUsername()+" is not active!")
        );
    }

    @Test
    void testFindByLevelGreaterThanEqual(){
        List<User> users = userRepository.findByLevelGreaterThanEqual(4);
        assertAll(
                () -> assertEquals(3, users.size()),
                () -> assertEquals(5,users.getFirst().getLevel())
        );
    }

    @Test
    void testFindByUsername(){
        List<User> usersContaining = userRepository.findByUsernameContaining("ar");
        List<User> usersLike = userRepository.findByUsernameLike("%b__h");
        List<User> usersStarting = userRepository.findByUsernameStartingWith("b");
        List<User> usersEnding = userRepository.findByUsernameEndingWith("ie");

        assertAll(
                () -> assertEquals(2, usersContaining.size()),
                () -> assertEquals(1, usersLike.size()),
                () -> assertEquals("beth", usersLike.getFirst().getUsername()),
                () -> assertEquals(2, usersStarting.size()),
                () -> assertEquals(2, usersEnding.size())
        );
    }

    @Test
    void testFindByActive() {
        List<User> usersActive = userRepository.findByActive(true);
        List<User> usersNotActive = userRepository.findByActive(false);

        assertAll(
                () -> assertEquals(8, usersActive.size()),
                () -> assertEquals(2, usersNotActive.size())
        );
    }

    @Test
    void testFindByRegistrationDateInNotIn() {
        LocalDate date1 = LocalDate.of(2020, Month.JANUARY, 18);
        LocalDate date2 = LocalDate.of(2021, Month.JANUARY, 5);

        List<LocalDate> dates = new ArrayList<>();
        dates.add(date1);
        dates.add(date2);

        List<User> usersList1 = userRepository.findByRegistrationDateIn(dates);
        List<User> usersList2 = userRepository.findByRegistrationDateNotIn(dates);

        assertAll(
                () -> assertEquals(3, usersList1.size()),
                () -> assertEquals(7, usersList2.size())
        );
    }
}
