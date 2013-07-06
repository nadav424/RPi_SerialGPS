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

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Job {
    
    private PreparedStatement ps;
    
    public Job() {
    }
    
    /**
     * @param query Query to execute
     * @return Object type of <code>ResultSet</code> if query returned one,
     *              otherwise returns Object type of <code>Boolean</code> with value of
     *              <code>TRUE</code> or <code>FALSE</code> dependent on if the query was successful
     * @throws SQLException
     */
    // this probably needs work... 
    public Object queryDB(String query) throws SQLException {
        try {
            if (DatabaseManager.getInstance().getDB().getConnection().isClosed()
                    && DatabaseManager.getInstance().getDB() != null) {
                
                DatabaseManager.getInstance().getDB().openConnection();
                
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        ps = DatabaseManager.getInstance().getDB().getConnection().prepareStatement(query);
        if (ps.execute()) {
            return ps.getResultSet();
        } else {
            return Boolean.FALSE;
        }
        
    }
    
    /**
     * @param query Query to execute
     * @param args Query arguments in correct order
     * @return Object type of <code>ResultSet</code> if query returned one,
     *              otherwise returns Object type of <code>Boolean</code> with value of
     *              <code>TRUE</code> or <code>FALSE</code> dependent on if the query was successful
     * @throws SQLException
     */
    // this probably needs work... 
    public Object queryDB(String query, String ... args) throws SQLException {
        try {
            if (DatabaseManager.getInstance().getDB().getConnection().isClosed()
                    && DatabaseManager.getInstance().getDB() != null) {
                
                DatabaseManager.getInstance().getDB().openConnection();
                
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        PreparedStatement ps = DatabaseManager.getInstance().getDB().getConnection().prepareStatement(query);
        for (int i = 0; i < args.length; i++) {
            ps.setString(i, args[i]);
        }
        if (ps.execute()) {
            return ps.getResultSet();
        } else {
            return Boolean.FALSE;
        }
        
    }
    
}
