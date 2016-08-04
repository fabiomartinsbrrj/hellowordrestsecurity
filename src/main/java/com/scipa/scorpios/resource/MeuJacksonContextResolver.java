package com.scipa.scorpios.resource;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.rpc.encoding.DeserializationContext;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationProblemHandler;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.logging.Logger;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MeuJacksonContextResolver implements ContextResolver<ObjectMapper> {
	private static final Logger logger = Logger.getLogger(MeuJacksonContextResolver.class);

	private ObjectMapper mapper;

	public MeuJacksonContextResolver() {
		logger.info("== My Jackson Context Resolver Creation == ");
		mapper = new ObjectMapper();
		mapper.getDeserializationConfig().addHandler(new DeserializationProblemHandler() {

			public boolean handleUnknownProperty(final DeserializationContext ctxt,
					final JsonDeserializer<?> deserializer, final Object beanOrClass, final String propertyName)
					throws IOException, JsonProcessingException {
				logger.info("Ignoring unknown property....");
				return true;
			}
		});
	}

	@Override
	public ObjectMapper getContext(final Class<?> type) {
		return mapper;
	}

}
