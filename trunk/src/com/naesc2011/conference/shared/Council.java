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
     * The key.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     * The council name.
     */
    @Persistent
    private String name;

    /**
     * The university.
     */
    @Persistent
    private String university;

    /**
     * The location.
     */
    @Persistent
    private String location;

    /**
     * The contact.
     */
    @Persistent
    private String contact;

    /**
     * The list of attendees.
     */
    @Persistent
    @Element(dependent = "true")
    private List<ConferenceAttendee> attendees;

    /**
     * The website.
     */
    @Persistent
    private String website;

    /**
     * The amount of money the council has Paid
     */
    @Persistent
    private double amountPaid;

    /**
     * The notes about the payment.
     */
    @Persistent
    private String paymentNotes;

    /**
     * The list of award submissions.
     */
    @Persistent
    @Element(dependent = "true")
    private List<AwardSubmission> awardSubmissions;

    /**
     * Creates a new instance of the Council class.
     * 
     * @param name
     *            The council name.
     * @param university
     *            The university.
     * @param location
     *            The location.
     * @param contact
     *            The contact.
     */
    public Council(String name, String university, String location,
            String contact) {
        this.setName(name);
        this.setUniversity(university);
        this.setLocation(location);
        this.setContact(contact);
        this.setWebsite("");
        this.setAmountPaid(0);
        this.setPaymentNotes("");
        this.attendees = new ArrayList<ConferenceAttendee>();
        this.awardSubmissions = new ArrayList<AwardSubmission>();
    }

    /**
     * Sets the voting delegate to the specified attendee id.
     * 
     * @param id
     *            The id of the attendee to designate as the voting delegate.
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
     * Sets the alternate delegate to the attendee with the specified id.
     * 
     * @param id
     *            The id of the attendee to designate as an alternate delegate.
     */
    public void SetAlternateDeleaget(String id) {
        for (int i = 0; i < this.attendees.size(); i++) {
            ConferenceAttendee att = this.attendees.get(i);
            if ((att.getKey().getId() + "").equals(id)) {
                att.setVoteStatus(VoteStatus.ALTERNATE);
            } else if (att.getVoteStatus() == null) {
                att.setVoteStatus(VoteStatus.NONE);
            } else if (att.getVoteStatus().equals(VoteStatus.ALTERNATE)) {
                att.setVoteStatus(VoteStatus.NONE);
            }
        }
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
     * Gets the university.
     * 
     * @return The university.
     */
    public String getUniversity() {
        return university;
    }

    /**
     * Sets the university.
     * 
     * @param university
     *            The university to set.
     */
    public void setUniversity(String university) {
        String s = university.replaceAll("\\<.*?>", "");
        if (!this.university.equals(s)) {
            this.university = s;
        }
    }

    /**
     * Gets the location.
     * 
     * @return The location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location.
     * 
     * @param location
     *            The location to set.
     */
    public void setLocation(String location) {
        String s = location.replaceAll("\\<.*?>", "");
        if (!this.location.equals(s)) {
            this.location = s;
        }
    }

    /**
     * Gets the contact.
     * 
     * @return The contact.
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the contact.
     * 
     * @param contact
     *            The contact to set.
     */
    public void setContact(String contact) {
        String s = contact.replaceAll("\\<.*?>", "");
        if (!this.contact.equals(s)) {
            this.contact = s;
        }
    }

    /**
     * Gets the list of attendees.
     * 
     * @return The attendee list.
     */
    public List<ConferenceAttendee> getAttendees() {
        return attendees;
    }

    /**
     * Gets the total cost of all of the attendees.
     * 
     * @return The total cost.
     */
    public double getAttendeeCost() {
        double total = 0;
        for (int i = 0; i < this.attendees.size(); i++) {
            total += this.attendees.get(i).getRegistartionFee();
        }

        return total;
    }

    /**
     * Gets the website.
     * 
     * @return The website.
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the website.
     * 
     * @param website
     *            The website to set.
     */
    public void setWebsite(String website) {
        String s = website.replaceAll("\\<.*?>", "");
        if (!this.website.equals(s)) {
            this.website = s;
        }
    }

    /**
     * Gets the amount paid.
     * 
     * @return The amount Paid.
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * Sets the amount paid.
     * 
     * @param amountPaid
     *            The amountPaid to set.
     */
    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * Gets the payment notes.
     * 
     * @return The payment Notes.
     */
    public String getPaymentNotes() {
        return paymentNotes;
    }

    /**
     * Sets the payment notes.
     * 
     * @param paymentNotes
     *            The paymentNotes to set.
     */
    public void setPaymentNotes(String paymentNotes) {
        String s = paymentNotes.replaceAll("\\<.*?>", "");
        if (!this.paymentNotes.equals(s)) {
            this.paymentNotes = s;
        }
    }

    /**
     * Gets the award submissions.
     * 
     * @return The awardSubmissions.
     */
    public List<AwardSubmission> getAwardSubmissions() {
        return awardSubmissions;
    }

    /**
     * Inserts a new council into the datastore.
     * 
     * @param pm
     *            The PersistenceManager.
     * @param council
     *            The Council to add.
     */
    public static void InsertCouncil(PersistenceManager pm, Council council) {
        pm.makePersistent(council);
    }

    /**
     * Gets all of the councils.
     * 
     * @param pm
     *            The PersistenceManager.
     * @return A list of all of the councils.
     */
    @SuppressWarnings("unchecked")
    public static List<Council> GetAllCouncils(PersistenceManager pm) {
        String query = "select from " + Council.class.getName();
        return (List<Council>) pm.newQuery(query).execute();
    }

    /**
     * Gets the specified council.
     * 
     * @param pm
     *            The PersistenceManager.
     * @param id
     *            The identifier for the council requested.
     * @return The requested Council.
     */
    public static Council GetCouncil(PersistenceManager pm, String id) {
        int i = Integer.parseInt(id);
        Key key = KeyFactory.createKey(Council.class.getSimpleName(), i);
        Council c = pm.getObjectById(Council.class, key);
        return c;
    }

    /**
     * Gets the specified council.
     * 
     * @param pm
     *            The PersistenceManager.
     * @param key
     *            The key to identify the council.
     * @return The requested council.
     */
    public static Council GetCouncil(PersistenceManager pm, Key key) {
        Council c = pm.getObjectById(Council.class, key);
        return c;
    }
}
