package com.cozyapp.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozyapp.backend.dto.BookingVisitsDto;
import com.cozyapp.backend.entity.BookingVisit;
import com.cozyapp.backend.service.BookingVisitService;

@RestController
@RequestMapping
public class BookingVisitController {

    @Autowired
    private BookingVisitService bookingVisitService;


    @PostMapping("/user/bookAVisit/{userId}/{availabilityId}")
    public ResponseEntity<BookingVisit> bookAVisit(@PathVariable Integer userId, @PathVariable Integer availabilityId, @RequestBody BookingVisitsDto bookingVisitDto) {
        System.out.println("BookingVisitController: bookAVisit");
        BookingVisit newBookingVisit = bookingVisitService.addBookingVisit(bookingVisitDto, userId, availabilityId);
        return new ResponseEntity<>(newBookingVisit, HttpStatus.CREATED);
    }
    
}
