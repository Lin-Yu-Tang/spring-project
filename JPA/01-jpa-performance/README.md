# 比較JPA, JDBC, CORSUR查詢語法的效能


## Background
* DB Memory: 4096MB 

![alt text](https://drive.google.com/uc?id=1alrjg7P-kUrkbspXQqgyyrinLpwtjqrx)

* Data Count: 31,263,601

![image](https://hackmd.io/_uploads/SytBRn3VR.png)

* Table Schema

![image](https://hackmd.io/_uploads/Sy8cR3nV0.png)

## Test Case
* Number of Threads: 1000
* Ramp-up period: 60 sec.
* Duration: 60 sec.
* Query strategy:
每次查詢的資料筆數為10筆, 並隨著計數器的增加而增加查詢的頁次
(第一次查詢為第一頁, 第二次查詢第二頁, 第三次查詢第三頁...)

## Statistics

* JPA
![image](https://hackmd.io/_uploads/HkSczp3EC.png)

* JDBC
![image](https://hackmd.io/_uploads/rJX2ManVR.png)

* CURSOR
![image](https://hackmd.io/_uploads/SJ2afThVC.png)


## Over Time

### Active Threads Over Time
* JPA
![image](https://hackmd.io/_uploads/Skf6Q634C.png)

* JDBC
![image](https://hackmd.io/_uploads/Hy9-V63NA.png)

* CURSOR
![image](https://hackmd.io/_uploads/rJum4a34R.png)


### Response Time Percentiles Over Time (successful responses)

* JPA
![image](https://hackmd.io/_uploads/H122NphNA.png)

* JDBC
![image](https://hackmd.io/_uploads/BJJHr6240.png)

* CURSOR
![image](https://hackmd.io/_uploads/HkP8r63V0.png)


### Latencies Over Time
* JPA
![image](https://hackmd.io/_uploads/SypxD6hNC.png)

* JDBC
![image](https://hackmd.io/_uploads/ryRbPanEC.png)

* CURSOR
![image](https://hackmd.io/_uploads/HkRzvp34R.png)
