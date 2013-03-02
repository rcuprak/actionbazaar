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

import java.io.Serializable;

/**
 * Image Record
 * @author Ryan Cuprak
 */
public class ImageRecord implements Serializable {

    /**
     * Original name of the file
     */
    private String originalName;

    /**
     * File name
     */
    private String fullResolutionFile;

    /**
     * Thumbnail image
     */
    private String thumbnailFile;

    /**
     * Username
     */
    private String username;


    /**
     * Creates a new ImageRecord
     * @param originalName - original name of the file
     * @param fullResolutionFile - fullResolutionFile
     * @param thumbnailFile - thumbnail image
     * @param username - username
     */
    public ImageRecord(String originalName, String fullResolutionFile, String thumbnailFile, String username) {
        this.originalName = originalName;
        this.fullResolutionFile = fullResolutionFile;
        this.thumbnailFile = thumbnailFile;
        this.username = username;
    }

    /**
     * Returns the original name
     * @return original name
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * Returns the file name (currently on disk)
     * @return file
     */
    public String getFullResolutionFile() {
        return fullResolutionFile;
    }

    /**
     * Returns the thumbnail file
     * @return
     */
    public String getThumbnailFile() {
        return thumbnailFile;
    }

    /**
     * Returns the username of the file uploader
     * @return file name
     */
    public String getUsername() {
        return username;
    }
}
