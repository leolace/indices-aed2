main: ./src/Main.java
	rm -rf ./out && javac ./src/*.java -d out && cd ./out && java Main