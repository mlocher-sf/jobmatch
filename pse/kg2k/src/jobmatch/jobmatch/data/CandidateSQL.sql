/* This SQL was generated for a Standard database. */

create table Candidate
(
/* class Candidate */
    AIESECMember BIT DEFAULT 0    ,
    City VARCHAR(30) DEFAULT ""    ,
    Competence TEXT(32) DEFAULT ""    ,
    Fax VARCHAR(25) DEFAULT ""    ,
    Fname VARCHAR(20) DEFAULT ""    ,
    HouseNumber VARCHAR(5) DEFAULT ""    ,
    Lname VARCHAR(25) DEFAULT ""    ,
    Natel VARCHAR(25) DEFAULT ""    ,
    Nationality VARCHAR(25) DEFAULT ""    ,
    PLZ MEDIUMINT UNSIGNED     ,
    Phone VARCHAR(25) DEFAULT ""    ,
    Picture MEDIUMBLOB     ,
    Residence VARCHAR(50) DEFAULT ""    ,
    Status BIT DEFAULT 0    ,
    Street VARCHAR(50) DEFAULT ""    ,
    Sex ENUM('w','m')(32) DEFAULT ""    ,
    Birthdate DATE     ,

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


