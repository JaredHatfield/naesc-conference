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

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.naesc2011.conference.shared.PMF;

public class PermissionManager {
    /**
	 * 
	 */
    private static final Logger log = Logger.getLogger(PermissionManager.class
            .getName());

    /**
	 * 
	 */
    UserService userService;

    /**
     * The user instance, if the user was logged in.
     */
    private User currentUser;

    /**
	 * 
	 */
    private PermissionUserInstance currentPermissions;

    /**
     * A flag that indicates that the user is an app engine admin.
     */
    private boolean appEngineAdmin;

    /**
	 * 
	 */
    public PermissionManager() {
        this.userService = UserServiceFactory.getUserService();
        this.currentUser = userService.getCurrentUser();

        // Set the admin flag
        this.appEngineAdmin = false;
        if (this.userService.isUserLoggedIn()) {
            this.appEngineAdmin = this.userService.isUserAdmin();
        }

        // Get the user permissions from the datastore.
        if (this.currentUser != null) {
            // Do some other stuff...
            PersistenceManager pm = PMF.get().getPersistenceManager();
            Query query = pm.newQuery(PermissionUserInstance.class);
            query.setFilter("userId == userIdParam");
            query.declareParameters("String userIdParam");
            try {
                @SuppressWarnings("unchecked")
                List<PermissionUserInstance> results = (List<PermissionUserInstance>) query
                        .execute(this.currentUser.getUserId());
                if (results.size() == 1) {
                    this.currentPermissions = results.get(0);
                    this.currentPermissions.setUser(this.currentUser);
                    this.currentPermissions.setEmail(this.currentUser
                            .getEmail());

                    log.info("Located the user's permissions."
                            + this.currentPermissions.getUserPermission());
                } else if (results.size() == 0) {
                    // Put the data into the datastore
                    this.currentPermissions = new PermissionUserInstance(
                            this.currentUser,
                            PermissionUserInstance.Permission.AUTHENTICATED);
                    pm.makePersistent(this.currentPermissions);
                    log.info("User permissions not found, defaulting to "
                            + this.currentPermissions.getUserPermission());
                } else {
                    log.warning("Multiple entries in the datastore exist for "
                            + this.currentUser.getUserId());
                }
            } finally {
                query.closeAll();
            }

            pm.close();
        }
    }

    /**
     * 
     * @return
     */
    public Boolean IsUserLoggedIn() {
        if (currentUser == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 
     * @return
     */
    public Boolean IsUserAdmin() {
        return this.appEngineAdmin;
    }

    /**
     * 
     * @return
     */
    public User getUser() {
        return this.currentUser;
    }

    /**
     * 
     * @return
     */
    public UserService getUserService() {
        return this.userService;
    }

    /**
     * 
     * @return
     */
    public PermissionUserInstance.Permission GetPermissionLevel() {
        if (this.currentPermissions != null) {
            return this.currentPermissions.getUserPermission();
        } else {
            return PermissionUserInstance.Permission.UNAUTHENTICATED;
        }
    }

    /**
     * 
     * @param pm
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<PermissionUserInstance> GetAllUsers(PersistenceManager pm) {
        String query = "select from " + PermissionUserInstance.class.getName();
        return (List<PermissionUserInstance>) pm.newQuery(query).execute();
    }

    /**
     * 
     * @param userId
     * @param permission
     */
    @SuppressWarnings("unchecked")
    public static void SetPermission(String userId,
            PermissionUserInstance.Permission permission) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(PermissionUserInstance.class);
        query.setFilter("userId == userIdParam");
        query.declareParameters("String userIdParam");
        try {
            List<PermissionUserInstance> results = (List<PermissionUserInstance>) query
                    .execute(userId);
            if (results.size() == 1) {
                results.get(0).setUserPermission(permission);
            }
        } finally {
            query.closeAll();
        }

        pm.close();
    }

    /**
     * Set up the information that is required to log in a user.
     * 
     * @param p
     * @param request
     * @return
     */
    public static boolean SetUpPermissions(PermissionManager p,
            HttpServletRequest request) {
        if (p.IsUserLoggedIn()) {
            // The user is logged in
            request.setAttribute("authenticated", true);
            request.setAttribute("username", p.getUser().getNickname());
            request.setAttribute("logouturl", p.getUserService()
                    .createLogoutURL("/home"));
            request.setAttribute("isadmin", p.IsUserAdmin());

            return true;
        } else {
            // The user is NOT logged in
            request.setAttribute("authenticated", false);
            request.setAttribute("loginurl",
                    p.getUserService().createLoginURL(request.getRequestURI()));
            request.setAttribute("isadmin", false);
            return false;
        }
    }
}
