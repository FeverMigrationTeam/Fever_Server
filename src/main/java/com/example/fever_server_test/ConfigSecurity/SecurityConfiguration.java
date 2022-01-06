package com.example.fever_server_test.ConfigSecurity;

// import 생략

import com.example.fever_server_test.ConfigSecurity.component.CommonEncoder;
import com.example.fever_server_test.service.CustomMemberDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/* 클라에서 api 호출을 하면 Controller에 도달하기 전에 filter를 거침
* 이러한 filter 설정을 아래에 한거임. : Token 기반 인증 절차 구현 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 시큐리티 가능하게
@EnableGlobalMethodSecurity( // 아래와 같은 어노테이션들을 컨트롤러에서 사용할 수 있게 해줌
        securedEnabled = true, // @Secured
        jsr250Enabled = true, // @RolesAllowed
        prePostEnabled = true // @PreAuthorize, @PostAuthorize
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private CustomMemberDetailService customMemberDetailService;
    private CommonEncoder commonEncoder;

//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//                authenticationManagerBuilder
//                .userDetailsService(customUserDetailService)
//                .passwordEncoder(commonEncoder);
//    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
    * Spring Security filter 설정 : HttpSecurity
    * URL에대한 보안걸기 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
                .csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증하므로 세션은 필요없으므로 생성안함.
                .and()
                .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크 , 여기서 부터 호출되는 메소드들의 요청 보안 수준을 설정정                .antMatchers("/test/signin", "/test/signup").permitAll() // 가입 및 인증 주소는 누구나 접근가능
//                    .antMatchers(HttpMethod.GET, "/helloworld/**").permitAll() // hellowworld로 시작하는 GET요청 리소스는 누구나 접근가능
//                .antMatchers(HttpMethod.GET, "/**").authenticated()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Request Header에 accesstoken 실어서 보내는 특별한 상황 허용 : Pre~~
                .antMatchers(HttpMethod.POST,"/sign/**").permitAll() // 로그인 혹은 회원가입에 사용되는 api 들은 허용 ( 해당 단계에서는 토큰을 발급 받을 수 없음 )
//                .antMatchers(HttpMethod.PUT,"/**").permitAll()
//                .antMatchers(HttpMethod.DELETE,"/**").permitAll()
                .anyRequest().authenticated() // 그외 나머지 요청은 모두 인증된 회원만 접근 가능 ( 토큰 기반 )
                .and()
                // 요청을 처리하기 전에 어떤 필터를 거치는지 설정함.
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증 필터 전에 넣는다
    }

    @Override // ignore check swagger resource
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**");

    }
}