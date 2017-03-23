DROP TABLE IF EXISTS baum;
CREATE TABLE baum (
  id            SERIAL PRIMARY KEY, 
  pflegeobjekt  integer, 
  objekttyp     integer, 
  bezirk        integer, 
  baumbestand   varchar(256),
  stammvon      double precision,
  stammbis      double precision,
  krone         double precision,
  hoehe         double precision,
  alter         double precision,
  gattung       varchar(256),
  art           varchar(256),
  sorte         varchar(256),
  deutsch       varchar(256),
  modtime       timestamp DEFAULT current_timestamp
);
SELECT AddGeometryColumn ('public','baum','geom',4326,'POINT',2);
