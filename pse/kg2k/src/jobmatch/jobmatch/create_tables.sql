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

create table MailQueue
(
/* class MailQueue */
    Mail MEDIUMBLOB  NOT NULL   ,
    Priority INTEGER  NOT NULL   ,
    Time TIMESTAMP(12)  NOT NULL   ,

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

create table ProviderAccount
(
/* class Account */
    Username VARCHAR(40) DEFAULT "" NOT NULL   ,
    Email VARCHAR(50) DEFAULT "" NOT NULL   ,
    LastLogin TIMESTAMP(12)  NOT NULL   ,
    LoginReminder INTEGER DEFAULT 0 NOT NULL   ,
    Passphrase VARCHAR(20) DEFAULT "" NOT NULL   ,
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

create table ProgrammingProfile
(
/* class ComputerRequest */
    Profile DECIMAL(19,0)  NOT NULL  REFERENCES Profile ( oid ) ,
    Mandatory BIT DEFAULT 0 NOT NULL   ,
    LeafNumber INTEGER DEFAULT 0    ,
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

create table OperatingSystemCandidate
(
/* class CVComputer */
    Capability DECIMAL(19,0)  NOT NULL   ,
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
/* class OperatingsystemCandidate */
    System DECIMAL(19,0)  NOT NULL  REFERENCES Operatingsystem ( oid ) ,

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

create table Candidate
(
/* class Candidate */
    AIESECMember BIT DEFAULT 0 NOT NULL   ,
    City VARCHAR(30) DEFAULT "" NOT NULL   ,
    Competence TEXT DEFAULT ""    ,
    Fax VARCHAR(25) DEFAULT ""    ,
    Fname VARCHAR(20) DEFAULT "" NOT NULL   ,
    HouseNumber VARCHAR(5) DEFAULT "" NOT NULL   ,
    Lname VARCHAR(25) DEFAULT "" NOT NULL   ,
    Natel VARCHAR(25) DEFAULT ""    ,
    Nationality VARCHAR(25) DEFAULT "" NOT NULL   ,
    PLZ MEDIUMINT UNSIGNED  NOT NULL   ,
    Phone VARCHAR(25) DEFAULT ""    ,
    Picture MEDIUMBLOB    ,
    Residence VARCHAR(50) DEFAULT ""    ,
    Status BIT DEFAULT 0 NOT NULL   ,
    Street VARCHAR(50) DEFAULT "" NOT NULL   ,
    Sex ENUM('w','m') DEFAULT 'm' NOT NULL   ,
    Birthdate TIMESTAMP(8)  NOT NULL   ,

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
/* class ComputerRequest */
    Profile DECIMAL(19,0)  NOT NULL  REFERENCES Profile ( oid ) ,
    Mandatory BIT DEFAULT 0 NOT NULL   ,
    LeafNumber INTEGER DEFAULT 0    ,
    MinCapability DECIMAL(19,0)  NOT NULL   ,
/* class OperationgsystemProfile */
    System DECIMAL(19,0)  NOT NULL  REFERENCES Operatingsystem ( oid ) ,

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

create table School
(
/* class School */
    Name VARCHAR(50) DEFAULT "" NOT NULL   ,
    Location VARCHAR(40) DEFAULT "" NOT NULL   ,
    Type DECIMAL(19,0)  NOT NULL   ,

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
    LastLogin TIMESTAMP(12)  NOT NULL   ,
    LoginReminder INTEGER DEFAULT 0 NOT NULL   ,
    Passphrase VARCHAR(20) DEFAULT "" NOT NULL   ,
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

create table LanguageCandidate
(
/* class LanguageCandidate */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
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

create table Hobby
(
/* class Hobby */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
    Title VARCHAR(20) DEFAULT "" NOT NULL   ,
    Description TEXT DEFAULT ""    ,
    Priority TINYINT UNSIGNED DEFAULT 0    ,

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
    ShortDescription TEXT DEFAULT ""    ,
    Contact DECIMAL(19,0)  NOT NULL   ,
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

create table AssociationCandidate
(
/* class AssociationCandidate */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
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

create table MatchQueue
(
/* class MatchQueue */
    Time TIMESTAMP(12)  NOT NULL   ,
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

create table ProgrammingCandidate
(
/* class CVComputer */
    Capability DECIMAL(19,0)  NOT NULL   ,
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
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

create table SoftwareCandidate
(
/* class CVComputer */
    Capability DECIMAL(19,0)  NOT NULL   ,
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
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

create table SoftwareProfile
(
/* class ComputerRequest */
    Profile DECIMAL(19,0)  NOT NULL  REFERENCES Profile ( oid ) ,
    Mandatory BIT DEFAULT 0 NOT NULL   ,
    LeafNumber INTEGER DEFAULT 0    ,
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

create table CompanyAccount
(
/* class Account */
    Username VARCHAR(40) DEFAULT "" NOT NULL   ,
    Email VARCHAR(50) DEFAULT "" NOT NULL   ,
    LastLogin TIMESTAMP(12)  NOT NULL   ,
    LoginReminder INTEGER DEFAULT 0 NOT NULL   ,
    Passphrase VARCHAR(20) DEFAULT "" NOT NULL   ,
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

create table Profile
(
/* class Profile */
    Name VARCHAR(40) DEFAULT "" NOT NULL   ,
    MinSchoolType DECIMAL(19,0)  NOT NULL   ,
    MaxSchoolType DECIMAL(19,0)  NOT NULL   ,
    Company DECIMAL(19,0)  NOT NULL  REFERENCES Company ( oid ) ,
    NeedsRematching BIT DEFAULT 1 NOT NULL   ,
    Notify BIT DEFAULT 1 NOT NULL   ,
    Period INTEGER DEFAULT 0 NOT NULL   ,
    MatchTree MEDIUMBLOB  NOT NULL   ,
    LastNotification TIMESTAMP(12)  NOT NULL   ,

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
    Remarks TEXT DEFAULT ""    ,
    Industry DECIMAL(19,0)  NOT NULL   ,
    Area DECIMAL(19,0)  NOT NULL   ,
    Function VARCHAR(50) DEFAULT ""    ,
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
    Company VARCHAR(30) DEFAULT "" NOT NULL   ,
    City VARCHAR(30) DEFAULT "" NOT NULL   ,
    County DECIMAL(19,0)  NOT NULL   ,

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
/* class LanguageProfile */
    Language DECIMAL(19,0)  NOT NULL  REFERENCES Language ( oid ) ,
    Profile DECIMAL(19,0)  NOT NULL  REFERENCES Profile ( oid ) ,
    Mandatory BIT DEFAULT 0 NOT NULL   ,
    Diploma VARCHAR(32) DEFAULT ""    ,
    MinWritten DECIMAL(19,0)  NOT NULL   ,
    MinSpoken DECIMAL(19,0)  NOT NULL   ,

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
/* class SchoolCandidate */
    School DECIMAL(19,0)  NOT NULL  REFERENCES School ( oid ) ,
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
    Begin TIMESTAMP(8)  NOT NULL   ,
    End TIMESTAMP(8)  NOT NULL   ,
    Diploma DECIMAL(19,0) REFERENCES Graduation (oid) ,
    Remarks TEXT DEFAULT "" ,

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

create table CandidateProfile
(
/* class CandidateProfile */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
    Profile DECIMAL(19,0)  NOT NULL  REFERENCES Profile ( oid ) ,
    MatchingTime TIMESTAMP(12)  NOT NULL   ,
    Deleted BIT DEFAULT 0 NOT NULL   ,

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
/* class EmployerCandidate */
    Candidate DECIMAL(19,0)  NOT NULL  REFERENCES Candidate ( oid ) ,
    Employer DECIMAL(19,0)  NOT NULL  REFERENCES Employer ( oid ) ,
    Begin TIMESTAMP(8)  NOT NULL   ,
    End TIMESTAMP(8)  NOT NULL   ,
    Remarks TEXT DEFAULT ""    ,
    Reference VARCHAR(30) DEFAULT ""    ,
    Function VARCHAR(20) DEFAULT "" NOT NULL   ,
    Pensum TINYINT UNSIGNED  NOT NULL   ,

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
