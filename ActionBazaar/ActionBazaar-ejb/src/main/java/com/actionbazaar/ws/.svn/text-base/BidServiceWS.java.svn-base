/**
 *  EJB 3 in Action
 *  Book: http://manning.com/panda2/
 *  Code: http://code.google.com/p/action-bazaar/
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.actionbazaar.ws;

import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.actionbazaar.buslogic.BidService;
import com.actionbazaar.ws.dto.BidDTO;
import com.actionbazaar.ws.dto.BidResponse;

/**
 * Bid Web Service
 * @author Ryan Cuprak
 */
@WebService(
        name="MakeBid",
        serviceName="BidService",
        targetNamespace="http://com.actionbazaar/bid",
        portName = "BidPort",
        wsdlLocation= "Bid.wsdl")
public class BidServiceWS {

    private static final Logger logger = Logger.getLogger("BidService");

    @EJB
    private BidService bidService;

    @WebMethod(operationName="placeBid")
    @SOAPBinding(style=SOAPBinding.Style.DOCUMENT,use=SOAPBinding.Use.LITERAL,
            parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public @WebResult(name="response", targetNamespace="http://com.actionbazaar/bid")
    BidResponse bid(
            @WebParam(name="bid", mode= WebParam.Mode.IN, targetNamespace="http://com.actionbazaar/bid")
            BidDTO bid) {
        logger.info("Webservice invoke!" + bid.getBidPrice());
        BidResponse br = new BidResponse();
        br.setAccepted(true);
        return br;
    }
}
