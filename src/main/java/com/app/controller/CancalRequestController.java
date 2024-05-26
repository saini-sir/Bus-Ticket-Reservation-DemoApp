package com.app.controller;

import com.app.dto.CancalDto;
import com.app.exceptions.ReservationException;
import com.app.service.CancalReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ComponentScan("com.app.Utilities")
@RequestMapping("/cancellation")
public class CancalRequestController {

    @Autowired
    private CancalReqService cancalReqService;

    @PostMapping("/RequestForCancel")
    public ResponseEntity<String> cancelRequest(@RequestBody CancalDto clcdto) throws ReservationException {
        String  msg = cancalReqService.cancalReq(clcdto);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PostMapping("/cancel/{reservationId}/{otp}")
    public ResponseEntity<?> cancelTicket(@PathVariable Integer reservationId, @PathVariable Integer otp) throws ReservationException {
        cancalReqService.cancalTicket(reservationId, otp);
        return new ResponseEntity<>("Ticket cancellation successful! Refund initiated.", HttpStatus.OK);
    }


}
