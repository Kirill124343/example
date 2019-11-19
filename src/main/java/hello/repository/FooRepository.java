package hello.repository;

import java.util.*;
import hello.model.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepository extends JpaRepository<Foo, Long> {
    List<Foo> findByName(String Name);
    List<Foo> findAll();
}