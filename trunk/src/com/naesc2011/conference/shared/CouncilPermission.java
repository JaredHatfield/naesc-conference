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
import com.naesc2011.conference.server.PermissionManager;

@PersistenceCapable
public class CouncilPermission {

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
     * The council key.
     */
    @Persistent
    private Key council;

    /**
     * Creates a new instance of the CouncilPermission class.
     * 
     * @param userId
     *            The user id.
     * @param council
     *            The council key.
     */
    public CouncilPermission(String userId, Key council) {
        this.userId = userId;
        this.council = council;
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
     * Gets the user id.
     * 
     * @return The user Id.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets the council key.
     * 
     * @return The council key.
     */
    public Key getCouncil() {
        return council;
    }

    /**
     * Inserts the CouncilPermission into the datastore.
     * 
     * @param pm
     *            The PersistenceManager.
     * @param cp
     *            the CouncilPermission to insert.
     */
    public static void InserCouncilPermission(PersistenceManager pm,
            CouncilPermission cp) {
        pm.makePersistent(cp);
    }

    /**
     * Gets all of the CouncilPermission objects.
     * 
     * @param pm
     *            The PersistenceManager.
     * @return A list of all of the CouncilPermissions.
     */
    @SuppressWarnings("unchecked")
    public static List<CouncilPermission> GetPermission(PersistenceManager pm,
            String userId) {
        javax.jdo.Query query = pm.newQuery(CouncilPermission.class);
        query.setFilter("userId == userIdParam");
        query.declareParameters("String userIdParam");
        return (List<CouncilPermission>) query.execute(userId);
    }

    /**
     * Tests if a given user has the necessary permissions to modify a council.
     * 
     * @param pm
     *            The Persistence Manager.
     * @param councilId
     *            The Council's identifier.
     * @param p
     *            The PermissionManager.
     * @return True if the user has permission to modify the council.
     */
    public static boolean HasPermission(PersistenceManager pm,
            String councilId, PermissionManager p) {
        long id = Long.parseLong(councilId);
        List<CouncilPermission> councils = CouncilPermission.GetPermission(pm,
                p.getUser().getUserId());
        Boolean haspermission = false;
        for (int i = 0; i < councils.size(); i++) {
            if (councils.get(i).getCouncil().getId() == id) {
                haspermission = true;
                break;
            }
        }

        return haspermission;
    }
}
