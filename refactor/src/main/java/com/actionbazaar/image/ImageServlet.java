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
package com.actionbazaar.image;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

/**
 * ImageServlet - serves up images of items that have been uploaded.
 * @author Ryan Cuprak
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet{

    /**
     * Image folder
     */
    @Resource(name="bazaar-images")
    private String imageFolder;

    /**
     * Image bean
     */
    @EJB
    private ImageBean imageBean;
    
    /**
     * Retrieves the specified image
     * @param req - request
     * @param resp - response
     * @throws ServletException - thrown if there is an error
     * @throws IOException - another exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/png");
        OutputStream outStream;
        outStream = resp.getOutputStream();
        InputStream is;
        String name = req.getParameter("name");
        if(name == null) {
            is = ImageServlet.class.getResourceAsStream("/com/actionbazaar/blank.png");
        } else {
            ImageRecord imageRecord = imageBean.getFile(name);
            if(imageRecord != null) {
                is = new BufferedInputStream(new FileInputStream(imageRecord.getThumbnailFile()));
            } else {
                is = ImageServlet.class.getResourceAsStream("/com/actionbazaar/blank.png");
            }
        }
        IOUtils.copy(is, outStream);
        outStream.close();
        outStream.flush();
    }
}
