package com.example.hotel.BookingService.Services;

import com.example.hotel.BookingService.Clients.AvailabilityClient;
import com.example.hotel.BookingService.rabbitmq.BookingProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class BookingService {
    private final AvailabilityClient availability;
    private final BookingProducer producer;

    @Value("${Name}")
    String name;

    @Value("${ID}")
    String id;

    public BookingService(AvailabilityClient availability,
                          BookingProducer producer) {
        this.availability = availability;
        this.producer     = producer;
    }
    public String createBooking(String roomType, int nights) {
        if(!availability.check(roomType, nights))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Room Type or Nights is not valid");
        }
        String bookingId = UUID.randomUUID().toString();
        String x= bookingId+" Dina_52_7599";
        producer.sendBooking(x);
        return x;

    }
}

