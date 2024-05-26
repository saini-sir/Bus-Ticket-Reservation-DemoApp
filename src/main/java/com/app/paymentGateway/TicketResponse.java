package com.app.paymentGateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {

    String secretKey;
    String razorpayTicketId;
    String applicationFee;
    String secreteId;
    String pgName;

}
