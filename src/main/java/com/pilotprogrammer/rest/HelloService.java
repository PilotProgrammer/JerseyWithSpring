package com.pilotprogrammer.rest;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pilotprogrammer.beans.MyXmlConfigBean;

@Path("") // important to note that even if no path, still need "@PATH"
public class HelloService {

	protected MyXmlConfigBean myAnnotationConfigBean;
	
	public HelloService(final @Context ServletContext servletContext) {
		final ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		myAnnotationConfigBean = context.getBean(MyXmlConfigBean.class);
	}
	
	@GET
	@Path("/hello")
	public Response hello() throws IOException {
		String hello = "Hello at " + new Date() + "!";
		return Response.status(200).entity(hello).type(MediaType.TEXT_PLAIN).build();
	}
	
	@POST
	@Path("/helloAgain")
	@Produces(MediaType.TEXT_PLAIN)
	public Response helloAgain1() throws IOException {
		String hello = "Hello again, produces text, at " + new Date() + "!";
		return Response.status(200).entity(hello).build();
	}
	
	@POST
	@Path("/helloAgain")
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloAgain2() throws IOException {
		String hello = "Hello again, produces json, at " + new Date() + "!";
		String msg = "{ \"hello\": \"" + hello + "\" }";
		return Response.status(200).entity(msg).build();
	}
		
	@GET
	@Path("/springTest")
	@Produces(MediaType.TEXT_PLAIN)
	public Response springTest() throws IOException {
		String hello = myAnnotationConfigBean.sayHello("Felix", "the Cat");
		return Response.status(200).entity(hello).build();
	}
}