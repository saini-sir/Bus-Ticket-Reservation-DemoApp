package com.app.service;

import com.app.dto.CancalDto;
import com.app.exceptions.ReservationException;

public interface CancalReqService {
    String cancalReq(CancalDto clcdto) throws ReservationException;

    void cancalTicket(Integer reservationId, Integer otp);
}
