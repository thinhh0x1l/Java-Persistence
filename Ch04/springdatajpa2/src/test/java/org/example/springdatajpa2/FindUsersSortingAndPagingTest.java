package org.example.springdatajpa2;

import org.example.springdatajpa2.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindUsersSortingAndPagingTest extends SpringDataJpaApplicationTests{

    @Test
    void testOrder(){
        User user1 = userRepository.findFirstByOrderByUsernameAsc();
        User user2 = userRepository.findTopByOrderByRegistrationDateDesc();
        Page<User> userPage = userRepository.findAll(PageRequest.of(5,3));
        Page<User> userPage1 = userRepository.findAll(PageRequest.of(1,3));
        Page<User> userPage2 = userRepository.findAll(PageRequest.of(3,3));
        List<User> users = userRepository.findFirst2ByLevel(
                2,
                Sort.by("registrationDate"));

        assertAll(
                () -> assertEquals("beth",user1.getUsername()),
                () -> assertEquals("julius",user2.getUsername()),
                () -> assertEquals(2,users.size()),
                () -> assertEquals(3,userPage.getSize()),
                () -> assertEquals(4,userPage.getTotalPages()), // tổng số trang
                () -> assertEquals(0,userPage.getNumberOfElements()), //
                () -> assertEquals(3,userPage1.getNumberOfElements()), // số lượng phần tử của trang hiện tại (trang 1)
                () -> assertEquals("beth", users.get(0).getUsername()),
                () -> assertEquals(1, userPage2.getNumberOfElements()),
                () -> assertEquals(10, userPage2.getTotalElements()), // Tổng phần tử của tất cả các page
                () -> assertEquals("marion", users.get(1).getUsername())

        );
    }

    @Test
    void testFindByLevel(){
        Sort.TypedSort<User> user = Sort.sort(User.class);
        List<User> users = userRepository.findByLevel(
                3,
                user.by(User::getRegistrationDate).descending());

        assertAll(
                () -> assertEquals(2, users.size()),
                () -> assertEquals("james", users.getFirst().getUsername())
        );
    }

    @Test
    void testFindByActive(){
        List<User> users = userRepository.findByActive(
                true, PageRequest.of(
                        1,
                        4,
                        Sort.by("registrationDate")));
        List<User> users1 = userRepository.findByActive(
                true, PageRequest.of(
                        2,
                        4,
                        Sort.by("registrationDate")));

        assertAll(
                () -> assertEquals(4, users.size()),
                () -> assertEquals(0, users1.size()),
                () -> assertEquals("burk", users.getFirst().getUsername())
        );
    }
}
