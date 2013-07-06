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

package com.vanomaly.rpi.serial.gps;

import java.sql.SQLException;

import com.vanomaly.rpi.serial.gps.db.DatabaseManager;
import com.vanomaly.rpi.serial.gps.util.Config;
import com.vanomaly.rpi.serial.gps.util.ConfigException;

/**
 * The Boot of the Strap
 */
public class Boot {
    
    // singelton up in heeere
    private static Boot instance = null;
    
    private Boot() {
        // kill outside instantiation
    }
    
    public static Boot getInstance() {
        if (instance == null) {
            instance = new Boot();
        }
        return instance;
    }

    /**
     * Bootstrap startup process
     * 
     * @throws ConfigException
     */
    public void bootstrap() throws ConfigException {
        Config.getInstance().setPropertyFile("props/gps.properties");
        if (Config.getInstance().getConfig("STORE").equalsIgnoreCase("TRUE")) {
            try {
                DatabaseManager.getInstance().initialize();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
