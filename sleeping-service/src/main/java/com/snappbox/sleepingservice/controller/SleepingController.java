package com.snappbox.sleepingservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SleepingController {

    @GetMapping(value = "/sleep/{time}")
    public boolean sleep(@PathVariable("time") int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return true;
    }
}
