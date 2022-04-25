package fr.legrain.validator.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;
//import org.hibernate.mapping.Property;


import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.lib.data.MessCtrlLgr;
import fr.legrain.lib.data.ModeObjet;

//public class LgrHibernateValidator implements Validator<LgrHibernateValidated>, PropertyConstraint {
public class LgrHibernateValidator implements ConstraintValidator<LgrHibernateValidated, Object> {
	
	static Logger logger = Logger.getLogger(LgrHibernateValidator.class);

	private String champ;
	private String champEntitee;
	private String table;
	private Class<?> clazz;
	private ICtrlLgr ctrl;
	
	public ICtrlLgr getICtrlLgr() {
		ICtrlLgr ctrlLgr = null;
		String validatorServerClass = "fr.legrain.validator.JPACtrl_Application";
		String validatorClientClass = "fr.legrain.lib.validator.JPACtrl_ApplicationClient";
		try {
			//return new JPACtrl_Application();
			Class c = Class.forName(validatorServerClass);
			ctrlLgr = (ICtrlLgr) c.newInstance();
			ctrlLgr.setModeServeur(true);
			return ctrlLgr;
		} catch(ClassNotFoundException e) {
			logger.info("Validator serveur ("+validatorServerClass+") introuvable dans le classpath => mode client");
			Class c;
			try {
				c = Class.forName(validatorClientClass);
				ctrlLgr = (ICtrlLgr) c.newInstance();
			} catch (ClassNotFoundException e1) {
				logger.info("Validator client ("+validatorServerClass+") introuvable dans le classpath => pas de validation");
			} catch (InstantiationException e1) {
				logger.error("",e);
			} catch (IllegalAccessException e1) {
				logger.error("",e);
			}
		} catch (InstantiationException e) {
			logger.error("",e);
		} catch (IllegalAccessException e) {
			logger.error("",e);
		}
		return null;
	}

//	part of the Validator<Annotation> contract,
//	allows to get and use the annotation values
	public void initialize(LgrHibernateValidated parameters) {
		champ = parameters.champ();
		table = parameters.table();
		clazz = parameters.clazz();
		//champEntitee = parameters.champEntitee();
		ctrl = getICtrlLgr();
	}

//	part of the property constraint contract
	@Override
	public boolean isValid(Object value,ConstraintValidatorContext arg1) {
		boolean valid = true;
		
		if(ctrl!=null) {
			System.out.println("Validate appellé automatiquement à partir des Beans Validation et annotation : "+champ);
			MessCtrlLgr mess = new MessCtrlLgr();
			mess.setNomChamp(champ);
			mess.setNomTable(table);
			mess.setEntityClass(clazz);
			ModeObjet m = new ModeObjet();
			m.setMode(ModeObjet.EnumModeObjet.C_MO_EDITION);
			mess.setModeObjet(m);
			mess.setValeur(value.toString());
			try {
				ctrl.ctrlSaisie(mess);
			} catch (ExceptLgr e) {
				logger.error("",e);
				valid = false;
			}
		}
		
		return valid;
	}

}        