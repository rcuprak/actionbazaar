package com.actionbazaar.email;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * EmailUtility
 * @author Ryan Cuprak
 */
public class EmailUtility implements Serializable {

    /**
     * Attribute that we are interested in replacing.
     */
    private static final String SRC = "src=\"";

    /**
     * CID prefix to be used
     */
    private static final String CID_PREFIX = "cid:file";

    /**
     * Counter used to create the CIDs
     */
    private int cidCounter = 0;

    /**
     * CID to file mappings, must check to make sure all of the files are
     * available before sending out the email.
     */
    private Map<String,File> cidMappings = new HashMap<String,File>();


    /**
     * Patched email
     */
    private String patchedEmail;

    /**
     * Raw email - as provided or read-in from a file
     */
    private String rawEmail;

    /**
     * Subject of the email
     */
    private String subject;

    /**
     * Attachments directory
     */
    private File attachmentsDirectory;

    /**
     * Creates the specified email with the raw email as a starting point. Graphics in the raw
     * email will be substituted and cids inserted.
     * @param rawEmail - raw email
     * @param subject - tokens to be replaced
     * @param attachmentsDirectory - directory containing the attachments
     */
    public EmailUtility(String rawEmail, String subject, File attachmentsDirectory) {
        this.rawEmail = rawEmail;
        this.subject = subject;
        this.attachmentsDirectory = attachmentsDirectory;
    }

    /**
     * Processes the email
     * @param replacementTokens - tokens to be replaced
     */
    public void process(Map<String,String> replacementTokens) throws EmailParseException {
        File file;
        String cid;
        StringBuilder builder;
        builder = new StringBuilder(rawEmail);
        try {

            int endIndex = 0;
            int index = builder.indexOf(SRC);
            endIndex = index;
            while (index > 0) {
                if (index > 1) {
                    index += SRC.length();
                    endIndex = builder.indexOf("\"", index);
                    file = new File(attachmentsDirectory.getPath() + File.separator +
                            builder.substring(index, endIndex));
                    if(!file.exists()) {
                        throw new EmailParseException("The file: " + file.getPath() + " doesn't exist.");
                    }
                    cid = CID_PREFIX + cidCounter++;
                    cidMappings.put(cid,file);
                    builder.replace(index, endIndex,cid);
                }
                index = builder.indexOf(SRC,endIndex);
            }
            patchedEmail = builder.toString();
            for(Map.Entry<String,String> entry : replacementTokens.entrySet()) {
                patchedEmail.replace(entry.getKey(),entry.getValue());
            }
        } catch (Throwable t) {
            throw new EmailParseException(t);
        }
    }

    /**
     * Returns the CID markings
     * @return
     */
    public Map<String,File> getCidMappings() {
        return cidMappings;
    }

    /**
     * Returns the patched email
     * @return patched email
     */
    public String getPatchedEmail() {
        return patchedEmail;
    }

    /**
     * Returns the subject
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

}
