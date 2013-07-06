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

package com.vanomaly.rpi.serial.gps.db;

import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vanomaly.rpi.serial.gps.util.Config;
import com.vanomaly.rpi.serial.gps.util.ConfigException;

/**
 * Manages database connection
 *
 */
public class DatabaseManager {
    
    private H2 db;

    // singelton up in heeere
    private static DatabaseManager instance = null;
    
    private DatabaseManager() {
        // kill outside instantiation
    }
    
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    /**
     * Initialize database object with credentials and path,
     *  verify database exists, or if it doesn't, create it
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public void initialize() throws ClassNotFoundException, SQLException {
        try {
            db = new H2(Config.getInstance().getConfig("H2_dbURL"), 
                    Config.getInstance().getConfig("H2_dbUser"),
                    Config.getInstance().getConfig("H2_dbPass"));
        } catch (ConfigException e) {
            e.printStackTrace();
        }
        DatabaseManager.getInstance().getDB().openConnection();
        Job job = new Job();
        if ( ! (job.queryDB("SELECT COUNT(*)") instanceof ResultSet) ) {
            job.queryDB(new Schema("props/schema.sql").getSchema());
        }
    }
    
    /**
     * Gets database object
     * 
     * @return  Database object
     */
    public H2 getDB() {
        return db;
    }
    
}
