package br.com.dbengine.springb4.security;

import br.com.dbengine.springb4.dbUtil.*;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${hasura-key}")
    private String hasuraKey;

    @Value("${admin-user}")
    private String adminUser;

    @Value("${admin-pass}")
    private String adminPass;

    @Value("${admin-role}")
    private String[] adminRoles;

    @Value("${user1}")
    private String[] user1;

    @Value("${user1Roles}")
    private String[] user1Roles;


    @Value("${user2}")
    private String[] user2;

    @Value("${user2Roles}")
    private String[] user2Roles;

    @Value("${user3}")
    private String[] user3;

    @Value("${user3Roles}")
    private String[] user3Roles;

    @Value("${user4}")
    private String[] user4;

    @Value("${user4Roles}")
    private String[] user4Roles;

    public SecProperties() {
    }

    @PostConstruct
    public void init() {
        //Sysout.s("================== >> " + canonicKey + " << ================== ");
        //Sysout.s("================== >> " + emailKey + " << ================== ");
        //Sysout.s("================== >> " + threadPoolKey + " << ================== ");
        //Sysout.s("================== >> " + restdbKey + " << ================== ");
        Sysout.s("================== >> " + hasuraKey + " << ================== ");
        Sysout.CANONIC_KEY = canonicKey;
        Sysout.HARPER_KEY = harperKey;
        Sysout.RESTDB_KEY = restdbKey;
        Sysout.HASURA_KEY = hasuraKey;

        UsersRepo usrRepo = new UsersRepo();
        usrRepo.setInicialUserList(adminUser,adminPass, adminRoles);
        usrRepo.setInicialUserList(user1[0],user1[1],user1Roles );
        usrRepo.setInicialUserList(user2[0],user2[1],user2Roles );
        usrRepo.setInicialUserList(user3[0],user3[1],user3Roles );
        usrRepo.setInicialUserList(user4[0],user4[1],user4Roles );
    }

}
