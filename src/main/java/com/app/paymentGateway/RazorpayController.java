package com.app.paymentGateway;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/razorpay")
public class RazorpayController {

    // http://localhost:8080/razorpay/createOrder
    private RazorpayClient client;
    private static final String SECRET_ID1 = "rzp_live_dqdNciDGwlIJMa";
    private static final String SECRET_KEY1 = "wKRx7xvsH4H10fvoBn0waTC6";
//        private static final String SECRET_ID2 = "rzp_test_J4fInjDpTX475d";
//        private static final String SECRET_KEY2 = "r8fNXAB78RmsVfdiQbWGwyjr";

    @PostMapping("/createOrder")
    public TicketResponse createOrder(@RequestBody TicketRequest ticketRequest) {
        TicketResponse response = new TicketResponse();
        try {
//                if (orderRequest.getAmount().intValue() > 1000) {
            client = new RazorpayClient(SECRET_ID1, SECRET_KEY1);
//                } else {
//                    client = new RazorpayClient(SECRET_ID2, SECRET_KEY2);
            Order order = createRazorPayOrder(ticketRequest.getAmount());
            System.out.println("---------------------------");
            String ticketId = (String) order.get("id");
            System.out.println("Ticket ID: " + ticketId);
            System.out.println("---------------------------");
            response.setRazorpayTicketId(ticketId);
            response.setApplicationFee("" + ticketRequest.getAmount());
//                if (orderRequest.getAmount().intValue() > 1000) {
            response.setSecretKey(SECRET_KEY1);
            response.setSecreteId(SECRET_ID1);
            response.setPgName("razor1");
//                } else {
//                    response.setSecretKey(SECRET_KEY2);
//                    response.setSecretId(SECRET_ID2);
//                    response.setPgName("razor2");
//                }
            return response;
        } catch (RazorpayException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;

    }

    private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {

        JSONObject options = new JSONObject();
        options.put("amount", amount.multiply(new BigInteger("100")));
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        options.put("payment_capture", 1); // You can enable this if you want to do Auto Capture.
        System.out.println(options);
        return client.orders.create(options);

    }

}

