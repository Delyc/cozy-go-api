package com.cozyapp.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@RestController
public class SmsController {
    @GetMapping(value = "/sendSMS")
        public ResponseEntity<String> sendSMS() {

                Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));

                Message.creator(new PhoneNumber("<TO number - +250781093895>"),
                                new PhoneNumber("<FROM number - +19387770809"), "Hello from Twilio 📞").create();

                return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
        }
}
