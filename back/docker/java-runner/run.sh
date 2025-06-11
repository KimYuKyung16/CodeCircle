#!/bin/bash

echo "run.sh 진입 성공"

echo "JAVA 확인:"
java -version
javac -version

# 1. 자바 코드 컴파일
echo "자바 코드 컴파일 중..."
javac Main.java 2>&1
if [ $? -ne 0 ]; then
  echo "COMPILE_ERROR"
  exit 1
fi

echo "자바 코드 실행 중..."
# 2. 실행 + 시간/메모리 측정
/usr/bin/time -v java -cp . Main < input.txt > output.txt 2> time.txt
