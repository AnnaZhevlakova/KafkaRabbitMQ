curl --location 'http://localhost:8080/api/credit' \
--header 'Content-Type: application/json' \
--data '{
"amount": 10000.00,
"term": 24,
"userIncome": 5000.00,
"currentCreditLoad": 1000.00,
"creditRating": 750
}'


curl --location 'http://localhost:8080/api/credit/3'