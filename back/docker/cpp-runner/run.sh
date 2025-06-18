#!/bin/bash

echo "run.sh 진입 성공"

echo "G++ 확인:"
g++ --version

echo "C++ 코드 컴파일 중..."
g++ -o main main.cpp 2>&1
if [ $? -ne 0 ]; then
  echo "COMPILE_ERROR"
  exit 1
fi

echo "C++ 코드 실행 중..."
/usr/bin/time -v ./main < input.txt > output.txt 2> time.txt
