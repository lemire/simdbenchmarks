#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $DIR

mkdir -p ../logs

java -jar -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly \
-XX:PrintAssemblyOptions=hsdis-print-bytes ../lib/simd.jar -o ../logs/asm-output.log