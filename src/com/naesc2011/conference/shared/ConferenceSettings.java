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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ConferenceSettings {

    /**
     * The key.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     * The name of the conference.
     */
    private String conferenceName;

    /**
     * The amount of to pay per attendee for early registration.
     */
    @Persistent
    private double earlyRegistrationFee;

    /**
     * The amount of to pay per attendee for late registration.
     */
    @Persistent
    private double lateRegistrationFee;

    /**
     * The early registration deadline.
     */
    @Persistent
    private Date earlyRegistrationDate;

    /**
     * The close of registration.
     */
    @Persistent
    private Date lateRegistrationDate;

    /**
     * The maximum number of attendees per council.
     */
    @Persistent
    private int maxAttendees;

    /**
     * The formatter for reading and writing the date
     */
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

    /**
     * Create a new instance of the ConferenceSettings class.
     * 
     * @param earlyFee
     *            The early registration fee.
     * @param lateFee
     *            The late registration fee.
     * @param earlyDate
     *            The early registration date.
     * @param lateDate
     *            The late registration date.
     */
    private ConferenceSettings(String conferenceName, double earlyFee,
            double lateFee, Date earlyDate, Date lateDate, int maxAttendees) {
        this.conferenceName = conferenceName;
        this.earlyRegistrationFee = earlyFee;
        this.lateRegistrationFee = lateFee;
        this.earlyRegistrationDate = earlyDate;
        this.lateRegistrationDate = lateDate;
        this.maxAttendees = maxAttendees;
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
     * Gets the conference name.
     * 
     * @return The conference Name.
     */
    public String getConferenceName() {
        return conferenceName;
    }

    /**
     * Sets the conference name.
     * 
     * @param conferenceName
     *            The conference Name to set.
     */
    public void setConferenceName(String conferenceName) {
        String s = conferenceName.replaceAll("\\<.*?>", "");
        if (!this.conferenceName.equals(s)) {
            this.conferenceName = s;
        }
    }

    /**
     * Gets the early registration fee.
     * 
     * @return The early Registration Fee.
     */
    public double getEarlyRegistrationFee() {
        return earlyRegistrationFee;
    }

    /**
     * Sets the early registration fee.
     * 
     * @param earlyRegistrationFee
     *            The early Registration Fee to set.
     */
    public void setEarlyRegistrationFee(double earlyRegistrationFee) {
        this.earlyRegistrationFee = earlyRegistrationFee;
    }

    /**
     * Gets the late registration fee.
     * 
     * @return The late Registration Fee.
     */
    public double getLateRegistrationFee() {
        return lateRegistrationFee;
    }

    /**
     * Sets the late registration fee.
     * 
     * @param lateRegistrationFee
     *            The late Registration Fee to set.
     */
    public void setLateRegistrationFee(double lateRegistrationFee) {
        this.lateRegistrationFee = lateRegistrationFee;
    }

    /**
     * Gets the early registration date.
     * 
     * @return The early Registration Date.
     */
    public Date getEarlyRegistrationDate() {
        return earlyRegistrationDate;
    }

    /**
     * Sets the early registration date.
     * 
     * @param earlyRegistrationDate
     *            The early Registration Date to set.
     */
    public void setEarlyRegistrationDate(Date earlyRegistrationDate) {
        this.earlyRegistrationDate = earlyRegistrationDate;
    }

    /**
     * Gets the early registration date in string form.
     * 
     * @return The early registration date string.
     */
    public String getEarlyRegistrationDateString() {
        return ConferenceSettings.df.format(this.earlyRegistrationDate);
    }

    /**
     * Sets the early registration date.
     * 
     * @param earlyRegistrationDate
     *            The early registration date in string form.
     * @throws ParseException
     */
    public void setEarlyRegistrationDate(String earlyRegistrationDate)
            throws ParseException {
        this.earlyRegistrationDate = ConferenceSettings.df
                .parse(earlyRegistrationDate);
    }

    /**
     * Gets the late registration date.
     * 
     * @return The late Registration Date.
     */
    public Date getLateRegistrationDate() {
        return lateRegistrationDate;
    }

    /**
     * Sets the late registration date.
     * 
     * @param lateRegistrationDate
     *            The late Registration Date to set.
     */
    public void setLateRegistrationDate(Date lateRegistrationDate) {
        this.lateRegistrationDate = lateRegistrationDate;
    }

    /**
     * Gets the late registration date in string form.
     * 
     * @return The late registration date string.
     */
    public String getLateRegistrationDateString() {
        return ConferenceSettings.df.format(this.lateRegistrationDate);
    }

    /**
     * Sets the late registration date.
     * 
     * @param lateRegistrationDate
     *            The late registration date in string form.
     * @throws ParseException
     */
    public void setLateRegistrationDate(String lateRegistrationDate)
            throws ParseException {
        this.lateRegistrationDate = ConferenceSettings.df
                .parse(lateRegistrationDate);
    }

    /**
     * Gets the max attendees per council.
     * 
     * @return The maximum number of attendees per council.
     */
    public int getMaxAttendees() {
        return maxAttendees;
    }

    /**
     * Sets the max attendees per council.
     * 
     * @param maxAttendees
     *            The maximum attendees to set.
     */
    public void setMaxAttendees(int maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    /**
     * Gets the current registration fee.
     * 
     * @return The current registration fee.
     */
    public double getRegistrationFee() {
        Date today = new Date();
        if (today.before(this.earlyRegistrationDate)) {
            return this.earlyRegistrationFee;
        } else if (today.before(this.lateRegistrationDate)) {
            return this.lateRegistrationFee;
        } else {
            return this.lateRegistrationFee;
        }
    }

    /**
     * Is registration open.
     * 
     * @return True if registration is open; otherwise false.
     */
    public boolean isRegistrationOpen() {
        Date today = new Date();
        return today.before(lateRegistrationDate);
    }

    /**
     * Is early registration open.
     * 
     * @return True if early registration is open; otherwise false.
     */
    public boolean isEarlyRegistrationOpen() {
        Date today = new Date();
        return today.before(earlyRegistrationDate);
    }

    /**
     * Gets the instance of the ConferenceSettings object.
     */
    @SuppressWarnings("unchecked")
    public static ConferenceSettings GetConferenceSettings(PersistenceManager pm) {
        String query = "select from " + ConferenceSettings.class.getName();
        List<ConferenceSettings> cs = (List<ConferenceSettings>) pm.newQuery(
                query).execute();
        if (cs.size() == 1) {
            return cs.get(0);
        } else if (cs.size() == 0) {
            ConferenceSettings c = new ConferenceSettings("My Conference", 10,
                    20, new Date(), new Date(), 1);
            pm.makePersistent(c);
            return c;
        } else {
            return null;
        }
    }
}
