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
import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class AwardApplication {

    /**
     * The key.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     * The first question.
     */
    @Persistent
    private Text question1;

    /**
     * The second question.
     */
    @Persistent
    private Text question2;

    /**
     * The third question.
     */
    @Persistent
    private Text question3;

    /**
     * The forth question.
     */
    @Persistent
    private Text question4;

    /**
     * Initializes a new instance of an AwardApplication.
     * 
     * @param one
     *            The first question.
     * @param two
     *            The second question.
     * @param three
     *            The third question.
     * @param four
     *            The forth question.
     */
    public AwardApplication(String one, String two, String three, String four) {
        this.question1 = new Text("");
        this.question2 = new Text("");
        this.question3 = new Text("");
        this.question4 = new Text("");
        this.setQuestion1(one);
        this.setQuestion2(two);
        this.setQuestion3(three);
        this.setQuestion4(four);
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
     * Gets question 1.
     * 
     * @return The question 1.
     */
    public Text getQuestion1() {
        return question1;
    }

    /**
     * Sets the question 1.
     * 
     * @param question1
     *            The question 1 to set.
     */
    public void setQuestion1(String question1) {
        String s = question1.replaceAll("\\<.*?>", "");
        if (!this.question1.getValue().equals(s)) {
            this.question1 = new Text(s);
        }
    }

    /**
     * Gets the question 2.
     * 
     * @return The question 2.
     */
    public Text getQuestion2() {
        return question2;
    }

    /**
     * Sets the question 2.
     * 
     * @param question2
     *            The question 2 to set.
     */
    public void setQuestion2(String question2) {
        String s = question2.replaceAll("\\<.*?>", "");
        if (!this.question2.getValue().equals(s)) {
            this.question2 = new Text(s);
        }
    }

    /**
     * Gets the question 3.
     * 
     * @return The question 3.
     */
    public Text getQuestion3() {
        return question3;
    }

    /**
     * Sets the question 3.
     * 
     * @param question3
     *            The question 3 to set.
     */
    public void setQuestion3(String question3) {
        String s = question3.replaceAll("\\<.*?>", "");
        if (!this.question3.getValue().equals(s)) {
            this.question3 = new Text(s);
        }
    }

    /**
     * Gets the question 4.
     * 
     * @return The question 4.
     */
    public Text getQuestion4() {
        return question4;
    }

    /**
     * Sets the question 4.
     * 
     * @param question4
     *            The question 4 to set.
     */
    public void setQuestion4(String question4) {
        String s = question4.replaceAll("\\<.*?>", "");
        if (!this.question4.getValue().equals(s)) {
            this.question4 = new Text(s);
        }
    }

    /**
     * Inserts a new AwardApplication into the datastore.
     * 
     * @param pm
     *            The PersistenceManager.
     * @param AwardApplication
     *            The AwardApplication to add.
     */
    public static void InsertAward(PersistenceManager pm,
            AwardApplication AwardApplication) {
        pm.makePersistent(AwardApplication);
    }

    /**
     * Gets all of the AwardApplications.
     * 
     * @param pm
     *            The PersistenceManager.
     * @return The list of AwardApplications.
     */
    @SuppressWarnings("unchecked")
    public static List<AwardApplication> GetAllAwards(PersistenceManager pm) {
        String query = "select from " + AwardApplication.class.getName();
        return (List<AwardApplication>) pm.newQuery(query).execute();
    }

    /**
     * Gets the requested AwardApplication.
     * 
     * @param pm
     *            The PersistenceManager.
     * @param id
     *            The id for the AwardApplication.
     * @return The requested AwardApplication.
     */
    public static AwardApplication GetAward(PersistenceManager pm, String id) {
        int i = Integer.parseInt(id);
        Key key = KeyFactory.createKey(AwardApplication.class.getSimpleName(),
                i);
        AwardApplication a = pm.getObjectById(AwardApplication.class, key);
        return a;
    }

    /**
     * Gets the requested AwardApplication.
     * 
     * @param pm
     *            The PersistenceManager.
     * @param key
     *            The key for the AwardApplication.
     * @return The requested AwardApplication.
     */
    public static AwardApplication GetAward(PersistenceManager pm, Key key) {
        AwardApplication a = pm.getObjectById(AwardApplication.class, key);
        return a;
    }
}
