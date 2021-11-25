package com.github.moraruvm;

import java.time.Duration;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class FluxTest {

  @Test
  void testFlux() {
    Flux.just("A", "B", "C")
        .log()
        .subscribe();
  }

  @Test
  void testFromIterable() {
    Flux.fromIterable(Arrays.asList("A", "B", "C"))
        .log()
        .subscribe();
  }

  @Test
  void testFromRange() {
    Flux.range(10, 5)
        .log()
        .subscribe();
  }

  @Test
  void testFromInterval() throws InterruptedException {
    Flux.interval(Duration.ofSeconds(1))
        .log()
        .take(2)
        .subscribe();

    Thread.sleep(5000);
  }

  @Test
  void fluxRequestCustomSubscriber() {
    Flux.range(1, 5)
        .log()
        .subscribe(new BaseSubscriber<>() {
          @Override
          protected void hookOnSubscribe(Subscription subscription) {
            subscription.request(3);
          }
        });
  }

  @Test
  void fluxRateLimit() {
    Flux.range(0, 7)
        .log()
        .limitRate(3)
        .subscribe();
  }

}
