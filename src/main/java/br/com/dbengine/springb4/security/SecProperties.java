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

    @Value("${restdb-key}")
    private String restdbKey;

    public SecProperties() {
    }

    @PostConstruct
    public void init() {
        //Sysout.s("================== >> " + canonicKey + " << ================== ");
        //Sysout.s("================== >> " + emailKey + " << ================== ");
        //Sysout.s("================== >> " + threadPoolKey + " << ================== ");
        Sysout.s("================== >> " + restdbKey + " << ================== ");
        Sysout.CANONIC_KEY = canonicKey;
        Sysout.HARPER_KEY = harperKey;
        Sysout.RESTDB_KEY = restdbKey;
    }

}
