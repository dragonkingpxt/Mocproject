package vn.com.fpt.mockproject.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vn.com.fpt.mockproject.model.User;
@Component
public class UserValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == User.class;
	}

	public void validate(Object arg0, Errors errors) {
		// TODO Auto-generated method stub
		User user=(User) arg0;
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "NotEmpty.user.id");
	       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.user.password");
	       
	}

}
