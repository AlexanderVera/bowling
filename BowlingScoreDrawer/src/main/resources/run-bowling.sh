echo off
arg1=$1
shift
java -jar target/BowlingScore-1.0.jar $arg1
