package de.devsurf.echo.frameworks.rs.service.filter;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.filter.UriConnegFilter;

import com.google.common.collect.Maps;

/**
 * The ContentNegotiationFilter maps calls to a URI like /something.xml to /something and sets
 * the right accept header for the ending xml. Currently this filter is configured to map .json
 * and .xml URI endings.
 */
public class ContentNegotiationFilter extends UriConnegFilter {
	private static final Map<String, MediaType> mappedMediaTypes = new HashMap<>(2);

	static {
		mappedMediaTypes.put("json", MediaType.APPLICATION_JSON_TYPE);
		mappedMediaTypes.put("xml", MediaType.APPLICATION_XML_TYPE);
		mappedMediaTypes.put("b64", new MediaType("application", "base64"));
	}

	public ContentNegotiationFilter() {
		super(mappedMediaTypes, Maps.<String, String>newHashMap());
	}
}
