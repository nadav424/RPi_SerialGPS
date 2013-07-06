/*******************************************************************************
 *  Copyright 2013 Jason Sipula                                                *
 *                                                                             *
 *  Licensed under the Apache License, Version 2.0 (the "License");            *
 *  you may not use this file except in compliance with the License.           *
 *  You may obtain a copy of the License at                                    *
 *                                                                             *
 *      http://www.apache.org/licenses/LICENSE-2.0                             *
 *                                                                             *
 *  Unless required by applicable law or agreed to in writing, software        *
 *  distributed under the License is distributed on an "AS IS" BASIS,          *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   *
 *  See the License for the specific language governing permissions and        *
 *  limitations under the License.                                             *
 *******************************************************************************/
/** 
 * Adapted from http://www.snakedoc.net/jutils
 */

package com.vanomaly.rpi.serial.gps.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Library Configuration Utilities.
 * Gets and Sets Property Values from lib.properties file
 * 
 * @author Jason Sipula
 *
 */
public class Config {
    
    protected Properties prop;
    protected String propertyFile;
    
    // singelton up in heeere
    private static Config instance = null;
    
    private Config() {
        // kill outside instantiation
        this.prop = new Properties();
    }
    
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
    
    /**
     * Gets instance property file.
     * 
     * @return Instance property file.
     */
    public String getPropertyFile() {
        return this.propertyFile;
    }
    
    /**
     * Sets instance property file.
     * 
     * @param propertyFile Property file to set.
     */
    public void setPropertyFile(String propertyFile) {
        this.propertyFile = propertyFile;
    }
    
    /**
     * Sets the property value for given property.
     *     Allows for either overwriting the original file from what
     *     is in memory (default Java Properties API behavior) or perform
     *     an in-place update of a given property.
     * 
     * @param propertyName Name of property to be changed.
     * @param value Value to change named property to.
     * @param overwrite True to overwrite (replace) properties file with what is in memory 
     *             (no guarantee of property order and comments will be gone),
     *             False to update properties file (preserve comments and property order etc).
     * @return Return's true if successful operation, false if something failed.
     */
    public boolean setConfig(String propertyName, String value, boolean overwrite) {
        if (overwrite) {
            try {
                this.prop.load(new FileInputStream(this.propertyFile));
            } catch (IOException e) {
                return false;
            }
            this.prop.setProperty(propertyName, value);
            try {
                this.prop.store(new FileOutputStream(this.propertyFile), null);
            } catch (IOException e) {
                return false;
            }
            return true;
        } else {
            
            BufferedReader br = null;
            BufferedWriter bw = null;

            try {
                
                String curVal = this.getConfig(propertyName);
                
                FileInputStream fis = new FileInputStream(new File(this.propertyFile));
                br = new BufferedReader(new InputStreamReader(fis));
                
                String str = "";
                String line = br.readLine();
                while (line != null) {
                    str = str.concat(line.concat(java.lang.System.getProperty("line.separator")));
                    line = br.readLine();
                }
                
                FileWriter fw = new FileWriter(new File(this.propertyFile));
                bw = new BufferedWriter(fw);
                
                bw.write(str.replace(propertyName + "=" + curVal, propertyName + "=" + value));
                
            } catch (IOException | ConfigException e) {
                return false;
            } finally {
                try {
                    br.close();
                    bw.close();
                } catch (IOException e) {
                    // does not matter
                }
            }
            return true;
        }
    }

    /**
     * Gets config from config.properties
     * 
     * @param propertyName Property name located in config.properties
     * @return String value of property
     * @throws ConfigException Error reading the property file.
     *              <br/>Check that a property file has been loaded via Config(String propertyFile),
     *              <br/>or setPropertyFile(String propertyFile) prior to attempting getConfig(String propertyName).
     */
    public String getConfig(String propertyName) throws ConfigException {
        try {
            this.prop.load(new FileInputStream(this.propertyFile));
        } catch (IOException | IllegalArgumentException | SecurityException e) {
            throw new ConfigException("Error reading the property file." +
                    " Check that a property file has been loaded via Config(String propertyFile)," +
                    "\n or setPropertyFile(String propertyFile) prior to attempting getConfig(String propertyName).", e);
        }
        return this.prop.getProperty(propertyName);
    }
}
