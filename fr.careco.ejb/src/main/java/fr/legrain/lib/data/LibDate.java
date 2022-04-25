package fr.legrain.lib.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LibDate {
	
	private static long C_DATE_DEFAUT = 0;
	private static String mois;
	private static String jour;
	private static String annee;
	private static String heure;
	private static String minute;
	private static String seconde;
	private static String millis;
	
	
	static public Date getDateDefaut() {
		return new Date(C_DATE_DEFAUT);
	}
	
	static public Date getDateLGR(Date date) {
//		Calendar d =Calendar.getInstance();
//		d.setTime(date);
//		String newDate =String.valueOf(d.get(d.YEAR))+"/"+String.valueOf(d.get(d.MONTH)+1)+"/"+String.valueOf(d.get(d.DAY_OF_MONTH)); 
////		d.set(d.get(d.YEAR), d.get(d.MONTH), d.get(d.DAY_OF_MONTH),0,0,0);
////		return d.getTime();
		return getDateLGR(date,"/");
	}
	static public Date getDateLGR(Date date,String separateur) {
		Calendar d =Calendar.getInstance();
		d.setTime(date);
		String newDate =String.valueOf(d.get(Calendar.YEAR))+separateur+String.valueOf(d.get(Calendar.MONTH)+1)+separateur+String.valueOf(d.get(Calendar.DAY_OF_MONTH)); 
//		d.set(d.get(d.YEAR), d.get(d.MONTH), d.get(d.DAY_OF_MONTH),0,0,0);
//		return d.getTime();
		return new Date(newDate);
	}
	/**
	 * 
	 * @param date
	 * @param anotherDate
	 * @return 0 si date égale, -1 si date < anotherDate et 
	 * 1 si date > anotherDate
	 */
	
	static public int compareTo(Date date,Date anotherDate) {
    	int result = -1;
    	if(date==null) date = new Date();
    	if(anotherDate==null) anotherDate = new Date();
		Calendar d =Calendar.getInstance();
		d.setTime(date);
		Calendar d2 =Calendar.getInstance();
		d2.setTime( anotherDate);
		System.out.println("Date : "+String.valueOf( d.get(Calendar.DATE)));
		System.out.println("Date : "+String.valueOf( d.get(Calendar.MONTH)));
		System.out.println("Date : "+String.valueOf( d.get(Calendar.DAY_OF_MONTH)));
		System.out.println("Date : "+String.valueOf( d.get(Calendar.YEAR)));
		if(d.get(Calendar.YEAR)==d2.get(Calendar.YEAR)) {
			if(d.get(Calendar.DAY_OF_YEAR)==d2.get(Calendar.DAY_OF_YEAR))
				return 0;
			else if(d.get(Calendar.DAY_OF_YEAR)<d2.get(Calendar.DAY_OF_YEAR))
				return -1;
			else if(d.get(Calendar.DAY_OF_YEAR)>d2.get(Calendar.DAY_OF_YEAR)) return 1;
		} else 
			if(d.get(Calendar.YEAR)<d2.get(Calendar.YEAR)) {
				return -1;
			}else return 1;

		
		
		return result;
	}

	static public int compareTo(String date,String anotherDate) {
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = stringToDate(date);
			d2 = stringToDate(anotherDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return compareTo(d1, d2);
	}	
	
	static public int compareTo(String date,Date anotherDate) {
		Date d1 = null;
		try {
			d1 = stringToDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return compareTo(d1, anotherDate);
	}	
	
	
	
	static public int compareTo(Date date, String anotherDate) {
		Date d2 = null;
		try {
			d2 = stringToDate(anotherDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return compareTo(date, d2);
	}
	
	/**
	 * 
	 * @param d
	 * @param separateur
	 * @return jj/MM/aaaa
	 */
	static public String dateToString(Date d,String separateur) {
		Calendar date = Calendar.getInstance();
		date.setTime(d);
		//TODO Gestion des symbols
		jour=String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		mois=String.valueOf(date.get(Calendar.MONTH)+1);
		annee=String.valueOf(date.get(Calendar.YEAR));
		
		while (mois.length()<2)
			mois="0"+mois;
		
		while (jour.length()<2)
			jour="0"+jour;
		while (annee.length()<4)
			annee="0"+annee;

		return jour+separateur+mois+separateur+annee;
	}
	
	static public String dateToStringTimeStamp(Date d,String separateurDate,String separateurDateTime, String separateurTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(d);
		
		String dateString = dateToString(d,separateurDate);
		heure=String.valueOf(date.get(Calendar.HOUR_OF_DAY));
		minute=String.valueOf(date.get(Calendar.MINUTE));
		seconde=String.valueOf(date.get(Calendar.SECOND));
		millis=String.valueOf(date.get(Calendar.MILLISECOND));
		
		//return dateString+separateurDateTime+heure+separateurTime+minute+separateurTime+seconde;
		return dateString+separateurDateTime+heure+separateurTime+minute+separateurTime+seconde+separateurTime+millis;
	}
	
	/**
	 * 
	 * @param d
	 * @param separateur
	 * @return jj/MM/aaaa
	 */
	static public String dateToString(Date d) {
		return dateToString(d,"/");
	}
	/**
	 * 
	 * @param d
	 * @return jj/MM/aaaa
	 */
	static public String dateToStringAA(Date d) {
		if(d!=null){
		String[] res = dateToString(d).split("/");
		res[2] = res[2].substring(2,4);
		return res[0]+"/"+res[1]+"/"+res[2];
		}
		return null;
	}
	

	static public String dateToStringSql(Date d) {
		Calendar date =Calendar.getInstance();
		date.setTime(d);
		
		jour=String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		mois=String.valueOf(date.get(Calendar.MONTH)+1);
		annee=String.valueOf(date.get(Calendar.YEAR));
		
		while (mois.length()<2)
			mois="0"+mois;
		
		while (jour.length()<2)
			jour="0"+jour;
		
		return annee+"/"+mois+"/"+jour;
	}
	
	static public String StringDateToStringSql(String dateValeur, Date defaut) throws ParseException {
			String jourLoc,moisLoc,anneeLoc;
			String dateTmp;
			String[] part,tmp;
			if(dateValeur!=null && !dateValeur.equals("")) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				df.setCalendar(Calendar.getInstance());
				df.getCalendar().setTime(defaut);
				
				part = dateValeur.split("/"); 
				jourLoc="";
				moisLoc="";
				anneeLoc="";
				if (part.length>0) jourLoc=part[0];
				if (part.length>1) moisLoc=part[1];
				if (part.length>2) anneeLoc=part[2];
				
				tmp=dateToString(defaut).split("/");
				jourLoc=jourLoc.replaceAll("-","");
				moisLoc=moisLoc.replaceAll("-","");
				anneeLoc=anneeLoc.replaceAll("-","");
				if (jourLoc.equals("") || jourLoc.equals("0")){
					jourLoc =tmp[0]; 
				}
				if (moisLoc.equals("")|| moisLoc.equals("0")){
					moisLoc = tmp[1];
				}
				if (anneeLoc.equals("")|| anneeLoc.equals("0")){
					anneeLoc = tmp[2];
				}
				while (moisLoc.length()<2)
					moisLoc="0"+moisLoc;
				
				while (jourLoc.length()<2)
					jourLoc="0"+jourLoc;
				
				while (anneeLoc.length()<4)
					if (anneeLoc.length()<2)
					anneeLoc="0"+anneeLoc;
					else if (anneeLoc.length()<3)
						anneeLoc="0"+anneeLoc;
						else anneeLoc="2"+anneeLoc;

					dateTmp = moisLoc+"/"+jourLoc+"/"+anneeLoc;
			} else {
				dateTmp = dateToString(defaut);
			}
			return dateTmp;
		}

	
	static public Date stringToDate(String date) throws ParseException {
		return stringToDate(date,new Date());
	}
	
	static public Date stringToDate2(String date)  {
		try {
			return stringToDate(date,new Date());
		} catch (ParseException e) {
			return new Date();
		}
	}
	static public Date stringToDate_old(String date) throws ParseException {
		return stringToDate_old(date,new Date());
	}
	
	static public Date stringToDate_old(String date, Date defaut) throws ParseException {
		if(date!=null && !date.equals("")) {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy  ");

			return new Date(df.parse(date).getTime());
		} else {
			return defaut;
		}
	}
	static public Date stringToDateNew(String date){
		//DateFormat df = DateFormat.getDateInstance( DateFormat.FULL , Locale.FRANCE );	
		try {
			return LibDate.stringToDate(date.replace(".", "/"));
		} catch (ParseException e) {
			return null;
		}
	}
	
	static public Date stringToDate(String dateValeur, Date defaut) throws ParseException {
		String jourLoc,moisLoc,anneeLoc;
		Date dateTmp;
		String[] part,tmp;
		if(dateValeur!=null && !dateValeur.equals("")) {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			df.setCalendar(Calendar.getInstance());
			df.getCalendar().setTime(defaut);
			
			part = dateValeur.split("/"); 
			jourLoc="";
			moisLoc="";
			anneeLoc="";
			if (part.length>0) jourLoc=part[0];
			if (part.length>1) moisLoc=part[1];
			if (part.length>2) anneeLoc=part[2];
			
			tmp=dateToString(defaut).split("/");
			jourLoc=jourLoc.replaceAll("-","");
			moisLoc=moisLoc.replaceAll("-","");
			anneeLoc=anneeLoc.replaceAll("-","");
			if (jourLoc.equals("") || jourLoc.equals("0")){
				jourLoc =tmp[0]; 
			}
			if (moisLoc.equals("")|| moisLoc.equals("0")){
				moisLoc = tmp[1];
			}
			if (anneeLoc.equals("")|| anneeLoc.equals("0")){
				anneeLoc = tmp[2];
			}
			while (moisLoc.length()<2)
				moisLoc="0"+moisLoc;
			
			while (jourLoc.length()<2)
				jourLoc="0"+jourLoc;
			
			while (anneeLoc.length()<4)
				if (anneeLoc.length()<2)
				anneeLoc="0"+anneeLoc;
				else if (anneeLoc.length()<3)
					anneeLoc="0"+anneeLoc;
					else anneeLoc="2"+anneeLoc;

				dateTmp = new Date(df.parse(jourLoc+"/"+moisLoc+"/"+anneeLoc).getTime());
		} else {
			dateTmp = defaut;
		}
		return dateTmp;
	}

//	static public Date stringToDate(String dateValeur, Date defaut) throws ParseException {
//		Calendar d =Calendar.getInstance();
//		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");		
//		d.setTime(new Date(dateValeur));
//		return  df.parse(d.get(d.DATE)+"/"+d.get(d.MONTH)+"/"+ d.get(d.YEAR));
//	}
	
	
	static public Date stringToDateSql(String date) throws ParseException {
		return stringToDateSql(date,new Date(C_DATE_DEFAUT));
	}
	static public Date stringToDateSql(String date, Date defaut) throws ParseException {
		if(date!=null && !date.equals("")) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			return new Date(df.parse(date).getTime());
		} else {
			return defaut;
		}
	}
	
	
	public static String getAnnee(Date d) {
		Calendar date =Calendar.getInstance();
		date.setTime(d);
		annee=String.valueOf(date.get(Calendar.YEAR));
		return annee;
	}
	
	public static String getMois(Date d) {
		Calendar date =Calendar.getInstance();
		date.setTime(d);
		mois=String.valueOf(date.get(Calendar.MONTH)+1);		
		while (mois.length()<2)
			mois="0"+mois;
		return mois;
	}

	public static String getJour(Date d) {
		Calendar date =Calendar.getInstance();
		date.setTime(d);
		jour=String.valueOf(date.get(Calendar.DAY_OF_MONTH));		
		while (jour.length()<2)
			jour="0"+jour;
		return jour;
	}


	public static String getHeure(Date d) {
		Calendar date =Calendar.getInstance();
		date.setTime(d);
		heure=String.valueOf(date.get(Calendar.HOUR_OF_DAY));
		return heure;
	}

	public static String getMinute(Date d) {
		Calendar date =Calendar.getInstance();
		date.setTime(d);
		minute=String.valueOf(date.get(Calendar.MINUTE));
		return minute;
	}


	public static String getSeconde(Date d) {
		Calendar date =Calendar.getInstance();
		date.setTime(d);
		seconde=String.valueOf(date.get(Calendar.SECOND));
		return seconde;
	}
	
	public static String getMilliseconde(Date d) {
		Calendar date =Calendar.getInstance();
		date.setTime(d);
		millis=String.valueOf(date.get(Calendar.MILLISECOND));
		return millis;
	}
	
	
	public static Date incrementDate(Date date,int incJour,int incMois,int incAnnee){
		int jour=LibConversion.stringToInteger(LibDate.getJour(date));
		int mois=LibConversion.stringToInteger(LibDate.getMois(date));
		int annee=LibConversion.stringToInteger(LibDate.getAnnee(date));
		String dateTemp =LibConversion.integerToString(mois+incMois)+"/"
		  +LibConversion.integerToString(jour+incJour)+"/"+LibConversion.integerToString(annee+incAnnee);

		return new Date(dateTemp);
	}
	
}



