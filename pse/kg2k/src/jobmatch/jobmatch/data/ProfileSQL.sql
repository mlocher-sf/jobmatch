/* This SQL was generated for a Standard database. */

create table Profile
(
/* class Profile */
    Name VARCHAR(40) DEFAULT "" NOT NULL   ,
    Company DECIMAL(19,0)  NOT NULL  REFERENCES Company ( oid ) ,
    NeedsRematching BIT DEFAULT 1 NOT NULL   ,
    Notify BIT DEFAULT 1 NOT NULL   ,
    Period INTEGER DEFAULT 0 NOT NULL   ,
    MatchTree MEDIUMBLOB  NOT NULL   ,
    LastNotification TIMESTAMP  NOT NULL   ,

    /* NOTICE: */
    /* This table represents a Data Object class  */
    /* derived from com.lutris.dods.builder.generator.dataobject.GenericDO. */
    /* The class GenericDO contains: */
    /* 1) an ObjectId (oid)     which is stored as a DECIMAL(19,0) */
    /* 2) an int      (version) which is stored as an INTEGER */
    /* When creating Data Objects in DODS, these two columns */
    /* (a.k.a class members, Data Object Attributes) */
    /* should never be defined explicitly. */

    /* These columns are defined here at the end of the */
    /* CREATE TABLE statement to simplify the handling of commas. */
    oid DECIMAL(19,0) NOT NULL PRIMARY KEY,
    version INTEGER NOT NULL

);


