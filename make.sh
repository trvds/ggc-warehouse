#!/bin/bash
cd ggc-core && make $1 && cd ..
cd ggc-app && make $1 && cd ..
