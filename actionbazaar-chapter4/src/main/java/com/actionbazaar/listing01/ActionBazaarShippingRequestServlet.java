/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actionbazaar.listing01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author <a href="mailto:mjremijan@yahoo.com">Michael Remijan</a>
 */
@WebServlet(name = "ActionBazaarShippingRequestServlet", urlPatterns = { "/ActionBazaarShippingRequestServlet" })
public class ActionBazaarShippingRequestServlet extends HttpServlet {

    @Inject
    @JMSConnectionFactory("java:app/jms/QueueConnectionFactory")
    private JMSContext context;

    @Resource(name = "java:app/jms/ShippingRequestQueue")
    private Destination destination;

    /**
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Throwable oops = null;
        try {
            sendShippingRequest(request);
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            oops = e;
        }

        if (oops == null) {
            
            response.sendRedirect("PendingShippingRequests.jsp");

        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                /*
                 * TODO output your page here. You may use following sample
                 * code.
                 */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet SendMessageServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet SendMessageServlet at " + request.getContextPath() + "</h1>");
                out.println("<p>Time:  " + String.valueOf(new Date()) + "</p>");
                out.println("<p>Opps:</p>");
                out.println("<xmp>");
                oops.printStackTrace(out);
                out.println("</xmp>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        }
    }

    /**
     * @param request
     * @return
     * @throws JMSException
     */
    private void sendShippingRequest(HttpServletRequest request) throws JMSException {

        ActionBazaarShippingRequest shippingRequest = new ActionBazaarShippingRequest();
        shippingRequest.setItem(request.getParameter("Item"));
        shippingRequest.setShippingAddress(request.getParameter("ShippingAddress"));
        shippingRequest.setShippingMethod(request.getParameter("ShippingMethod"));
        shippingRequest.setInsuranceAmount(Double.parseDouble(request.getParameter("InsuranceAmount")));

        ObjectMessage om = context.createObjectMessage();
        om.setObject(shippingRequest);

        JMSProducer producer = context.createProducer();
        producer.send(destination, om);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
    // the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request
     *            servlet request
     * @param response
     *            servlet response
     * @throws ServletException
     *             if a servlet-specific error occurs
     * @throws IOException
     *             if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request
     *            servlet request
     * @param response
     *            servlet response
     * @throws ServletException
     *             if a servlet-specific error occurs
     * @throws IOException
     *             if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
