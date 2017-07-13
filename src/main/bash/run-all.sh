#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $DIR

mkdir -p ../logs

java -jar ../lib/simd.jar -o ../logs/jmh-output.log