package ISA.BloodBank.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ISA.BloodBank.model.JwtAuthenticationRequest;
import ISA.BloodBank.model.TokenUtils;
import ISA.BloodBank.model.User;
import ISA.BloodBank.model.UserTokenState;
import ISA.BloodBank.service.ConfirmationTokenService;
import ISA.BloodBank.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthentificationController {

	private TokenUtils tokenUtils;

	private UserService userService;

	private AuthenticationManager authenticationManager;

	private ConfirmationTokenService confirmationTokenService;

	@Autowired
	public AuthentificationController(TokenUtils tokenUtils, UserService userService,
			AuthenticationManager authenticationManager, ConfirmationTokenService confirmationTokenService) {
		super();
		this.tokenUtils = tokenUtils;
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.confirmationTokenService = confirmationTokenService;
	}

	@PreAuthorize("hasAnyRole('ROLE_REGISTERED_USER', 'ROLE_CENTER_ADMINISTRATOR', 'ROLE_SYSTEM_ADMINISTRATOR')")
	@GetMapping
	public ResponseEntity<User> getLoggedInUser() {
		return new ResponseEntity<User>(userService.findLoggedInUser(), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<UserTokenState> login(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {
		try {
	
			User logInUser = userService.login(authenticationRequest);
			StringBuilder passwordWithSalt = new StringBuilder();
			passwordWithSalt.append(authenticationRequest.getPassword());
			passwordWithSalt.append(logInUser.getSalt());

			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), passwordWithSalt.toString()));
			

			SecurityContextHolder.getContext().setAuthentication(authentication);
			User user = (User) authentication.getPrincipal();
			
			if (user.getEnabled()) {
				String jwt = tokenUtils.generateToken(authenticationRequest.getEmail());
				int expiresIn = tokenUtils.getExpiredIn();
				return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, user));
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
