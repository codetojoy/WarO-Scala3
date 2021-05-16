#!/bin/bash

set -e

rm -rf ./target/scala*/classes
sbt run 

