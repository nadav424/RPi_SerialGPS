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

package com.vanomaly.rpi.serial.gps.util;

import java.io.IOException;
import java.io.InputStream;

public class System {

    // singelton up in heeere
    private static System instance = null;
    
    private System() {
        // kill outside instantiation
    }
    
    public static System getInstance() {
        if (instance == null) {
            instance = new System();
        }
        return instance;
    }
    
    /**
     * Execute native system command, return the <code>Process</code>
     * 
     * @param cmd Native system command to run
     * @return <code>Process</code> from cmd
     * @throws IOException
     */
    public Process exec(String cmd) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        return process;
    }
    
    /**
     * Forcefully Stops running process
     * 
     * @param proc <code>Process</code> to stop
     */
    public void stop(Process proc) {
        proc.destroy();
    }
    
    /**
     * Gets <code>InputStream</code> 
     *  from running <code>Process</code>
     * 
     * @param proc <code>Process</code> to get stream from
     * @return <code>InputStream</code> from running <code>Process</code>
     */
    public InputStream getProcInputStream(Process proc) {
        return proc.getInputStream();
    }
    
}
