package fr.legrain.careco.webapp.controller;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("bigDecimalConverter")
public class BigDecimalCoverter implements Converter{        

    @Override
     public Object getAsObject(FacesContext context, UIComponent component, String value) {
            BigDecimal valueDecimal = new BigDecimal(value);  
//            if(valueDecimal.compareTo(valueDecimal)>9999 && valueDecimal.compareTo(valueDecimal)<-9999){
//                 valueDecimal.setScale(2, RoundingMode.CEILING)
//            }
//            else{                       
//                FacesMessage msg = new FacesMessage("Number not in range");
//                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//                throw new ConverterException(msg);                   
//            }       
            return valueDecimal;
       }

        @Override
        public String getAsString(FacesContext context, UIComponent component,
            Object value) {
            return value.toString();

        }   
  }
