package com.kafka.broker.consumer;

import com.kafka.broker.message.OrderMessage;
import com.kafka.broker.message.OrderReplyMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderReplyConsumer {

    @KafkaListener(topics = "t-commodity-order")
    @SendTo("t-commodity-order-reply")
    public OrderReplyMessage listen(ConsumerRecord<String, OrderMessage> consumerRecord) {
        var header = consumerRecord.headers();
        var orderMessage = consumerRecord.value();

        log.info("Processing order {},item {}, credit card number {}", orderMessage.getOrderNumber(),
                orderMessage.getItemName(), orderMessage.getCreditCardNumber());
        log.info("Headers: ");
        header.forEach(h -> log.info(" Key: {}, Value: {}", h.key(), new String(h.value())));

        var headerValue = ObjectUtils.isEmpty(header.lastHeader("surpriseBonus").value()) ? "0"
                : new String(header.lastHeader("surpriseBonus").value());

        var bonusPercentage = Integer.parseInt(headerValue);
        var bonusAmount = (bonusPercentage / 100d) * orderMessage.getPrice() * orderMessage.getQuantity();

        log.info("Bonus amount is {}", bonusAmount);

        var replyMessage = new OrderReplyMessage();
        replyMessage.setReplyMessage(
                "Order " + orderMessage.getOrderNumber() + ", item " + orderMessage.getItemName() + " processed");

        return replyMessage;
    }
}
