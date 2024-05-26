package com.app.service;

import com.app.Utilities.EmailService;
import com.app.dto.CancalDto;
import com.app.exceptions.ReservationException;
import com.app.model.Reservations;
import com.app.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CancalReqServiceImpl implements CancalReqService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private EmailService emailService;

    static final Map<Integer, Integer> response = new HashMap<>();


    @Override
    public String cancalReq(CancalDto clcdto) throws ReservationException {
        Reservations reservation = reservationRepo.findById(clcdto.getReservationId()).get();
        if (reservation != null) {
            Random random = new Random();
            int otp = random.nextInt(900000) + 100000;
            response.put(clcdto.getReservationId(), otp);
            emailService.sendMail(clcdto.getEmail(), "Cancellation OTP", "Your cancellation OTP is: " + otp);
        } else {
            throw new ReservationException("Reservation ID does not exist: " + clcdto.getReservationId());
        }
        return "cancellation otp sent";
    }

    @Override
    public void cancalTicket(Integer reservationId, Integer otp) {
        Integer storedOtp = response.get(reservationId);
        if (storedOtp != null && storedOtp.equals(otp)) {
            Reservations ticket = reservationRepo.findById(reservationId)
                    .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
            reservationRepo.delete(ticket);
        } else {
            throw new IllegalArgumentException("Invalid OTP for reservation ID: " + reservationId);
        }
    }
}
