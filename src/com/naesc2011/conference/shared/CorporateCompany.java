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
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class CorporateCompany {
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
	 * @param name
	 */
	public CorporateCompany(String name){
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	public Key getKey(){
		return this.key;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * 
	 * @param pm
	 * @param company
	 */
	public static void InsertCompany(PersistenceManager pm, CorporateCompany company){
		pm.makePersistent(company);
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public static List<CorporateCompany> GetAllCompanies(PersistenceManager pm){
		String query = "select from " + CorporateCompany.class.getName();
	    return (List<CorporateCompany>) pm.newQuery(query).execute();
	}
	
	/**
	 * 
	 * @param pm
	 * @param i
	 * @return
	 */
	public static CorporateCompany GetCompany(PersistenceManager pm, String id ){
		int i = Integer.parseInt(id);
		Key key = KeyFactory.createKey(CorporateCompany.class.getSimpleName(), i);
		CorporateCompany c = pm.getObjectById(CorporateCompany.class, key);
		return c;
	}
}
