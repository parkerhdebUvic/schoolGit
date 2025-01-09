10 %% 3 #modulus
10 %/% 3 #removes the decimal
2 ^ 3 #exponential
(2+3) * 5 #addition and multiplication

# a[i] + b *c #indexing for loops

x <- 5
y <- 10
x + y
z <- x + y
print(z)

# numeric is a floating point number whereas integer is always a whole number; 
# integer division is different than numeric division

class(x)

sqrt(16) #square root, always positive
log(100, 10) # log(number, base)
log(100) # natural log, base "e"; ln(100)
# exp means "e^a"
exp(4)
abs(-15) #absolute value
round(3.1456, 2) #rounds to the specified decimal places. 
# Always round to 2 or 3 in this class
ceiling(3.14) # always rounds up
floor(3.14) # always rounds down

a <- 7
b <- 10
a == b #logical operator
a != b #logical operator
a < b
a > b
a < b && a > 5 # logical AND
a > b || a > 5 # logical OR

v = c(1,2,3,4,5) #vector creation
v
v = c(1:10) # abreviated range)
v

numbers <- c(1:5)
numbers + 2 # vector addition
numbers * 3 # vector multiplication
sum(numbers)
mean(numbers)
length(numbers)

numbers[1] # first element
numbers[2:4] # numbers[2,3,4]
numbers[-1] # all elements except the first
numbers[numbers < 2] # all elements less than 2

seq(1,10,2) # seq(a,b,c) gives a sequence from a to b incremented by c
rep(3,5) # rep(a,b) repeat a number of times equal to b
rep(c(1,2),3) #repeats a vector pattern b number of times

my_list <- list(name = "Andrew", age=25, scores = c(85,90,88))
my_list
my_list$name
my_list$scores[2]

# dataframs! We will always use dataframes in the course
# what is the difference between a list and a dataframe?
# a list is a infinite array, a dataframe is a table
names <- c("Student1", "Student2", "Student3")
scores <- c(85, 90, 88)
data <- data.frame(names, scores)
View(data) #same as clicking on the variable in the Environment tab
data$scores
data[2, "scores"]
data[1:2, ]
data[,"names"]

# Plotting Basics
x <- c(1:5)
y <- seq(2,10,2)
plot(x,y, main="Simple Plot", xlab="X-Axis", ylab="Y-Axis", col="blue", pch=16)
lines(x,y,col="red",lty=3)
legend("topleft", legend=c("line","Points"), col=c("red", "green"), pch=c(NA,19), lty=c(1,NA)) #UNFINISHED, check uploaded

install.packages("ggplot2")
library(ggplot2)

?mean
help.start()

install.packages("readr")
library(readr)

guess_parser("2025-01-08")
guess_parser("16:10")
guess_parser(c("TRUE", "FALSE"))
guess_parser(c("a", "b", "c"))
guess_parser(c("12,352,561"))
