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

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

public class ConferenceAttendee {
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
	private String council;
	
	/**
	 * 
	 */
	@Persistent
	private String major;
	
	/**
	 * 
	 */
	@Persistent
	private String email;
	
	/**
	 * 
	 */
	@Persistent
	private Boolean delegate;
	
	/**
	 * 
	 */
	@Persistent
	private Gender gender;
	
	/**
	 * 
	 */
	@Persistent
	private ShirtSize shirtSize;
	
	/**
	 * 
	 */
	@Persistent
	private String emergencyContact;
	
	/**
	 * 
	 */
	@Persistent
	private String emergencyPhone;
	
	/**
	 *
	 */
	public enum Gender {
	    MALE, FEMALE
	}
	
	/**
	 * 
	 */
	public enum ShirtSize {
		S, M, L, XL, XXL
	}
}
