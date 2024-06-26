package com.app.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerCommon {

    private final EmailService emailService;

    public SchedulerCommon(EmailService emailService) {
        this.emailService = emailService;
    }


     //@Scheduled(cron = "0 30 22 15 * *")     // Execute on the 15th of every month at 10:30 PM
    //@Scheduled(cron = "0 0 8 * * *") // Schedule this method to run every day at 8:00 AM(ss:mm:hr:day:month:day in week =cron expression)
   // @Scheduled(fixedRate = 30000) // Execute every 30 seconds
    public void sendDailyEmails() {
        emailService.sendMail("rknewsbki@gmail.com", "Scheduler test", "This is autogenerated Message no send reply " );
    }
}