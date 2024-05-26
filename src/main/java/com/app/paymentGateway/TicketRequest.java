package com.app.paymentGateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {

    String customerName;
    String email;
    String mobile;
    BigInteger amount;
}
