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
        this.setQuestion1(one);
        this.setQuestion2(two);
        this.setQuestion3(three);
        this.setQuestion4(four);
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
     * @return the question1
     */
    public Text getQuestion1() {
        return question1;
    }

    /**
     * @param question1
     *            the question1 to set
     */
    public void setQuestion1(String question1) {
        this.question1 = new Text(question1.replaceAll("\\<.*?>", ""));
    }

    /**
     * @return the question2
     */
    public Text getQuestion2() {
        return question2;
    }

    /**
     * @param question2
     *            the question2 to set
     */
    public void setQuestion2(String question2) {
        this.question2 = new Text(question2.replaceAll("\\<.*?>", ""));
    }

    /**
     * @return the question3
     */
    public Text getQuestion3() {
        return question3;
    }

    /**
     * @param question3
     *            the question3 to set
     */
    public void setQuestion3(String question3) {
        this.question3 = new Text(question3.replaceAll("\\<.*?>", ""));
    }

    /**
     * @return the question4
     */
    public Text getQuestion4() {
        return question4;
    }

    /**
     * @param question4
     *            the question4 to set
     */
    public void setQuestion4(String question4) {
        this.question4 = new Text(question4.replaceAll("\\<.*?>", ""));
    }

    /**
     * 
     * @param pm
     * @param AwardApplication
     */
    public static void InsertAward(PersistenceManager pm,
            AwardApplication AwardApplication) {
        pm.makePersistent(AwardApplication);
    }

    /**
     * 
     * @param pm
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<AwardApplication> GetAllAwards(PersistenceManager pm) {
        String query = "select from " + AwardApplication.class.getName();
        return (List<AwardApplication>) pm.newQuery(query).execute();
    }

    /**
     * 
     * @param pm
     * @param id
     * @return
     */
    public static AwardApplication GetAward(PersistenceManager pm, String id) {
        int i = Integer.parseInt(id);
        Key key = KeyFactory.createKey(AwardApplication.class.getSimpleName(),
                i);
        AwardApplication a = pm.getObjectById(AwardApplication.class, key);
        return a;
    }

    /**
     * 
     * @param pm
     * @param key
     * @return
     */
    public static AwardApplication GetAward(PersistenceManager pm, Key key) {
        AwardApplication a = pm.getObjectById(AwardApplication.class, key);
        return a;
    }
}
