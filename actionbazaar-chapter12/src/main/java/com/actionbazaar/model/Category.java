/**
 *  Category.java
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
package com.actionbazaar.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Represents a category
 * @author Ryan Cuprak
 */
@Entity
@Table(name = "CATEGORIES")
@NamedQueries({
    @NamedQuery(name="Categories.findAll", query="SELECT c FROM Category c order by c.categoryName")
})
public class Category implements Serializable {

    @SequenceGenerator(name = "CATEGORY_SEQ_GEN", sequenceName = "CATEGORY_SEQ", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ_GEN")
    @Column(name = "CATEGORY_ID", nullable = false)
    private Long categoryId;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @Column(name = "CREATE_DATE", insertable = false, updatable = false)
    private Timestamp createDate;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "CATEGORIES_ITEMS", joinColumns = @JoinColumn(name = "CI_CATEGORY_ID", referencedColumnName = "CATEGORY_ID"), inverseJoinColumns = @JoinColumn(name = "CI_ITEM_ID", referencedColumnName = "ITEM_ID"))
    private Set<Item> items;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "CATEGORY_ID")
    private Category parentCategory;

    /**
     * Sub-categories
     */
    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Category> subCategories = new HashSet<Category>();

    /**
     * Keywords associated with this category
     */
    @ElementCollection
    @CollectionTable(name ="keywords")
    private Set<String> keywords = new HashSet<String>();

    /**
     * Default constructor
     */
    public Category() {
        // constructor used for JPA
    }

    /**
     * Creates a new category with 
     * @param categoryName - category name
     * @param keywords - keywords associated with the category
     */
    public Category(String categoryName, String[] keywords) {
        this.categoryName = categoryName;
        Collections.addAll(this.keywords, keywords);

    }


    public Category(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Item addItem(Item item) {
        getItems().add(item);
        item.addCategory(this);
        return item;
    }

    public Item removeItem(Item item) {
        getItems().remove(item);
        item.setCategory(null);
        return item;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getSubCategories() {
        return subCategories;
    }

    public void setCategorySet(Set<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public Category addCategory(Category category) {
        subCategories.add(category);
        category.setParentCategory(this);
        return category;
    }

    public Category removeCategory(Category category) {
        subCategories.remove(category);
        category.setParentCategory(null);
        return category;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
