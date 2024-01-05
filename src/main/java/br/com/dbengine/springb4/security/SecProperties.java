package br.com.dbengine.springb4.security;

import br.com.dbengine.springb4.dbUtil.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

import javax.annotation.*;

//@Configuration
@Component
@PropertySource("classpath:/sec/secconfig.properties")
public class SecProperties {

    @Value("${canonic-key}")
    private String canonicKey;

    @Value("${email}")
    private String emailKey;

    @Value("${thread-pool}")
    private String threadPoolKey;

    @Value("${harper-key}")
    private String harperKey;

    public SecProperties() {
    }

    @PostConstruct
    public void init() {
        System.out.println("================== >> " + canonicKey + " << ================== ");
        System.out.println("================== >> " + emailKey + " << ================== ");
        System.out.println("================== >> " + threadPoolKey + " << ================== ");
        //this.canonicKey = canonicKey;
        //this.emailKey = emailKey;
        //this.threadPoolKey = threadPoolKey;
        Sysout.CANONIC_KEY = canonicKey;
        Sysout.HARPER_KEY = harperKey;
    }

}
