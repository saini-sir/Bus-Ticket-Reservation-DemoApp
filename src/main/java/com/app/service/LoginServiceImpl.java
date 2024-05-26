package com.app.service;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import com.app.dto.ForgetPasswordDto;
import com.app.dto.PasswordResetDto;
import com.app.exceptions.UserException;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.dto.LoginDTO;
import com.app.exceptions.LoginException;
import com.app.model.CurrentUserSession;
import com.app.model.User;
import com.app.repository.SessionRepo;
import com.app.repository.UserRepo;

import net.bytebuddy.utility.RandomString;


@Service
public class LoginServiceImpl implements LoginService {


    private static  final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Value("${twilio.account.sid}")
	private String twilioAccountSid;

	@Value("${twilio.auth.token}")
	private String twilioAuthToken;

	@Value("${twilio.phone.number}")
	private String twilioPhoneNumber;

	@Autowired
	private SessionRepo sr;

	@Autowired
	private UserRepo ur;

	static final Map<String , Integer> responce = new HashMap<>();

	@Override
	public String logInIntoAccount(LoginDTO dto) throws LoginException {
		User user = ur.findByContact(dto.getContact());

		if (user == null) {
			throw new LoginException("Please Enter A Valid Contact Detail..");
		}

		Optional<CurrentUserSession> validCustomerSessionOpt = sr.findById(user.getUserLoginId());

		if (validCustomerSessionOpt.isPresent()) {
			throw new LoginException("User Already Logged In with this Id..");
		}

		if (user.getPassword().equals(dto.getPassword())) {
			String key = RandomString.make(6);

			CurrentUserSession cus = new CurrentUserSession(user.getUserLoginId(), key, LocalDateTime.now());

			sr.save(cus);

			return cus.toString();

		} else {
			throw new LoginException("Please enter valid password..");
		}

	}

	@Override
	public String LogOutFromAccount(String key) throws LoginException {

		CurrentUserSession cus = sr.findByUuid(key);

		if (cus == null) {
			throw new LoginException("User not Logged In with this key..");
		}

		sr.delete(cus);

		return "USER LOGGED OUT..";

	}

	@Override
	public void resetPassword(PasswordResetDto passwordResetDto)throws UserException {
		String stredOtp = String.valueOf(responce.get(passwordResetDto.getUuid()));
		CurrentUserSession loginU = sr.findByUuid(passwordResetDto.getUuid());
		if(loginU != null){
			stredOtp.equals(passwordResetDto.getOtp());
			User sUser = ur.findById(loginU.getUserId()).get();
			sUser.setPassword(passwordResetDto.getNewPassword());
			ur.save(sUser);
		}else {
             throw  new UserException("User not login !! Please login first.....");
		}

	}

	@Override
    public String forgetPassword(ForgetPasswordDto forgetPasswordDto) throws UserException {
        CurrentUserSession userSession = sr.findByUuid(forgetPasswordDto.getUuid());
        if (userSession != null) {
            Random random = new Random();
            int otp = random.nextInt(900000) + 100000;
            responce.put(forgetPasswordDto.getUuid(), otp);
            try {
                Twilio.init(twilioAccountSid, twilioAuthToken);
                Message message = Message.creator(
                                new PhoneNumber(forgetPasswordDto.getContact()),
                                new PhoneNumber(twilioPhoneNumber),
                                String.valueOf(otp))
                        .create();
                return ("OTP sent successfully. SID: " + message.getSid());
            } catch ( TwilioException e) {
                // Log the exception for further investigation
                logger.error("SMS failed: " + e.getMessage());
                return ("SMS failed: " + e.getMessage());
            }
        } else {
            return ("User not found! Please enter a valid User UUID.");
        }
    }
}

