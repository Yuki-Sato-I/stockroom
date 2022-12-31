login-front:
	docker exec -it stockroom-front-1 /bin/sh
login-api:
	docker exec -it stockroom-api-1 /bin/sh
login-postgres:
	docker exec -it postgres /bin/sh