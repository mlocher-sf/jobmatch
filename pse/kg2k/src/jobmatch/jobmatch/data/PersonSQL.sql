/* This SQL was generated for a Standard database. */

create table Person
(
/* class Person */
    Picture DECIMAL(19,0)     ,
    Email VARCHAR(50) DEFAULT ""    ,
    Fax VARCHAR(20) DEFAULT ""    ,
    Adress DECIMAL(19,0)     ,
    Fname VARCHAR(20) DEFAULT ""    ,
    Lname VARCHAR(25) DEFAULT ""    ,
    Natel VARCHAR(25) DEFAULT ""    ,
    Phone VARCHAR(25) DEFAULT ""    ,
    Residence VARCHAR(50) DEFAULT ""    ,
    Sex ENUM('w','m')(32) DEFAULT ""    ,
    Birthdate DATE     ,
    Nationality DECIMAL(19,0)     ,
    URL VARCHAR(50) DEFAULT ""    ,

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


