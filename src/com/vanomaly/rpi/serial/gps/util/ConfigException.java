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

public class ConfigException extends Exception {

    /**
     * generated serialVersion UID
     */
    private static final long serialVersionUID = -3668130040656771466L;

    public ConfigException () {} // empty constructor

    public ConfigException (String message) {
        super (message);
    }

    public ConfigException (Throwable cause) {
        super (cause);
    }

    public ConfigException (String message, Throwable cause) {
        super (message, cause);
    }
}
