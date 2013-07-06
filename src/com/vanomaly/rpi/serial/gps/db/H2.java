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

package com.vanomaly.rpi.serial.gps.db;

import java.sql.*;

import com.vanomaly.rpi.serial.gps.util.Config;
import com.vanomaly.rpi.serial.gps.util.ConfigException;

/**
 * Valid constructor: 
 * <br/>
 * H2()
 * <br/>
 * H2(String dbURL, String dbUser, String dbPass)
 * <br/>
 * Configure properties in lib.properties file.
 * 
 * @author Jason Sipula
 *
 */
public class H2 extends _Database {
    
    protected final String propertyPrefix = "H2_";
    
    /**
     * Default Constructor
     * 
     * @throws ConfigException Error reading the property file.
     *              <br/>Check that a property file has been loaded via Config(String propertyFile),
     *              <br/>or setPropertyFile(String propertyFile) prior to attempting getConfig(String propertyName).
     */
    public H2() throws ConfigException {
        
        this.dbPath = Config.getInstance().getConfig(this.propertyPrefix + "dbURL");
        this.dbUser = Config.getInstance().getConfig(this.propertyPrefix + "dbUser");
        this.dbPass = Config.getInstance().getConfig(this.propertyPrefix + "dbPass");
    }
    
    /**
     * Pass-in Value Constructor
     * <br/>
     * String dbURL, String dbUser, String dbPass
     */
    public H2(String dbURL, String dbUser, String dbPass) { 
        
        this.dbPath = dbURL;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }
     
    /* (non-Javadoc)
     * @see net.snakedoc.jutils.database._Database#openConnection()
     */
    @Override
    public void openConnection() throws SQLException, ClassNotFoundException {
        this.conn = DriverManager.getConnection(getURL(), this.dbUser, this.dbPass);
    }
    
    /* (non-Javadoc)
     * @see net.snakedoc.jutils.database._Database#getURL()
     */
    @Override
    protected String getURL() {
        return "jdbc:h2://" + this.dbPath;
    }
}
