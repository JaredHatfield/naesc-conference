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

@PersistenceCapable
public class Tour {
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
    private String description;

    /**
     * 
     */
    @Persistent
    @Element(dependent = "false")
    private List<ConferenceAttendee> attendees;

    /**
     * 
     * @param name
     * @param description
     */
    public Tour(String name, String description) {
        this.name = name;
        this.description = description;
        this.attendees = new ArrayList<ConferenceAttendee>();
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param attendees
     *            the attendees to set
     */
    public void setAttendees(List<ConferenceAttendee> attendees) {
        this.attendees = attendees;
    }

    /**
     * @return the attendees
     */
    public List<ConferenceAttendee> getAttendees() {
        return attendees;
    }

    /**
     * Adds a ConferenceAttendee to a tour.
     * 
     * @param att
     *            The ConferenceAttendee.
     */
    public void addAttendee(ConferenceAttendee att) {
        att.setTour(this);
        this.attendees.add(att);
    }

    /**
     * Removes a ConferenceAttendee from a tour.
     * 
     * @param att
     *            The ConferenceAttendee.
     */
    public void removeAttendee(ConferenceAttendee att) {
        att.setTour(null);
        this.attendees.remove(att);
    }

    /**
     * 
     * @param pm
     * @param company
     */
    public static void InsertTour(PersistenceManager pm, Tour tour) {
        pm.makePersistent(tour);
    }

    /**
     * 
     * @param pm
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Tour> GetAllTours(PersistenceManager pm) {
        String query = "select from " + Tour.class.getName();
        return (List<Tour>) pm.newQuery(query).execute();
    }

    /**
     * 
     * @param pm
     * @param i
     * @return
     */
    public static Tour GetTour(PersistenceManager pm, String id) {
        int i = Integer.parseInt(id);
        Key key = KeyFactory.createKey(Tour.class.getSimpleName(), i);
        Tour t = pm.getObjectById(Tour.class, key);
        return t;
    }

    /**
     * 
     * @param pm
     * @param key
     * @return
     */
    public static Tour GetTour(PersistenceManager pm, Key key) {
        Tour t = pm.getObjectById(Tour.class, key);
        return t;
    }
}
