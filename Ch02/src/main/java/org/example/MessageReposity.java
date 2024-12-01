package org.example;

import org.springframework.data.repository.CrudRepository;

public interface MessageReposity  extends CrudRepository<Message, Long> {
}
