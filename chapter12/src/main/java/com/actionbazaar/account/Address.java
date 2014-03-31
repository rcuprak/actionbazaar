/**
 *  Address.java
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
package com.actionbazaar.account;

import com.actionbazaar.State;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Encapsulates the address information. This information is stored within
 * the users' table. This groups these properties together thereby reducing clutter
 * in the users object.
 */
@Embeddable
public class Address implements Serializable {

    /**
     * Street name - first line
     */
    @NotNull
    @Size(min=2,max=30,message="{streetNameSize}")
    private String streetName1;

    /**
     * City
     */
    @NotNull
    @Size(min=2,max=30,message="{citySize}")
    private String city;
    /**
     * State
     */
    @NotNull
    private State state;

    /**
     * Zip code
     */
    @NotNull
    @Size(min=5,max=5,message="{zipCodeSize}")
    private String zipCode;

    /**
     * Phone number
     */
    @Size(min=10,max=10)
    private String phone;
    
    /**
     * No-arg constructor for JPA
     */
    public Address() {}
    
    /**
     * Constructs a new address with the information provided
     * @param streetName1 - street name
     * @param city - city
     * @param state - state
     * @param zipCode - zip code
     * @param phone - phone
     */
    public Address(String streetName1, String city, State state, String zipCode, String phone) {
        this.streetName1 = streetName1;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
    }

    /**
     * Returns the street - line 1
     * @return street line 1
     */
    public String getStreetName1() {
        return streetName1;
    }

    /**
     * Returns the city
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the state
     * @return state
     */
    @Column(name = "STATE_CODE")
    public State getState() {
        return state;
    }

    /**
     * Returns the zip code
     * @return zip code
     */
    @Column(name = "ZIP_CODE")
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the street name line 1
     * @param streetName1 - line 1 street
     */
    public void setStreetName1(String streetName1) {
        this.streetName1 = streetName1;
    }

    /**
     * Sets the street name line 2
     * @param streetName2 - street name line 2
     */
    public void setStreetName2(String streetName2) {
        this.streetName1 = streetName2;
    }

    /**
     * Sets the city
     * @param city - city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the state
     * @param state - state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Sets the zip code
     * @param zipCode - zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Returns the phone number
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number
     * @param phone - phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
