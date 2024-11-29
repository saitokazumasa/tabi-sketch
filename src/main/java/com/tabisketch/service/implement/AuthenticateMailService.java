package com.tabisketch.service.implement;

import com.tabisketch.service.IAuthenticateMailService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateMailService implements IAuthenticateMailService {
    @Override
    public boolean execute(String token) {
        return false;
    }
}
