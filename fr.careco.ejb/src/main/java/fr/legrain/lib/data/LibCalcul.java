package fr.legrain.lib.data;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class LibCalcul {
	
	public final static int C_MULTIPLIER  = 1;
	public final static int C_ADDITIONNER = 2; 
	public final static int C_SOUSTRAIRE  = 3;
	public final static int C_DIVISER     = 4; 
	
	static public double calculStringDouble(String x, String y, int typeCalcul) {
		if(x==null) x="0";
		if(y==null) y="0";
		Double d = null;
		switch(typeCalcul) {
			case C_MULTIPLIER:
				d=LibConversion.stringToDouble(x)*LibConversion.stringToDouble(y);
				break;
			case C_ADDITIONNER:
				d=LibConversion.stringToDouble(x)+LibConversion.stringToDouble(y);
				break;
			case C_SOUSTRAIRE:
				d=LibConversion.stringToDouble(x)-LibConversion.stringToDouble(y);
				break;
			case C_DIVISER:
				d=LibConversion.stringToDouble(x)/LibConversion.stringToDouble(y);
				break;
		}
		return d;
	}

	static public double calculStringDoubleArrondi(String x, String y, int typeCalcul) {
		if(x==null) x="0";
		if(y==null) y="0";
		Double d = null;
		switch(typeCalcul) {
			case C_MULTIPLIER:
				d=LibConversion.stringToDouble(x)*LibConversion.stringToDouble(y);
				break;
			case C_ADDITIONNER:
				d=LibConversion.stringToDouble(x)+LibConversion.stringToDouble(y);
				break;
			case C_SOUSTRAIRE:
				d=LibConversion.stringToDouble(x)-LibConversion.stringToDouble(y);
				break;
			case C_DIVISER:
				d=LibConversion.stringToDouble(x)/LibConversion.stringToDouble(y);
				break;
		}
		return  arrondi(d,2);
	}
	
	static public String calculStringString(String x, String y, int typeCalcul,boolean precision) {
		if (precision) return String.valueOf(calculStringDouble(x,y,typeCalcul));
		else
		return String.valueOf(calculStringDoubleArrondi(x,y,typeCalcul));
	}
	
	static public double arrondi(double x, int puissance) {
		MathContext mathContext = new MathContext(3,RoundingMode.HALF_UP);
		x *= Math.pow(10,puissance);
		long i = Math.round(x);
		x = i/Math.pow(10.0,puissance);
		return x;
	}
	
	static public double arrondi(double x) {
		return arrondi(x,2);
	}
	
	static public double arrondi(Double x) {
		return arrondi(x,2);
	}
	
	static public Object arrondi(Object x) {
		Object result = x;
		if(x instanceof Double
				|| x instanceof Float) {
			result = arrondi((Double)x);
		} else if(x instanceof BigDecimal) {
			result = arrondi((BigDecimal)x).doubleValue();
		} 
		return result;
	}

	static public BigDecimal arrondi(BigDecimal x, int puissance) {
		if(x!=null){
			MathContext mathContext = new MathContext(puissance,RoundingMode.HALF_UP);
			Double value=LibConversion.BigDecimalToDouble(x);
			value *= Math.pow(10,puissance);
			long i = Math.round(value);
			value = i/Math.pow(10.0,puissance);
			return BigDecimal.valueOf(value);
		}
		else 
			return x;
	}
	
	static public BigDecimal arrondi(BigDecimal x) {
		return arrondi(x,2);
	}
}

