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
package com.naesc2011.conference.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@PersistenceCapable
public class PermissionUserInstance {
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
	private String userId;
	
	/**
	 * 
	 */
	@Persistent
	private User user;
	
	/**
	 * 
	 */
	@Persistent
	private Permission userPermission;
	
	/**
	 * 
	 */
	public enum Permission{
		UNAUTHENTICATED,
		AUTHENTICATED,
		USER,
		MANAGER,
		ADMIN
	}
	
	/**
	 * 
	 */
	public PermissionUserInstance(User user, Permission userPermission){
		this.userId = user.getUserId();
		this.user = user;
		this.userPermission = userPermission;
	}
	
	/**
	 * 
	 * @return
	 */
	public Key getKey(){
		return this.key;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userPermission
	 */
	public Permission getUserPermission() {
		return userPermission;
	}

	/**
	 * @param userPermission the userPermission to set
	 */
	public void setUserPermission(Permission userPermission) {
		this.userPermission = userPermission;
	}
}
