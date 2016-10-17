package com.sgk.rest;

import com.sgk.resource.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class GreetingController {

    private static final String GREET = "Welcome, %s!";

    @RequestMapping(value = "/greeting")
    public ResponseEntity<Greeting> sayHi(@RequestParam(value = "name", required = false, defaultValue = "my friend") String name) {
        Greeting greeting = new Greeting(String.format(GREET, name));
        greeting.add(linkTo(methodOn(GreetingController.class).sayHi(name)).withSelfRel());
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

}
