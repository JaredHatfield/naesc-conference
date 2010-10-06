/*
 * NAESC Conference is a Google App Engine web application that provides
 * a conference registration system.
 * Copyright (C) 2010  Speed School Student Council
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.naesc2011.conference.shared;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class AwardSubmission {

    /**
     * The key.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     * The award.
     */
    @Persistent
    private Key award;

    /**
     * The application..
     */
    @Persistent
    private Key application;

    /**
     * Flag indicating that the application was submitted.
     */
    @Persistent
    private Boolean submitted;

    /**
     * The time when the application was submitted.
     */
    @Persistent
    private Date submittedOn;

    /**
     * Creates a new instance of the AwardSubmission class.
     * 
     * @param award
     *            The referenced award.
     * @param application
     *            The referenced application.
     */
    public AwardSubmission(Key award, Key application) {
        this.award = award;
        this.application = application;
        this.submitted = false;
    }

    /**
     * Gets the key.
     * 
     * @return The key.
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     * 
     * @param key
     *            The key to set.
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Gets the award.
     * 
     * @return The award.
     */
    public Key getAward() {
        return award;
    }

    /**
     * Gets the application.
     * 
     * @return The application.
     */
    public Key getApplication() {
        return application;
    }

    /**
     * Gets the submitted flag.
     * 
     * @return The submitted flag.
     */
    public Boolean getSubmitted() {
        return submitted;
    }

    /**
     * Sets the submitted flag.
     * 
     * @param submitted
     *            The submitted flag value.
     */
    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    /**
     * Gets the submittedOn date.
     * 
     * @return The submittedOn date.
     */
    public Date getSubmittedOn() {
        return submittedOn;
    }

    /**
     * Sets the submitted on date.
     * 
     * @param submittedOn
     *            The submittedOn date.
     */
    public void setSubmittedOn(Date submittedOn) {
        this.submittedOn = submittedOn;
    }
}
