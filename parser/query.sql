---(1) Write MySQL query to find IPs that mode more than a certain number of requests for a given time period.

	SELECT IP, COUNT(IP) as REQUISITIONS FROM WH_LOG WHERE (REQUEST_DATE BETWEEN ? AND ?) GROUP BY IP HAVING REQUISITIONS > ?

--(2) Write MySQL query to find requests made by a given IP.

	SELECT * FROM WH_LOG WHERE IP = ?