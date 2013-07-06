DROP TABLE IF EXISTS AAM
DROP TABLE IF EXISTS ALM
DROP TABLE IF EXISTS APA
DROP TABLE IF EXISTS APB
DROP TABLE IF EXISTS ASD
DROP TABLE IF EXISTS BEC
DROP TABLE IF EXISTS BOD
DROP TABLE IF EXISTS BWC
DROP TABLE IF EXISTS BWR
DROP TABLE IF EXISTS BWW
DROP TABLE IF EXISTS DBT
DROP TABLE IF EXISTS DCN
DROP TABLE IF EXISTS DPT
DROP TABLE IF EXISTS FSI
DROP TABLE IF EXISTS GGA
DROP TABLE IF EXISTS GLC
DROP TABLE IF EXISTS GLL
DROP TABLE IF EXISTS GSA
DROP TABLE IF EXISTS GSV;
DROP TABLE IF EXISTS GXA;
DROP TABLE IF EXISTS HDG;
DROP TABLE IF EXISTS HDT;
DROP TABLE IF EXISTS HSC;
DROP TABLE IF EXISTS LCD;
DROP TABLE IF EXISTS MTA;
DROP TABLE IF EXISTS MTW;
DROP TABLE IF EXISTS MWD;
DROP TABLE IF EXISTS MWV;
DROP TABLE IF EXISTS OLN;
DROP TABLE IF EXISTS OSD;
DROP TABLE IF EXISTS R00;
DROP TABLE IF EXISTS RMA;
DROP TABLE IF EXISTS RMB;
DROP TABLE IF EXISTS RMC;
DROP TABLE IF EXISTS ROT;
DROP TABLE IF EXISTS RPM;
DROP TABLE IF EXISTS RSA;
DROP TABLE IF EXISTS RSD;
DROP TABLE IF EXISTS RTE;
DROP TABLE IF EXISTS SFI;
DROP TABLE IF EXISTS STN;
DROP TABLE IF EXISTS TRF;
DROP TABLE IF EXISTS TTM;
DROP TABLE IF EXISTS VBW;
DROP TABLE IF EXISTS VDR;
DROP TABLE IF EXISTS VHW;
DROP TABLE IF EXISTS VLW;
DROP TABLE IF EXISTS VPW;
DROP TABLE IF EXISTS VTG;
DROP TABLE IF EXISTS WCV;
DROP TABLE IF EXISTS WNC;
DROP TABLE IF EXISTS WPL;
DROP TABLE IF EXISTS XDR;
DROP TABLE IF EXISTS XTE;
DROP TABLE IF EXISTS XTR;
DROP TABLE IF EXISTS ZDA;
DROP TABLE IF EXISTS ZFO;
DROP TABLE IF EXISTS ZTG;

CREATE TABLE AAM
(
    raw VARCHAR(255)
);
CREATE TABLE ALM
(
    raw VARCHAR(255)
);
CREATE TABLE APA
(
    raw VARCHAR(255)
);
CREATE TABLE APB
(
    raw VARCHAR(255)
);
CREATE TABLE ASD
(
    raw VARCHAR(255)
);
CREATE TABLE BEC
(
    raw VARCHAR(255)
);
CREATE TABLE BOD
(
    raw VARCHAR(255)
);
CREATE TABLE BWC
(
    raw VARCHAR(255)
);
CREATE TABLE BWR
(
    raw VARCHAR(255)
);
CREATE TABLE BWW
(
    raw VARCHAR(255)
);
CREATE TABLE DBT
(
    raw VARCHAR(255)
);
CREATE TABLE DCN
(
    raw VARCHAR(255)
);
CREATE TABLE DPT
(
    raw VARCHAR(255)
);
CREATE TABLE FSI
(
    raw VARCHAR(255)
);
CREATE TABLE GGA
(
    message_id VARCHAR(6) NOT NULL,
    utc_time DECIMAL(9, 3)NOT NULL,
    latitude DECIMAL(8, 4),
    ns_indicator CHAR(1),
    longitude DECIMAL(9, 4),
    ew_indicator CHAR(1),
    position_fix_indicator TINYINT,
    satellites_used TINYINT,
    hdop DECIMAL(8, 2),
    msl_altitude DECIMAL(8, 2),
    msl_altitude_units CHAR(1),
    geoid_separation DECIMAL(8, 2),
    geoid_separation_units CHAR(1),
    age_of_diff_corr INT,
    diff_ref_station_id INT,
    checksum VARCHAR(3) NOT NULL,
    CONSTRAINT satellites_used CHECK (satellites_used <= 12 AND satelliates_used >= 0)
    CONSTRAINT position_fix_indicator CHECK (position_fix_indicator <= 3 AND position_fix_indicator >= 0)
);
CREATE TABLE GLC
(
    raw VARCHAR(255)
);
CREATE TABLE GLL
(
    raw VARCHAR(255)
);
CREATE TABLE GSA
(
    raw VARCHAR(255)
);
CREATE TABLE GSV
(
    raw VARCHAR(255)
);
CREATE TABLE GXA
(
    raw VARCHAR(255)
);
CREATE TABLE HDG
(
    raw VARCHAR(255)
);
CREATE TABLE HDT
(
    raw VARCHAR(255)
);
CREATE TABLE HSC
(
    raw VARCHAR(255)
);
CREATE TABLE LCD
(
    raw VARCHAR(255)
);
CREATE TABLE MTA
(
    raw VARCHAR(255)
);
CREATE TABLE MTW
(
    raw VARCHAR(255)
);
CREATE TABLE MWD
(
    raw VARCHAR(255)
);
CREATE TABLE MWV
(
    raw VARCHAR(255)
);
CREATE TABLE OLN
(
    raw VARCHAR(255)
);
CREATE TABLE OSD
(
    raw VARCHAR(255)
);
CREATE TABLE R00
(
    raw VARCHAR(255)
);
CREATE TABLE RMA
(
    raw VARCHAR(255)
);
CREATE TABLE RMB
(
    raw VARCHAR(255)
);
CREATE TABLE RMC
(
    raw VARCHAR(255)
);
CREATE TABLE ROT
(
    raw VARCHAR(255)
);
CREATE TABLE RPM
(
    raw VARCHAR(255)
);
CREATE TABLE RSA
(
    raw VARCHAR(255)
);
CREATE TABLE RSD
(
    raw VARCHAR(255)
);
CREATE TABLE RTE
(
    raw VARCHAR(255)
);
CREATE TABLE SFI
(
    raw VARCHAR(255)
);
CREATE TABLE STN
(
    raw VARCHAR(255)
);
CREATE TABLE TRF
(
    raw VARCHAR(255)
);
CREATE TABLE TTM
(
    raw VARCHAR(255)
);
CREATE TABLE VBW
(
    raw VARCHAR(255)
);
CREATE TABLE VDR
(
    raw VARCHAR(255)
);
CREATE TABLE VHW
(
    raw VARCHAR(255)
);
CREATE TABLE VLW
(
    raw VARCHAR(255)
);
CREATE TABLE VPW
(
    raw VARCHAR(255)
);
CREATE TABLE VTG
(
    raw VARCHAR(255)
);
CREATE TABLE WCV
(
    raw VARCHAR(255)
);
CREATE TABLE WNC
(
    raw VARCHAR(255)
);
CREATE TABLE WPL
(
    raw VARCHAR(255)
);
CREATE TABLE XDR
(
    raw VARCHAR(255)
);
CREATE TABLE XTE
(
    raw VARCHAR(255)
);
CREATE TABLE XTR
(
    raw VARCHAR(255)
);
CREATE TABLE ZDA
(
    raw VARCHAR(255)
);
CREATE TABLE ZFO
(
    raw VARCHAR(255)
);
CREATE TABLE ZTG
(
    raw VARCHAR(255)
);