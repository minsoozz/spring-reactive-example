package com.github.minsoozz.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

class Example {

    @Test
    void test() {
        Flux
                .just(1, 2, 3, 4, 5, 6)
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .subscribe(System.out::println);
    }
}
