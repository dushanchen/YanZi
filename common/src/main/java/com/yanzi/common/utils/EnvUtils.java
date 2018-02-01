package com.yanzi.common.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yanzi.common.constants.ValidValue;

@Component
public class EnvUtils implements InitializingBean {

    @Value("#{configProperties['common.env.valid']}")
    private String envValidStr;
    private ValidValue envValid;

    public ValidValue getEnvValid() {
        return envValid;
    }
    
    public boolean isValid(int valid){
        return valid <= this.getEnvValid().getValue();
    }
    
    public boolean isNotValid(int valid){
        return !isValid(valid);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        envValid = ValidValue.valueOf(envValidStr);
    }
}
