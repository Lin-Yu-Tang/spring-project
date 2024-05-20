package com.tom.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.tom.security.dto.LoginResponse;
import com.tom.security.dto.LoginResquest;
import com.tom.bean.UserInfoVO;
import com.tom.security.dto.ApiResponseMessage;
import com.tom.security.util.SecurityCipher;
import com.tom.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

//	private AuthenticationManager authenticationManager;
	
//	@Autowired
//	private UserService userService;
	
	@Autowired
	FindByIndexNameSessionRepository sessionRepository;
	@PostMapping("login")
//	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> login(
			@RequestBody LoginResquest loginRequest,
			HttpServletRequest request) {
	//	public ResponseEntity<LoginResponse> login(@CookieValue(name = "accessToken", required = false) String accessToken,
//			@CookieValue(name = "refreshToken", required = false) String refreshToken,
//			@RequestBody LoginResquest loginRequest,
//			HttpServletRequest request) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//		Authentication authentication =
//				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
		
		UserInfoVO userInfo = new UserInfoVO();
        userInfo.setUsername(loginRequest.getUsername());
        userInfo.setOrganization("ntpu");
        userInfo.setLocale("TW");
        
        HttpSession session = request.getSession();
        request.getSession().setAttribute("USER_INFO", userInfo);
        
        
		if (isAlreadyLoggedIn(loginRequest.getUsername())) {
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setError("User Already logged in");
			return ResponseEntity.ok(loginResponse);
		}
//		SecurityContextHolder.getContext().setAuthentication(null);
//		String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
//		String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);
//		return userService.login(loginRequest, decryptedAccessToken, decryptedRefreshToken);
		return ResponseEntity.ok(null);
	}

	@PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> refreshToken(
			@CookieValue(name = "accessToken", required = false) String accessToken,
			@CookieValue(name = "refreshToken", required = false) String refreshToken) {
		String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
		String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);
//		return userService.refresh(decryptedAccessToken, decryptedRefreshToken);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("basic")
	public UserInfoVO getSession(HttpServletRequest request) {
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		
		UserInfoVO attribute = (UserInfoVO)requestAttributes.getAttribute("USER_INFO", 0);
		String sessionId = requestAttributes.getSessionId();
		
		UserInfoVO userInfo = (UserInfoVO)request
								.getSession()
								.getAttribute("USER_INFO");
		System.out.println(userInfo);
		return userInfo;
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logOut(HttpServletRequest request, HttpServletResponse response) {
//		return new ResponseEntity(new ApiResponseMessage(true, userService.logout(request, response)), HttpStatus.OK);
		
		return ResponseEntity.ok(null);

	}

	private Boolean isAlreadyLoggedIn(String pricipalName) {

		Map user = sessionRepository
				.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, pricipalName);
		return user.size() > 0;
	}
}