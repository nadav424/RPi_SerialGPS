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

package com.vanomaly.rpi.serial.gps.nmea0183;

/**
 * NMEA 0183 Sentence
 *
 */
public class NMEA0183Sentence {

    private String rawSentence;
    private String[] sentence;
    
    /**
     * Constructor
     * 
     * @param rawSentence Raw sentence data string
     */
    public NMEA0183Sentence(String rawSentence) {
        this.rawSentence = rawSentence;
        this.sentence = this.rawSentence.split(",");
    }
    
    /**
     * Blocked default constructor
     */
    @SuppressWarnings("unused")
    private NMEA0183Sentence() {
        // block default constructor
    }
    
    /**
     * Gets raw sentence
     * 
     * @return String of sentence
     */
    public String getRawSentence() {
        return rawSentence;
    }
    
    /**
     * Gets sentence
     * 
     * @return String array of sentence
     */
    public String[] getSentence() {
        return sentence;
    }
    
    /**
     * Validates <code>rawSentence</code> against it's NMEA 0183 checksum.
     * @return <code>True</code> or <code>False</code> dependent on if sentence is valid or not
     */
    public boolean validateSentence() {
        return rawSentence.substring(rawSentence.length() - 2)
                .equalsIgnoreCase(NMEA0183Sentence.GetCheckSum(rawSentence))
                ? true : false;
    }
    
    /**
     * Trims the checksum off an NMEA message, then
     * recalculates the checksum
     * to compare it with the one passed along with the message later
     * 
     * @param msg String containing the full NMEA message (including checksum)
     * @return String containing the checksum
     */
    private static String GetCheckSum(String s) {
        // perform NMEA checksum calculation
        int chk = 0;
        // strip checksum from sentence
        s = s.substring(0, s.length() - 3);
        // strip '$' if it's in the sentence data
        if (s.startsWith("$")) {
            s = s.substring(1);
        }
        //run through each character of the message length
        //and XOR the value of chk with the byte value
        //of the character that is being evaluated
        for (int i = 1; i < s.length(); i++) {
            chk ^= s.charAt(i);
        }

        //convert the retreived integer to a HexString in uppercase
        String chk_s = Integer.toHexString(chk).toUpperCase();

        // checksum must be 2 characters!
        // if it falls short, add a zero before the checksum
        while (chk_s.length() < 2) {
            chk_s = "0" + chk_s;
        }

        //show the calculated checksum
        // System.out.println("    calculated checksum : " + chk_s);

        //return the calculated checksum
        return chk_s;
    }
    
    /**
     * Get sentence type description
     * 
     * @return Description of sentence type
     */
    public String getSentenceDesc() {

        if (sentence[0].equalsIgnoreCase("$GPAAM")) {
            return "Waypoint Arrival Alarm";
        } else if (sentence[0].equalsIgnoreCase("$GPALM")) {
            return "GPS Almanac Data";
        } else if (sentence[0].equalsIgnoreCase("$GPAPA")) {
            return "Autopilot Sentence \"A\"";
        } else if (sentence[0].equalsIgnoreCase("$GPAPB")) {
            return "Autopilot Sentence \"B\"";
        } else if (sentence[0].equalsIgnoreCase("$GPASD")) {
            return "Autopilot System Data";
        } else if (sentence[0].equalsIgnoreCase("$GPBEC")) {
            return "Bearing & Distance to Waypoint, Dead Reckoning";
        } else if (sentence[0].equalsIgnoreCase("$GPBOD")) {
            return "Bearing, Origin to Destination";
        } else if (sentence[0].equalsIgnoreCase("$GPBWC")) {
            return "Bearing & Distance to Waypoint, Great Circle";
        } else if (sentence[0].equalsIgnoreCase("$GPBWR")) {
            return "Bearing & Distance to Waypoint, Rhumb Line";
        } else if (sentence[0].equalsIgnoreCase("$GPBWW")) {
            return "Bearing, Waypoint to Waypoint";
        } else if (sentence[0].equalsIgnoreCase("$GPDBT")) {
            return "Depth Below Transducer";
        } else if (sentence[0].equalsIgnoreCase("$GPDCN")) {
            return "Decca Position";
        } else if (sentence[0].equalsIgnoreCase("$GPDPT")) {
            return "Depth";
        } else if (sentence[0].equalsIgnoreCase("$GPFSI")) {
            return "Frequency Set Information";
        } else if (sentence[0].equalsIgnoreCase("$GPGGA")) {
            return "Global Positioning System Fix Data";
        } else if (sentence[0].equalsIgnoreCase("$GPGLC")) {
            return "Geographic Position, Loran-C";
        } else if (sentence[0].equalsIgnoreCase("$GPGLL")) {
            return "Geographic Position, Latitude/Longitude";
        } else if (sentence[0].equalsIgnoreCase("$GPGSA")) {
            return "GPS DOP and Active Satellites";
        } else if (sentence[0].equalsIgnoreCase("$GPGSV")) {
            return "GPS Satellites in View";
        } else if (sentence[0].equalsIgnoreCase("$GPGXA")) {
            return "TRANSIT Position";
        } else if (sentence[0].equalsIgnoreCase("$GPHDG")) {
            return "Heading, Deviation & Variation";
        } else if (sentence[0].equalsIgnoreCase("$GPHDT")) {
            return "Heading, True";
        } else if (sentence[0].equalsIgnoreCase("$GPHSC")) {
            return "Heading Steering Command";
        } else if (sentence[0].equalsIgnoreCase("$GPLCD")) {
            return "Loran-C Signal Data";
        } else if (sentence[0].equalsIgnoreCase("$GPMTA")) {
            return "Air Temperature (to be phased out)";
        } else if (sentence[0].equalsIgnoreCase("$GPMTW")) {
            return "Water Temperature";
        } else if (sentence[0].equalsIgnoreCase("$GPMWD")) {
            return "Wind Direction";
        } else if (sentence[0].equalsIgnoreCase("$GPMWV")) {
            return "Wind Speed and Angle";
        } else if (sentence[0].equalsIgnoreCase("$GPOLN")) {
            return "Omega Lane Numbers";
        } else if (sentence[0].equalsIgnoreCase("$GPOSD")) {
            return "Own Ship Data";
        } else if (sentence[0].equalsIgnoreCase("$GPR00")) {
            return "Waypoint active route (not standard)";
        } else if (sentence[0].equalsIgnoreCase("$GPRMA")) {
            return "Recommended Minimum Specific Loran-C Data";
        } else if (sentence[0].equalsIgnoreCase("$GPRMB")) {
            return "Recommended Minimum Navigation Information";
        } else if (sentence[0].equalsIgnoreCase("$GPRMC")) {
            return "Recommended Minimum Specific GPS/TRANSIT Data";
        } else if (sentence[0].equalsIgnoreCase("$GPROT")) {
            return "Rate of Turn";
        } else if (sentence[0].equalsIgnoreCase("$GPRPM")) {
            return "Revolutions";
        } else if (sentence[0].equalsIgnoreCase("$GPRSA")) {
            return "Rudder Sensor Angle";
        } else if (sentence[0].equalsIgnoreCase("$GPRSD")) {
            return "RADAR System Data";
        } else if (sentence[0].equalsIgnoreCase("$GPRTE")) {
            return "Routes";
        } else if (sentence[0].equalsIgnoreCase("$GPSFI")) {
            return "Scanning Frequency Information";
        } else if (sentence[0].equalsIgnoreCase("$GPSTN")) {
            return "Multiple Data ID";
        } else if (sentence[0].equalsIgnoreCase("$GPTRF")) {
            return "Transit Fix Data";
        } else if (sentence[0].equalsIgnoreCase("$GPTTM")) {
            return "Tracked Target Message";
        } else if (sentence[0].equalsIgnoreCase("$GPVBW")) {
            return "Dual Ground/Water Speed";
        } else if (sentence[0].equalsIgnoreCase("$GPVDR")) {
            return "Set and Drift";
        } else if (sentence[0].equalsIgnoreCase("$GPVHW")) {
            return "Water Speed and Heading";
        } else if (sentence[0].equalsIgnoreCase("$GPVLW")) {
            return "Distance Traveled through the Water";
        } else if (sentence[0].equalsIgnoreCase("$GPVPW")) {
            return "Speed, Measured Parallel to Wind";
        } else if (sentence[0].equalsIgnoreCase("$GPVTG")) {
            return "Track Made Good and Ground Speed";
        } else if (sentence[0].equalsIgnoreCase("$GPWCV")) {
            return "Waypoint Closure Velocity";
        } else if (sentence[0].equalsIgnoreCase("$GPWNC")) {
            return "Distance, Waypoint to Waypoint";
        } else if (sentence[0].equalsIgnoreCase("$GPWPL")) {
            return "Waypoint Location";
        } else if (sentence[0].equalsIgnoreCase("$GPXDR")) {
            return "Transducer Measurements";
        } else if (sentence[0].equalsIgnoreCase("$GPXTE")) {
            return "Cross-Track Error, Measured";
        } else if (sentence[0].equalsIgnoreCase("$GPXTR")) {
            return "Cross-Track Error, Dead Reckoning";
        } else if (sentence[0].equalsIgnoreCase("$GPZDA")) {
            return "Time & Date";
        } else if (sentence[0].equalsIgnoreCase("$GPZFO")) {
            return "UTC & Time from Origin Waypoint";
        } else if (sentence[0].equalsIgnoreCase("$GPZTG")) {
            return "UTC & Time to Destination Waypoint";
        } else {
            return "INVALID SENTENCE TYPE";
        }
        
    }
    
}
