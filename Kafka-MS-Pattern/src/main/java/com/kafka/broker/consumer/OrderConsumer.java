package com.kafka.broker.consumer;

import com.kafka.broker.message.OrderMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderConsumer {

    @KafkaListener(topics = "t-commodity-order")
    public void listen(OrderMessage message){

        var totalAmount = message.getQuantity() * message.getPrice();

        log.info("Processing order {}, item {}, credit card number {}. Total amount for this item is {}",
                message.getOrderNumber(), message.getItemName(), message.getCreditCardNumber(), totalAmount);
    }
}
