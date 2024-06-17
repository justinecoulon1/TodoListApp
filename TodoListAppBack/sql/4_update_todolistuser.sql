ALTER TABLE todolistuser ADD PASSWORD varchar(255);
ALTER TABLE todolistuser RENAME COLUMN name TO email;
ALTER TABLE todolistuser ADD sessiontoken varchar(255);