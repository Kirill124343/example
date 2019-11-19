package hello.controller;

import hello.DTO.Greeting;
import hello.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class GreetingController {

    @Autowired
    private FooRepository fooRepository;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/stat")
    public ResponseEntity<?> stat(@RequestParam(value = "name", required = false) String name) {
        if (name != null)
            return new ResponseEntity<>("Name = " + fooRepository.findByName(name), OK);

        return new ResponseEntity<>("List: " + fooRepository.findAll(), OK);
    }
}