package fr.careco.aaa.ws.clientsample;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

public class HeaderHandlerResolver implements HandlerResolver {

	public static final int TYPE_B2B = 1;
	public static final int TYPE_B2C = 2;
	
	private int type = 0;
	
	public HeaderHandlerResolver(int type) {
		this.type = type;
	}

	public List<Handler> getHandlerChain(PortInfo portInfo) {
		List<Handler> handlerChain = new ArrayList<Handler>();

		//HeaderHandler hh = new HeaderHandler();
		
		Handler hh = null;
		
		if(type==TYPE_B2B) {
			hh = new HeaderHandler();
		} else if(type==TYPE_B2C) {
			hh = new HeaderHandlerB2C();
		} else {
			hh = new HeaderHandler();
		}
		

		handlerChain.add(hh);

		return handlerChain;
	}
}
