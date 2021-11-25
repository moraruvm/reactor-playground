package com.github.moraruvm;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoTest {

  @Test
  void testJust() {
    Mono.just("A").log()
        .subscribe(System.out::println);
  }

  @Test
  void testEmptyMono() {
    Mono.empty().log()
        .subscribe(System.out::println,
            null, () ->
                System.out.println("Done")
        );
  }

  @Test
  void testUncheckedException() {
    Mono.error(new RuntimeException()).log()
        .subscribe(System.out::println, e -> System.out.println("Error ocurred " + e));
  }

  @Test
  void testCheckedException() {
    Mono.error(new Exception()).log()
        .subscribe(System.out::println, e -> System.out.println("Error ocurred " + e));
  }

  @Test
  void testDoOnError() {
    Mono.error(new RuntimeException("Ooops")).log()
        .doOnError(e -> System.out.println("Something happened: " + e))
        .subscribe();
  }

  @Test
  void testOnErrorResume() {
    Mono.error(new RuntimeException("I know what to do with this")).log()
        .onErrorResume(e -> {
          System.out.println("Caught " + e);
          return Mono.just("B");
        })
        .log()
        .subscribe();
  }
}
