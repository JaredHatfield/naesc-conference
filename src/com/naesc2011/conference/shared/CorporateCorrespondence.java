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

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class CorporateCorrespondence {
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
	private Date startTime;

	/**
	 * 
	 */
	@Persistent
	private Date endTime;

	/**
	 * 
	 */
	@Persistent
	private String correspondenceType;

	/**
	 * 
	 */
	@Persistent
	private String representativeName;

	/**
	 * 
	 */
	@Persistent
	private String representativeTitle;

	/**
	 * 
	 */
	@Persistent
	private String reasonForCorrespondence;

	/**
	 * 
	 */
	@Persistent
	private String outcome;

	/**
	 * 
	 */
	@Persistent
	private String nextSteps;

	/**
	 * 
	 */
	@Persistent
	private String notes;
	
	/**
	 * 
	 * @param outcome
	 * @param nextSteps
	 * @param notes
	 */
	public CorporateCorrespondence(String outcome, String nextSteps, String notes){
		this.outcome = outcome;
		this.nextSteps = nextSteps;
		this.notes = notes;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the correspondenceType
	 */
	public String getCorrespondenceType() {
		return correspondenceType;
	}

	/**
	 * @param correspondenceType the correspondenceType to set
	 */
	public void setCorrespondenceType(String correspondenceType) {
		this.correspondenceType = correspondenceType;
	}

	/**
	 * @return the representativeName
	 */
	public String getRepresentativeName() {
		return representativeName;
	}

	/**
	 * @param representativeName the representativeName to set
	 */
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	/**
	 * @return the representativeTitle
	 */
	public String getRepresentativeTitle() {
		return representativeTitle;
	}

	/**
	 * @param representativeTitle the representativeTitle to set
	 */
	public void setRepresentativeTitle(String representativeTitle) {
		this.representativeTitle = representativeTitle;
	}

	/**
	 * @return the reasonForCorrespondence
	 */
	public String getReasonForCorrespondence() {
		return reasonForCorrespondence;
	}

	/**
	 * @param reasonForCorrespondence the reasonForCorrespondence to set
	 */
	public void setReasonForCorrespondence(String reasonForCorrespondence) {
		this.reasonForCorrespondence = reasonForCorrespondence;
	}

	/**
	 * @return the outcome
	 */
	public String getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome the outcome to set
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/**
	 * @return the nextSteps
	 */
	public String getNextSteps() {
		return nextSteps;
	}

	/**
	 * @param nextSteps the nextSteps to set
	 */
	public void setNextSteps(String nextSteps) {
		this.nextSteps = nextSteps;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}
}
