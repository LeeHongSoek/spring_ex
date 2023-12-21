# test_db
A sample database with an integrated test suite, used to test your applications and database servers

This repository was migrated from [Launchpad](https://launchpad.net/test-db).

See usage in the [MySQL docs](https://dev.mysql.com/doc/employee/en/index.html)


## Where it comes from

The original data was created by Fusheng Wang and Carlo Zaniolo at 
Siemens Corporate Research. The data is in XML format.
http://timecenter.cs.aau.dk/software.htm

Giuseppe Maxia made the relational schema and Patrick Crews exported
the data in relational format.

The database contains about 300,000 employee records with 2.8 million 
salary entries. The export data is 167 MB, which is not huge, but
heavy enough to be non-trivial for testing.

The data was generated, and as such there are inconsistencies and subtle
problems. Rather than removing them, we decided to leave the contents
untouched, and use these issues as data cleaning exercises.

## Prerequisites

You need a MySQL database server (5.0+) and run the commands below through a 
user that has the following privileges:

    SELECT, INSERT, UPDATE, DELETE, 
    CREATE, DROP, RELOAD, REFERENCES, 
    INDEX, ALTER, SHOW DATABASES, 
    CREATE TEMPORARY TABLES, 
    LOCK TABLES, EXECUTE, CREATE VIEW

## Installation:

1. Download the repository
2. Change directory to the repository

Then run

    mysql < employees.sql


If you want to install with two large partitioned tables, run

    mysql < employees_partitioned.sql


## Testing the installation

After installing, you can run one of the following

    mysql -t < test_employees_md5.sql
    # OR
    mysql -t < test_employees_sha.sql

For example:

    mysql  -t < test_employees_md5.sql
    +----------------------+
    | INFO                 |
    +----------------------+
    | TESTING INSTALLATION |
    +----------------------+
    +--------------+------------------+----------------------------------+
    | table_name   | expected_records | expected_crc                     |
    +--------------+------------------+----------------------------------+
    | employees    |           300024 | 4ec56ab5ba37218d187cf6ab09ce1aa1 |
    | departments  |                9 | d1af5e170d2d1591d776d5638d71fc5f |
    | dept_manager |               24 | 8720e2f0853ac9096b689c14664f847e |
    | dept_emp     |           331603 | ccf6fe516f990bdaa49713fc478701b7 |
    | titles       |           443308 | bfa016c472df68e70a03facafa1bc0a8 |
    | salaries     |          2844047 | fd220654e95aea1b169624ffe3fca934 |
    +--------------+------------------+----------------------------------+
    +--------------+------------------+----------------------------------+
    | table_name   | found_records    | found_crc                        |
    +--------------+------------------+----------------------------------+
    | employees    |           300024 | 4ec56ab5ba37218d187cf6ab09ce1aa1 |
    | departments  |                9 | d1af5e170d2d1591d776d5638d71fc5f |
    | dept_manager |               24 | 8720e2f0853ac9096b689c14664f847e |
    | dept_emp     |           331603 | ccf6fe516f990bdaa49713fc478701b7 |
    | titles       |           443308 | bfa016c472df68e70a03facafa1bc0a8 |
    | salaries     |          2844047 | fd220654e95aea1b169624ffe3fca934 |
    +--------------+------------------+----------------------------------+
    +--------------+---------------+-----------+
    | table_name   | records_match | crc_match |
    +--------------+---------------+-----------+
    | employees    | OK            | ok        |
    | departments  | OK            | ok        |
    | dept_manager | OK            | ok        |
    | dept_emp     | OK            | ok        |
    | titles       | OK            | ok        |
    | salaries     | OK            | ok        |
    +--------------+---------------+-----------+


## DISCLAIMER

To the best of my knowledge, this data is fabricated and
it does not correspond to real people. 
Any similarity to existing people is purely coincidental.


## LICENSE
This work is licensed under the 
Creative Commons Attribution-Share Alike 3.0 Unported License. 
To view a copy of this license, visit 
http://creativecommons.org/licenses/by-sa/3.0/ or send a letter to 
Creative Commons, 171 Second Street, Suite 300, San Francisco, 
California, 94105, USA.







# test_db
애플리케이션 및 데이터베이스 서버를 테스트하는 데 사용되는 통합 테스트 제품군이 있는 샘플 데이터베이스

이 리포지토리는 [Launchpad](https://launchpad.net/test-db) 에서 마이그레이션되었습니다.

[MySQL 문서](https://dev.mysql.com/doc/employee/en/index.html) 에서 사용법을 참조하십시오.


## 어디서 왔는지

원래 데이터는 Fusheng Wang과 Carlo Zaniolo가 만들었습니다. 
지멘스 기업 연구소. 데이터는 XML 형식입니다.
http://timecenter.cs.aau.dk/software.htm

주세페 맥시아는 관계 스키마를 만들었고 패트릭 크루는 수출했습니다.
관계형 데이터입니다.

데이터베이스에는 약 300,000명의 직원 기록과 280,000명의 직원 기록이 포함되어 있습니다. 
급여 항목 수출 데이터는 167MB로 크지는 않지만,
시험을 위해 trivial이 아닐 정도로 무겁습니다.

데이터가 생성되었고, 따라서 불일치와 미묘한 문제가 있습니다.
문제. 그것들을 제거하기보다는 내용물을 남겨두기로 했습니다.
손상되지 않은 상태에서 이러한 문제를 데이터 청소 연습으로 사용합니다.

## 전제조건

MySQL 데이터베이스 서버(5.0+)가 필요하고 아래 명령을 실행해야 합니다. 
다음 권한을 가진 사용자:

    선택, 삽입, 업데이트, 삭제, 
    생성, 삭제, 다시 로드, 참조, 
    인덱스, 변경, 데이터베이스 표시, 
    임시 표 만들기, 
    표 잠금, 실행, 보기 작성

## 설치:

1.리포지토리 다운로드
2.디렉터리를 리포지토리로 변경

그럼 달려요.

    mysql< employers.sql


두 개의 큰 분할 테이블과 함께 설치하려면 실행

    mysql< employee_sql.sql


## 설치 테스트

설치 후 다음 중 하나를 실행할 수 있습니다.

    mysql -t < test_ employees_md5.sql
    # 오어
    mysql -t < test_ employees_sha.sql

예:

    mysql -t < test_ employees_md5.sql
    +----------------------+
    | INFO |
    +----------------------+
    | 설치 테스트 |
    +----------------------+
    +--------------+------------------+----------------------------------+
    | table_name | 예상_records | 예상_crc |
    +--------------+------------------+----------------------------------+
    | 직원 | 300024 | 4ec56ab5ba37218d 187cf6ab09ce1aa1 |
    | 부서 | 9 | d1af5e170d2d1591d776d5638d71fc5f |
    | dept_매니저 | 24 | 8720e2f0853ac9096b689c 14664f847e |
    | dept_emp     |           331603 | ccf6fe516f990bdaa49713fc478701b7 |
    | 타이틀 | 443308 | bfa016c472df68e70a03faca1bc0a8 |
    | 급여 | 2844047 | fd220654e95aea1b169624ffe3fca934 |
    +--------------+------------------+----------------------------------+
    +--------------+------------------+----------------------------------+
    | table_name | found_ records | found_ crc |
    +--------------+------------------+----------------------------------+
    | 직원 | 300024 | 4ec56ab5ba37218d 187cf6ab09ce1aa1 |
    | 부서 | 9 | d1af5e170d2d1591d776d5638d71fc5f |
    | dept_매니저 | 24 | 8720e2f0853ac9096b689c 14664f847e |
    | dept_emp     |           331603 | ccf6fe516f990bdaa49713fc478701b7 |
    | 타이틀 | 443308 | bfa016c472df68e70a03faca1bc0a8 |
    | 급여 | 2844047 | fd220654e95aea1b169624ffe3fca934 |
    +--------------+------------------+----------------------------------+
    +--------------+---------------+-----------+
    | table_name | records_match | crc_match |
    +--------------+---------------+-----------+
    | 직원 | OK | OK |
    | 부서 | OK | OK |
    | dep_manager | OK | OK |
    | dep_emp | OK | OK |
    | 제목 | OK | OK |
    | 급여 | OK | OK |
    +--------------+---------------+-----------+


## 부인

제가 아는 한, 이 데이터는 조작되고
그것은 실제 사람들과 일치하지 않습니다. 
기존 사람들과의 유사성은 순전히 우연입니다.


## 면허증.
이 작업은 아래에 따라 라이센스가 부여됩니다. 
Creative Commons Attribution-Share 유사 3.0 Unported License. 
이 라이센스 사본을 보려면 다음을 방문하십시오. 
http://creativecommons.org/licenses/by-sa/3.0/ 또는 다음으로 편지를 보냅니다. 
크리에이티브 커먼즈, 171 세컨드 스트리트, 스위트 300, 샌프란시스코, 
캘리포니아, 94105, 미국.
