package com.snappbox.tomcatperformance.service.impl;

import com.snappbox.tomcatperformance.service.PerformanceService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    private final static String URL = "http://localhost:8091/sleep/";
    private final WebClient webClient;

    public PerformanceServiceImpl() {
        this.webClient = WebClient.builder().build();
    }

    public Boolean doWithBlocking(int millis) {
        return new RestTemplate().getForObject(URL + millis, Boolean.TYPE);
    }

    @Async("threadPoolTaskExecutor")
    @Override
    public CompletableFuture<Boolean> doWithAsync(int millis) {
        return CompletableFuture.completedFuture(doWithBlocking(millis));
    }

    @Override
    public Mono<Boolean> doWithWebClient(int millis) {
        return webClient.get().uri(URL + millis).retrieve().bodyToMono(Boolean.class);
    }
}
