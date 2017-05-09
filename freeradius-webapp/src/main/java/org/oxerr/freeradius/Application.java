package org.oxerr.freeradius;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharingFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oxerr.commons.ws.rs.exceptionmapper.IllegalArgumentExceptionMapper;
import org.oxerr.commons.ws.rs.provider.InstantProvider;
import org.oxerr.commons.ws.rs.provider.OffsetDateTimeProvider;
import org.oxerr.jackson.module.jsr250.Jsr250Module;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class Application {

	private final Logger log = LogManager.getLogger();

	/**
	 * Writes the request URI (and optionally the query string) to the Commons Log.
	 *
	 * @return the filter registration bean for the {@link CommonsRequestLoggingFilter}.
	 */
	@Bean
	public FilterRegistrationBean commonsRequestLoggingFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CommonsRequestLoggingFilter requestLoggingFilter = new CommonsRequestLoggingFilter();
		registrationBean.setFilter(requestLoggingFilter);
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registrationBean;
	}

	@Bean
	public ObjectMapper objectMapper() {
		final ObjectMapper mapper = new ObjectMapper();

		mapper.registerModule(new JaxbAnnotationModule());
		mapper.registerModule(new JavaTimeModule());

		final Hibernate5Module hibernate5Module = new Hibernate5Module();
		hibernate5Module.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
		hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
		mapper.registerModule(hibernate5Module);

		mapper.registerModule(new Jsr250Module());

		mapper.setSerializationInclusion(Include.NON_ABSENT);
		return mapper;
	}

	@Bean
	public CrossOriginResourceSharingFilter crossOriginResourceSharingFilter() {
		final CrossOriginResourceSharingFilter corsf = new CrossOriginResourceSharingFilter();
		final List<String> exposeHeaders = new ArrayList<>(corsf.getExposeHeaders());
		exposeHeaders.add("X-Auth-Token");
		log.debug("exposeHeaders: {}", String.join(", ", exposeHeaders));
		corsf.setExposeHeaders(exposeHeaders);
		return corsf;
	}

	@Bean
	public JacksonJsonProvider jacksonJsonProvider(ObjectMapper objectMapper) {
		return new JacksonJsonProvider(objectMapper);
	}

	@Bean
	public InstantProvider instantProvider() {
		return new InstantProvider();
	}

	@Bean
	public OffsetDateTimeProvider offsetDateTimeProvider() {
		return new OffsetDateTimeProvider(ZoneOffset.UTC);
	}

	@Bean
	public IllegalArgumentExceptionMapper illegalArgumentExceptionMapper() {
		return new IllegalArgumentExceptionMapper();
	}

}
