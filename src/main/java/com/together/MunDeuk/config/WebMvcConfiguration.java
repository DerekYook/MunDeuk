package com.together.MunDeuk.config;
/*
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import net.rakugakibox.util.YamlResourceBundle;
import org.apache.catalina.Context;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.apache.tomcat.util.scan.StandardJarScanFilter;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.http.CacheControl;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.SimpleSpringPreparerFactory;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	

	
	
	@Profile("local")
	@Configuration
	public static class LocalMvcConfiguration implements WebMvcConfigurer {

		@Value("${myproject.version}")
		private String appVersion;

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/").setViewName("forward:/index");
			// 우선순위를 가장 높게 잡는다. 
			registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		}

		//xss 방어 폼데이터
		@Bean
		public FilterRegistrationBean getFilterRegistrationBean() {
			FilterRegistrationBean registrationBean = new FilterRegistrationBean();
			registrationBean.setFilter(new XssEscapeServletFilter());
			registrationBean.setOrder(1);
			registrationBean.addUrlPatterns("*.do", "/*"); //filter를 거칠 url patterns
			return registrationBean;
		}
		//Request Body JSON
		//xss 방어 json 데이터
		@Bean
		public FilterRegistrationBean getRequestBodyXSSFileterRegistrationBean() {
			FilterRegistrationBean registrationBean = new FilterRegistrationBean();
			registrationBean.setFilter(new RequestBodyXSSFileter());
			// registrationBean.setOrder(1);
			//특정 url
			registrationBean.addUrlPatterns("*.do", "/*");
			return registrationBean;
		}
		
//		@Bean
//		    public FilterRegistrationBean registerMultipartFilter() {
//		    FilterRegistrationBean reg = new FilterRegistrationBean<>(new MultipartFilter());
//		    reg.setFilter(new RequestBodyXSSFileter());
//		    reg.setOrder(2);
//		    reg.addUrlPatterns("*.do", "/*"); //filter를 거칠 url patterns
//		    return reg;
//		}
		
		

		@Bean
		public ServletContextInitializer clearJsession() {
			return new ServletContextInitializer() {
				@Override
				public void onStartup(ServletContext servletContext) throws ServletException {
					servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
					SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
					sessionCookieConfig.setHttpOnly(true);
					//sessionCookieConfig.setSecure(true);
				}
			};
		}

		@Bean
		public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
			return new ResourceUrlEncodingFilter();
		}

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {

			registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").setCachePeriod(-1)
					//.setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
					//.setCacheControl(CacheControl.maxAge(24, TimeUnit.HOURS))
					.resourceChain(true).addResolver(new VersionResourceResolver().addFixedVersionStrategy(this.appVersion, "/**"))

			//.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));            	
			;

			//registry.addResourceHandler("/ckeditor/**").addResourceLocations("classpath:/static/ckeditor/").setCachePeriod(20);
			//registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(20);
			//registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/font/").setCachePeriod(20);
			//registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/").setCachePeriod(20);
			//registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(20);
			//registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static//favicon.ico").setCachePeriod(20);
			registry.addResourceHandler("/upload/**").addResourceLocations("file:///D:/app2/sse/upload/");

			//registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS); 
		}

		//Tiles View set1
		@Bean
		public TilesConfigurer tilesConfigurer() {
			final TilesConfigurer configurer = new TilesConfigurer();
			configurer.setDefinitions(new String[] { "/WEB-INF/tiles/tiles-definition.xml" });
			configurer.setCheckRefresh(true);

			// menuPreparer에서 Autowired를 사용
			configurer.setPreparerFactoryClass(SimpleSpringPreparerFactory.class);

			return configurer;
		}

		//Tiles View set2
		@Bean
		public TilesViewResolver tilesViewResolver() {
			final TilesViewResolver tilesViewResolver = new TilesViewResolver();
			tilesViewResolver.setViewClass(TilesView.class);
			tilesViewResolver.setOrder(1); //뷰 우선순위
			return tilesViewResolver;
		}
  
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowCredentials(false)
					.allowedOrigins("https://dev-live-kor.samsungagm.com","https://dev-live-eng.samsungagm.com","https://samsungagm.com","http://localhost:8750", "http://192.168.0.14:8750", "http://192.168.0.91:8750", "http://192.168.0.172:8750")
					//.allowedOrigins("*")
					.maxAge(3600)
			//.allowedMethods("*")
			.allowedHeaders("*")
			.allowCredentials(true)
			;
		}

		@Bean
		MappingJackson2JsonView jsonView() {
			return new MappingJackson2JsonView();
		}

		@Bean // 세션에 지역설정. default는 KOREAN = 'ko'
		public LocaleResolver localeResolver() {
			//    SessionLocaleResolver slr = new SessionLocaleResolver();
			//     slr.setDefaultLocale(Locale.KOREAN);

			CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
			cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
			cookieLocaleResolver.setCookieName("SITE_LANG");
			cookieLocaleResolver.setCookieHttpOnly(true);

			return cookieLocaleResolver;
		}

		@Bean // 지역설정을 변경하는 인터셉터. 요청시 파라미터에 lang 정보를 지정하면 언어가 변경됨.
		public LocaleChangeInterceptor localeChangeInterceptor() {
			LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
			lci.setParamName("lang");
		
			return lci;
		}

		@Override // 인터셉터를 시스템 레지스트리에 등록
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(localeChangeInterceptor());
		}

		@Bean // yml 파일을 참조하는 MessageSource 선언
		public MessageSource messageSource(@Value("${spring.messages.basename}") String basenames, @Value("${spring.messages.encoding}") String encoding) {
			YamlMessageSource ms = new YamlMessageSource();
			//ms.setBasename(basename);
			ms.setBasenames(basenames.split(","));
			ms.setDefaultEncoding(encoding);
			ms.setAlwaysUseMessageFormat(true);
			ms.setUseCodeAsDefaultMessage(true);
			ms.setFallbackToSystemLocale(true);
			return ms;
		}

		// locale 정보에 따라 다른 yml 파일을 읽도록 처리
		private static class YamlMessageSource extends ResourceBundleMessageSource {
			@Override
			protected ResourceBundle doGetBundle(String basename, Locale locale) {
				return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
			}
		}

		@Bean
		public TomcatContextCustomizer sameSiteCookiesConfig() {
			return context -> {
				final LegacyCookieProcessor cookieProcessor = new LegacyCookieProcessor();
				//final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
				cookieProcessor.setSameSiteCookies(SameSiteCookies.LAX.getValue());
				context.setCookieProcessor(cookieProcessor);
			};
		}

		@Bean
		public TomcatServletWebServerFactory tomcatFactory() {
			return new TomcatServletWebServerFactory() {
				@Override
				protected void postProcessContext(Context context) {
					//((StandardJarScanner) context.getJarScanner()).setScanManifest(false); // totally skip!  
					Set<String> pattern = new LinkedHashSet<>();
					pattern.add("batik*.jar");
					pattern.add("xercesImpl.jar");
					pattern.add("xml-apis.jar");
					StandardJarScanFilter filter = new StandardJarScanFilter();
					filter.setTldSkip(StringUtils.collectionToCommaDelimitedString(pattern));
					((StandardJarScanner) context.getJarScanner()).setJarScanFilter(filter);
				}
			};
		}
	}

	@Profile("prod")
	@Configuration
	public static class ProdMvcConfiguration implements WebMvcConfigurer {

		@Value("${myproject.version}")
		private String appVersion;
		
		@Value("${path.config}")
		private String pathConfig;
		
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/").setViewName("forward:/index");

			// 우선순위를 가장 높게 잡는다. 
			registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

		}

//		@Bean
//		public DispatcherServlet dispatcherServlet() {
//			DispatcherServlet ds = new DispatcherServlet();
//			ds.setThrowExceptionIfNoHandlerFound(true);
//			return ds;
//		}
//
//		@Bean
//		public ConfigurableServletWebServerFactory webServerFactory2() {
//			TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//			factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error"));
//			return factory;
//
//		}
//
//		@Bean
//		public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
//			TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//			factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error"));
//			DispatcherServlet ds = new DispatcherServlet();
//			ds.setThrowExceptionIfNoHandlerFound(true);
//			return factory;
//		}

		//xss 방어 폼데이터
		@Bean
		public FilterRegistrationBean getFilterRegistrationBean() {
			FilterRegistrationBean registrationBean = new FilterRegistrationBean();
			registrationBean.setFilter(new XssEscapeServletFilter());
			registrationBean.setOrder(1);
			registrationBean.addUrlPatterns("*.do", "/*"); //filter를 거칠 url patterns

			return registrationBean;
		}

		//Request Body JSON  
		//xss 방어 json 데이터
		@Bean
		public FilterRegistrationBean getRequestBodyXSSFileterRegistrationBean() {
			FilterRegistrationBean registrationBean = new FilterRegistrationBean();
			registrationBean.setFilter(new RequestBodyXSSFileter());
			//특정 url
			registrationBean.addUrlPatterns("*.do", "/*");
			return registrationBean;
		}

		@Bean
		public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
			return new ResourceUrlEncodingFilter();
		}

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
					//.setCacheControl(CacheControl.maxAge(24, TimeUnit.HOURS))
					.resourceChain(true).addResolver(new VersionResourceResolver().addFixedVersionStrategy(this.appVersion, "/**"));
			//.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
	//		registry.addResourceHandler("/upload/**").addResourceLocations("file:/home/centos/ssp/data/upload/");
			registry.addResourceHandler("/upload/**").addResourceLocations(this.pathConfig);
			//registry.addResourceHandler("/well-known/**").addResourceLocations("file:/home/emodoom/service/onofflink/ssp/well-known/");
			//registry.addResourceHandler("/.well-known/**").addResourceLocations("file:/home/emodoom/service/onofflink/ssp/well-known/");
		}

		//Tiles View set1
		@Bean
		public TilesConfigurer tilesConfigurer() {
			final TilesConfigurer configurer = new TilesConfigurer();
			configurer.setDefinitions(new String[] { "/WEB-INF/tiles/tiles-definition.xml" });
			configurer.setCheckRefresh(true);

			// menuPreparer에서 Autowired를 사용
			configurer.setPreparerFactoryClass(SimpleSpringPreparerFactory.class);

			return configurer;
		}

		//Tiles View set2
		@Bean
		public TilesViewResolver tilesViewResolver() {
			final TilesViewResolver tilesViewResolver = new TilesViewResolver();
			tilesViewResolver.setViewClass(TilesView.class);
			tilesViewResolver.setOrder(1); //뷰 우선순위
			return tilesViewResolver;
		}

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowCredentials(true).allowedOrigins(
					"https://onofflink.agmkorea.com", "https://samsungcnt.agmkorea.com", "https://admin.agmkorea.com"
					,"https://samsunglife.agmkorea.com"

					,"https://samsunglife.agmkorea.com"
					,"https://samsungcnt.agmkorea.com"
					,"https://samsungengineering.agmkorea.com"
					,"https://samsungpop.agmkorea.com"
					,"https://samsungbiologics.agmkorea.com"
					,"https://mobis.agmkorea.com"//2023-02-13이건우 추가
					,"https://mobiis.agmkorea.com"//2023-02-13이건우 추가
					,"https://s1.agmkorea.com"//2023-02-13이건우 추가 
					,"https://lg.agmkorea.com"//2023-02-16이건우 추가  
					,"https://skc.agmkorea.com"//2023-02-27이건우 추가

					,"https://onofflink.mobilecen.co.kr"
					,"http://onofflink.mobilecen.co.kr"

					,"https://onofflink.emodoom.com","https://dev-www.samsungagm.com", "https://stg-www.samsungagm.com", "https://stg-admin.samsungagm.com", "https://www.samsungagm.com", "https://admin.samsungagm.com").maxAge(3600)
				.allowedMethods("POST","GET")
			//.allowedMethods("*")
			//.allowedHeaders("*")
			;
		} 

		@Bean
		MappingJackson2JsonView jsonView() {
			return new MappingJackson2JsonView();
		}

		@Bean // 세션에 지역설정. default는 KOREAN = 'ko'
		public LocaleResolver localeResolver() {
			//    SessionLocaleResolver slr = new SessionLocaleResolver();
			//     slr.setDefaultLocale(Locale.KOREAN);

			CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
			cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
			cookieLocaleResolver.setCookieName("SITE_LANG");
			cookieLocaleResolver.setCookieHttpOnly(true);

			return cookieLocaleResolver;
		}

		@Bean // 지역설정을 변경하는 인터셉터. 요청시 파라미터에 lang 정보를 지정하면 언어가 변경됨.
		public LocaleChangeInterceptor localeChangeInterceptor() {
			LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
			lci.setParamName("lang");
		
			return lci;
		}

		@Override // 인터셉터를 시스템 레지스트리에 등록
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(localeChangeInterceptor());
		}

		@Bean // yml 파일을 참조하는 MessageSource 선언
		public MessageSource messageSource(@Value("${spring.messages.basename}") String basenames, @Value("${spring.messages.encoding}") String encoding) {
			YamlMessageSource ms = new YamlMessageSource();
			//ms.setBasename(basename);
			ms.setBasenames(basenames.split(","));
			ms.setDefaultEncoding(encoding);
			ms.setAlwaysUseMessageFormat(true);
			ms.setUseCodeAsDefaultMessage(true);
			ms.setFallbackToSystemLocale(true);
			return ms;
		}

		// locale 정보에 따라 다른 yml 파일을 읽도록 처리
		private static class YamlMessageSource extends ResourceBundleMessageSource {
			@Override
			protected ResourceBundle doGetBundle(String basename, Locale locale) {
				return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
			}
		}

		@Bean
		public TomcatContextCustomizer sameSiteCookiesConfig() {
			return context -> {
				LegacyCookieProcessor cookieProcessor = new LegacyCookieProcessor();
				//final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
				cookieProcessor.setSameSiteCookies(SameSiteCookies.LAX.getValue());
				context.setCookieProcessor(cookieProcessor);
			};
		}

		@Bean
		public TomcatServletWebServerFactory tomcatFactory() {
			return new TomcatServletWebServerFactory() {
				@Override
				protected void postProcessContext(Context context) {
					//((StandardJarScanner) context.getJarScanner()).setScanManifest(false); // totally skip!  
					Set<String> pattern = new LinkedHashSet<>();
					pattern.add("batik*.jar");
					pattern.add("xercesImpl.jar");
					pattern.add("xml-apis.jar");
					StandardJarScanFilter filter = new StandardJarScanFilter();
					filter.setTldSkip(StringUtils.collectionToCommaDelimitedString(pattern));
					((StandardJarScanner) context.getJarScanner()).setJarScanFilter(filter);
				}
			};
		}
	}

}
*/