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

public class ConferenceAttendee {
	/**
	 * 
	 */
	private String name;
	
	/**
	 * 
	 */
	private String council;
	
	/**
	 * 
	 */
	private String major;
	
	/**
	 * 
	 */
	private String email;
	
	/**
	 * 
	 */
	private Boolean delegate;
	
	/**
	 * 
	 */
	private Gender gender;
	
	/**
	 * 
	 */
	private ShirtSize shirtSize;
	
	/**
	 * 
	 */
	private String emergencyContact;
	
	/**
	 * 
	 */
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
