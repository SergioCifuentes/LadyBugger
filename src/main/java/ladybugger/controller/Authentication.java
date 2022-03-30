// package ladybugger.controller;

// import java.util.Date;
// import java.util.List;
// import java.util.stream.Collectors;


// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.AuthorityUtils;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import ladybugger.model.Employee;

// @RestController
// public class Authentication {

// 	@PostMapping("user")
// 	public Employee login(@RequestParam("user") String email, @RequestParam("password") String pwd) {
		
// 		String token = getJWTToken(email);
// 		Employee user = new Employee();
// 		user.setEmail(email);
// 		user.setToken(token);		
// 		return user;
		
// 	}

// 	private String getJWTToken(String username) {
// 		String secretKey = "mySecretKey";
// 		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
// 				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
// 		String token = Jwts
// 				.builder()
// 				.setId("softtekJWT")
// 				.setSubject(username)
// 				.claim("authorities",
// 						grantedAuthorities.stream()
// 								.map(GrantedAuthority::getAuthority)
// 								.collect(Collectors.toList()))
// 				.setIssuedAt(new Date(System.currentTimeMillis()))
// 				.setExpiration(new Date(System.currentTimeMillis() + 600000))
// 				.signWith(SignatureAlgorithm.HS512,
// 						secretKey.getBytes()).compact();

// 		return "Bearer " + token;
// 	}
// }