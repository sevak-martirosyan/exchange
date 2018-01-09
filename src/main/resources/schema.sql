DROP TABLE IF EXISTS jpymodel;

CREATE TABLE jpymodel (

  id INTEGER IDENTITY PRIMARY KEY,
  num_code INTEGER DEFAULT 392,
  char_code VARCHAR(30) ,
  nominal INTEGER ,
  name VARCHAR(30)  ,
  value DOUBLE ,
  time_receipt TIMESTAMP DEFAULT now()  NOT NULL
);




