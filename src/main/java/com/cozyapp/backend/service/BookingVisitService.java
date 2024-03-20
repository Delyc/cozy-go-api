package com.cozyapp.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozyapp.backend.dto.BookingVisitsDto;
import com.cozyapp.backend.entity.Availability;
import com.cozyapp.backend.entity.BookingVisit;
import com.cozyapp.backend.entity.OurUsers;
import com.cozyapp.backend.repository.AvailabilityRepo;
import com.cozyapp.backend.repository.BookingVisitRepo;
import com.cozyapp.backend.repository.OurUserRepo;

@Service
public class BookingVisitService {
    @Autowired
    private BookingVisitRepo bookingVisitRepo;
    
    @Autowired
    private OurUserRepo ourUserRepo;

    @Autowired
    private AvailabilityRepo availabilityRepo;

    public BookingVisit addBookingVisit(BookingVisitsDto bookingVisitDto, Integer userId, Integer availabilityId) {
        System.out.printf("BookingVisitService: addBookingVisit", availabilityId, userId);
        OurUsers user = ourUserRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found")); 
        Availability availability = availabilityRepo.findById(availabilityId)
            .orElseThrow(() -> new RuntimeException("Availability not found")); 

        BookingVisit bookingVisit = new BookingVisit();
        bookingVisit.setMessage(bookingVisitDto.getMessage());
        bookingVisit.setUser(user);
        bookingVisit.setAvailability(availability);
        bookingVisit.setBookingStatus(bookingVisitDto.getBookingStatus());

        return bookingVisitRepo.save(bookingVisit);
    }

    public void deleteBookingVisitById(Integer id) {
        bookingVisitRepo.deleteById(id);
    }

    

    public Optional<BookingVisit> updateBookingVisit(Integer id, BookingVisit bookingVisitDetails) {
        return bookingVisitRepo.findById(id).map(existingBookingVisit -> {
            if (bookingVisitDetails.getMessage() != null) {
                existingBookingVisit.setMessage(bookingVisitDetails.getMessage());
            }
            if (bookingVisitDetails.getUser() != null) {
                existingBookingVisit.setUser(bookingVisitDetails.getUser());
            }
            if (bookingVisitDetails.getAvailability() != null) {
                existingBookingVisit.setAvailability(bookingVisitDetails.getAvailability());
            }
            if (bookingVisitDetails.getBookingStatus() != null) {
                existingBookingVisit.setBookingStatus(bookingVisitDetails.getBookingStatus());
            }
            return bookingVisitRepo.save(existingBookingVisit);
        });
    }

    public Optional<BookingVisit> decision(Integer id, BookingVisit bookingVisitDetails) {
        return bookingVisitRepo.findById(id).map(existingBookingVisit -> {
            if (bookingVisitDetails.getMessage() != null) {
                existingBookingVisit.setMessage(bookingVisitDetails.getMessage());
            }
            if (bookingVisitDetails.getUser() != null) {
                existingBookingVisit.setUser(bookingVisitDetails.getUser());
            }
            if (bookingVisitDetails.getAvailability() != null) {
                existingBookingVisit.setAvailability(bookingVisitDetails.getAvailability());
            }
            if (bookingVisitDetails.getBookingStatus() != null) {
                existingBookingVisit.setBookingStatus(bookingVisitDetails.getBookingStatus());
            }
            return bookingVisitRepo.save(existingBookingVisit);
        });
    }

    public Optional<BookingVisit> getBookingVisitById(Integer id) {
        return bookingVisitRepo.findById(id);
    }

    public List<BookingVisit> getAllBookingVisits() {
        return bookingVisitRepo.findAll();
    }
}
