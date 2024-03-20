package com.cozyapp.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cozyapp.backend.dto.BookingVisitsDto;
import com.cozyapp.backend.entity.BookingVisit;
import com.cozyapp.backend.entity.House;
import com.cozyapp.backend.service.BookingVisitService;

@RestController
@RequestMapping
public class BookingVisitController {

    @Autowired
    private BookingVisitService bookingVisitService;

    @PostMapping("/user/book/{userId}/availability/{availabilityId}")
    public ResponseEntity<BookingVisit> book(@PathVariable Integer userId, @PathVariable Integer availabilityId,
            @RequestBody BookingVisitsDto bookingVisitDto) {
        System.out.printf("BookingVisitController: bookAVisit", availabilityId, userId);
        System.out.printf("BookingVisitController: bookAVisit", bookingVisitDto);
        BookingVisit newBookingVisit = bookingVisitService.addBookingVisit(bookingVisitDto, userId, availabilityId);
        return new ResponseEntity<>(newBookingVisit, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/deleteBooking/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        bookingVisitService.deleteBookingVisitById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/adminuser/allBookings")
    public List<BookingVisit> getAllBookings() {
        return bookingVisitService.getAllBookingVisits();
    }

    @GetMapping("/adminuser/booking/{id}")
    public ResponseEntity<BookingVisit> getBookingById(@PathVariable Integer id) {
        return bookingVisitService.getBookingVisitById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
               
    }

    @PatchMapping("/user/updateBooking/{id}")
    public ResponseEntity<BookingVisit> updateBooking(@PathVariable Integer id, @RequestBody BookingVisit bookingVisitDetails) {
        return bookingVisitService.updateBookingVisit(id, bookingVisitDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/agent/updateBooking/{id}")
    public ResponseEntity<BookingVisit> decision(@PathVariable Integer id, @RequestBody BookingVisit bookingVisitDetails) {
        return bookingVisitService.decision(id, bookingVisitDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
