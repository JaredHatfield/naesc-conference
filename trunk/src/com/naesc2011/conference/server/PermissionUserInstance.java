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
     * The key.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     * The user id.
     */
    @Persistent
    private String userId;

    /**
     * The user.
     */
    @Persistent
    private User user;

    /**
     * The user's email.
     */
    @Persistent
    private String email;

    /**
     * The user's permission level.
     */
    @Persistent
    private Permission userPermission;

    /**
     * The possible permission levels.
     */
    public enum Permission {
        UNAUTHENTICATED, AUTHENTICATED, USER, MANAGER, ADMIN
    }

    /**
     * Creates a new instance of the PermissionUserInstance class.
     */
    public PermissionUserInstance(User user, Permission userPermission) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.user = user;
        this.userPermission = userPermission;
    }

    /**
     * Gets the key.
     * 
     * @return The key.
     */
    public Key getKey() {
        return this.key;
    }

    /**
     * Gets the user id.
     * 
     * @return The userId.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     * 
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the user.
     * 
     * @return The user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user.
     * 
     * @param user
     *            The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get the email.
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
        this.email = email;
    }

    /**
     * Gets the user permission.
     * 
     * @return The userPermission.
     */
    public Permission getUserPermission() {
        return userPermission;
    }

    /**
     * Sets the user permission.
     * 
     * @param userPermission
     *            The userPermission to set.
     */
    public void setUserPermission(Permission userPermission) {
        this.userPermission = userPermission;
    }
}
