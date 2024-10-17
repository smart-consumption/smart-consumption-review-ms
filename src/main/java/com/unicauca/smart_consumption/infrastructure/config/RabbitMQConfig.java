package com.unicauca.smart_consumption.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

    public static final String PRODUCT_CREATED_QUEUE = "product.created.queue";
    public static final String PRODUCT_UPDATED_QUEUE = "product.updated.queue";
    public static final String PRODUCT_EXCHANGE = "product.exchange";
    public static final String ROUTING_KEY_PRODUCT_CREATED = "product.created";
    public static final String ROUTING_KEY_PRODUCT_UPDATED = "product.updated";

  @Bean
  RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
  }   

  @Bean
  Jackson2JsonMessageConverter jsonMessageConverter() {
      return new Jackson2JsonMessageConverter();
  }
  

  @Bean
  Queue productCreatedQueue() {
    return new Queue(PRODUCT_CREATED_QUEUE, true);
  }

  @Bean
  Queue productUpdatedQueue() {
    return new Queue(PRODUCT_UPDATED_QUEUE, true);
  }

  @Bean
  TopicExchange productExchange() {
    return new TopicExchange(PRODUCT_EXCHANGE);
  }

  @Bean
  Binding bindingCreated() {
    return BindingBuilder.bind(productCreatedQueue()).to(productExchange()).with(ROUTING_KEY_PRODUCT_CREATED);
  }

  @Bean
  Binding bindingUpdated() {
    return BindingBuilder.bind(productUpdatedQueue()).to(productExchange()).with(ROUTING_KEY_PRODUCT_UPDATED);
  }


}
