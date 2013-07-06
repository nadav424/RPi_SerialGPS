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

import java.sql.Connection;
import java.sql.SQLException;

public abstract class _Database {
    
    protected String dbPath;
    protected int dbPort;
    protected String dbUser;
    protected String dbPass;
    
    protected String driver;
    
    protected Connection conn;
    
    /**
     * Gets Connection object and passes back to calling method.
     * 
     * @return
     *      Connection object
     */
    public Connection getConnection() {
        return this.conn;
    }
    
    /**
     * Returns a connection URL string
     * to the calling method.
     * 
     * @return Formatted connection URL
     */
    protected abstract String getURL();
    
    /**
     * Opens database connection.
     * This method must be overridden.
     * Every database has a variant to
     * the standard connection.
     * 
     * @throws SQLException
     *              If database access error occurs.
     */
    public abstract void openConnection() throws Exception ;
    
    /**
     * Closes database connection
     * 
     * @return
     *      True if connection successful.
     * @throws SQLException
     */
    public boolean closeConnection() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
        }
        return true;
    }
    
    /**
     * Gets dbPath
     * 
     * @return Current dbPath value from instance
     */
    public String getDbPath() {
        return this.dbPath;
    }
    
    /**
     * Gets dbPort
     * 
     * @return Current dbPort value from instance
     */
    public int getDbPort() {
        return this.dbPort;
    }

    /**
     * Gets dbUser
     * 
     * @return Current dbUser from instance
     */
    public String getDbUser() {
        return this.dbUser;
    }
     
    /**
     * Gets dbPass New dbPass
     * 
     * @return Current dbPass from instance
     */
    public String getDbPass() {
        return this.dbPass;
    }
    
    /**
     * Sets dbPath
     * 
     * @param dbPath New dbPath value
     */
    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }
    
    /**
     * Sets dbPort
     * 
     * @param dbPort New dbPort value
     */
    public void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }
    
    /**
     * Sets dbUser
     * 
     * @param dbUser New dbUser value
     */
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }
    
    /**
     * Sets dbPass
     * 
     * @param dbPass New dbPass value
     */
    public void setDbPass(String dbPass) {
        this.dbPass = dbPass;
    }
}
