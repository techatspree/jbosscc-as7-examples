package de.akquinet.jbosscc.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/greeting")
public class GreetingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private GreetingBean greetingBean;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String greeting;

		if (name != null && !name.isEmpty()) {
			greeting = greetingBean.sayHelloTo(name);

		} else {
			greeting = greetingBean.sayHello();
		}

		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.println("<head></head>");
		writer.println("<body>");
		writer.println("<h1>" + greeting + "</h1>");
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

}
