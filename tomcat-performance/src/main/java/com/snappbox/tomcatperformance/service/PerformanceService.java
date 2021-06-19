package com.snappbox.tomcatperformance.service;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public interface PerformanceService {

    Boolean doWithBlocking(int millis);
    CompletableFuture<Boolean> doWithAsync(int millis);
    Mono<Boolean> doWithWebClient(int millis);
}
