INSERT INTO COMPANY VALUES (1, 'Chart of Accounts', 'Chart of Accounts');

INSERT INTO ACCOUNT
(account_type, active_status, code, currency, internal_type, name, note, company_id, parent_id)
VALUES ('asset', 'Y', '1', 'IDR', 'view', 'Balance Sheet', 'Balance Sheet', 1, null);
INSERT INTO ACCOUNT
(account_type, active_status, code, currency, internal_type, name, note, company_id, parent_id)
VALUES ('asset', 'Y', '2', 'IDR', 'view', 'Balance Short', 'Balance Short', 1, 1);
INSERT INTO ACCOUNT
(account_type, active_status, code, currency, internal_type, name, note, company_id, parent_id)
VALUES ('asset', 'Y', '3', 'IDR', 'view', 'Balance Looks', 'Balance Looks', 1, 2);
INSERT INTO ACCOUNT
(account_type, active_status, code, currency, internal_type, name, note, company_id, parent_id)
VALUES ('asset', 'Y', '4', 'IDR', 'view', 'Balance Goods', 'Balance Goods', 1, null);
INSERT INTO ACCOUNT
(account_type, active_status, code, currency, internal_type, name, note, company_id, parent_id)
VALUES ('asset', 'Y', '5', 'IDR', 'view', 'Balance Long', 'Balance Long', 1, 1);
INSERT INTO ACCOUNT
(account_type, active_status, code, currency, internal_type, name, note, company_id, parent_id)
VALUES ('asset', 'Y', '6', 'IDR', 'view', 'Balance Integer', 'Balance Integer', 1, 1);
INSERT INTO ACCOUNT
(account_type, active_status, code, currency, internal_type, name, note, company_id, parent_id)
VALUES ('asset', 'Y', '7', 'IDR', 'view', 'Balance String', 'Balance String', 1, 6);

insert into PERSON values (1, 'William', 1, 1, null);
insert into PERSON values (2, 'Henry', 1, 1, 1);
insert into PERSON values (3, 'Matt', 1, 1, 1);
insert into PERSON values (4, 'Alisa', 1, 1, 2);
insert into PERSON values (5, 'May', 1, 1, 1);
insert into PERSON values (6, 'Alexa', 1, 1, 4);
insert into PERSON values (7, 'Sophi', 1, 1, 3);

--insert into AGGREGATORS values (1,'day','2019-09-08',1,null);
--insert into AGGREGATORS values (2,'market','Bangalore',2,1);
--insert into AGGREGATORS values (3,'locality','koramangala',3,2);
--insert into AGGREGATORS values (4,'market','pune',2,1);
--insert into AGGREGATORS values (5,'locality','whitefield',3,2);

