package com.jonas.feature.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ContextService {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public String getActiveProfile() {
        return activeProfile;
    }
}
