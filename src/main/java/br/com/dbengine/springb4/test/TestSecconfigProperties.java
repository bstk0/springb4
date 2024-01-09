package br.com.dbengine.springb4.test;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
//@TestPropertySource("/secconfig.properties")
@TestPropertySource("classpath:secconfig.properties")
public class TestSecconfigProperties {

    @Value("${canonicKey}")
    private String foo;

    @Test
    public void whenFilePropertyProvided_thenProperlyInjected() {
        System.out.println(">>" + foo);
        //assertThat(foo).isEqualTo("bar");
    }
}