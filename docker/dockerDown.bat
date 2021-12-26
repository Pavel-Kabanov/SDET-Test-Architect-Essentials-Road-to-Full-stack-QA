@REM set FileName=Build-%date%-%time:~0,2%-%time:~3,2%-%time:~6,2%
@REM docker-compose -f docker\docker-compose.yml up >> docker\log\%FileName%.log
docker-compose -f docker\docker-compose.yml down