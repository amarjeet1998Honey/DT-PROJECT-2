package com.niit.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ProjectInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//Support for Asynchronous Service...
		@Override
		protected void customizeRegistration(javax.servlet.ServletRegistration.Dynamic registration) {
			
			/*Here we have configured the dispatcherServlet by setting its dispatchOptionsRequest 
			 * to true*/
			registration.setInitParameter("dispatchOptionsRequest", "true");
			
			/*DispatcherServlet can handle two types of Request :-
			 * 1. Synchronous :- Synchronous means u will get immediate response
			 * 2. Asynchronous :- Asynchronous means response will take some time .
			 * 
			 * Here , we are our application supports asynchronous request*/
			registration.setAsyncSupported(true);
		}

		/*DBConfig.class is the class which we have created in backend . Here we have all the
		 database related configuration.
		 
		 HelloWorldConfiguration is the class which we need to create in frontend ,
		 this class is created to do all the configuration of springs.xml 
		 */

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{ProjectConfiguration.class,DBConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{WebSocketConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		//Here we are mapping all the requests with DispatcherServlet
				return new String[]{"/"};
	}


	//This function will do the character encoding for messaging
	 @Override
	    protected Filter[] getServletFilters() {
		 CharacterEncodingFilter filter=new CharacterEncodingFilter();
		 filter.setEncoding(StandardCharsets.UTF_8.name());
	        return new Filter[]{filter};
	    }

}
