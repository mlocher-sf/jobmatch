/* This SQL was generated for a Standard database. */

create table Graduation
(
/* class Graduation */
    Type VARCHAR(32) DEFAULT "" NOT NULL   ,

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


/* This SQL was generated for a Standard database. */

create table CandidateAccount
(
/* class Account */
    Username VARCHAR(40) DEFAULT "" NOT NULL   ,
    Password VARCHAR(20) DEFAULT "" NOT NULL   ,
/* class CandidateAccount */

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

CREATE INDEX I1_CandidateAccount ON CandidateAccount ( Username );

/* This SQL was generated for a Standard database. */

create table Area
(
/* class Area */
    Name VARCHAR(30) DEFAULT "" NOT NULL   ,

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


/* This SQL was generated for a Standard database. */

create table CompanyAccount
(
/* class Account */
    Username VARCHAR(40) DEFAULT "" NOT NULL   ,
    Password VARCHAR(20) DEFAULT "" NOT NULL   ,
/* class CompanyAccount */

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

CREATE INDEX I1_CompanyAccount ON CompanyAccount ( Username );

/* This SQL was generated for a Standard database. */

create table Contactperson
(
/* class Contactperson */
    Name VARCHAR(40) DEFAULT "" NOT NULL   ,
    Fax VARCHAR(20) DEFAULT ""    ,
    Email VARCHAR(50) DEFAULT "" NOT NULL   ,

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


/* This SQL was generated for a Standard database. */

create table Industry
(
/* class Industry */
    Type VARCHAR(30) DEFAULT "" NOT NULL   ,

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


/* This SQL was generated for a Standard database. */

create table Jobwish
(
/* class Jobwish */
    Pensum TINYINT UNSIGNED     ,
    Remarks TEXT(70) DEFAULT ""    ,
    Industry DECIMAL(19,0)  NOT NULL  REFERENCES Industry ( oid ) ,
    Area DECIMAL(19,0)  NOT NULL  REFERENCES Area ( oid ) ,
    Function VARCHAR(50) DEFAULT ""    ,

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


/* This SQL was generated for a Standard database. */

create table Country
(
/* class Country */
    Name VARCHAR(32) DEFAULT "" NOT NULL   ,

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


/* This SQL was generated for a Standard database. */

create table Company
(
/* class Company */
    Active BIT DEFAULT 0 NOT NULL   ,
    Earnings INTEGER     ,
    GraduatesPerYear INTEGER     ,
    Location VARCHAR(40) DEFAULT "" NOT NULL   ,
    Name VARCHAR(30) DEFAULT "" NOT NULL   ,
    NumberEmployees INTEGER     ,
    ShortDescription LONGVARCHAR(32) DEFAULT ""    ,
    Contact DECIMAL(19,0)  NOT NULL  REFERENCES Contactperson ( oid ) ,
    Industry DECIMAL(19,0)    REFERENCES Industry ( oid ) ,
    Account DECIMAL(19,0)  NOT NULL   ,
    Presence VARCHAR(40) DEFAULT ""    ,
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


create table objectid(
 next DECIMAL(19,0) NOT NULL 
);
