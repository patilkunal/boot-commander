docker run -it --rm  --mount 'type=volume,src=testcasedb-data,dst=/data' -p 9001:9001 --name hsqldbserver pocketguru/hsqldb-server:latest
