package com.snappbox.tomcatperformance.controller;

import com.snappbox.tomcatperformance.service.PerformanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blocking")
public class BlockingController {

    private final PerformanceService performanceService;

    public BlockingController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping(value = "/{time}")
    public Boolean useBlocking(@PathVariable("time") int millis) {
        return performanceService.doWithBlocking(millis);
    }
}
