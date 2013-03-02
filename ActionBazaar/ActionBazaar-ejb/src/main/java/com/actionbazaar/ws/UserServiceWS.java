package com.actionbazaar.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.actionbazaar.ws.dto.CreateUserResponse;
import com.actionbazaar.ws.dto.UserDTO;

@WebService(
        name="CreateUser",
        serviceName="UserService",
        targetNamespace="http://com.actionbazaar/user",
        portName = "UserPort",
        wsdlLocation= "UserService.wsdl")
@SOAPBinding(style=SOAPBinding.Style.RPC)
public interface UserServiceWS {

    @WebMethod(operationName="createUser")
    @SOAPBinding(style=SOAPBinding.Style.DOCUMENT,use=SOAPBinding.Use.LITERAL,
                parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public @WebResult(name="response", targetNamespace="http://com.actionbazaar/user") CreateUserResponse
        createUser(
            @WebParam(name="user", mode= WebParam.Mode.IN, targetNamespace="http://com.actionbazaar/user") UserDTO user);
}




