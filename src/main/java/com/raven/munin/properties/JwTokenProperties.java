package com.raven.munin.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwTokenProperties {
    private String KEY;
    private int   EXPIRATION_TIME;
}
