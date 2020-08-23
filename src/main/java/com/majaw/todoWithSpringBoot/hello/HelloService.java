package com.majaw.todoWithSpringBoot.hello;

import com.majaw.todoWithSpringBoot.lang.Lang;
import com.majaw.todoWithSpringBoot.lang.LangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class HelloService {
    private static final String FALLBACK_NAME = "world";
    private static final Lang FALLBACK_LANG = new Lang(1, "Hello", "en");

    private LangRepository langRepository;

    @Autowired
    HelloService(LangRepository langRepository) {
        this.langRepository = langRepository;
    }

    String prepareGreetings(String name, Integer langId) {
        langId = Optional.ofNullable(langId).orElse(FALLBACK_LANG.getId());
        String welcomeMsg = langRepository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        String nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg + " " + nameToWelcome + "!";
    }
}
