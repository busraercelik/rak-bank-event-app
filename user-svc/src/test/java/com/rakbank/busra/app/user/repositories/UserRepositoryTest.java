package com.rakbank.busra.app.user.repositories;

import com.rakbank.busra.app.user.models.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.rakbank.busra.app.user.models.Gender.FEMALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
class UserRepositoryTest {

  @Autowired
  private UserRepository repository;

  @Autowired
  private TestEntityManager entityManager;

  private Long id;

  @BeforeEach
  public void setUp() {
    User user = new User();
    user.setName("testuser")
        .setEmail("testuser@example.com")
        .setPhone("12345678");
    User persisted = entityManager.persist(user);
    id = persisted.getId();
  }


  @Test
  @Order(1)
  public void findByEmailIgnoreCaseOrPhone() {
    Optional<User> user = repository.findByEmailIgnoreCaseOrPhone("testuser@example.com", "12345678");
    assertTrue(user.isPresent());
    assertEquals("12345678", user.get().getPhone(), "User phone number should be 12345678");
  }


  @Test
  @Order(2)
  public void createUser() {
    // Create a new user and save it to db
    User user = new User();
    user.setId(100L)
        .setName("Amanda Dan")
        .setGender(FEMALE)
        .setEmail("amanda.dan@gmail.com")
        .setPhone("1239843940");
    User savedUser = repository.save(user);

    // Validate the saved user
    assertEquals("Amanda Dan", savedUser.getName());
    assertEquals("1239843940", savedUser.getPhone());

    // Validate that we can get it back out of the db
    Optional<User> loadedUser = repository.findById(savedUser.getId());
    assertTrue(loadedUser.isPresent());
    assertEquals("Amanda Dan", loadedUser.get().getName());
    assertEquals("amanda.dan@gmail.com", loadedUser.get().getEmail());
  }

  @Test
  @Order(3)
  public void getUserById() {
    Optional<User> user = repository.findById(id);
    assertTrue(user.isPresent(), "User with ID "+ id +" should be found");
    assertEquals(id, user.get().getId(), "Product ID should be 100");
  }
}