package com.company.springredditclone.controller;

import com.company.springredditclone.dto.AuthenticationRespone;
import com.company.springredditclone.dto.LoginRequest;
import com.company.springredditclone.dto.RegisterRequest;
import com.company.springredditclone.exception.SpringRedditException;
import com.company.springredditclone.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup (@RequestBody RegisterRequest registerRequest) throws SpringRedditException {
        authService.signup(registerRequest);
        return  new ResponseEntity<>("User Registration Successful",HttpStatus.OK);

    }
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccpunt(@PathVariable String token ) throws SpringRedditException {
      authService.verifyAccount(token);
      return new ResponseEntity<>("Account Activated Successfully",HttpStatus.OK);
    }
    @PostMapping("/login")
    public AuthenticationRespone login(@RequestBody LoginRequest loginRequest) throws SpringRedditException {
      return authService.login(loginRequest);
    }


}
