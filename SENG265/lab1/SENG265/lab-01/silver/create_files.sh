#!/bin/bash

mkdir ./files
cd ./files

for (( counter=0 ; counter<10 ; counter++ ))
do
	touch ./silver$counter.txt
	echo silver$counter.txt created
	# to recover the value of counter, use $counter
done

