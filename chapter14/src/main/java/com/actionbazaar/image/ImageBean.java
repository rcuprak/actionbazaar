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

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;

/**
 * Maintains a cache of the image
 * @author Ryan Cuprak
 */
@Singleton
public class ImageBean {

    /**
     * Image cache - keyed off of file name (UUID)
     */
    private Map<String,ImageRecord> images = new HashMap<>();

    /**
     * Adds the image to the cache
     * @param imageId - image id
     * @param imageRecord - image record
     */
    public void addImage(String imageId, ImageRecord imageRecord) {
        images.put(imageId,imageRecord);
    }

    /**
     * Retrieves the image from the cache
     * @param imageId - image id
     * @return file name
     */
    public ImageRecord getFile(String imageId) {
        return images.get(imageId);
    }
    
    
}
