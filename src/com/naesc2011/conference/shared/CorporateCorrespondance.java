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
public class CorporateCorrespondance {
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
	private String correspondanceType;

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
	private String reasonForCorrespondance;

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
	
}
