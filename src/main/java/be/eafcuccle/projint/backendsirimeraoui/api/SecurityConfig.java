package be.eafcuccle.projint.backendsirimeraoui.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${auth0.audience}")
  private String audience;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuer;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    /*
     * This is where we configure the security required for our endpoints and setup
     * our app to serve as
     * an OAuth2 Resource Server, using JWT validation.
     */
    http.authorizeRequests()
        .mvcMatchers("/api/v1/roles/**").hasAuthority("SCOPE_read:roles")
        // .mvcMatchers("/api/roles/**").authenticated()
        .mvcMatchers("/api/v1/addresses/**").hasAuthority("SCOPE_read:addresses")
        .mvcMatchers("/api/v1/airline_companies/**").hasAuthority("SCOPE_read:airline_companies")
        .mvcMatchers("/api/v1/contacts_info/**").hasAuthority("SCOPE_read:contacts_info")
        .mvcMatchers("/api/v1/flight_classes/**").hasAuthority("SCOPE_read:flight_classes")
        .mvcMatchers("/api/v1/airports/**").hasAuthority("SCOPE_read:airports")
        .mvcMatchers("/api/v1/reservations/**").hasAuthority("SCOPE_read:reservations")
        .mvcMatchers("/api/v1/reservations/**").hasAuthority("SCOPE_create:reservations")
        .mvcMatchers("/api/v1/addresses/**").hasAuthority("SCOPE_create:addresses")
        .mvcMatchers("/api/v1/airline_companies/**").hasAuthority("SCOPE_create:airline_companies")
        .mvcMatchers("/api/v1/contacts_info/**").hasAuthority("SCOPE_create:contacts_info")
        .mvcMatchers("/api/v1/flight_classes/**").hasAuthority("SCOPE_create:flight_classes")
        .mvcMatchers("/api/v1/airports/**").hasAuthority("SCOPE_create:airports")
        .and().cors()
        .and().oauth2ResourceServer().jwt();
  }

  @Bean
  JwtDecoder jwtDecoder() {
    NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(issuer);

    OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
    OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

    jwtDecoder.setJwtValidator(withAudience);

    return jwtDecoder;
  }
}