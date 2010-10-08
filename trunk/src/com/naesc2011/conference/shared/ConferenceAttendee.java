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
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ConferenceAttendee {

    /**
     * The key.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     * The date the attendee was added.
     */
    @Persistent
    private Date added;

    /**
     * The date the attendee was updated.
     */
    @Persistent
    private Date updated;

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
    @Persistent
    private VoteStatus voteStatus;

    /**
     * The selected tour.
     */
    @Persistent
    private Key tour;

    /**
     * The resume.
     */
    @Persistent
    private BlobKey resume;

    /**
     * The registration fee.
     */
    @Persistent
    private double registartionFee;

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
    public ConferenceAttendee(double registrationFee, String email) {
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";
        this.major = "";
        this.email = email;
        this.emergencyContactName = "";
        this.emergencyContactPhone = "";
        this.arrivalInformation = "";
        this.allergies = "";
        this.vegetarian = false;
        Date now = new Date();
        this.voteStatus = VoteStatus.NONE;
        this.gender = Gender.MALE;
        this.shirtSize = ShirtSize.S;
        this.added = now;
        this.updated = now;
        this.setRegistartionFee(registrationFee);
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
     * Gets the added date.
     * 
     * @return The added date.
     */
    public Date getAdded() {
        return added;
    }

    /**
     * Gets the updated date.
     * 
     * @return The updated date.
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * Set the new updated time to now.
     */
    public void update() {
        this.updated = new Date();
    }

    /**
     * Gets the first name.
     * 
     * @return The first Name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     * 
     * @param firstName
     *            The first Name to set.
     */
    public void setFirstName(String firstName) {
        String s = firstName.replaceAll("\\<.*?>", "");
        if (!this.firstName.equals(s)) {
            this.firstName = s;
        }
    }

    /**
     * Gets the middle name.
     * 
     * @return The middleName.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name.
     * 
     * @param middleName
     *            The middle Name to set.
     */
    public void setMiddleName(String middleName) {
        String s = middleName.replaceAll("\\<.*?>", "");
        if (!this.middleName.equals(s)) {
            this.middleName = s;
        }
    }

    /**
     * Gets the last name.
     * 
     * @return The last Name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     * 
     * @param lastName
     *            The last Name to set.
     */
    public void setLastName(String lastName) {
        String s = lastName.replaceAll("\\<.*?>", "");
        if (!this.lastName.equals(s)) {
            this.lastName = s;
        }
    }

    /**
     * Gets the attendee's full name.
     */
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Gets the major.
     * 
     * @return The major.
     */
    public String getMajor() {
        return major;
    }

    /**
     * Sets the major.
     * 
     * @param major
     *            The major to set.
     */
    public void setMajor(String major) {
        String s = major.replaceAll("\\<.*?>", "");
        if (!this.major.equals(s)) {
            this.major = s;
        }
    }

    /**
     * Gets the email.
     * 
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * 
     * @param email
     *            The email to set.
     */
    public void setEmail(String email) {
        String s = email.replaceAll("\\<.*?>", "");
        if (!this.email.equals(s)) {
            this.email = s;
        }
    }

    /**
     * Gets the gender.
     * 
     * @return The gender.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     * 
     * @param gender
     *            The gender to set.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Gets the shirt size.
     * 
     * @return The shirt Size.
     */
    public ShirtSize getShirtSize() {
        return shirtSize;
    }

    /**
     * Sets the shirt size.
     * 
     * @param shirtSize
     *            The shirt Size to set.
     */
    public void setShirtSize(ShirtSize shirtSize) {
        this.shirtSize = shirtSize;
    }

    /**
     * Gets the emergency contact name.
     * 
     * @return The emergency Contact Name.
     */
    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    /**
     * Sets the emergencyContactName.
     * 
     * @param emergencyContactName
     *            The emergency Contact Name to set.
     */
    public void setEmergencyContactName(String emergencyContactName) {
        String s = emergencyContactName.replaceAll("\\<.*?>", "");
        if (!this.emergencyContactName.equals(s)) {
            this.emergencyContactName = s;
        }
    }

    /**
     * Gets the emergency contact phone.
     * 
     * @return The emergency Contact Phone.
     */
    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    /**
     * Sets the emergency contact phone.
     * 
     * @param emergencyContactPhone
     *            The emergency Contact Phone to set.
     */
    public void setEmergencyContactPhone(String emergencyContactPhone) {
        String s = emergencyContactPhone.replaceAll("\\<.*?>", "");
        if (!this.emergencyContactPhone.equals(s)) {
            this.emergencyContactPhone = s;
        }
    }

    /**
     * Gets the arrival information.
     * 
     * @return The arrival Information.
     */
    public String getArrivalInformation() {
        return arrivalInformation;
    }

    /**
     * Sets the arrival information.
     * 
     * @param arrivalInformation
     *            The arrival Information to set.
     */
    public void setArrivalInformation(String arrivalInformation) {
        String s = arrivalInformation.replaceAll("\\<.*?>", "");
        if (!this.arrivalInformation.equals(s)) {
            this.arrivalInformation = s;
        }
    }

    /**
     * Gets the vegetarian flag.
     * 
     * @return The vegetarian flag.
     */
    public boolean getVegetarian() {
        return vegetarian;
    }

    /**
     * Sets the vegetarian flag.
     * 
     * @param vegetarian
     *            The vegetarian flag to set.
     */
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    /**
     * Gets the allergies.
     * 
     * @return The allergies.
     */
    public String getAllergies() {
        return allergies;
    }

    /**
     * Sets the allergies.
     * 
     * @param allergies
     *            The allergies to set.
     */
    public void setAllergies(String allergies) {
        String s = allergies.replaceAll("\\<.*?>", "");
        if (!this.allergies.equals(s)) {
            this.allergies = s;
        }
    }

    /**
     * Gets the vote status.
     * 
     * @return The vote Status.
     */
    public VoteStatus getVoteStatus() {
        if (voteStatus == null) {
            return VoteStatus.NONE;
        } else {
            return voteStatus;
        }
    }

    /**
     * Sets the vote status.
     * 
     * @param voteStatus
     *            The vote Status to set.
     */
    public void setVoteStatus(VoteStatus voteStatus) {
        this.voteStatus = voteStatus;
    }

    /**
     * Gets the tour.
     * 
     * @return The tour.
     */
    public Key getTour() {
        return tour;
    }

    /**
     * Sets the tour.
     * 
     * @param tour
     *            The tour to set.
     */
    public void setTour(Key tour) {
        this.tour = tour;
    }

    /**
     * Gets the resume.
     * 
     * @return The resume.
     */
    public BlobKey getResume() {
        return resume;
    }

    /**
     * Sets the resume.
     * 
     * @param resume
     *            The resume to set.
     */
    public void setResume(BlobKey resume) {
        this.resume = resume;
    }

    /**
     * Gets the resume key.
     * 
     * @return The resume key.
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
     * Gets the registration fee.
     * 
     * @return The registration Fee.
     */
    public double getRegistartionFee() {
        return registartionFee;
    }

    /**
     * Sets the registration fee.
     * 
     * @param registartionFee
     *            The registration Fee to set.
     */
    public void setRegistartionFee(double registartionFee) {
        this.registartionFee = registartionFee;
    }

    /**
     * Performs a check to see if the attendee record has all of the required
     * fields.
     * 
     * @return True if the attendee record has all of the required fields.
     */
    public boolean isAttendeeComplete() {
        return ((major.length() > 0) && (email.length() > 6)
                && (gender != null) && (shirtSize != null)
                && (emergencyContactName.length() > 0)
                && (emergencyContactPhone.length() > 6)
                && (arrivalInformation.length() > 0) && (voteStatus != null)
                && (tour != null) && (resume != null));
    }
}
