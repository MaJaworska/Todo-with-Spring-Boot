package com.majaw.todoWithSpringBoot.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class HelloServlet {
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    private HelloService service;

    HelloServlet(HelloService service) {
        this.service = service;
    }

    @GetMapping(params = {"lang", "name"})
    String welcome(@RequestParam("lang") Integer langId, @RequestParam String name) {
        logger.info("Request got");
        return service.prepareGreetings(name, langId);
    }

    @GetMapping()
    String welcome() {
        return service.prepareGreetings(null, null);
    }
}
