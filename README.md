How to run it in IDE:
- Install Lombok plugin
- Enable annotation processing

GET: /transaction/simulate

params:
code - Three letters string
amount - positive number
buyDate - yyyy-MM-dd
sellDate - yyyy-MM-dd

Example Request:

http://localhost:8080/transaction/simulate?code=EUR&amount=100&buyDate=2018-11-13&sellDate=2019-02-22