package com.github.moraruvm;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OperatorTest {

  @Test
  void testMap() {
    Flux.range(1, 5)
        .map(i -> i * 10)
        .subscribe(System.out::println);
  }

  @Test
  void testFlatMap() {
    Flux.range(1, 5)
        .flatMap(i -> Flux.range(i * 10, 3))
        .subscribe(System.out::println);
  }

  @Test
  void testFlatMapMany() {
    Mono.just(3)
        .flatMapMany(i -> Flux.range(1, i))
        .subscribe(System.out::println);
  }

  @Test
  void concat() throws InterruptedException {
    Flux<Integer> oneToFive = Flux.range(1, 5).delayElements(Duration.ofMillis(200));
    Flux<Integer> sixToTen = Flux.range(6, 5).delayElements(Duration.ofMillis(400));

    Flux.concat(oneToFive, sixToTen)
        .subscribe(System.out::println);

    Thread.sleep(4000);
  }

  @Test
  void testMerge() throws InterruptedException {
    Flux<Integer> oneToFive = Flux.range(1, 5).delayElements(Duration.ofMillis(200));
    Flux<Integer> sixToTen = Flux.range(6, 5).delayElements(Duration.ofMillis(400));

    Flux.merge(oneToFive, sixToTen)
        .subscribe(System.out::println);

    Thread.sleep(4000);
  }

  @Test
  void testZip() throws InterruptedException {
    Flux<Integer> oneToFive = Flux.range(1, 5);
    Flux<Integer> sixToTen = Flux.range(6, 5);

    Flux.zip(oneToFive, sixToTen, (a, b) -> a + ", " + b)
        .subscribe(System.out::println);
  }

}
