package com.actionbazaar.buslogic;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "HelloService")
@Stateless(name="TestService")
public class TestService {


    @WebMethod
    public String add( String name) {

        return "Hello " + name;
    }
}
