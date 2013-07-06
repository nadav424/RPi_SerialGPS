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

import java.io.BufferedInputStream;
import java.io.IOException;

import com.vanomaly.rpi.serial.gps.db.DatabaseManager;
import com.vanomaly.rpi.serial.gps.util.ConfigException;

public class Main {
    
    // singelton up in heeere
    private static Main instance = null;
    
    private Main() {
        // kill outside instantiation
    }
    
    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }
    
    public static void main (String[] args) {
        Main.getInstance().initialize();
    }
    
    public void initialize() {
        try {
            Boot.getInstance().bootstrap();
        } catch (ConfigException e) {
            // failed to initialize
            new Exception("FAILED TO INITIALIZE", e);
            System.exit(1);
        }
        Main.getInstance().addShutDownHook();
        Main.getInstance().run();
    }
    
    /**
     * Main program logic
     * 
     * Open stream from gps and display/log
     */
    private void run() {
        try {
            GPS.getInstance().setConnection();
            GPS.getInstance().startStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        BufferedInputStream in;
        try {
            /*
            in = new BufferedInputStream(GPS.getInstance().readStream());
            byte[] _byte = new byte[128];
            in.read(_byte);
            System.out.print(new String(_byte));
            while (in.read(_byte) != -1) {
                System.out.print(new String(_byte));
            }
            */
            
            in = new BufferedInputStream(GPS.getInstance().readStream());
            int _byte = in.read();
            System.out.print((char)_byte);
            while ((_byte = in.read()) != -1) {
                System.out.print((char)_byte);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 
     * Add shutdown hook to close resources at JVM shutdown
     * 
     */
    private void addShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    DatabaseManager.getInstance().getDB().closeConnection();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
    }
    
}
