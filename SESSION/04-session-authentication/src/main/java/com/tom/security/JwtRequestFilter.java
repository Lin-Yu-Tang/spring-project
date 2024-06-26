package com.tom.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import com.tom.security.util.SecurityCipher;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Value("${authentication-test.auth.accessTokenCookieName}")
	private String accessTokenCookieName;
	@Value("${authentication-test.auth.refreshTokenCookieName}")
	private String refreshTokenCookieName;
	@Autowired
	private MyUserDetails jwtUserDetailsService;

	private final JwtTokenProvider jwtTokenUtil;

	public JwtRequestFilter(JwtTokenProvider jwtTokenUtil) {
		this.jwtTokenUtil = jwtTokenUtil;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		try {
			String jwt = getJwtToken(httpServletRequest, true);
			if (StringUtils.hasText(jwt) && jwtTokenUtil.validateToken(jwt)) {
				String username = jwtTokenUtil.getUsername(jwt);
				UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			String accessToken = bearerToken.substring(7);
			if (accessToken == null)
				return null;

			return SecurityCipher.decrypt(accessToken);
		}
		return null;
	}

	private String getJwtFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return "";
		for (Cookie cookie : cookies) {
			if (accessTokenCookieName.equals(cookie.getName())) {
				String accessToken = cookie.getValue();
				if (accessToken == null)
					return null;

				return SecurityCipher.decrypt(accessToken);
			}
		}
		return null;
	}

	private String getJwtToken(HttpServletRequest request, boolean fromCookie) {
		if (fromCookie)
			return getJwtFromCookie(request);

		return getJwtFromRequest(request);
	}

}
