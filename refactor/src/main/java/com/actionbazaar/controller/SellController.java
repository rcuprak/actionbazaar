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
package com.actionbazaar.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import com.actionbazaar.buslogic.PickListBean;
import com.actionbazaar.image.ImageBean;
import com.actionbazaar.image.ImageRecord;
import com.actionbazaar.persistence.Category;
import com.actionbazaar.web.PageNavigationEnum;
import com.actionbazaar.web.UserDisplay;

/**
 * Controller for selling an item.
 * @author Ryan Cuprak
 */
@Named
@ConversationScoped
public class SellController implements Serializable {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("SellController");

    /**
     * Conversation
     */
    @Inject
    private Conversation conversation;

    /**
     * Keywords 
     */
    private String keywords;

    /**
     * Search categories
     */
    private DualListModel<Category> searchCategories = new DualListModel<Category>();

    /**
     * Root of the tree
     */
    private TreeNode root;

    /**
     * Selected tree nodes
     */
    private TreeNode selectedNodes[];

    /**
     * Pick list bean
     */
    @EJB
    private PickListBean pickListBean;

    /**
     * Image bean
     */
    @EJB
    private ImageBean imageBean;

    /**
     * First image
     */
    private String image1;

    /**
     * Second image
     */
    private String image2;

    /**
     * Title of the item
     */
    private String title;

    /**
     * HTML description of the item
     */
    private String description;

    /**
     * Duration in days (gets converted to an actual date)
     */
    private int duration;

    /**
     * Minimum price of the item
     */
    private BigDecimal minimumPrice;

    /**
     * User display name
     */
    @Inject
    private UserDisplay userDisplay;

    /**
     * Image folder - note images will be stored in a sub-username folder - access will then be restricted
     * so that other users could never access pre-post pictures (for example to get advance notice of a new bid)
     */
    @Resource(name="bazaar-images")
    private String imageFolder;

    /**
     * Initializes the lists
     */
    @PostConstruct
    private void init() {
        List<Category> roots = pickListBean.getCategories();
        root = new DefaultTreeNode("Root",null);
        DefaultTreeNode child;
        for(Category rt : roots) {
            child = new DefaultTreeNode(rt,root);
            recurse(rt,child);
        }
    }

    /**
     * Begins the conversation
     * @return sell page
     */
    public String startSellWizard() {
        logger.info("Conversation starting...");
        this.conversation.begin();
        return PageNavigationEnum.SELL.toString();
    }

    /**
     * Recurses over the nodes building the tree
     * @param category - category
     * @param parent - parent
     */
    protected void recurse(Category category,TreeNode parent) {
        for(Category cat : category.getSubCategories()) {
            TreeNode tn = new DefaultTreeNode(cat,parent);
            recurse(cat,tn);
        }
    }

    /**
     * Returns categories
     * @return categories
     */
    public TreeNode getCategories() {
        return root;
    }

    /**
     * Sets the categories
     * @param root - root of the tree
     */
    public void setCategories(TreeNode root) {
        this.root = root;
    }

    /**
     * Sets the search categories 
     * @param searchCategories - search categories
     */
    public void setSearchCategories(DualListModel<Category> searchCategories) {
        this.searchCategories = searchCategories;
    }

    /**
     * Returns the search categories
     * @return search categories
     */
    public DualListModel<Category> getSearchCategories() {
        return searchCategories;
    }

    /**
     * Sets the keywords
     * @param keywords - keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns the keywords
     * @return keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Performs the category search
     */
    public void performCategorySearch() {
        List<String> split = new LinkedList<String>();
        StringTokenizer tokenizer = new StringTokenizer(keywords," ");
        while(tokenizer.hasMoreElements()) {
            split.add((String) tokenizer.nextElement());
        }
        String splitArray[] = new String[split.size()];
        splitArray = split.toArray(splitArray);
        searchCategories.setSource(pickListBean.findCategories(splitArray));
    }

    /**
     * Sets the category selection
     * @param selectedNodes - category selection
     */
    public void setCategorySelection(TreeNode selectedNodes[]) {
        this.selectedNodes = selectedNodes;
    }

    /**
     * Returns the category selection
     * @return category selection
     */
    public TreeNode[] getCategorySelection() {
        return selectedNodes;
    }

    /**
     * Moves to the next page
     * @return document page
     */
    public String performContinue() {
        if((selectedNodes == null || selectedNodes.length == 0) && searchCategories.getTarget().size() == 0) {
            FacesContext.getCurrentInstance().addMessage("search",new FacesMessage("At least one category must be selected."));
            return null;
        }
        return PageNavigationEnum.DOCUMENT.toString();
    }

    /**
     * Submits the item for bidding
     * @return home
     */
    public String submitListing() {
        return PageNavigationEnum.HOME.toString();
    }

    /**
     * Sets the first image
     * @param image1 - first image
     */
    public void uploadImage1(FileUploadEvent image1) {
        this.image1 = save(image1.getFile());
    }

    /**
     * Returns image 1
     * @return image 1
     */
    public String getImage1() {
        return image1;
    }

    /**
     * Sets the second image
     * @param image2 - image 2
     */
    public void uploadImage2(FileUploadEvent image2) {
        this.image2 = save(image2.getFile());
    }

    /**
     * Returns image 2
     * @return image 2
     */
    public String getImage2() {
        return image2;
    }

    /**
     * Returns the title of the item
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the item
     * @param title - title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns a description of the item
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the item
     * @param description - description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the duration of the bidding
     * @return bidding duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the bidding
     * @param duration - bidding duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns the minimum price
     * @return minimum price
     */
    public BigDecimal getMinimumPrice() {
        return minimumPrice;
    }

    /**
     * Sets the minimum price
     * @param minimumPrice - minimum price
     */
    public void setMinimumPrice(BigDecimal minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    /**
     * Saves the uploaded file into a folder for the user.
     * @param imageFile - image file to be saved
     * @return image id
     */
    private String save(UploadedFile imageFile) {
        try {
            File saveFld = new File(imageFolder+File.separator+userDisplay.getUser().getUsername());
            if(!saveFld.exists()) {
                if(!saveFld.mkdir()) {
                    logger.info("Unable to create folder: " + saveFld.getAbsolutePath());
                    return null;
                }
            }
            File tmp = File.createTempFile("img","img");
            IOUtils.copy(imageFile.getInputstream(),new FileOutputStream(tmp));
            File thumbnailImage = new File(saveFld+File.separator+UUID.randomUUID().toString()+".png");
            File fullResolution = new File(saveFld+File.separator+UUID.randomUUID().toString()+".png");

            // Create the thumbnail
            BufferedImage image = ImageIO.read(tmp);
            Image thumbnailIm = image.getScaledInstance(310,210, Image.SCALE_SMOOTH);
            // Convert the thumbnail java.awt.Image into a rendered image which we can save
            BufferedImage thumbnailBi = new BufferedImage(thumbnailIm.getWidth(null),thumbnailIm.getHeight(null),BufferedImage.TYPE_INT_RGB);
            Graphics bg = thumbnailBi.getGraphics();
            bg.drawImage(thumbnailIm, 0, 0, null);
            bg.dispose();

            ImageIO.write(thumbnailBi,"png",thumbnailImage);
            // Write out the full resolution image as a thumbnail
            ImageIO.write(image,"png",fullResolution);
            if(!tmp.delete()) {
                logger.info("Unable to delete: " + tmp.getAbsolutePath());
            }
            String imageId = UUID.randomUUID().toString();
            imageBean.addImage(imageId,new ImageRecord(imageFile.getFileName(),fullResolution.getAbsolutePath(),
                    thumbnailImage.getAbsolutePath(),userDisplay.getUser().getUsername()));
            return imageId;
        } catch (Throwable t) {
            logger.log(Level.SEVERE,"Unable to save the image.",t);
            return null;
        }
    }
}
