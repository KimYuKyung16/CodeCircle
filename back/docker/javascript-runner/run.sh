#!/bin/bash

echo "run.sh 진입 성공"

echo "Node 확인:"
node -v

echo "자바스크립트 코드 실행 중..."
/usr/bin/time -v node main.js < input.txt > output.txt 2> time.txt
