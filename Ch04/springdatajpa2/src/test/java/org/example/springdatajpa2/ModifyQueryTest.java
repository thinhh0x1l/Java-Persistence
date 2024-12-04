package org.example.springdatajpa2;

import org.example.springdatajpa2.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModifyQueryTest extends SpringDataJpaApplicationTests{

    @Test
    void testModifyLevel(){
        int updated = userRepository.updateLevel(5,4);
        List<User> users = userRepository.findByLevel(4, Sort.by("username"));
        assertAll(
                () -> assertEquals(1, updated),
                () -> assertEquals(3, users.size()),
                () -> assertEquals("katie", users.get(1).getUsername())
        );
    }
}
