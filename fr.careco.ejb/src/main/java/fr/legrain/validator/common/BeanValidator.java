package fr.legrain.validator.common;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;


public class BeanValidator<T> {

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Class clazz = null;

	public BeanValidator(Class c) {
		this.clazz = c;
	}

	public boolean validate(T bean) throws Exception {
		Set<ConstraintViolation<T>> violations = factory.getValidator().validate(bean,new Class<?>[] { Default.class });

		if (violations.size() > 0) {
			String messageComplet = "Validation errors";
			for (ConstraintViolation<T> cv : violations) {
				messageComplet+=" "+cv.getMessage()+"\n";
			}

			throw new Exception(messageComplet);
		}
		return false;
	}

	public boolean validateField(T bean, String propertyName) throws Exception {
		Set<ConstraintViolation<Object>> violations = factory.getValidator().validateValue(clazz,propertyName,bean);

		if (violations.size() > 0) {
			String messageComplet = "Validation errors";
			for (ConstraintViolation<Object> cv : violations) {
				messageComplet+=" "+cv.getMessage()+"\n";
			}
			throw new Exception(messageComplet);
		}
		return false;
	}

}
