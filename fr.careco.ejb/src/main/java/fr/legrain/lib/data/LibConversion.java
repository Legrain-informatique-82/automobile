package fr.legrain.lib.data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.log4j.Logger;

public class LibConversion {
	private static String  C_STRING_DEFAUT = "";
	private static Integer C_INTEGER_DEFAUT = 0;
	private static Float   C_FLOAT_DEFAUT = new Float(0);
	private static Double  C_DOUBLE_DEFAUT = 0.0;
	private static Short   C_SHORT_DEFAUT = 0;
	private static Long    C_LONG_DEFAUT = new Long(0);
	private static BigDecimal    C_BIGDECIMAL_DEFAUT = new BigDecimal(0);
	private static Boolean C_BOOLEAN_DEFAUT = false;
	
	private static NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
	
	public static NumberFormat formatEuro = new DecimalFormat("##,###.00 €");
	public static NumberFormat formatBigDec = new DecimalFormat("##,###.00");
	
	static Logger logger = Logger.getLogger(LibConversion.class.getName());	
	
	public static Integer booleanToInt(Boolean value){
		if(value==null)return 0;
		if(value)
			return 1;
		else 
			return 0;
	}
	
	public static BigDecimal DoubleToBigDecimal(Double value){
		if (value==null)
			return new BigDecimal(0);
		else
		return new BigDecimal(value);
	}
	
	
	public static Float BigDecimalToFloat(BigDecimal value){
		if (value==null)
			return new Float(0);
		else
		return value.floatValue();
	}
	
	public static Double BigDecimalToDouble(BigDecimal value){
		if (value==null)
			return new Double(0);
		else
		return value.doubleValue();
	}
	public static String booleanToStringLettre(Boolean value){
		if(value)
			return "true";
		else 
			return "false";
	}
	public static String booleanToString(Boolean value){
		if(value)
			return "1";
		else 
			return "0";
	}
	public static String booleanToStringFrancais(Boolean value){
		if(value)
			return "vrai";
		else 
			return "faux";
	}

	public static Boolean StringToBoolean(String value,Boolean defaut){
		if(value==null) return defaut;
		if (value.equals("0"))return false;
		if (value.equals("true"))return true;
		if (value.equals("vrai"))return true;
		if (value.equals("false"))return false;
		if (value.equals("faux"))return false;
		if (value.equals("1"))return true;
		if (value.equals("oui"))return true;
		if (value.equals("non"))return false;
		if (value.equals("y"))return true;
		if (value.equals("n"))return false;
		if (value.equals("yes"))return true;
		if (value.equals("no"))return false;
		if (value.equals("o"))return true;
		if (value.equals("n"))return false;
		logger.debug("[StringToBoolean] Attention, valeur : "+value);
		return defaut;

	}	

	public static String StringNotNull(String value){
		return StringNotNull(value,C_STRING_DEFAUT);
	}
	

	public static String StringNotNull(String value,String defaut){
		if(value==null) return defaut;
		return value;

	}	

	public static Boolean StringToBoolean(String value){
		return StringToBoolean(value,C_BOOLEAN_DEFAUT);
	}
	
	public static Boolean intToBoolean(int value,Boolean defaut){
		if (value==0){
			return false;
		}
		else
			if (value==1)
			  return true;
			else {
				logger.debug("[intToBoolean] Attention, valeur : "+value);
				return defaut;
			}
	}

	
	public static Boolean intToBoolean(int value){
		return intToBoolean(value,C_BOOLEAN_DEFAUT);	
	}
	
	public static String integerToString(Integer value, String defaut){
		if (value==null){
			logger.debug("[integerToString] Attention, valeur null");
			return defaut;
		}
		else
			return value.toString();
	}
	
	public static String bigDecimalToString(BigDecimal value, String defaut){
		if (value==null){
			logger.debug("[bigDecimalToString] Attention, valeur null");
			return defaut;
		}
		else
			return value.toString();
	}
	
	public static String bigDecimalToString(BigDecimal value){
		return bigDecimalToString(value,C_BIGDECIMAL_DEFAUT.toString());
	}
	
	public static String integerToString(Integer value){
		return integerToString(value,C_INTEGER_DEFAUT.toString());
	}
	
	public static Integer stringToInteger(String value, Integer defaut) {
		if(value!=null && !value.equals("")){
			try {
				return Integer.parseInt(value);
			} catch(NumberFormatException e) {
				logger.debug("erreur de conversion de "+value+" en integer");
				return defaut;
			}
		} else {
			return defaut;
		}
	}
	
	public static String doubleToString(Double value){
		return doubleToString(value,C_DOUBLE_DEFAUT.toString());
	}
	
	public static String doubleToString(Double value, String defaut){
		if (value==null){
			logger.debug("[doubleToString] Attention, valeur null");
			return defaut;
		}
		else
			return value.toString();
	}
	
	public static String floatToString(Float value){
		return floatToString(value,C_FLOAT_DEFAUT.toString());
	}
	
	public static String floatToString(Float value, String defaut){
		if (value==null){
			logger.debug("[floatToString] Attention, valeur null");
			return defaut;
		}
		else
			return value.toString();
	}
	
	public static Integer stringToInteger(String value) {
		return stringToInteger(value,C_INTEGER_DEFAUT);
	}
	
	public static Float stringToFloat(String value, Float defaut) {
		if(value!=null && !value.equals("")){
			try {
				return Float.parseFloat(value);
			} catch(NumberFormatException e) {
				logger.debug("erreur de conversion de "+value+" en float");
				return defaut;
			}
		} else {
			return defaut;
		}
	}
	
	public static Float stringToFloat(String value) {
		return stringToFloat(value,C_FLOAT_DEFAUT);
	}
	
	public static BigDecimal stringFRToBigDecimal(String value, BigDecimal defaut) {
		//http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4510618
		if(value!=null && !value.equals("")){
			Number n = null;
			try {
				n = nf.parse(value);
			} catch (ParseException e) {
				return defaut;
			}
			return BigDecimal.valueOf(n.doubleValue());
		} else {
			return defaut;
		}
	}
	
	public static BigDecimal stringFRToBigDecimal(String value) {
		return  stringFRToBigDecimal(value,C_BIGDECIMAL_DEFAUT);
	}
	
	
	public static BigDecimal stringToBigDecimal(String value, BigDecimal defaut) {
		if(value!=null && !value.equals("")){
			return BigDecimal.valueOf(Double.parseDouble(value));
		} else {
			return defaut;
		}
	}
	
	public static BigDecimal stringToBigDecimal(String value) {
		return  stringToBigDecimal(value,C_BIGDECIMAL_DEFAUT);
	}
	
	
	public static Double stringToDouble(String value, Double defaut) {
		if(value!=null && !value.equals("")){
			return Double.parseDouble(value);
		} else {
			return defaut;
		}
	}
	
	public static Double stringToDouble(String value) {
		return stringToDouble(value,C_DOUBLE_DEFAUT);
	}
	
	public static Short stringToShort(String value, Short defaut) {
		if(value!=null && !value.equals("")){
			return Short.parseShort(value);
		} else {
			return defaut;
		}
	}
	
	public static Short stringToShort(String value) {
		return stringToShort(value,C_SHORT_DEFAUT);
	}
	
	public static Long stringToLong(String value, Long defaut) {
		if(value!=null && !value.equals("")){
			return Long.parseLong(value);
		} else {
			return defaut;
		}
	}
	
	public static Long stringToLong(String value) {
		return stringToLong(value,C_LONG_DEFAUT);
	}
	
	
	public static boolean queDesChiffres(String valeur,boolean formate) {
		DecimalFormatSymbols decimalSeparateur = new DecimalFormatSymbols();
		String V= "";
		try {
			if(! LibChaine.emptyNumeric(valeur)){
				if (valeur!=null){
					for (int i = 0; i < valeur.length(); i++) {
						V =  valeur.substring(i,i+1);
						try {
							if (formate && !LibChaine.formatChiffre(V))							
								LibConversion.stringToInteger(V);
							else if (!formate)
								LibConversion.stringToInteger(V);
						} catch (Exception e) {
							return false;
						}
					}
				}
			}
			return true;
		} catch(Exception e) {
			logger.error("Erreur : queDesChiffres", e);
			return false;
		}
	}
	
	public static Integer shortToInteger(Short value, Integer defaut) {
		if(value!=null && !value.equals("")){
			return Integer.parseInt(value.toString());
		} else {
			return defaut;
		}
	}
	public static Integer shortToInteger(Short value) {
		return shortToInteger(value,C_INTEGER_DEFAUT);
	}
	
	
	
	public static Short integerToShort(Integer value, Short defaut) {
		if(value!=null && !value.equals("")){
			return Short.parseShort(value.toString());
		} else {
			return defaut;
		}
	}
	
	public static Short integerToShort(Integer value) {
		return integerToShort(value,C_SHORT_DEFAUT);
	}
	
	public static Integer VariantToInteger(Object value){
		
		return null;
	}
	
  public static Object renvoieValue(String value,Class typeObjetRetour){
	  if(typeObjetRetour==java.util.Date.class)
	  {
		  
	  }
	return value;
	  
  }
}
