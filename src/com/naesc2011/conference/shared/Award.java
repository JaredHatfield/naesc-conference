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
public class Award {
    /**
     * The key.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     * The application name.
     */
    @Persistent
    private String name;

    /**
     * The first question.
     */
    @Persistent
    private String question1;

    /**
     * The second question.
     */
    @Persistent
    private String question2;

    /**
     * The third question.
     */
    @Persistent
    private String question3;

    /**
     * The forth question.
     */
    @Persistent
    private String question4;

    /**
     * Initializes a new instance of an Award.
     * 
     * @param name
     *            The name of the application.
     * @param one
     *            The first question.
     * @param two
     *            The second question.
     * @param three
     *            The third question.
     * @param four
     *            The forth question.
     */
    public Award(String name, String one, String two, String three, String four) {
        this.name = name;
        this.question1 = one;
        this.question2 = two;
        this.question3 = three;
        this.question4 = four;
    }

    /**
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        String s = name.replaceAll("\\<.*?>", "");
        if (!this.name.equals(s)) {
            this.name = s;
        }
    }

    /**
     * @return the question1
     */
    public String getQuestion1() {
        return question1;
    }

    /**
     * @param question1
     *            the question1 to set
     */
    public void setQuestion1(String question1) {
        String s = question1.replaceAll("\\<.*?>", "");
        if (!this.question1.equals(s)) {
            this.question1 = s;
        }
    }

    /**
     * @return the question2
     */
    public String getQuestion2() {
        return question2;
    }

    /**
     * @param question2
     *            the question2 to set
     */
    public void setQuestion2(String question2) {
        String s = question2.replaceAll("\\<.*?>", "");
        if (!this.question2.equals(s)) {
            this.question2 = s;
        }
    }

    /**
     * @return the question3
     */
    public String getQuestion3() {
        return question3;
    }

    /**
     * @param question3
     *            the question3 to set
     */
    public void setQuestion3(String question3) {
        String s = question3.replaceAll("\\<.*?>", "");
        if (!this.question3.equals(s)) {
            this.question3 = s;
        }
    }

    /**
     * @return the question4
     */
    public String getQuestion4() {
        return question4;
    }

    /**
     * @param question4
     *            the question4 to set
     */
    public void setQuestion4(String question4) {
        String s = question4.replaceAll("\\<.*?>", "");
        if (!this.question4.equals(s)) {
            this.question4 = s;
        }
    }

    /**
     * 
     * @param pm
     * @param award
     */
    public static void InsertAward(PersistenceManager pm, Award award) {
        pm.makePersistent(award);
    }

    /**
     * 
     * @param pm
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Award> GetAllAwards(PersistenceManager pm) {
        String query = "select from " + Award.class.getName();
        return (List<Award>) pm.newQuery(query).execute();
    }

    /**
     * 
     * @param pm
     * @param id
     * @return
     */
    public static Award GetAward(PersistenceManager pm, String id) {
        int i = Integer.parseInt(id);
        Key key = KeyFactory.createKey(Award.class.getSimpleName(), i);
        Award a = pm.getObjectById(Award.class, key);
        return a;
    }

    /**
     * 
     * @param pm
     * @param key
     * @return
     */
    public static Award GetAward(PersistenceManager pm, Key key) {
        Award a = pm.getObjectById(Award.class, key);
        return a;
    }
}
