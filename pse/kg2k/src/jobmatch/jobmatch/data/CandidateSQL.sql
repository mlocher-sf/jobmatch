/* This SQL was generated for a Standard database. */

create table Candidate
(
/* class Candidate */
    AIESECMember BIT DEFAULT 0 NOT NULL   ,
    Nationality VARCHAR(25) DEFAULT "" NOT NULL   ,
    Sex ENUM('w','m') DEFAULT 0 NOT NULL   ,
    Status BIT DEFAULT 0 NOT NULL   ,
    Competence VARCHAR(32) DEFAULT ""    ,
    Jobwish DECIMAL(19,0)    REFERENCES Jobwish ( oid ) ,
    Picture VARBINARY     ,
    Lname VARCHAR(25) DEFAULT "" NOT NULL   ,
    Fname VARCHAR(20) DEFAULT "" NOT NULL   ,
    Email VARCHAR(50) DEFAULT "" NOT NULL   ,
    Birthdate DATE  NOT NULL   ,
    Residence VARCHAR(50) DEFAULT ""    ,
    Street VARCHAR(50) DEFAULT "" NOT NULL   ,
    HouseNumber VARCHAR(5) DEFAULT "" NOT NULL   ,
    PLZ MEDIUMINT UNSIGNED  NOT NULL   ,
    City VARCHAR(30) DEFAULT "" NOT NULL   ,
    Phone VARCHAR(25) DEFAULT ""    ,
    Natel VARCHAR(25) DEFAULT ""    ,
    Fax VARCHAR(25) DEFAULT ""    ,
    LastLogin TIMESTAMP  NOT NULL   ,
    Account DECIMAL(19,0)  NOT NULL   ,

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


