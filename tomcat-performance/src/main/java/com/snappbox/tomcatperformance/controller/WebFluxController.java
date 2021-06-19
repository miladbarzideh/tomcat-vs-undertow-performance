package com.snappbox.tomcatperformance.controller;

import com.snappbox.tomcatperformance.service.PerformanceService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("webflux")
public class WebFluxController {

    private final PerformanceService performanceService;

    public WebFluxController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping(value = "/{time}")
    public Mono<Boolean> useWebFluxWebClient(@PathVariable("time") int time) {
        return performanceService.doWithWebClient(time);
    }
}
