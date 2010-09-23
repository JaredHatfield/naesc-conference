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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class ConferenceAttendee {
    /**
     * The key.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     * The attendee's first name.
     */
    @Persistent
    private String firstName;

    /**
     * The attendee's middle name.
     */
    @Persistent
    private String middleName;

    /**
     * The attendee's last name.
     */
    @Persistent
    private String lastName;

    /**
     * The attendee's major.
     */
    @Persistent
    private String major;

    /**
     * The attendee's email.
     */
    @Persistent
    private String email;

    /**
     * The attendee's gender.
     */
    @Persistent
    private Gender gender;

    /**
     * The attendee's shirt size.
     */
    @Persistent
    private ShirtSize shirtSize;

    /**
     * The emergency contact's name.
     */
    @Persistent
    private String emergencyContactName;

    /**
     * The emergency contact's phone.
     */
    @Persistent
    private String emergencyContactPhone;

    /**
     * The arrival information.
     */
    @Persistent
    private String arrivalInformation;

    /**
     * The vegetarian option.
     */
    @Persistent
    private boolean vegetarian;

    /**
     * The list of allergies.
     */
    @Persistent
    private String allergies;

    /**
     * The attendee's voting status.
     */
    private VoteStatus voteStatus;

    /**
     * The resume.
     */
    private BlobKey resume;

    /**
     *
     */
    public enum VoteStatus {
        NONE, VOTING, ALTERNATE
    }

    /**
     * The valid options for gender.
     */
    public enum Gender {
        MALE, FEMALE
    }

    /**
     * The valid options for shirt size.
     */
    public enum ShirtSize {
        S, M, L, XL, XXL
    }

    /**
     * Creates a new instance of ConferenceAttendee.
     */
    public ConferenceAttendee() {
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName
     *            the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     */
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * @return the major
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major
     *            the major to set
     */
    public void setMajor(String major) {
        this.major = major;
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
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @return the shirtSize
     */
    public ShirtSize getShirtSize() {
        return shirtSize;
    }

    /**
     * @param shirtSize
     *            the shirtSize to set
     */
    public void setShirtSize(ShirtSize shirtSize) {
        this.shirtSize = shirtSize;
    }

    /**
     * @return the emergencyContactName
     */
    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    /**
     * @param emergencyContactName
     *            the emergencyContactName to set
     */
    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    /**
     * @return the emergencyContactPhone
     */
    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    /**
     * @param emergencyContactPhone
     *            the emergencyContactPhone to set
     */
    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    /**
     * @return the arrivalInformation
     */
    public String getArrivalInformation() {
        return arrivalInformation;
    }

    /**
     * @param arrivalInformation
     *            the arrivalInformation to set
     */
    public void setArrivalInformation(String arrivalInformation) {
        this.arrivalInformation = arrivalInformation;
    }

    /**
     * @return the vegetarian option
     */
    public boolean getVegetarian() {
        return vegetarian;
    }

    /**
     * @param vegetarian
     *            the vegetarian to set
     */
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    /**
     * @return the allergies
     */
    public String getAllergies() {
        return allergies;
    }

    /**
     * @param allergies
     *            the allergies to set
     */
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    /**
     * @return the voteStatus
     */
    public VoteStatus getVoteStatus() {
        return voteStatus;
    }

    /**
     * @param voteStatus
     *            the voteStatus to set
     */
    public void setVoteStatus(VoteStatus voteStatus) {
        this.voteStatus = voteStatus;
    }

    /**
     * @return the resume
     */
    public BlobKey getResume() {
        return resume;
    }

    /**
     * @param resume
     *            the resume to set
     */
    public void setResume(BlobKey resume) {
        this.resume = resume;
    }

    /**
     * 
     * @return
     */
    public String getResumeKey() {
        String s = this.key.toString();
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @param pm
     * @param attendee
     */
    public static void InsertAttendee(PersistenceManager pm,
            ConferenceAttendee attendee) {
        pm.makePersistent(attendee);
    }

    /**
     * 
     * @param pm
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<ConferenceAttendee> GetAllAttendees(PersistenceManager pm) {
        String query = "select from " + ConferenceAttendee.class.getName();
        return (List<ConferenceAttendee>) pm.newQuery(query).execute();
    }

    /**
     * 
     * @param pm
     * @param i
     * @return
     */
    public static ConferenceAttendee GetAttendee(PersistenceManager pm,
            String id) {
        int i = Integer.parseInt(id);
        Key key = KeyFactory.createKey(
                ConferenceAttendee.class.getSimpleName(), i);
        ConferenceAttendee a = pm.getObjectById(ConferenceAttendee.class, key);
        return a;
    }
}
