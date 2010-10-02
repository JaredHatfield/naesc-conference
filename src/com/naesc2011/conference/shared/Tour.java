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
    private int maximum;

    /**
     * 
     */
    @Persistent
    @Element(dependent = "true")
    private List<TourMember> tourMembers;

    /**
     * 
     * @param name
     * @param description
     */
    public Tour(String name, String description, int maximum) {
        this.name = name;
        this.description = description;
        this.maximum = maximum;
        this.setTourMembers(new ArrayList<TourMember>());
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
        this.name = name.replaceAll("\\<.*?>", "");
        ;
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
        this.description = description.replaceAll("\\<.*?>", "");
        ;
    }

    /**
     * @param tourMembers
     *            the tourMembers to set
     */
    public void setTourMembers(List<TourMember> tourMembers) {
        this.tourMembers = tourMembers;
    }

    /**
     * @return the tourMembers
     */
    public List<TourMember> getTourMembers() {
        return tourMembers;
    }

    /**
     * @param maximum
     *            the maximum to set
     */
    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    /**
     * @return the maximum
     */
    public int getMaximum() {
        return maximum;
    }

    /**
     * 
     * @return
     */
    public boolean hasRoom() {
        if (this.maximum <= 0) {
            return true;
        } else if (this.maximum > this.tourMembers.size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds a ConferenceAttendee to a tour.
     * 
     * @param att
     *            The ConferenceAttendee.
     */
    public void addAttendee(Key council, ConferenceAttendee att) {
        att.setTour(this.key);
        TourMember tm = new TourMember(council, att.getKey());
        this.tourMembers.add(tm);
    }

    /**
     * Removes a ConferenceAttendee from a tour.
     * 
     * @param att
     *            The ConferenceAttendee.
     */
    public void removeAttendee(Key council, ConferenceAttendee att) {
        att.setTour(null);
        TourMember tm = null;
        for (int i = 0; i < this.tourMembers.size(); i++) {
            tm = this.tourMembers.get(i);
            if (tm.getCouncil().equals(council)
                    && tm.getAttendee().equals(att.getKey())) {
                break;
            }
        }

        if (tm != null) {
            this.tourMembers.remove(tm);
        }
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
