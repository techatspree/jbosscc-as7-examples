package de.akquinet.jbosscc.webservice;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Model
@RequestScoped
public class OrderService {

	@Inject
	private Logger log;

	@Inject
	private OrderServiceClient orderServiceClient;

	private String product;

	public void order() {
		String order = orderServiceClient.order(product);
		log.info("order result =" + order);

		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setDetail(order);
		message.setSummary(order);
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

}
