package bzu.gradproj.optivet.backend.config;

import bzu.gradproj.optivet.backend.security.AuthenticationTokenFilter;
import bzu.gradproj.optivet.backend.security.EntryPointUnauthorizedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final EntryPointUnauthorizedHandler unauthorizedHandler;
    private final AuthenticationConfiguration authConfig;
    private final AuthenticationTokenFilter authenticationTokenFilter;

    //This bean provides password encoding using BCrypt, which is a secure way to store passwords.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //The AuthenticationManager is responsible for processing authentication requests.
    // It's configured through the AuthenticationConfiguration.
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfig.getAuthenticationManager();
    }

    //The SecurityFilterChain method configures how HTTP requests are secured.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors() // Enable CORS
                .and()
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless APIs
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless sessions
                .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll() // Allow internal dispatcher requests
                        .requestMatchers(
                                antMatcher("/**/api-docs/**"),
                                antMatcher("/swagger-ui.html"),
                                antMatcher("/swagger-ui/**"),
                                antMatcher("/auth/**"), // Allow authentication endpoints
                                antMatcher("/api/pets/uploads/**") // Allow access to serveImage endpoint

                        ).permitAll()
                        .requestMatchers("/api/clients/**").hasRole("CLIENT") // Restrict client APIs to CLIENT role
                        .requestMatchers("/api/users/**").hasRole("USER")   // Restrict user APIs to USER role
                        .anyRequest().authenticated() // Require authentication for all other endpoints
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler)) // Handle unauthorized access
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*"); // Allow all origins dynamically
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "X-Auth-Token", "Content-Type"));
        configuration.setAllowCredentials(true); // Allow credentials

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOriginPattern("*"); // Allow all origins dynamically
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//        configuration.setAllowCredentials(true); // Allow credentials
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
/*
3. What's Missing for Role-Based Access Control?
To enforce role-based access control, you would typically define specific access rules for different roles. For example:


http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
    authorizationManagerRequestMatcherRegistry
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .requestMatchers("/leader/**").hasRole("TEAM_LEADER")
        .requestMatchers("/developer/**").hasRole("DEVELOPER")
        .anyRequest().authenticated());


4. Next Steps
Ensure that your User entity and UserDetailsServiceImpl are correctly setting up roles.
Consider adjusting the authorizeHttpRequests configuration to include role-based restrictions as needed.
Verify that the AuthenticationTokenFilter is correctly extracting and validating roles from the JWT.
*/

/*
full chat response:

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final EntryPointUnauthorizedHandler unauthorizedHandler;
    private final AuthenticationConfiguration authConfig;
    private final AuthenticationTokenFilter authenticationTokenFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless APIs
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless sessions
                .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll() // Allow internal dispatcher requests
                        .requestMatchers(
                                antMatcher("/**api-docs/**"),
 antMatcher("/swagger-ui.html"),
 antMatcher("/swagger-ui/**"),
 antMatcher("/auth/**") // Allow authentication endpoints
 ).permitAll()
 .requestMatchers("/api/clients/**").hasRole("CLIENT") // Restrict client APIs to CLIENT role
 .requestMatchers("/api/users/**").hasRole("USER")   // Restrict user APIs to USER role
 .anyRequest().authenticated() // Require authentication for all other endpoints
 )
 .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler)) // Handle unauthorized access
 .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
 return http.build();
 }
 }


 */