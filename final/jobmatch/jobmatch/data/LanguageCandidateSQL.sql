/* This SQL was generated for a Standard database. */

create table LanguageCandidate
(
/* class CVSection */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
/* class LanguageCandidate */
    Language DECIMAL(19,0)  NOT NULL  REFERENCES Language ( oid ) ,
    Diploma VARCHAR(32) DEFAULT ""    ,
    Written DECIMAL(19,0)  NOT NULL   ,
    Spoken DECIMAL(19,0)  NOT NULL   ,

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


