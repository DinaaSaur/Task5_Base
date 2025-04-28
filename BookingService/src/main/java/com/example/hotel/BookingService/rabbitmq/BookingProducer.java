package com.example.hotel.BookingService.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendBooking(String bookingId) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.BOOKING_ROUTING,
                bookingId
        );
        System.out.println("Sent From 52_7599: " + bookingId);
    }
}
