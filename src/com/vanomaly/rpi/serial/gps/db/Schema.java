/*******************************************************************************
 *  Copyright (c) 2013 superD contributors, snakedoc.net and others            *
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

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class Schema {
    
    String schemaPath;
    
    public Schema(String schemaPath) {
        this.schemaPath = schemaPath;
    }
    
    /**
     * Get cleaned schema (string \r\n and \n chars)
     * 
     * @return Cleaned schema String
     */
    public String getSchema() {
        return cleanString(cleanString(readFromFile(schemaPath), "\\r\\n"), "\\n");
    }
    
    /**
     * Cleans string by stripping chars
     * 
     * @param input Raw input string
     * @param stripChars Characters to strip from string
     * @return Cleaned string
     */
    private String cleanString(String input, String stripChars) {
        String output = "";
        String[] temp = input.split(stripChars);
        for (int i = 0; i < temp.length; i++) {
            output += temp[i];
        }
        return output;
    }
    
    /**
     * Reads <code>File</code> from string path
     * 
     * @param strFile
     * @return
     */
    private String readFromFile(String strFile) {
        File file = new File(strFile);
        URI uri = file.toURI();
        byte[] bytes = null;
        try {
            bytes = java.nio.file.Files.readAllBytes(
                    java.nio.file.Paths.get(uri));
        } catch(IOException e) {
            e.printStackTrace(); 
            return "ERROR loading file " + strFile;
        }
        return new String(bytes);
    }
    
}
