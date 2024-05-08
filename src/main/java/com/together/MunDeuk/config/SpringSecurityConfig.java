package com.together.MunDeuk.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.together.MunDeuk.utils.CustomLoginSuccessHandler;
import com.together.MunDeuk.utils.JwtTokenizer;
import com.together.MunDeuk.utils.LoginAuthenticationFilter;
import com.together.MunDeuk.web.OAuth2.service.CustomOAuth2UserService;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcReactiveOAuth2UserService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = {"com.together.MunDeuk.utils"})
public class SpringSecurityConfig extends AbstractHttpConfigurer {
  // todo: oauth2 not use
//  private final AuthenticationConfiguration authenticationConfiguration;
//  private final JwtTokenizer jwtTokenizer;
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
//    http.authenticationManager(authenticationManager);
//
//    http
//        .authorizeHttpRequests(authorizeRequest ->
//                authorizeRequest
//                    .requestMatchers(new AntPathRequestMatcher("/members/**")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/main/**")).hasRole("User")
////                .requestMatchers(new AntPathRequestMatcher("/main/**")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/admin/main/**")).hasRole("Admin")
//                    .requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/loginFail/**")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/h2/**")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/", "/public/**")).permitAll()
//                    .anyRequest().permitAll()
//        )
//        // 필터 변경
//        .addFilterAt(
//            this.abstractAuthenticationProcessingFilter(authenticationManager, jwtTokenizer),
//            UsernamePasswordAuthenticationFilter.class)
//        .headers(
//            headersConfigurer ->
//                headersConfigurer
//                    // SameOrigin Policy
//                    .frameOptions(
//                        FrameOptionsConfig::sameOrigin
//                    )
//                    // CSP
//                    .contentSecurityPolicy(policyConfig ->
//                        policyConfig.policyDirectives(
//                            "script-src 'self'; img-src 'self'; font-src 'self' data:; default-src 'self'; frame-src 'self'"
//                        ).reportOnly()
//                    )
//        )
//////        .formLogin(AbstractHttpConfigurer::disable)
////        .formLogin(login -> login
////                .loginPage("/login")
////                // 기본 redirect url 지정
//////            .defaultSuccessUrl("/main",true)
//////            .permitAll()
////                // successHandler 이용 redirect url 지정
//////                .successHandler(customSuccessHandler())
////        )
////        // CSRF Disable
//////        .csrf(AbstractHttpConfigurer::disable)
//        .csrf(csrf -> csrf.disable());
//
//    return http.build();
//  }
//
//  // 인증 필터
//  public AbstractAuthenticationProcessingFilter abstractAuthenticationProcessingFilter(
////      final AuthenticationManager authenticationManager) {
//      // 토큰 정보 추가
//      final AuthenticationManager authenticationManager, JwtTokenizer jwtTokenizer) {
//    LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter("/ajax/loginProcess", authenticationManager);
////    loginAuthenticationFilter.setAuthenticationSuccessHandler(customSuccessHandler());
//    // Handler에 토큰 정보 추가
//    loginAuthenticationFilter.setAuthenticationSuccessHandler(customSuccessHandler(jwtTokenizer));
//    // Rest API 방식을 사용하기 위해 추가
//    loginAuthenticationFilter.setSecurityContextRepository(
//        new DelegatingSecurityContextRepository(
//            new RequestAttributeSecurityContextRepository(),
//            new HttpSessionSecurityContextRepository()
//        ));
//    return loginAuthenticationFilter;
//  }
//
//  // 로그인 성공시 handler
//  @Bean
//  public AuthenticationSuccessHandler customSuccessHandler(JwtTokenizer jwtTokenizer) {
//    return new CustomLoginSuccessHandler(jwtTokenizer);
//  }
}


//@RequiredArgsConstructor
//@EnableWebFluxSecurity
//@Configuration
//@ComponentScan(basePackages = {"com.together.MunDeuk.utils"})
////webflux로 변경
//public class SpringSecurityConfig extends AbstractHttpConfigurer {
//
//  private final AuthenticationConfiguration authenticationConfiguration;
//  private final JwtTokenizer jwtTokenizer;
//
//  @Bean
//  public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity serverHttpSecurity){
//    serverHttpSecurity
//        .authorizeExchange(authorize -> authorize
//            .anyExchange().authenticated())
//        .exceptionHandling(exceptionHandling -> exceptionHandling
//            .authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint("/login"))) // 로그인 페이지
//        .oauth2Login(oauth2 -> oauth2
////            .authenticationConverter(this.authenticationConverter())
//            .authenticationMatcher(new PathPatternParserServerWebExchangeMatcher("/login/oauth2/callback/{registrationId}")) // 리다이렉션 endpoint
////            .authenticationManager(this.authenticationManager())
////            .authenticationSuccessHandler(this.authenticationSuccessHandler()) // 성공
////            .authenticationFailureHandler(this.authenticationFailureHandler()) // 실패
////            .clientRegistrationRepository(this.clientRegistrationRepository())
////            .authorizedClientRepository(this.authorizedClientRepository())
////            .authorizedClientService(this.authorizedClientService())
//            .authorizationRequestResolver(this.authorizationRequestResolver()) // 로그인 페이지
////            .authorizationRequestRepository(this.authorizationRequestRepository())
////            .securityContextRepository(this.securityContextRepository()))
//        )
////        .oauth2Client(oauth2 -> oauth2
////            .clientRegistrationRepository(this.clientRegistrationReposirtory())
////            .authorizedClientRepository(this.authorizedClientRepository())
////            .authorizationRequestRepository(this.authorizationRequestRepository())
////            .authorizationRequestResolver(this.authorizationRequestResolver())
////            .authenticationConverter(this.authenticationConverter())
////            .authenticationManager(this.authenticationManager()))
////        )
//        .csrf(csrf -> csrf.disable());
//
//    return serverHttpSecurity.build();
//  }
//
//  // 로그인 요청
//  private ServerOAuth2AuthorizationRequestResolver authorizationRequestResolver(){
//    ServerWebExchangeMatcher authorizationRequestMatcher =
//        // registartionId가 OAuth2 제공 서버 구분자
//        new PathPatternParserServerWebExchangeMatcher("/login/oauth2/authorization/{registrationId}");
//    return new DefaultServerOAuth2AuthorizationRequestResolver(
//        this.clientRegistrationRepository(),authorizationRequestMatcher);
//  }
//
//  @Bean
//  // UserInfo에서의 권한 Mapping
//  public ReactiveOAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
//    final OidcReactiveOAuth2UserService delegate = new OidcReactiveOAuth2UserService();
//
//    return (userRequest) -> {
//      // Delegate to the default implementation for loading a user
//      return delegate.loadUser(userRequest)
//          .flatMap((oidcUser) -> {
//            OAuth2AccessToken accessToken = userRequest.getAccessToken();
//            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
//
//            // TODO
//            // 1) Fetch the authority information from the protected resource using accessToken
//            // 2) Map the authority information to one or more GrantedAuthority's and add it to mappedAuthorities
//
//            // 3) Create a copy of oidcUser but use the mappedAuthorities instead
//            oidcUser = new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
//
//            return Mono.just(oidcUser);
//          });
//    };
//  }
//
//  @Bean
//  public ReactiveClientRegistrationRepository clientRegistrationRepository() {
//    return new InMemoryReactiveClientRegistrationRepository(this.googleClientRegistration());
//  }
//
//  private ClientRegistration googleClientRegistration() {
////    return ClientRegistration.withRegistrationId("google")
////        .clientId("google-client-id")
////        .clientSecret("google-client-secret")
////        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
////        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
////        .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
////        .scope("openid", "profile", "email", "address", "phone")
////        .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
////        .tokenUri("https://www.googleapis.com/oauth2/v4/token")
////        .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
////        .userNameAttributeName(IdTokenClaimNames.SUB)
////        .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
////        .clientName("Google")
////        .build();
//    return CommonOAuth2Provider.GOOGLE.getBuilder("google")
//        .clientId("google-client-id")
//        .clientSecret("google-client-secret")
//        .redirectUri("{baseUrl}/login/oauth2/callback/{registrationId}")
//        .build();
//  }
//}
