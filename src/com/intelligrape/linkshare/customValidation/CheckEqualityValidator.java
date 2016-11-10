package com.intelligrape.linkshare.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.intelligrape.linkshare.dao.User;

public class CheckEqualityValidator implements ConstraintValidator<CheckEquality, User> {

	@Override
	public void initialize(CheckEquality value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(User user, ConstraintValidatorContext arg1) {
		if(user.getPassword().equals(user.getConfirmPassword()))
		{
			return true;
		}
		return false;
	}

}
