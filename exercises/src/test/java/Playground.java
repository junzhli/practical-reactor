import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Playground {
    @Test
    public void test() throws InterruptedException {
        var mono = Flux.range(0, 10)
                .map(x -> Mono.just(x).flatMapMany(pub -> Flux.just(x, x * 2, x * 4).delayElements(Duration.ofSeconds(1))))
                .flatMap(x -> x)
                .doOnEach(x -> System.out.println("my thread " + Thread.currentThread().getName()));
        mono.subscribe(System.out::println);
        Thread.sleep(1000*1000);
    }

}
