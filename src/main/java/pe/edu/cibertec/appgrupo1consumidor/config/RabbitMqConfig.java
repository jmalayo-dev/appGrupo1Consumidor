package pe.edu.cibertec.appgrupo1consumidor.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String EXCHANGE = "Grupo1Exchange";
    public static final String QUEUE = "Grupo1Queue";
    public static final String ROUTING_KEY = "Grupo1Routing";

    @Bean
    public DirectExchange grupo1Exchange() {
        return new DirectExchange(EXCHANGE);
    }
    @Bean
    public Queue grupo1Queue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public Binding grupo1Binding() {
        return BindingBuilder.bind(grupo1Queue())
                .to(grupo1Exchange())
                .with(ROUTING_KEY);
    }
}
