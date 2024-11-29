package com.tabisketch.bean.valueobject;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ServerMailAddress {
    @Value("${spring.mail.username}")
    private String value;
}
