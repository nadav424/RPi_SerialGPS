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

import java.io.IOException;
import java.io.InputStream;

import com.vanomaly.rpi.serial.gps.util.System;

/**
 * GPS object
 *
 */
public class GPS {
    
    private Process proc;
    
    // singelton up in heeere
    private static GPS instance = null;
    
    private GPS() {
        // kill outside instantiation
    }
    
    public static GPS getInstance() {
        if (instance == null) {
            instance = new GPS();
        }
        return instance;
    }

    /**
     * Setup serial port on pi for GPS
     * 
     * @throws IOException
     */
    public void setConnection () throws IOException {
        System.getInstance().exec("stty -F /dev/ttyAMA0 4800");
    }
    
    /**
     * Start streaming GPS sentences
     * 
     * @throws IOException
     */
    public void startStream() throws IOException {
        proc = System.getInstance().exec("cat /dev/ttyAMA0");
    }
    
    /**
     * Get <code>InputStream</code> from GPS process
     * 
     * @return
     * @throws IOException
     */
    public InputStream readStream() throws IOException {
        return System.getInstance().getProcInputStream(proc);
    }
    
    /**
     * Kill/stop streaming process
     */
    public void stopStream() {
        System.getInstance().stop(proc);
    }
    
    /**
     * Get GPS process
     * 
     * @return GPS process
     */
    public Process getProcess() {
        return proc;
    }
    
    /**
     * Set GPS process.
     * Use caution with this method.
     * 
     * @param proc Process to set in GPS object
     */
    public void setProcess(Process proc) {
        this.proc = proc;
    }
    
}
