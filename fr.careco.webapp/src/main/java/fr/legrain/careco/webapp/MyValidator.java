package fr.legrain.careco.webapp;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;

//@FacesValidator("myValidator")
public class MyValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {
    	if(value!=null) {
    		System.out.println("valeur : "+value.toString());
    	} else {
    		System.out.println("valeur nulle");
    	}
//        if (value is not valid) {
//            throw new ValidatorException(new FacesMessage(...));
//        }
    }

}
