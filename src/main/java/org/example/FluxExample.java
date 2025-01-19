package org.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxExample {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("Hello", "World", "From", "Project", "Reactor");
        Mono<String> flux_mono = Mono.just("Hello");
        Flux<Integer> numbers = Flux.just(1,20,300,4000).map(n -> {
            if (n == 20) throw new RuntimeException("Błąd!!!");
            return n;
        });

        flux.subscribe(
                System.out::println, // onNext
                error -> System.err.println("Error: " + error.getMessage()), //onError
                () -> System.out.println("Completed")); // onComplete

        numbers.subscribe(
                System.out::println, // onNext
                error -> System.err.println("Error: " + error.getMessage()), //onError
                () -> System.out.println("Completed")); // onComplete
    }
}