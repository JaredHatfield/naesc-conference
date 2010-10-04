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
     * 
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

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
     * 
     * @param earlyFee
     * @param lateFee
     * @param earlyDate
     * @param lateDate
     */
    private ConferenceSettings(double earlyFee, double lateFee, Date earlyDate,
            Date lateDate, int maxAttendees) {
        this.earlyRegistrationFee = earlyFee;
        this.lateRegistrationFee = lateFee;
        this.earlyRegistrationDate = earlyDate;
        this.lateRegistrationDate = lateDate;
        this.maxAttendees = maxAttendees;
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
     * @return the earlyRegistrationFee
     */
    public double getEarlyRegistrationFee() {
        return earlyRegistrationFee;
    }

    /**
     * @param earlyRegistrationFee
     *            the earlyRegistrationFee to set
     */
    public void setEarlyRegistrationFee(double earlyRegistrationFee) {
        this.earlyRegistrationFee = earlyRegistrationFee;
    }

    /**
     * @return the lateRegistrationFee
     */
    public double getLateRegistrationFee() {
        return lateRegistrationFee;
    }

    /**
     * @param lateRegistrationFee
     *            the lateRegistrationFee to set
     */
    public void setLateRegistrationFee(double lateRegistrationFee) {
        this.lateRegistrationFee = lateRegistrationFee;
    }

    /**
     * @return the earlyRegistrationDate
     */
    public Date getEarlyRegistrationDate() {
        return earlyRegistrationDate;
    }

    /**
     * 
     * @param lateRegistrationDate
     * @throws ParseException
     */
    public void setLateRegistrationDate(String lateRegistrationDate)
            throws ParseException {
        this.lateRegistrationDate = ConferenceSettings.df
                .parse(lateRegistrationDate);
    }

    /**
     * 
     * @return
     */
    public String getEarlyRegistrationDateString() {
        return ConferenceSettings.df.format(this.earlyRegistrationDate);
    }

    /**
     * @param earlyRegistrationDate
     *            the earlyRegistrationDate to set
     */
    public void setEarlyRegistrationDate(Date earlyRegistrationDate) {
        this.earlyRegistrationDate = earlyRegistrationDate;
    }

    /**
     * @return the lateRegistrationDate
     */
    public Date getLateRegistrationDate() {
        return lateRegistrationDate;
    }

    /**
     * 
     * @param earlyRegistrationDate
     * @throws ParseException
     */
    public void setEarlyRegistrationDate(String earlyRegistrationDate)
            throws ParseException {
        this.earlyRegistrationDate = ConferenceSettings.df
                .parse(earlyRegistrationDate);
    }

    /**
     * 
     * @return
     */
    public String getLateRegistrationDateString() {
        return ConferenceSettings.df.format(this.lateRegistrationDate);
    }

    /**
     * @param lateRegistrationDate
     *            the lateRegistrationDate to set
     */
    public void setLateRegistrationDate(Date lateRegistrationDate) {
        this.lateRegistrationDate = lateRegistrationDate;
    }

    /**
     * @return the maxAttendees
     */
    public int getMaxAttendees() {
        return maxAttendees;
    }

    /**
     * @param maxAttendees
     *            the maxAttendees to set
     */
    public void setMaxAttendees(int maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    /**
     * 
     * @return
     * @throws Exception
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
     * Gets the conference settings
     */
    @SuppressWarnings("unchecked")
    public static ConferenceSettings GetConferenceSettings(PersistenceManager pm) {
        String query = "select from " + ConferenceSettings.class.getName();
        List<ConferenceSettings> cs = (List<ConferenceSettings>) pm.newQuery(
                query).execute();
        if (cs.size() == 1) {
            return cs.get(0);
        } else if (cs.size() == 0) {
            ConferenceSettings c = new ConferenceSettings(10, 20, new Date(),
                    new Date(), 1);
            pm.makePersistent(c);
            return c;
        } else {
            return null;
        }
    }
}
