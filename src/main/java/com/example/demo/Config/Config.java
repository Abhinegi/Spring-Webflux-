package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Config {
    int id = 1;
    ReentrantLock lock = new ReentrantLock();

    @Bean
    public RouterFunction<ServerResponse> routeToHandler() {
        return route(GET("/check"), (request) -> handleCall());
    }

    public Mono<ServerResponse> handleCall() {
          //Applying  the lock in starting
            lock.lock();
            System.out.println("Starting Time : " + (LocalDateTime.now()));
            //Chaining MONO's to access common resource
            return Mono.just(1).flatMap(value-> {
                return Mono.just(2).flatMap(value2 -> {
                    try {
                        return Mono.just(value2 + getValue()).flatMap(value3 -> {
                            //Do Some Work Here
                            System.out.println("Ending Time : " + (LocalDateTime.now()));
                            //Unlock
                            lock.unlock();
                            return ServerResponse.ok().bodyValue(value3);
                        });
                    } catch (InterruptedException e) {
                        // Unlocking lock at every possible place where exception can occur
                        System.out.println("Exception :: " + e);
                        lock.unlock();
                        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue("Error");
                    }
                }).onErrorResume(err -> {
                    // Unlocking lock at every possible place where exception can occur
                    lock.unlock();
                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue("Error");
                }).onErrorResume(err -> {
                    // Unlocking lock at every possible place where exception can occur
                    lock.unlock();
                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue("Error");
                });
            });
    }

    public int getValue() throws InterruptedException {
        //Do Some Work Here
        Thread.sleep(3000);
        return ++id;
    }

}