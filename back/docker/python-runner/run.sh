#!/bin/bash

echo "run.sh 진입 성공"

echo "Python 확인:"
python --version

echo "파이썬 코드 실행 중..."
/usr/bin/time -v python main.py < input.txt > output.txt 2> time.txt
