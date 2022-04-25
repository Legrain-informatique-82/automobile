package fr.legrain.careco.webapp;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

@ManagedBean
@ViewScoped 
public class PushMessageBean {
	private String text;
	private String summary;
	private String detail;
	
	public void send() {
		PushContext pushContext = PushContextFactory.getDefault().getPushContext();
		pushContext.push("/notifications", new FacesMessage(summary, detail));
	}

	public String getText() {
		return text;
	}

	public String getSummary() {
		return summary;
	}

	public String getDetail() {
		return detail;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
