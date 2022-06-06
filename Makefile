start-tracer:
	docker-compose up -d

stop-tracer:
	docker-compose stop

start-bet-service:
	cd bet-service && ./gradlew run

start-user-service:
	cd user-service && ./gradlew run