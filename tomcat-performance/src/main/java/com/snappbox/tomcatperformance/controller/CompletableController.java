package com.snappbox.tomcatperformance.controller;

import com.snappbox.tomcatperformance.service.PerformanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("completable-future")
public class CompletableController {

    public final PerformanceService performanceService;

    public CompletableController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping(value = "/{time}")
    public CompletableFuture<Boolean> useCompletableFuture(@PathVariable("time") int millis) {
       log.info("Got request " + threadName());
        CompletableFuture<Boolean> completableFuture = performanceService.doWithAsync(millis);
        log.info("Return completableFuture " + threadName());
        return completableFuture;
    }

    private String threadName() {
        return Thread.currentThread().getName();
    }
}
