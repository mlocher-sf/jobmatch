/* This SQL was generated for a Standard database. */

create table Students
(
/* class Students */
    Name VARCHAR(32) DEFAULT "" NOT NULL   ,
    Lname VARCHAR(32) DEFAULT "" NOT NULL   ,

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

CREATE INDEX I1_Students ON Students ( Name );

create table objectid(
 next DECIMAL(19,0) NOT NULL 
);
