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
     * The key.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     * The tour name.
     */
    @Persistent
    private String name;

    /**
     * The tour description.
     */
    @Persistent
    private String description;

    /**
     * The maximum number of people that can go on the tour.
     */
    @Persistent
    private int maximum;

    /**
     * The URL for additional information about the tour.
     */
    @Persistent
    private String url;

    /**
     * The list of tour members.
     */
    @Persistent
    @Element(dependent = "true")
    private List<TourMember> tourMembers;

    /**
     * Creates a new instance of the Tour class.
     * 
     * @param name
     *            The tour name.
     * @param description
     *            The tour description
     * @param maximum
     *            The max number of people for the tour.
     */
    public Tour(String name, String description, int maximum, String url) {
        this.name = name;
        this.description = description;
        this.maximum = maximum;
        this.url = url;
        this.tourMembers = new ArrayList<TourMember>();
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
     * Gets the name.
     * 
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        String s = name.replaceAll("\\<.*?>", "");
        if (!this.name.equals(s)) {
            this.name = s;
        }
    }

    /**
     * Gets the description.
     * 
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * 
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        String s = description.replaceAll("\\<.*?>", "");
        if (!this.description.equals(s)) {
            this.description = s;
        }
    }

    /**
     * Gets the url.
     * 
     * @return The url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     * 
     * @param url
     *            The url to set.
     */
    public void setUrl(String url) {
        String s = url.replaceAll("\\<.*?>", "");
        if (!this.url.equals(s)) {
            this.url = s;
        }
    }

    /**
     * Gets the tour members.
     * 
     * @return The tour Members.
     */
    public List<TourMember> getTourMembers() {
        return tourMembers;
    }

    /**
     * Gets the maximum.
     * 
     * @return The maximum.
     */
    public int getMaximum() {
        return maximum;
    }

    /**
     * Sets the maximum.
     * 
     * @param maximum
     *            The maximum to set.
     */
    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    /**
     * Test is the tour has room for more people.
     * 
     * @return True if there are open spots; otherwise false.
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
     * Inserts a Tour into the datastore.
     * 
     * @param pm
     *            The PersistenceManager.
     * @param tour
     *            The Tour to insert.
     */
    public static void InsertTour(PersistenceManager pm, Tour tour) {
        pm.makePersistent(tour);
    }

    /**
     * Gets all of the Tours.
     * 
     * @param pm
     *            The PersistenceManager.
     * @return A list of all of the tours.
     */
    @SuppressWarnings("unchecked")
    public static List<Tour> GetAllTours(PersistenceManager pm) {
        String query = "select from " + Tour.class.getName() + " order by name";
        return (List<Tour>) pm.newQuery(query).execute();
    }

    /**
     * Gets the specified tour.
     * 
     * @param pm
     *            The PersistenceManager.
     * @param id
     *            The identifier for the tour.
     * @return The requested tour object.
     */
    public static Tour GetTour(PersistenceManager pm, String id) {
        int i = Integer.parseInt(id);
        Key key = KeyFactory.createKey(Tour.class.getSimpleName(), i);
        Tour t = pm.getObjectById(Tour.class, key);
        return t;
    }

    /**
     * Gets the specified tour.
     * 
     * @param pm
     *            The PersistenceManager.
     * @param key
     *            The key for the tour.
     * @return The requested object.
     */
    public static Tour GetTour(PersistenceManager pm, Key key) {
        Tour t = pm.getObjectById(Tour.class, key);
        return t;
    }
}
