package com.niit.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages="com.niit")
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chatmodule").withSockJS();
		
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// /queue/ - destinationPrefix - send chat msg from server to client[Chat]
		// /topic/ - destinationPrefix - send user name to all clients[String]
		registry.enableSimpleBroker("/queue/","/topic/");
		//for browser - to send messages from browser to server 
		registry.setApplicationDestinationPrefixes("/myApp");
	}

	
	
}
