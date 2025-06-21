main: ./src/Main.java
	rm -rf ./out && javac ./src/*.java -d out && cp ./mangas.txt ./out && cd ./out && java Main