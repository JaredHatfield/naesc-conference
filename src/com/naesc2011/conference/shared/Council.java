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

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.naesc2011.conference.shared.ConferenceAttendee.VoteStatus;

@PersistenceCapable
public class Council {
    /**
     * 
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     * 
     */
    @Persistent
    private String name;

    /**
     * 
     */
    @Persistent
    private String university;

    /**
     * 
     */
    @Persistent
    private String location;

    /**
     * 
     */
    @Persistent
    @Element(dependent = "true")
    private List<ConferenceAttendee> attendees;

    /**
     * 
     */
    @Persistent
    private String website;

    /**
     * 
     * @param name
     */
    public Council(String name) {
        this.name = name;
        this.attendees = new ArrayList<ConferenceAttendee>();
    }

    /**
     * 
     * @param id
     */
    public void SetVotingDelegate(String id) {
        for (int i = 0; i < this.attendees.size(); i++) {
            ConferenceAttendee att = this.attendees.get(i);
            if ((att.getKey().getId() + "").equals(id)) {
                att.setVoteStatus(VoteStatus.VOTING);
            } else if (att.getVoteStatus() == null) {
                att.setVoteStatus(VoteStatus.NONE);
            } else if (att.getVoteStatus().equals(VoteStatus.VOTING)) {
                att.setVoteStatus(VoteStatus.NONE);
            }
        }
    }

    /**
     * 
     * @param id
     */
    public void SetAlternateDeleaget(String id) {
        for (int i = 0; i < this.attendees.size(); i++) {
            ConferenceAttendee att = this.attendees.get(i);
            if ((att.getKey().getId() + "").equals(id)) {
                att.setVoteStatus(VoteStatus.ALTERNATE);
            } else if (att.getVoteStatus().equals(VoteStatus.ALTERNATE)) {
                att.setVoteStatus(VoteStatus.NONE);
            } else if (att.getVoteStatus() == null) {
                att.setVoteStatus(VoteStatus.NONE);
            }
        }
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the university
     */
    public String getUniversity() {
        return university;
    }

    /**
     * @param university
     *            the university to set
     */
    public void setUniversity(String university) {
        this.university = university;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the attendees
     */
    public List<ConferenceAttendee> getAttendees() {
        return attendees;
    }

    /**
     * @param attendees
     *            the attendees to set
     */
    public void setAttendees(List<ConferenceAttendee> attendees) {
        this.attendees = attendees;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website
     *            the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * 
     * @param pm
     * @param company
     */
    public static void InsertCouncil(PersistenceManager pm, Council council) {
        pm.makePersistent(council);
    }

    /**
     * 
     * @param pm
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Council> GetAllCouncils(PersistenceManager pm) {
        String query = "select from " + Council.class.getName();
        return (List<Council>) pm.newQuery(query).execute();
    }

    /**
     * 
     * @param pm
     * @param i
     * @return
     */
    public static Council GetCouncil(PersistenceManager pm, String id) {
        int i = Integer.parseInt(id);
        Key key = KeyFactory.createKey(Council.class.getSimpleName(), i);
        Council c = pm.getObjectById(Council.class, key);
        return c;
    }

    /**
     * 
     * @param pm
     * @param key
     * @return
     */
    public static Council GetCouncil(PersistenceManager pm, Key key) {
        Council c = pm.getObjectById(Council.class, key);
        return c;
    }
}
