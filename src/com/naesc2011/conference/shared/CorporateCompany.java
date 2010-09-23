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

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Element;
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
	 */
    @Persistent
    private String address;

    /**
	 * 
	 */
    @Persistent
    private Double pledged;

    /**
	 * 
	 */
    @Persistent
    private String pledgeDate;

    /**
	 * 
	 */
    @Persistent
    private String sector;

    /**
	 * 
	 */
    @Persistent
    private String products;

    /**
	 * 
	 */
    @Persistent
    private Boolean majorMechanical;

    /**
	 * 
	 */
    @Persistent
    private Boolean majorCivil;

    /**
	 * 
	 */
    @Persistent
    private Boolean majorComputer;

    /**
	 * 
	 */
    @Persistent
    private Boolean majorElectrical;

    /**
	 * 
	 */
    @Persistent
    private Boolean majorChemical;

    /**
	 * 
	 */
    @Persistent
    private Boolean majorBiological;

    /**
	 * 
	 */
    @Persistent
    private Boolean majorIndustrial;

    /**
	 * 
	 */
    @Persistent
    private Boolean majorAeronautical;

    /**
	 * 
	 */
    @Persistent
    private Boolean majorManagement;

    /**
	 * 
	 */
    @Persistent
    private Boolean majorMaterials;

    /**
	 * 
	 */
    @Persistent
    private String description;

    /**
	 * 
	 */
    @Persistent
    private String primaryPOCName;

    /**
	 * 
	 */
    @Persistent
    private String primaryPOCTitle;

    /**
	 * 
	 */
    @Persistent
    private String primaryPOCCellPhone;

    /**
	 * 
	 */
    @Persistent
    private String primaryPOCWorkPhone;

    /**
	 * 
	 */
    @Persistent
    private String primaryPOCEmail;

    /**
	 * 
	 */
    @Persistent
    @Element(dependent = "true")
    private List<CorporateCorrespondence> correspondenceList;

    /**
     * 
     * @param name
     */
    public CorporateCompany(String name) {
        this.name = name;
        this.correspondenceList = new ArrayList<CorporateCorrespondence>();
    }

    /**
     * 
     * @return
     */
    public Key getKey() {
        return this.key;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the pledged
     */
    public Double getPledged() {
        return pledged;
    }

    /**
     * @param pledged
     *            the pledged to set
     */
    public void setPledged(Double pledged) {
        this.pledged = pledged;
    }

    /**
     * @return the pledgeDate
     */
    public String getPledgeDate() {
        return pledgeDate;
    }

    /**
     * @param pledgeDate
     *            the pledgeDate to set
     */
    public void setPledgeDate(String pledgeDate) {
        this.pledgeDate = pledgeDate;
    }

    /**
     * @return the sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * @param sector
     *            the sector to set
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * @return the products
     */
    public String getProducts() {
        return products;
    }

    /**
     * @param products
     *            the products to set
     */
    public void setProducts(String products) {
        this.products = products;
    }

    /**
     * @return the majorMechanical
     */
    public Boolean getMajorMechanical() {
        return majorMechanical;
    }

    /**
     * @param majorMechanical
     *            the majorMechanical to set
     */
    public void setMajorMechanical(Boolean majorMechanical) {
        this.majorMechanical = majorMechanical;
    }

    /**
     * @return the majorCivil
     */
    public Boolean getMajorCivil() {
        return majorCivil;
    }

    /**
     * @param majorCivil
     *            the majorCivil to set
     */
    public void setMajorCivil(Boolean majorCivil) {
        this.majorCivil = majorCivil;
    }

    /**
     * @return the majorComputer
     */
    public Boolean getMajorComputer() {
        return majorComputer;
    }

    /**
     * @param majorComputer
     *            the majorComputer to set
     */
    public void setMajorComputer(Boolean majorComputer) {
        this.majorComputer = majorComputer;
    }

    /**
     * @return the majorElectrical
     */
    public Boolean getMajorElectrical() {
        return majorElectrical;
    }

    /**
     * @param majorElectrical
     *            the majorElectrical to set
     */
    public void setMajorElectrical(Boolean majorElectrical) {
        this.majorElectrical = majorElectrical;
    }

    /**
     * @return the majorChemical
     */
    public Boolean getMajorChemical() {
        return majorChemical;
    }

    /**
     * @param majorChemical
     *            the majorChemical to set
     */
    public void setMajorChemical(Boolean majorChemical) {
        this.majorChemical = majorChemical;
    }

    /**
     * @return the majorBiological
     */
    public Boolean getMajorBiological() {
        return majorBiological;
    }

    /**
     * @param majorBiological
     *            the majorBiological to set
     */
    public void setMajorBiological(Boolean majorBiological) {
        this.majorBiological = majorBiological;
    }

    /**
     * @return the majorIndustrial
     */
    public Boolean getMajorIndustrial() {
        return majorIndustrial;
    }

    /**
     * @param majorIndustrial
     *            the majorIndustrial to set
     */
    public void setMajorIndustrial(Boolean majorIndustrial) {
        this.majorIndustrial = majorIndustrial;
    }

    /**
     * @return the majorAeronautical
     */
    public Boolean getMajorAeronautical() {
        return majorAeronautical;
    }

    /**
     * @param majorAeronautical
     *            the majorAeronautical to set
     */
    public void setMajorAeronautical(Boolean majorAeronautical) {
        this.majorAeronautical = majorAeronautical;
    }

    /**
     * @return the majorManagement
     */
    public Boolean getMajorManagement() {
        return majorManagement;
    }

    /**
     * @param majorManagement
     *            the majorManagement to set
     */
    public void setMajorManagement(Boolean majorManagement) {
        this.majorManagement = majorManagement;
    }

    /**
     * @return the majorMaterials
     */
    public Boolean getMajorMaterials() {
        return majorMaterials;
    }

    /**
     * @param majorMaterials
     *            the majorMaterials to set
     */
    public void setMajorMaterials(Boolean majorMaterials) {
        this.majorMaterials = majorMaterials;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the primaryPOCName
     */
    public String getPrimaryPOCName() {
        return primaryPOCName;
    }

    /**
     * @param primaryPOCName
     *            the primaryPOCName to set
     */
    public void setPrimaryPOCName(String primaryPOCName) {
        this.primaryPOCName = primaryPOCName;
    }

    /**
     * @return the primaryPOCTitle
     */
    public String getPrimaryPOCTitle() {
        return primaryPOCTitle;
    }

    /**
     * @param primaryPOCTitle
     *            the primaryPOCTitle to set
     */
    public void setPrimaryPOCTitle(String primaryPOCTitle) {
        this.primaryPOCTitle = primaryPOCTitle;
    }

    /**
     * @return the primaryPOCCellPhone
     */
    public String getPrimaryPOCCellPhone() {
        return primaryPOCCellPhone;
    }

    /**
     * @param primaryPOCCellPhone
     *            the primaryPOCCellPhone to set
     */
    public void setPrimaryPOCCellPhone(String primaryPOCCellPhone) {
        this.primaryPOCCellPhone = primaryPOCCellPhone;
    }

    /**
     * @return the primaryPOCWorkPhone
     */
    public String getPrimaryPOCWorkPhone() {
        return primaryPOCWorkPhone;
    }

    /**
     * @param primaryPOCWorkPhone
     *            the primaryPOCWorkPhone to set
     */
    public void setPrimaryPOCWorkPhone(String primaryPOCWorkPhone) {
        this.primaryPOCWorkPhone = primaryPOCWorkPhone;
    }

    /**
     * @return the primaryPOCEmail
     */
    public String getPrimaryPOCEmail() {
        return primaryPOCEmail;
    }

    /**
     * @param primaryPOCEmail
     *            the primaryPOCEmail to set
     */
    public void setPrimaryPOCEmail(String primaryPOCEmail) {
        this.primaryPOCEmail = primaryPOCEmail;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * @return the correspondence
     */
    public List<CorporateCorrespondence> getCorrespondence() {
        if (this.correspondenceList == null) {
            this.correspondenceList = new ArrayList<CorporateCorrespondence>();
        }

        return correspondenceList;
    }

    /**
     * 
     * @param pm
     * @param company
     */
    public static void InsertCompany(PersistenceManager pm,
            CorporateCompany company) {
        pm.makePersistent(company);
    }

    /**
     * 
     * @param pm
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<CorporateCompany> GetAllCompanies(PersistenceManager pm) {
        String query = "select from " + CorporateCompany.class.getName();
        return (List<CorporateCompany>) pm.newQuery(query).execute();
    }

    /**
     * 
     * @param pm
     * @param i
     * @return
     */
    public static CorporateCompany GetCompany(PersistenceManager pm, String id) {
        int i = Integer.parseInt(id);
        Key key = KeyFactory.createKey(CorporateCompany.class.getSimpleName(),
                i);
        CorporateCompany c = pm.getObjectById(CorporateCompany.class, key);
        return c;
    }
}
