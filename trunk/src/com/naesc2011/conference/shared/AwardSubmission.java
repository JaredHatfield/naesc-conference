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
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

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
     * 
     * @param award
     * @param application
     */
    public AwardSubmission(Key award, Key application) {
        this.award = award;
        this.application = application;
        this.submitted = false;
    }

    /**
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * @return the award
     */
    public Key getAward() {
        return award;
    }

    /**
     * @param award
     *            the award to set
     */
    public void setAward(Key award) {
        this.award = award;
    }

    /**
     * @return the application
     */
    public Key getApplication() {
        return application;
    }

    /**
     * @param application
     *            the application to set
     */
    public void setApplication(Key application) {
        this.application = application;
    }

    /**
     * @return the submitted
     */
    public Boolean getSubmitted() {
        return submitted;
    }

    /**
     * @param submitted
     *            the submitted to set
     */
    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    /**
     * @param submittedOn
     *            the submittedOn to set
     */
    public void setSubmittedOn(Date submittedOn) {
        this.submittedOn = submittedOn;
    }

    /**
     * @return the submittedOn
     */
    public Date getSubmittedOn() {
        return submittedOn;
    }

    /**
     * 
     * @param pm
     * @param AwardSubmission
     */
    public static void InsertAward(PersistenceManager pm,
            AwardSubmission AwardSubmission) {
        pm.makePersistent(AwardSubmission);
    }

    /**
     * 
     * @param pm
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<AwardSubmission> GetAllAwards(PersistenceManager pm) {
        String query = "select from " + AwardSubmission.class.getName();
        return (List<AwardSubmission>) pm.newQuery(query).execute();
    }

    /**
     * 
     * @param pm
     * @param id
     * @return
     */
    public static AwardSubmission GetAward(PersistenceManager pm, String id) {
        int i = Integer.parseInt(id);
        Key key = KeyFactory
                .createKey(AwardSubmission.class.getSimpleName(), i);
        AwardSubmission a = pm.getObjectById(AwardSubmission.class, key);
        return a;
    }

    /**
     * 
     * @param pm
     * @param key
     * @return
     */
    public static AwardSubmission GetAward(PersistenceManager pm, Key key) {
        AwardSubmission a = pm.getObjectById(AwardSubmission.class, key);
        return a;
    }
}
