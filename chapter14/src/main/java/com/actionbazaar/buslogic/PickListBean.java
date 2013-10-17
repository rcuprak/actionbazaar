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
package com.actionbazaar.buslogic;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.Singleton;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.actionbazaar.model.Category;

/**
 * This bean is responsible for managing pick lists.
 * @author Ryan Cuprak
 */
@Named
@Singleton
public class PickListBean {

    /**
     * Persistence Context
     */
    @PersistenceContext(unitName="actionbazaar")
    private EntityManager entityManager;

    /**
     * Returns the list of available categories
     * @return categories
     */
    public List<Category> getCategories() {
        Query q = entityManager.createQuery("select c from Category c where c.parentCategory is null");
        List<Category> categories = (List<Category>)q.getResultList();
        return categories;
    }

    /**
     * Given a set of keywords, find matching categories
     * @param keywords - keywords
     * @return List<Category>
     */
    public List<Category> findCategories(String keywords[]) {
        List<Category> categories = new LinkedList<>();
        for(String keyword : keywords) {
            Query q = entityManager.createNativeQuery("select category_category_id from keywords where keywords = ?keyword");
            q.setParameter("keyword",keyword);
            List<Long> ids = q.getResultList();
            for(Long id : ids) {
                categories.add(entityManager.find(Category.class,id));
            }
        }
        return categories;
    }

    /**
     * Loads the categories into the database
     * @param categories - categories
     */
    public void addCategories(List<Category> categories) {
        for(Category category : categories) {
            entityManager.persist(category);
        }
    }
}
