# moneychnager
Money changer application


First change spring.datasource.url=jdbc:h2:file:D:/moneyExchanger/moneyexchanger/data/money-manager-app file path to relevent path in property file
then run the application for first time. just up the server. then h2 database will be created with the tables.
then execute the following queries in h2 console

insert into CURRENCY_EXCHANGE_RATES vlaues ( 1,1.3392,1.3574);
insert into CURRENCY_EXCHANGE_RATES values ( 2,0.1738,0.1698);
insert into CURRENCY_EXCHANGE_RATES  values ( 3,0,0)


insert into CURRENCY  values ('sgd','1000,500,100','Singapore dollars',3);
insert into CURRENCY  values ('usd','1000,500,100','USD dollars',1);
insert into CURRENCY  values ('hkd','1000,500,100','HK dollars',2);

insert into  BRANCH values (1,1200,1,'ABC', 'sgd');
insert into  BRANCH values (2,1200,1,'ABC', 'usd');
insert into  BRANCH values (3,1200,1,'ABC', 'hkd');

then run the server again.

API URLs 
to get exchangerate : http://localhost:8080/get-exchange-rate
to get inquires : http://localhost:8080/get-inquiry-details/SELL/branch/{branchId}/currency/{currancyID}/amount/{amount}
  example : http://localhost:8080/get-inquiry-details/SELL/branch/1/currency/hkd/amount/200
to savr transaction : http://localhost:8080/saveTransaction
  body : {
	"currencyId":"hkd",
	"receivedAmout":12.30,
	"exchangedAmount":23.0,
	"branchId":1,
	"transactionType":"BUY"
}


