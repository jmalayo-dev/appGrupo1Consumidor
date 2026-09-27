package pe.edu.cibertec.appgrupo1consumidor.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.appgrupo1consumidor.config.RabbitMqConfig;
import pe.edu.cibertec.appgrupo1consumidor.service.FibonacciService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
@Component
public class FibonacciConsumidor {
    private final FibonacciService fibonacciService;

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void calcularFibonacci(String cadenaNumeros) {

        log.info("Mensaje recibido de RabbitMQ: {}", cadenaNumeros);

        Integer[] integerArray = Stream.of(cadenaNumeros.split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        List<Integer> posiciones = List.of(integerArray);

        List<Long> resultado = fibonacciService.calculateSequence(posiciones);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (Integer n : posiciones) {
            log.info("fibonacci({}) = {}", n, fibonacciService.fibonacci(n));
        }

        log.info("Resultado: {}", resultado);
        log.info("Procesado, fecha y hora {}", LocalDateTime.now());
        log.info("-----------------------------------------");
    }
}
