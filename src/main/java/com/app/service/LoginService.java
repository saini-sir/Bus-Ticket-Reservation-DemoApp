package com.app.service;

import com.app.dto.ForgetPasswordDto;
import com.app.dto.LoginDTO;
import com.app.dto.PasswordResetDto;
import com.app.exceptions.LoginException;
import com.app.exceptions.UserException;

public interface LoginService {
	
	
	public String logInIntoAccount(LoginDTO dto)throws LoginException;
	
	public String LogOutFromAccount(String key)throws LoginException;

    void resetPassword(PasswordResetDto passwordResetDto) throws UserException;

	String forgetPassword(ForgetPasswordDto forgetPasswordDto) throws UserException;
}
