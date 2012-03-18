package de.akquinet.jbosscc.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class Client {

	public static void main(String[] args) throws Exception {

		Client client = new Client();

		client.print(client.get(
				"http://localhost:8080/rest-example/rest/user/1",
				MediaType.APPLICATION_JSON_TYPE));

		client.print(client.get(
				"http://localhost:8080/rest-example/rest/user/3",
				MediaType.TEXT_XML_TYPE));

		client.print(client.get("http://localhost:8080/rest-example/rest/user",
				MediaType.TEXT_XML_TYPE));

		// client.delete(
		// "http://localhost:8080/rest-example/rest/user/2",
		// MediaType.TEXT_XML_TYPE);

		// client.put("http://localhost:8080/rest-example/rest/user",
		// MediaType.TEXT_XML , client.convertXMLFileToString("/user.xml"));

		client.post(
				"http://localhost:8080/rest-example/rest/user",
				MediaType.TEXT_XML,
				client.convertXMLFileToString("/update_user.xml"));

	}

	public String print(ClientResponse<String> response) throws Exception {
		String result = null;

		// Check the HTTP status of the request
		// HTTP 200 indicates the request is OK
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed request with HTTP status: "
					+ response.getStatus());
		}

		// We have a good response, let's now read it
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(response.getEntity().getBytes())));

		// Loop over the br in order to print out the contents
		System.out.println("\n*** Response from Server ***\n");
		String output = null;
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			result = output;
		}

		System.out.println("\n===============================================");

		return result;
	}

	public ClientResponse<String> get(String url, MediaType mediaType)
			throws Exception {
		ClientRequest request = new ClientRequest(url);

		request.accept(mediaType);

		ClientResponse<String> response = request.get(String.class);
		return response;
	}

	public ClientResponse<String> delete(String url, MediaType mediaType)
			throws Exception {
		ClientRequest request = new ClientRequest(url);

		request.accept(mediaType);

		ClientResponse response = request.delete();
		return response;
	}

	public ClientResponse<String> put(String url, String contentType,
			Object data) throws Exception {
		ClientRequest request = new ClientRequest(url);

		request.body(contentType, data);
		ClientResponse response = request.put();
		return response;
	}

	public ClientResponse<String> post(String url, String contentType,
			Object data) throws Exception {
		ClientRequest request = new ClientRequest(url);

		request.body(contentType, data);
		ClientResponse response = request.post();
		return response;
	}

	public String convertXMLFileToString(String fileName) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		InputStream inputStream = Client.class.getResourceAsStream(fileName);
		org.w3c.dom.Document doc = documentBuilderFactory.newDocumentBuilder()
				.parse(inputStream);
		StringWriter stw = new StringWriter();
		Transformer serializer = TransformerFactory.newInstance()
				.newTransformer();
		serializer.transform(new DOMSource(doc), new StreamResult(stw));
		return stw.toString();
	}

}
