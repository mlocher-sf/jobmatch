/* This SQL was generated for a Standard database. */

create table Compcapability
(
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class Capability */
    Ordinal INTEGER DEFAULT 0 NOT NULL   ,
/* class Compcapability */

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

create table Language
(
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class Language */

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

create table Hobby
(
/* class CVSection */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
/* class Hobby */
    Title VARCHAR(20) DEFAULT "" NOT NULL   ,
    Description TEXT DEFAULT ""    ,
    Priority INTEGER DEFAULT 0    ,

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
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class Country */

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

create table ProviderAccount
(
/* class Account */
    Username VARCHAR(40) DEFAULT "" NOT NULL   ,
    Email VARCHAR(50) DEFAULT "" NOT NULL   ,
    LastLogin TIMESTAMP  NOT NULL   ,
    LoginReminder INTEGER DEFAULT 0 NOT NULL   ,
    Passphrase VARCHAR(20) DEFAULT "" NOT NULL   ,
    LastIP VARCHAR(25) DEFAULT ""    ,
    LastHost VARCHAR(30) DEFAULT ""    ,
/* class ProviderAccount */

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

CREATE INDEX I1_ProviderAccount ON ProviderAccount ( Username );

/* This SQL was generated for a Standard database. */

create table Languagecapability
(
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class Capability */
    Ordinal INTEGER DEFAULT 0 NOT NULL   ,
/* class Languagecapability */

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

create table Schooltype
(
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class Schooltype */

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

create table SchoolProfile
(
/* class TreeLeaf */
    LeafNumber INTEGER     ,
    Profile DECIMAL(19,0)  NOT NULL   ,
    Mandatory BIT DEFAULT 0 NOT NULL   ,
/* class SchoolProfile */
    MinSchoolType DECIMAL(19,0)  NOT NULL  REFERENCES Schooltype ( oid ) ,
    MaxSchoolType DECIMAL(19,0)  NOT NULL  REFERENCES Schooltype ( oid ) ,

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

create table Software
(
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class Software */

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

create table Association
(
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class Association */

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

create table Operatingsystem
(
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class Operatingsystem */

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

create table Area
(
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class Area */

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

create table MailQueue
(
/* class Queue */
    Time TIMESTAMP  NOT NULL   ,
/* class MailQueue */
    Mail MEDIUMBLOB  NOT NULL   ,
    Priority INTEGER  NOT NULL   ,

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

create table Picture
(
/* class Picture */
    MimeType VARCHAR(30) DEFAULT "" NOT NULL   ,
    Data MEDIUMBLOB  NOT NULL   ,

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

create table Graduation
(
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class Graduation */

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
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class Industry */

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

create table Programming
(
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class Programming */

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

create table OperatingSystemCandidate
(
/* class CVSection */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
/* class CVComputer */
    Capability DECIMAL(19,0)  NOT NULL   ,
/* class OperatingsystemCandidate */
    Operatingsystem DECIMAL(19,0)  NOT NULL  REFERENCES Operatingsystem ( oid ) ,

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
/* class CVSection */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
/* class Jobwish */
    Remarks TEXT DEFAULT ""    ,
    Industry DECIMAL(19,0)  NOT NULL   ,
    Area DECIMAL(19,0)     ,
    Function VARCHAR(50) DEFAULT ""    ,
    Pensum INTEGER     ,

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


/* This SQL was generated for a Standard database. */

create table Adress
(
/* class Adress */
    Street VARCHAR(40) DEFAULT ""    ,
    HouseNumber VARCHAR(5) DEFAULT ""    ,
    ZIP VARCHAR(10) DEFAULT ""    ,
    City VARCHAR(30) DEFAULT ""    ,
    Country DECIMAL(19,0)     ,

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

create table PersonalProfile
(
/* class TreeLeaf */
    LeafNumber INTEGER     ,
    Profile DECIMAL(19,0)  NOT NULL   ,
    Mandatory BIT DEFAULT 0 NOT NULL   ,
/* class PersonalProfile */
    MinAge INTEGER     ,
    MaxAge INTEGER     ,
    Nationality DECIMAL(19,0)     ,
    Sex ENUM('m','w') DEFAULT 'm'    ,

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

create table AssociationCandidate
(
/* class CVSection */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
/* class AssociationCandidate */
    Association DECIMAL(19,0)  NOT NULL  REFERENCES Association ( oid ) ,
    Function VARCHAR(40) DEFAULT ""    ,

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

create table School
(
/* class ConstantTable */
    Description VARCHAR(40) DEFAULT "" NOT NULL   ,
/* class School */
    Location VARCHAR(40) DEFAULT ""    ,
    Type DECIMAL(19,0)  NOT NULL  REFERENCES Schooltype ( oid ) ,

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

create table LanguageProfile
(
/* class TreeLeaf */
    LeafNumber INTEGER     ,
    Profile DECIMAL(19,0)  NOT NULL   ,
    Mandatory BIT DEFAULT 0 NOT NULL   ,
/* class LanguageProfile */
    Language DECIMAL(19,0)  NOT NULL  REFERENCES Language ( oid ) ,
    Diploma VARCHAR(32) DEFAULT ""    ,
    MinWritten DECIMAL(19,0)  NOT NULL  REFERENCES Languagecapability ( oid ) ,
    MinSpoken DECIMAL(19,0)  NOT NULL  REFERENCES Languagecapability ( oid ) ,

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

create table SoftwareProfile
(
/* class TreeLeaf */
    LeafNumber INTEGER     ,
    Profile DECIMAL(19,0)  NOT NULL   ,
    Mandatory BIT DEFAULT 0 NOT NULL   ,
/* class ComputerRequest */
    MinCapability DECIMAL(19,0)  NOT NULL   ,
/* class SoftwareProfile */
    Software DECIMAL(19,0)  NOT NULL  REFERENCES Software ( oid ) ,

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

create table ProgrammingCandidate
(
/* class CVSection */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
/* class CVComputer */
    Capability DECIMAL(19,0)  NOT NULL   ,
/* class ProgrammingCandidate */
    Language DECIMAL(19,0)  NOT NULL  REFERENCES Programming ( oid ) ,

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
    Picture DECIMAL(19,0)     ,
    Email VARCHAR(50) DEFAULT ""    ,
    Fax VARCHAR(20) DEFAULT ""    ,
    Adress DECIMAL(19,0)     ,
    Fname VARCHAR(20) DEFAULT ""    ,
    Lname VARCHAR(25) DEFAULT ""    ,
    Natel VARCHAR(25) DEFAULT ""    ,
    Phone VARCHAR(25) DEFAULT ""    ,
    Residence VARCHAR(50) DEFAULT ""    ,
    Sex ENUM('w','m') DEFAULT 'm'    ,
    Birthdate DATE     ,
    Nationality DECIMAL(19,0)     ,
    URL VARCHAR(50) DEFAULT ""    ,
    AIESECMember BIT DEFAULT 0    ,
    Competence TEXT DEFAULT ""    ,
    Status BIT DEFAULT 0    ,

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

create table SoftwareCandidate
(
/* class CVSection */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
/* class CVComputer */
    Capability DECIMAL(19,0)  NOT NULL   ,
/* class SoftwareCandidate */
    Software DECIMAL(19,0)  NOT NULL  REFERENCES Software ( oid ) ,

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

create table ProgrammingProfile
(
/* class TreeLeaf */
    LeafNumber INTEGER     ,
    Profile DECIMAL(19,0)  NOT NULL   ,
    Mandatory BIT DEFAULT 0 NOT NULL   ,
/* class ComputerRequest */
    MinCapability DECIMAL(19,0)  NOT NULL   ,
/* class ProgrammingProfile */
    Language DECIMAL(19,0)  NOT NULL  REFERENCES Programming ( oid ) ,

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

create table OperatingsystemProfile
(
/* class TreeLeaf */
    LeafNumber INTEGER     ,
    Profile DECIMAL(19,0)  NOT NULL   ,
    Mandatory BIT DEFAULT 0 NOT NULL   ,
/* class ComputerRequest */
    MinCapability DECIMAL(19,0)  NOT NULL   ,
/* class OperatingsystemProfile */
    OperatingSystem DECIMAL(19,0)  NOT NULL  REFERENCES Operatingsystem ( oid ) ,

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
    Sex ENUM('w','m') DEFAULT 'm'    ,
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


/* This SQL was generated for a Standard database. */

create table CandidateAccount
(
/* class Account */
    Username VARCHAR(40) DEFAULT "" NOT NULL   ,
    Email VARCHAR(50) DEFAULT "" NOT NULL   ,
    LastLogin TIMESTAMP  NOT NULL   ,
    LoginReminder INTEGER DEFAULT 0 NOT NULL   ,
    Passphrase VARCHAR(20) DEFAULT "" NOT NULL   ,
    LastIP VARCHAR(25) DEFAULT ""    ,
    LastHost VARCHAR(30) DEFAULT ""    ,
/* class CandidateAccount */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,

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

create table MatchQueue
(
/* class Queue */
    Time TIMESTAMP  NOT NULL   ,
/* class MatchQueue */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,

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

create table Employer
(
/* class Employer */
    Name VARCHAR(30) DEFAULT ""    ,
    Adress DECIMAL(19,0)     ,

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

create table SchoolCandidate
(
/* class CVSection */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
/* class CVTimespan */
    BeginDate DATE     ,
    EndDate DATE     ,
/* class SchoolCandidate */
    School DECIMAL(19,0)  NOT NULL  REFERENCES School ( oid ) ,
    Diploma DECIMAL(19,0)     ,
    Remarks TEXT DEFAULT ""    ,

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

create table EmployerCandidate
(
/* class CVSection */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
/* class CVTimespan */
    BeginDate DATE     ,
    EndDate DATE     ,
/* class EmployerCandidate */
    Employer DECIMAL(19,0)  NOT NULL  REFERENCES Employer ( oid ) ,
    Remarks TEXT  DEFAULT ""    ,
    Reference VARCHAR(30) DEFAULT ""    ,
    Function VARCHAR(20) DEFAULT ""    ,
    Pensum INTEGER     ,

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
    Name VARCHAR(30) DEFAULT ""    ,
    Adress DECIMAL(19,0)     ,
    Active BIT DEFAULT 0    ,
    Earnings INTEGER     ,
    GraduatesPerYear INTEGER     ,
    NumberEmployees INTEGER     ,
    ShortDescription TEXT  DEFAULT ""    ,
    Contact DECIMAL(19,0)    REFERENCES Person ( oid ) ,
    Industry DECIMAL(19,0)     ,
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

create table Profile
(
/* class Profile */
    Company DECIMAL(19,0)  NOT NULL  REFERENCES Company ( oid ) ,
    LastNotification TIMESTAMP     ,
    MatchTree MEDIUMBLOB     ,
    Name VARCHAR(40) DEFAULT ""    ,
    NeedsRematching BIT DEFAULT 1    ,
    Notify BIT DEFAULT 1    ,
    NotificationPeriod INTEGER DEFAULT 0    ,

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
    Email VARCHAR(50) DEFAULT "" NOT NULL   ,
    LastLogin TIMESTAMP  NOT NULL   ,
    LoginReminder INTEGER DEFAULT 0 NOT NULL   ,
    Passphrase VARCHAR(20) DEFAULT "" NOT NULL   ,
    LastIP VARCHAR(25) DEFAULT ""    ,
    LastHost VARCHAR(30) DEFAULT ""    ,
/* class CompanyAccount */
    Company DECIMAL(19,0)  NOT NULL  REFERENCES Company ( oid ) ,

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

create table CandidateProfile
(
/* class CandidateProfile */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
    MatchingTime TIMESTAMP  NOT NULL   ,
    Profile DECIMAL(19,0)  NOT NULL  REFERENCES Profile ( oid ) ,
    Score DOUBLE     , 	

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
