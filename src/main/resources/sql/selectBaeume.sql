SELECT 
  id,
  pflegeobjekt,
  objekttyp,
  bezirk,
  baumbestand,
  stammvon,
  stammbis,
  krone,
  hoehe,
  alter,
  gattung,
  art,
  sorte,
  deutsch,
  ST_X(geom) as x,
  ST_Y(geom) as y,
  ST_AsGeoJSON(geom) as geojson,
  geom
FROM 
  baum 