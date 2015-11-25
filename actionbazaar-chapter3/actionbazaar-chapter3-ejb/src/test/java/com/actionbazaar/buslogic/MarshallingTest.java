/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.actionbazaar.buslogic;

import com.actionbazaar.persistence.Bidder;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import org.junit.Test;

/**
 *
 * @author Ryan Cuprak
 */
public class MarshallingTest {

    @Test
    public void testBidderMarshalling () throws Exception {
        Bidder bidder = new Bidder(5L);
        JAXBContext ctx = JAXBContext.newInstance(Bidder.class);
        StringWriter writer = new StringWriter();
        ctx.createMarshaller().marshal(bidder,writer);
        String test = writer.toString();
        System.out.println("Output: " + test);
    }

}
