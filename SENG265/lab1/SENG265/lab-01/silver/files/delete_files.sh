#!/bin/bash
# removes silverN.txt files with even numbers

for (( int=0 ; int<10 ; int++ ))
do
	if [ $(expr $int % 2) -eq 0 ]
	then
		rm silver$int.txt
		echo removed silver$int.txt
	fi
done
