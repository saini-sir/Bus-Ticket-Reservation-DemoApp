package com.app.paypal;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PaypalConfig {

    @Value("${paypal.client.id}")
    private String clientId;
    @Value("${paypal.client.secret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;
    @Bean
    public Map<String , String>  paypalconfigSdk(){
        Map<String, String> config = new HashMap<>();
        config.put("mode",mode);
        return  config;
    }
    @Bean
    public OAuthTokenCredential oAuthTokenCredential(){
        return  new OAuthTokenCredential(clientId,clientSecret,paypalconfigSdk());
    }


    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(paypalconfigSdk());
        return context;
    }

}
