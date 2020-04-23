INSERT INTO regions
VALUES ( 1
       , 'Europe');

INSERT INTO regions
VALUES ( 2
       , 'Americas');

INSERT INTO regions
VALUES ( 3
       , 'Asia');

INSERT INTO regions
VALUES ( 4
       , 'Middle East and Africa');


INSERT INTO countries
VALUES ( 'IT'
       , 'Italy'
       , 1);

INSERT INTO countries
VALUES ( 'JP'
       , 'Japan'
       , 3);

INSERT INTO countries
VALUES ( 'US'
       , 'United States of America'
       , 2);

INSERT INTO countries
VALUES ( 'CA'
       , 'Canada'
       , 2);

INSERT INTO countries
VALUES ( 'CN'
       , 'China'
       , 3);

INSERT INTO countries
VALUES ( 'IN'
       , 'India'
       , 3);

INSERT INTO countries
VALUES ( 'AU'
       , 'Australia'
       , 3);

INSERT INTO countries
VALUES ( 'ZW'
       , 'Zimbabwe'
       , 4);

INSERT INTO countries
VALUES ( 'SG'
       , 'Singapore'
       , 3);

INSERT INTO countries
VALUES ( 'UK'
       , 'United Kingdom'
       , 1);

INSERT INTO countries
VALUES ( 'FR'
       , 'France'
       , 1);

INSERT INTO countries
VALUES ( 'DE'
       , 'Germany'
       , 1);

INSERT INTO countries
VALUES ( 'ZM'
       , 'Zambia'
       , 4);

INSERT INTO countries
VALUES ( 'EG'
       , 'Egypt'
       , 4);

INSERT INTO countries
VALUES ( 'BR'
       , 'Brazil'
       , 2);

INSERT INTO countries
VALUES ( 'CH'
       , 'Switzerland'
       , 1);

INSERT INTO countries
VALUES ( 'NL'
       , 'Netherlands'
       , 1);

INSERT INTO countries
VALUES ( 'MX'
       , 'Mexico'
       , 2);

INSERT INTO countries
VALUES ( 'KW'
       , 'Kuwait'
       , 4);

INSERT INTO countries
VALUES ( 'IL'
       , 'Israel'
       , 4);

INSERT INTO countries
VALUES ( 'DK'
       , 'Denmark'
       , 1);

INSERT INTO countries
VALUES ( 'ML'
       , 'Malaysia'
       , 3);

INSERT INTO countries
VALUES ( 'NG'
       , 'Nigeria'
       , 4);

INSERT INTO countries
VALUES ( 'AR'
       , 'Argentina'
       , 2);

INSERT INTO countries
VALUES ( 'BE'
       , 'Belgium'
       , 1);


INSERT INTO locations
VALUES ( 1000
       , '1297 Via Cola di Rie'
       , '00989'
       , 'Roma'
       , NULL
       , 'IT');

INSERT INTO locations
VALUES ( 1100
       , '93091 Calle della Testa'
       , '10934'
       , 'Venice'
       , NULL
       , 'IT');

INSERT INTO locations
VALUES ( 1200
       , '2017 Shinjuku.ku'
       , '1689'
       , 'Tokyo'
       , 'Tokyo Prefecture'
       , 'JP');

INSERT INTO locations
VALUES ( 1300
       , '9450 Kamiya-cho'
       , '6823'
       , 'Hiroshima'
       , NULL
       , 'JP');

INSERT INTO locations
VALUES ( 1400
       , '2014 Jabberwocky Rd'
       , '26192'
       , 'Southlake'
       , 'Texas'
       , 'US');

INSERT INTO locations
VALUES ( 1500
       , '2011 Interiors Blvd'
       , '99236'
       , 'South San Francisco'
       , 'California'
       , 'US');

INSERT INTO locations
VALUES ( 1600
       , '2007 Zagora St'
       , '50090'
       , 'South Brunswick'
       , 'New Jersey'
       , 'US');

INSERT INTO locations
VALUES ( 1700
       , '2004 Charade Rd'
       , '98199'
       , 'Seattle'
       , 'Washington'
       , 'US');

INSERT INTO locations
VALUES ( 1800
       , '147 Spadina Ave'
       , 'M5V 2L7'
       , 'Toronto'
       , 'Ontario'
       , 'CA');

INSERT INTO locations
VALUES ( 1900
       , '6092 Boxwood St'
       , 'YSW 9T2'
       , 'Whitehorse'
       , 'Yukon'
       , 'CA');

INSERT INTO locations
VALUES ( 2000
       , '40-5-12 Laogianggen'
       , '190518'
       , 'Beijing'
       , NULL
       , 'CN');

INSERT INTO locations
VALUES ( 2100
       , '1298 Vileparle (E)'
       , '490231'
       , 'Bombay'
       , 'Maharashtra'
       , 'IN');

INSERT INTO locations
VALUES ( 2200
       , '12-98 Victoria Street'
       , '2901'
       , 'Sydney'
       , 'New South Wales'
       , 'AU');

INSERT INTO locations
VALUES ( 2300
       , '198 Clementi North'
       , '540198'
       , 'Singapore'
       , NULL
       , 'SG');

INSERT INTO locations
VALUES ( 2400
       , '8204 Arthur St'
       , NULL
       , 'London'
       , NULL
       , 'UK');

INSERT INTO locations
VALUES ( 2500
       , 'Magdalen Centre, The Oxford Science Park'
       , 'OX9 9ZB'
       , 'Oxford'
       , 'Oxford'
       , 'UK');

INSERT INTO locations
VALUES ( 2600
       , '9702 Chester Road'
       , '09629850293'
       , 'Stretford'
       , 'Manchester'
       , 'UK');

INSERT INTO locations
VALUES ( 2700
       , 'Schwanthalerstr. 7031'
       , '80925'
       , 'Munich'
       , 'Bavaria'
       , 'DE');

INSERT INTO locations
VALUES ( 2800
       , 'Rua Frei Caneca 1360 '
       , '01307-002'
       , 'Sao Paulo'
       , 'Sao Paulo'
       , 'BR');

INSERT INTO locations
VALUES ( 2900
       , '20 Rue des Corps-Saints'
       , '1730'
       , 'Geneva'
       , 'Geneve'
       , 'CH');

INSERT INTO locations
VALUES ( 3000
       , 'Murtenstrasse 921'
       , '3095'
       , 'Bern'
       , 'BE'
       , 'CH');

INSERT INTO locations
VALUES ( 3100
       , 'Pieter Breughelstraat 837'
       , '3029SK'
       , 'Utrecht'
       , 'Utrecht'
       , 'NL');

INSERT INTO locations
VALUES ( 3200
       , 'Mariano Escobedo 9991'
       , '11932'
       , 'Mexico City'
       , 'Distrito Federal,'
       , 'MX');


ALTER TABLE DEPARTMENTS
    DROP CONSTRAINT DEPT_MGR_FK;

/*ALTER TABLE departments
  DISABLE CONSTRAINT dept_mgr_fk;*/

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Administration'
       , 100
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Marketing'
       , 1
       , 1800);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Purchasing'
       , 14
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Human Resources'
       , 103
       , 2400);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Shipping'
       , 21
       , 1500);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'IT'
       , 3
       , 1400);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Public Relations'
       , 4
       , 2700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Sales'
       , 45
       , 2500);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Executive'
       , 1
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Finance'
       , 8
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Accounting'
       , 5
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Treasury'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Corporate Tax'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Control And Credit'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Shareholder Services'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Benefits'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Manufacturing'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Construction'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Contracting'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Operations'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'IT Support'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'NOC'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'IT Helpdesk'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Government Sales'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Retail Sales'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Recruiting'
       , NULL
       , 1700);

INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
VALUES ( 
       'Payroll'
       , NULL
       , 1700);

INSERT INTO jobs
VALUES ( 'AD_PRES'
       , 'President'
       , 20080
       , 40000);
INSERT INTO jobs
VALUES ( 'AD_VP'
       , 'Administration Vice President'
       , 15000
       , 30000);

INSERT INTO jobs
VALUES ( 'AD_ASST'
       , 'Administration Assistant'
       , 3000
       , 6000);

INSERT INTO jobs
VALUES ( 'FI_MGR'
       , 'Finance Manager'
       , 8200
       , 16000);

INSERT INTO jobs
VALUES ( 'FI_ACCOUNT'
       , 'Accountant'
       , 4200
       , 9000);

INSERT INTO jobs
VALUES ( 'AC_MGR'
       , 'Accounting Manager'
       , 8200
       , 16000);

INSERT INTO jobs
VALUES ( 'AC_ACCOUNT'
       , 'Public Accountant'
       , 4200
       , 9000);
INSERT INTO jobs
VALUES ( 'SA_MAN'
       , 'Sales Manager'
       , 10000
       , 20080);

INSERT INTO jobs
VALUES ( 'SA_REP'
       , 'Sales Representative'
       , 6000
       , 12008);

INSERT INTO jobs
VALUES ( 'PU_MAN'
       , 'Purchasing Manager'
       , 8000
       , 15000);

INSERT INTO jobs
VALUES ( 'PU_CLERK'
       , 'Purchasing Clerk'
       , 2500
       , 5500);

INSERT INTO jobs
VALUES ( 'ST_MAN'
       , 'Stock Manager'
       , 5500
       , 8500);
INSERT INTO jobs
VALUES ( 'ST_CLERK'
       , 'Stock Clerk'
       , 2008
       , 5000);

INSERT INTO jobs
VALUES ( 'SH_CLERK'
       , 'Shipping Clerk'
       , 2500
       , 5500);

INSERT INTO jobs
VALUES ( 'IT_PROG'
       , 'Programmer'
       , 4000
       , 10000);

INSERT INTO jobs
VALUES ( 'MK_MAN'
       , 'Marketing Manager'
       , 9000
       , 15000);

INSERT INTO jobs
VALUES ( 'MK_REP'
       , 'Marketing Representative'
       , 4000
       , 9000);

INSERT INTO jobs
VALUES ( 'HR_REP'
       , 'Human Resources Representative'
       , 4000
       , 9000);

INSERT INTO jobs
VALUES ( 'PR_REP'
       , 'Public Relations Representative'
       , 4500
       , 10500);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Steven'
       , 'King'
       , 'SKING'
       , '515.123.4567'
       , DATE('17.06.2003')
       , 'AD_PRES'
       , 24000
       , NULL
       , 1
       , 9);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Neena'
       , 'Kochhar'
       , 'NKOCHHAR'
       , '515.123.4568'
       , DATE('21.09.2005')
       , 'AD_VP'
       , 17000
       , NULL
       , 1
       , 9);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Lex'
       , 'De Haan'
       , 'LDEHAAN'
       , '515.123.4569'
       , DATE('13.01.2001')
       , 'AD_VP'
       , 17000
       , NULL
       , 1
       , 9);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Alexander'
       , 'Hunold'
       , 'AHUNOLD'
       , '590.423.4567'
       , DATE('03.01.2006')
       , 'IT_PROG'
       , 9000
       , NULL
       , 2
        , 6);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Bruce'
       , 'Ernst'
       , 'BERNST'
       , '590.423.4568'
       , DATE('21.05.2007')
       , 'IT_PROG'
       , 6000
       , NULL
       , 3
        , 6);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'David'
       , 'Austin'
       , 'DAUSTIN'
       , '590.423.4569'
       , DATE('25.06.2005')
       , 'IT_PROG'
       , 4800
       , NULL
       , 3
        , 6);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Valli'
       , 'Pataballa'
       , 'VPATABAL'
       , '590.423.4560'
       , DATE('05.02.2006')
       , 'IT_PROG'
       , 4800
       , NULL
       , 3
        , 6);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Diana'
       , 'Lorentz'
       , 'DLORENTZ'
       , '590.423.5567'
       , DATE('07.02.2007')
       , 'IT_PROG'
       , 4200
       , NULL
       , 3
        , 6);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Nancy'
       , 'Greenberg'
       , 'NGREENBE'
       , '515.124.4569'
       , DATE('17.08.2002')
       , 'FI_MGR'
       , 2008
       , NULL
       , 1
        , 10);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Daniel'
       , 'Faviet'
       , 'DFAVIET'
       , '515.124.4169'
       , DATE('16.08.2002')
       , 'FI_ACCOUNT'
       , 9000
       , NULL
       , 8
        , 10);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'John'
       , 'Chen'
       , 'JCHEN'
       , '515.124.4269'
       , DATE('28.09.2005')
       , 'FI_ACCOUNT'
       , 8200
       , NULL
       , 8
        , 10);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Ismael'
       , 'Sciarra'
       , 'ISCIARRA'
       , '515.124.4369'
       , DATE('30.09.2005')
       , 'FI_ACCOUNT'
       , 7700
       , NULL
       , 8
        , 10);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Jose Manuel'
       , 'Urman'
       , 'JMURMAN'
       , '515.124.4469'
       , DATE('07.03.2006')
       , 'FI_ACCOUNT'
       , 7800
       , NULL
       , 8
        , 10);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Luis'
       , 'Popp'
       , 'LPOPP'
       , '515.124.4567'
       , DATE('07.12.2007')
       , 'FI_ACCOUNT'
       , 6900
       , NULL
       , 8
        , 10);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Den'
       , 'Raphaely'
       , 'DRAPHEAL'
       , '515.127.4561'
       , DATE('07.12.2002')
       , 'PU_MAN'
       , 11000
       , NULL
       , 1
        , 3);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Alexander'
       , 'Khoo'
       , 'AKHOO'
       , '515.127.4562'
       , DATE('18.05.2003')
       , 'PU_CLERK'
       , 3100
       , NULL
       , 14
        , 3);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Shelli'
       , 'Baida'
       , 'SBAIDA'
       , '515.127.4563'
       , DATE('24.12.2005')
       , 'PU_CLERK'
       , 2900
       , NULL
       , 14
        , 3);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Sigal'
       , 'Tobias'
       , 'STOBIAS'
       , '515.127.4564'
       , DATE('24.07.2005')
       , 'PU_CLERK'
       , 2800
       , NULL
       , 14
        , 3);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Guy'
       , 'Himuro'
       , 'GHIMURO'
       , '515.127.4565'
       , DATE('15.11.2006')
       , 'PU_CLERK'
       , 2600
       , NULL
       , 14
        , 3);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Karen'
       , 'Colmenares'
       , 'KCOLMENA'
       , '515.127.4566'
       , DATE('10.08.2007')
       , 'PU_CLERK'
       , 2500
       , NULL
       , 14
        , 3);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Matthew'
       , 'Weiss'
       , 'MWEISS'
       , '650.123.1234'
       , DATE('18.07.2004')
       , 'ST_MAN'
       , 8000
       , NULL
       , 1
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Adam'
       , 'Fripp'
       , 'AFRIPP'
       , '650.123.2234'
       , DATE('10.04.2005')
       , 'ST_MAN'
       , 8200
       , NULL
       , 1
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Payam'
       , 'Kaufling'
       , 'PKAUFLIN'
       , '650.123.3234'
       , DATE('01.05.2003')
       , 'ST_MAN'
       , 7900
       , NULL
       , 1
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Shanta'
       , 'Vollman'
       , 'SVOLLMAN'
       , '650.123.4234'
       , DATE('10.10.2005')
       , 'ST_MAN'
       , 6500
       , NULL
       , 1
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Kevin'
       , 'Mourgos'
       , 'KMOURGOS'
       , '650.123.5234'
       , DATE('16.11.2007')
       , 'ST_MAN'
       , 5800
       , NULL
       , 1
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Julia'
       , 'Nayer'
       , 'JNAYER'
       , '650.124.1214'
       , DATE('16.07.2005')
       , 'ST_CLERK'
       , 3200
       , NULL
       , 20
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Irene'
       , 'Mikkilineni'
       , 'IMIKKILI'
       , '650.124.1224'
       , DATE('28.09.2006')
       , 'ST_CLERK'
       , 2700
       , NULL
       , 20
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'James'
       , 'Landry'
       , 'JLANDRY'
       , '650.124.1334'
       , DATE('14.01.2007')
       , 'ST_CLERK'
       , 2400
       , NULL
       , 20
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Steven'
       , 'Markle'
       , 'SMARKLE'
       , '650.124.1434'
       , DATE('08.03.2008')
       , 'ST_CLERK'
       , 2200
       , NULL
       , 20
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Laura'
       , 'Bissot'
       , 'LBISSOT'
       , '650.124.5234'
       , DATE('20.08.2005')
       , 'ST_CLERK'
       , 3300
       , NULL
       , 21
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Mozhe'
       , 'Atkinson'
       , 'MATKINSO'
       , '650.124.6234'
       , DATE('30.10.2005')
       , 'ST_CLERK'
       , 2800
       , NULL
       , 21
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'James'
       , 'Marlow'
       , 'JAMRLOW'
       , '650.124.7234'
       , DATE('16.02.2005')
       , 'ST_CLERK'
       , 2500
       , NULL
       , 21
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'TJ'
       , 'Olson'
       , 'TJOLSON'
       , '650.124.8234'
       , DATE('10.04.2007')
       , 'ST_CLERK'
       , 2100
       , NULL
       , 21
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Jason'
       , 'Mallin'
       , 'JMALLIN'
       , '650.127.1934'
       , DATE('14.06.2004')
       , 'ST_CLERK'
       , 3300
       , NULL
       , 22
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Michael'
       , 'Rogers'
       , 'MROGERS'
       , '650.127.1834'
       , DATE('26.08.2006')
       , 'ST_CLERK'
       , 2900
       , NULL
       , 22
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Ki'
       , 'Gee'
       , 'KGEE'
       , '650.127.1734'
       , DATE('12.12.2007')
       , 'ST_CLERK'
       , 2400
       , NULL
       , 22
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Hazel'
       , 'Philtanker'
       , 'HPHILTAN'
       , '650.127.1634'
       , DATE('06.02.2008')
       , 'ST_CLERK'
       , 2200
       , NULL
       , 22
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Renske'
       , 'Ladwig'
       , 'RLADWIG'
       , '650.121.1234'
       , DATE('14.07.2003')
       , 'ST_CLERK'
       , 3600
       , NULL
       , 23
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Stephen'
       , 'Stiles'
       , 'SSTILES'
       , '650.121.2034'
       , DATE('26.10.2005')
       , 'ST_CLERK'
       , 3200
       , NULL
       , 23
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'John'
       , 'Seo'
       , 'JSEO'
       , '650.121.2019'
       , DATE('12.02.2006')
       , 'ST_CLERK'
       , 2700
       , NULL
       , 23
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Joshua'
       , 'Patel'
       , 'JPATEL'
       , '650.121.1834'
       , DATE('06.04.2006')
       , 'ST_CLERK'
       , 2500
       , NULL
       , 23
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Trenna'
       , 'Rajs'
       , 'TRAJS'
       , '650.121.8009'
       , DATE('17.10.2003')
       , 'ST_CLERK'
       , 3500
       , NULL
       , 24
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Curtis'
       , 'Davies'
       , 'CDAVIES'
       , '650.121.2994'
       , DATE('29.01.2005')
       , 'ST_CLERK'
       , 3100
       , NULL
       , 24
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Randall'
       , 'Matos'
       , 'RMATOS'
       , '650.121.2874'
       , DATE('15.03.2006')
       , 'ST_CLERK'
       , 2600
       , NULL
       , 24
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Peter'
       , 'Vargas'
       , 'PVARGAS'
       , '650.121.2004'
       , DATE('09.07.2006')
       , 'ST_CLERK'
       , 2500
       , NULL
       , 24
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'John'
       , 'Russell'
       , 'JRUSSEL'
       , '011.44.1344.429268'
       , DATE('01.10.2004')
       , 'SA_MAN'
       , 14000
       , .4
       , 1
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Karen'
       , 'Partners'
       , 'KPARTNER'
       , '011.44.1344.467268'
       , DATE('05.01.2005')
       , 'SA_MAN'
       , 13500
       , .3
       , 1
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Alberto'
       , 'Errazuriz'
       , 'AERRAZUR'
       , '011.44.1344.429278'
       , DATE('10.03.2005')
       , 'SA_MAN'
       , 12000
       , .3
       , 1
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Gerald'
       , 'Cambrault'
       , 'GCAMBRAU'
       , '011.44.1344.619268'
       , DATE('15.10.2007')
       , 'SA_MAN'
       , 11000
       , .3
       , 1
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Eleni'
       , 'Zlotkey'
       , 'EZLOTKEY'
       , '011.44.1344.429018'
       , DATE('29.01.2008')
       , 'SA_MAN'
       , 10500
       , .2
       , 1
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Peter'
       , 'Tucker'
       , 'PTUCKER'
       , '011.44.1344.129268'
       , DATE('30.01.2005')
       , 'SA_REP'
       , 10000
       , .3
       , 45
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'David'
       , 'Bernstein'
       , 'DBERNSTE'
       , '011.44.1344.345268'
       , DATE('24.03.2005')
       , 'SA_REP'
       , 9500
       , .25
       , 45
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Peter'
       , 'Hall'
       , 'PHALL'
       , '011.44.1344.478968'
       , DATE('20.08.2005')
       , 'SA_REP'
       , 9000
       , .25
       , 45
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Christopher'
       , 'Olsen'
       , 'COLSEN'
       , '011.44.1344.498718'
       , DATE('30.03.2006')
       , 'SA_REP'
       , 8000
       , .2
       , 45
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Nanette'
       , 'Cambrault'
       , 'NCAMBRAU'
       , '011.44.1344.987668'
       , DATE('09.12.2006')
       , 'SA_REP'
       , 7500
       , .2
       , 45
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Oliver'
       , 'Tuvault'
       , 'OTUVAULT'
       , '011.44.1344.486508'
       , DATE('23.11.2007')
       , 'SA_REP'
       , 7000
       , .15
       , 45
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Janette'
       , 'King'
       , 'JKING'
       , '011.44.1345.429268'
       , DATE('30.01.2004')
       , 'SA_REP'
       , 10000
       , .35
       , 46
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Patrick'
       , 'Sully'
       , 'PSULLY'
       , '011.44.1345.929268'
       , DATE('04.03.2004')
       , 'SA_REP'
       , 9500
       , .35
       , 46
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Allan'
       , 'McEwen'
       , 'AMCEWEN'
       , '011.44.1345.829268'
       , DATE('01.08.2004')
       , 'SA_REP'
       , 9000
       , .35
       , 46
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Lindsey'
       , 'Smith'
       , 'LSMITH'
       , '011.44.1345.729268'
       , DATE('10.03.2005')
       , 'SA_REP'
       , 8000
       , .3
       , 46
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Louise'
       , 'Doran'
       , 'LDORAN'
       , '011.44.1345.629268'
       , DATE('15.12.2005')
       , 'SA_REP'
       , 7500
       , .3
       , 46
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Sarath'
       , 'Sewall'
       , 'SSEWALL'
       , '011.44.1345.529268'
       , DATE('03.11.2006')
       , 'SA_REP'
       , 7000
       , .25
       , 46
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Clara'
       , 'Vishney'
       , 'CVISHNEY'
       , '011.44.1346.129268'
       , DATE('11.11.2005')
       , 'SA_REP'
       , 10500
       , .25
       , 47
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Danielle'
       , 'Greene'
       , 'DGREENE'
       , '011.44.1346.229268'
       , DATE('19.03.2007')
       , 'SA_REP'
       , 9500
       , .15
       , 47
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Mattea'
       , 'Marvins'
       , 'MMARVINS'
       , '011.44.1346.329268'
       , DATE('24.01.2008')
       , 'SA_REP'
       , 7200
       , .10
       , 47
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'David'
       , 'Lee'
       , 'DLEE'
       , '011.44.1346.529268'
       , DATE('23.02.2008')
       , 'SA_REP'
       , 6800
       , .1
       , 47
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Sundar'
       , 'Ande'
       , 'SANDE'
       , '011.44.1346.629268'
       , DATE('24.03.2008')
       , 'SA_REP'
       , 6400
       , .10
       , 47
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Amit'
       , 'Banda'
       , 'ABANDA'
       , '011.44.1346.729268'
       , DATE('21.04.2008')
       , 'SA_REP'
       , 6200
       , .10
       , 47
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Lisa'
       , 'Ozer'
       , 'LOZER'
       , '011.44.1343.929268'
       , DATE('11.03.2005')
       , 'SA_REP'
       , 11500
       , .25
       , 48
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Harrison'
       , 'Bloom'
       , 'HBLOOM'
       , '011.44.1343.829268'
       , DATE('23.03.2006')
       , 'SA_REP'
       , 10000
       , .20
       , 48
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Tayler'
       , 'Fox'
       , 'TFOX'
       , '011.44.1343.729268'
       , DATE('24.01.2006')
       , 'SA_REP'
       , 9600
       , .20
       , 48
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'William'
       , 'Smith'
       , 'WSMITH'
       , '011.44.1343.629268'
       , DATE('23.02.2007')
       , 'SA_REP'
       , 7400
       , .15
       , 48
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Elizabeth'
       , 'Bates'
       , 'EBATES'
       , '011.44.1343.529268'
       , DATE('24.03.2007')
       , 'SA_REP'
       , 7300
       , .15
       , 48
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Sundita'
       , 'Kumar'
       , 'SKUMAR'
       , '011.44.1343.329268'
       , DATE('21.04.2008')
       , 'SA_REP'
       , 6100
       , .10
       , 48
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Ellen'
       , 'Abel'
       , 'EABEL'
       , '011.44.1644.429267'
       , DATE('11.05.2004')
       , 'SA_REP'
       , 11000
       , .30
       , 49
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Alyssa'
       , 'Hutton'
       , 'AHUTTON'
       , '011.44.1644.429266'
       , DATE('19.03.2005')
       , 'SA_REP'
       , 8800
       , .25
       , 49
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Jonathon'
       , 'Taylor'
       , 'JTAYLOR'
       , '011.44.1644.429265'
       , DATE('24.03.2006')
       , 'SA_REP'
       , 8600
       , .20
       , 49
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Jack'
       , 'Livingston'
       , 'JLIVINGS'
       , '011.44.1644.429264'
       , DATE('23.04.2006')
       , 'SA_REP'
       , 8400
       , .20
       , 49
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Kimberely'
       , 'Grant'
       , 'KGRANT'
       , '011.44.1644.429263'
       , DATE('24.05.2007')
       , 'SA_REP'
       , 7000
       , .15
       , 49
       , NULL);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Charles'
       , 'Johnson'
       , 'CJOHNSON'
       , '011.44.1644.429262'
       , DATE('04.01.2008')
       , 'SA_REP'
       , 6200
       , .10
       , 49
       , 8);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Winston'
       , 'Taylor'
       , 'WTAYLOR'
       , '650.507.9876'
       , DATE('24.01.2006')
       , 'SH_CLERK'
       , 3200
       , NULL
       , 20
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Jean'
       , 'Fleaur'
       , 'JFLEAUR'
       , '650.507.9877'
       , DATE('23.02.2006')
       , 'SH_CLERK'
       , 3100
       , NULL
       , 20
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Martha'
       , 'Sullivan'
       , 'MSULLIVA'
       , '650.507.9878'
       , DATE('21.06.2007')
       , 'SH_CLERK'
       , 2500
       , NULL
       , 20
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Girard'
       , 'Geoni'
       , 'GGEONI'
       , '650.507.9879'
       , DATE('03.02.2008')
       , 'SH_CLERK'
       , 2800
       , NULL
       , 20
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Nandita'
       , 'Sarchand'
       , 'NSARCHAN'
       , '650.509.1876'
       , DATE('27.01.2004')
       , 'SH_CLERK'
       , 4200
       , NULL
       , 21
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Alexis'
       , 'Bull'
       , 'ABULL'
       , '650.509.2876'
       , DATE('20.02.2005')
       , 'SH_CLERK'
       , 4100
       , NULL
       , 21
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Julia'
       , 'Dellinger'
       , 'JDELLING'
       , '650.509.3876'
       , DATE('24.06.2006')
       , 'SH_CLERK'
       , 3400
       , NULL
       , 21
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Anthony'
       , 'Cabrio'
       , 'ACABRIO'
       , '650.509.4876'
       , DATE('07.02.2007')
       , 'SH_CLERK'
       , 3000
       , NULL
       , 21
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Kelly'
       , 'Chung'
       , 'KCHUNG'
       , '650.505.1876'
       , DATE('14.06.2005')
       , 'SH_CLERK'
       , 3800
       , NULL
       , 22
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Jennifer'
       , 'Dilly'
       , 'JDILLY'
       , '650.505.2876'
       , DATE('13.08.2005')
       , 'SH_CLERK'
       , 3600
       , NULL
       , 22
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Timothy'
       , 'Gates'
       , 'TGATES'
       , '650.505.3876'
       , DATE('11.07.2006')
       , 'SH_CLERK'
       , 2900
       , NULL
       , 22
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Randall'
       , 'Perkins'
       , 'RPERKINS'
       , '650.505.4876'
       , DATE('19.12.2007')
       , 'SH_CLERK'
       , 2500
       , NULL
       , 22
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Sarah'
       , 'Bell'
       , 'SBELL'
       , '650.501.1876'
       , DATE('04.02.2004')
       , 'SH_CLERK'
       , 4000
       , NULL
       , 23
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Britney'
       , 'Everett'
       , 'BEVERETT'
       , '650.501.2876'
       , DATE('03.03.2005')
       , 'SH_CLERK'
       , 3900
       , NULL
       , 23
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Samuel'
       , 'McCain'
       , 'SMCCAIN'
       , '650.501.3876'
       , DATE('01.07.2006')
       , 'SH_CLERK'
       , 3200
       , NULL
       , 23
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Vance'
       , 'Jones'
       , 'VJONES'
       , '650.501.4876'
       , DATE('17.03.2007')
       , 'SH_CLERK'
       , 2800
       , NULL
       , 23
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Alana'
       , 'Walsh'
       , 'AWALSH'
       , '650.507.9811'
       , DATE('24.04.2006')
       , 'SH_CLERK'
       , 3100
       , NULL
       , 24
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Kevin'
       , 'Feeney'
       , 'KFEENEY'
       , '650.507.9822'
       , DATE('23.05.2006')
       , 'SH_CLERK'
       , 3000
       , NULL
       , 24
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Donald'
       , 'OConnell'
       , 'DOCONNEL'
       , '650.507.9833'
       , DATE('21.06.2007')
       , 'SH_CLERK'
       , 2600
       , NULL
       , 24
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Douglas'
       , 'Grant'
       , 'DGRANT'
       , '650.507.9844'
       , DATE('13.01.2008')
       , 'SH_CLERK'
       , 2600
       , NULL
       , 24
        , 5);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Jennifer'
       , 'Whalen'
       , 'JWHALEN'
       , '515.123.4444'
       , DATE('17.09.2003')
       , 'AD_ASST'
       , 4400
       , NULL
       , 1
       , 10);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Michael'
       , 'Hartstein'
       , 'MHARTSTE'
       , '515.123.5555'
       , DATE('17.02.2004')
       , 'MK_MAN'
       , 13000
       , NULL
       , 1
        , 2);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Pat'
       , 'Fay'
       , 'PFAY'
       , '603.123.6666'
       , DATE('17.08.2005')
       , 'MK_REP'
       , 6000
       , NULL
       , 1
        , 2);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Susan'
       , 'Mavris'
       , 'SMAVRIS'
       , '515.123.7777'
       , DATE('07.06.2002')
       , 'HR_REP'
       , 6500
       , NULL
       , 1
        , 4);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Hermann'
       , 'Baer'
       , 'HBAER'
       , '515.123.8888'
       , DATE('07.06.2002')
       , 'PR_REP'
       , 10000
       , NULL
       , 1
        , 7);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'Shelley'
       , 'Higgins'
       , 'SHIGGINS'
       , '515.123.8080'
       , DATE('07.06.2002')
       , 'AC_MGR'
       , 12008
       , NULL
       , 1
        , 11);

iNSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
VALUES ( 
       'William'
       , 'Gietz'
       , 'WGIETZ'
       , '515.123.8181'
       , DATE('07.06.2002')
       , 'AC_ACCOUNT'
       , 8300
       , NULL
       , 105
        , 11);


INSERT INTO job_history
VALUES ( 102
       , DATE('13.01.2001')
       , DATE('24.07.2006')
       , 'IT_PROG'
        , 6);

INSERT INTO job_history
VALUES ( 101
       , DATE('21.09.1997')
       , DATE('27.10.2001')
       , 'AC_ACCOUNT'
        , 11);

INSERT INTO job_history
VALUES ( 101
       , DATE('28.10.2001')
       , DATE('15.03.2005')
       , 'AC_MGR'
        , 11);

INSERT INTO job_history
VALUES ( 101
       , DATE('17.02.2004')
       , DATE('19.12.2007')
       , 'MK_REP'
        , 2);

INSERT INTO job_history
VALUES ( 14
       , DATE('24.03.2006')
       , DATE('31.12.2007')
       , 'ST_CLERK'
        , 5);

INSERT INTO job_history
VALUES ( 22
       , DATE('01.01.2007')
       , DATE('31.12.2007')
       , 'ST_CLERK'
        , 5);

INSERT INTO job_history
VALUES ( 100
       , DATE('17.09.1995')
       , DATE('17.06.2001')
       , 'AD_ASST'
       , 9);

INSERT INTO job_history
VALUES ( 76
       , DATE('24.03.2006')
       , DATE('31.12.2006')
       , 'SA_REP'
       , 8);

INSERT INTO job_history
VALUES ( 76
       , DATE('01.01.2007')
       , DATE('31.12.2007')
       , 'SA_MAN'
       , 8);

INSERT INTO job_history
VALUES ( 100
       , DATE('01.07.2002')
       , DATE('31.12.2006')
       , 'AC_ACCOUNT'
       , 9);

/*ALTER TABLE departments
  ENABLE CONSTRAINT dept_mgr_fk;*/

ALTER TABLE departments
    ADD CONSTRAINT dept_mgr_fk
        FOREIGN KEY (manager_id)
            REFERENCES employees (employee_id);


