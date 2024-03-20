package com.cozyapp.backend.service;

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
}
