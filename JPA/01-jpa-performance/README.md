# 比較JPA, JDBC, CORSUR查詢語法的效能


## Background
* DB Memory: 4096MB 

![alt text](https://drive.google.com/uc?id=1alrjg7P-kUrkbspXQqgyyrinLpwtjqrx)

* Data Count: 31,263,601

![image](https://drive.google.com/uc?id=1P3v0rDCJYOA732L2HUhgxJ1FmQoJz-hP)

* Table Schema

![image](https://drive.google.com/uc?id=1xAyBogZA3V8W6H6Hn6VvZsZKk_B8gFWU)

## Test Case
* Number of Threads: 1000
* Ramp-up period: 60 sec.
* Duration: 60 sec.
* Query strategy:
每次查詢的資料筆數為10筆, 並隨著計數器的增加而增加查詢的頁次
(第一次查詢為第一頁, 第二次查詢第二頁, 第三次查詢第三頁...)

## Statistics

* JPA
![image](https://drive.google.com/uc?id=1g-45JEeGRuXM80GqFBI_xdhqyPa4OsU0)

* JDBC
![image](https://drive.google.com/uc?id=1gB_RPwDxYDjHbfUNdPUho5ocNTyER6z0)

* CURSOR
![image](https://drive.google.com/uc?id=1I1vAZE_73GKNlBS2yM2dYEoUs87MJiP0)


## Over Time

### Active Threads Over Time
* JPA
![image](https://drive.google.com/uc?id=1nvDxOAkY2Q6XahzUMqlIlFnHsHza79ko)

* JDBC
![image](https://drive.google.com/uc?id=1Ohp9nkVVUB2YUxwj90SpFmSeCidpVaUR)

* CURSOR
![image](https://drive.google.com/uc?id=1sa0bhDvUh7dMICxQWk2sf_vYtsd9UxWD)


### Response Time Percentiles Over Time (successful responses)

* JPA
![image](https://drive.google.com/uc?id=1cIMrIaXY8d3aAz7jFwML9JDZB1OQer5O)

* JDBC
![image](https://drive.google.com/uc?id=1wWJXz6saOakrxSJ4vlZEVTJNyM452pvd)

* CURSOR
![image](https://drive.google.com/uc?id=1UFuq30REUNFQ3iEe4E5YttNOzqpSpoRo)


### Latencies Over Time
* JPA
![image](https://drive.google.com/uc?id=1PFB76ukVaaR-JGhLXpamqSArkm0RqcG0)

* JDBC
![image](https://drive.google.com/uc?id=1KUIkJajabPpLJGbDBMM6DJaMcWd--16c)

* CURSOR
![image](https://drive.google.com/uc?id=1TpgEo9nK_qdlD1Di7nAVcmWBsW2hXgBm)
