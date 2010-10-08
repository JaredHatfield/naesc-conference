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

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class AttendeePermission {

    /**
     * The key.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     * The user email.
     */
    @Persistent
    private String email;

    /**
     * The council key.
     */
    @Persistent
    private Key council;

    /**
     * The attendee key.
     */
    @Persistent
    private Key attendee;

    /**
     * 
     * @param email
     * @param council
     * @param attendee
     */
    public AttendeePermission(String email, Key council, Key attendee) {
        this.email = email;
        this.council = council;
        this.attendee = attendee;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the council
     */
    public Key getCouncil() {
        return council;
    }

    /**
     * @return the attendee
     */
    public Key getAttendee() {
        return attendee;
    }

    /**
     * 
     * @param pm
     * @param ap
     */
    public static void InserAttendeePermission(PersistenceManager pm,
            AttendeePermission ap) {
        pm.makePersistent(ap);
    }

    /**
     * 
     * @param pm
     * @param email
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<AttendeePermission> GetPermission(PersistenceManager pm,
            String email) {
        javax.jdo.Query query = pm.newQuery(AttendeePermission.class);
        query.setFilter("email == emailParam");
        query.declareParameters("String emailParam");
        return (List<AttendeePermission>) query.execute(email);
    }

    /**
     * 
     * @param pm
     * @param council
     * @param attendee
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<AttendeePermission> GetPermission(PersistenceManager pm,
            Key council, Key attendee) {
        javax.jdo.Query query = pm.newQuery(AttendeePermission.class);
        query.setFilter("council == councilParam && attendee == attendeeParam");
        query.declareParameters("com.google.appengine.api.datastore.Key councilParam, com.google.appengine.api.datastore.Key attendeeParam");
        return (List<AttendeePermission>) query.execute(council, attendee);
    }

    /**
     * 
     * @param pm
     * @param email
     * @param council
     * @param attendee
     * @return
     */
    public static boolean HasPermission(PersistenceManager pm, String email,
            String council, String attendee) {
        List<AttendeePermission> list = AttendeePermission.GetPermission(pm,
                email);
        if (list == null) {
            return false;
        }

        for (int i = 0; i < list.size(); i++) {
            AttendeePermission a = list.get(i);
            if ((a.getCouncil().getId() + "").equals(council)
                    && (a.getAttendee().getId() + "").equals(attendee)) {
                return true;
            }
        }

        return false;
    }

    public static boolean HasPermission(PersistenceManager pm, String email,
            String council) {
        List<AttendeePermission> list = AttendeePermission.GetPermission(pm,
                email);
        if (list == null) {
            return false;
        }

        for (int i = 0; i < list.size(); i++) {
            AttendeePermission a = list.get(i);
            if ((a.getCouncil().getId() + "").equals(council)) {
                return true;
            }
        }

        return false;
    }
}
