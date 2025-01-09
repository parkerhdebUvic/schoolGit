# Welcome to R! This script introduces basic commands and concepts.
# Any text after "#" is a comment and will not be executed.

# R as a Calculator
# You can use R like a calculator for basic arithmetic and mathematical operations.

# Basic Arithmetic
3 + 5   # Addition
10 - 4  # Subtraction
6 * 7   # Multiplication
9 / 3   # Division
2^3     # Exponentiation
10 %% 3 # Modulus (remainder of 10 divided by 3)
10 %/% 3 # Integer division (quotient)

# Order of Operations (PEMDAS)
# R follows the precedence rules: Parentheses, Exponents, Multiplication/Division, Addition/Subtraction.
2 + 3 * 5  # Multiplication is done before addition
(2 + 3) * 5  # Parentheses change the order

# Assigning Values to Variables
# The assignment operator "<-" is used to assign values to variables.
x <- 5    # Assign 5 to x
y <- 10   # Assign 10 to y
x + y     # Add x and y
z <- x * y  # Assign the product of x and y to z

print(z)

# Data Types in R
# R has several fundamental data types, such as:
# - Numeric (e.g., 3.14, 42)
# - Integer (e.g., 5L, where "L" indicates an integer)
# - Character (e.g., "Hello")
# - Logical (e.g., TRUE, FALSE)

class(x)       # Check the data type of x
x <- "Hello"   # Change x to a character
class(x)       # Check the new data type of x

# Functions and Built-in Math
# Functions are pre-defined commands in R that perform specific tasks.
sqrt(16)      # Square root
log(100, 10)  # Logarithm base 10
exp(2)        # Exponential function
abs(-15)      # Absolute value
round(3.14159, 2)  # Round to 2 decimal places
ceiling(3.1)      # Round up
floor(3.9)        # Round down

# Logical Operators
# Logical operators allow comparisons and return TRUE or FALSE.
a <- 7
b <- 10
a > b    # Is a greater than b?
a == b   # Is a equal to b?
a != b   # Is a not equal to b?
a < b && a > 5  # Logical AND
a < b || a < 5  # Logical OR

# Working with Vectors
# A vector is a sequence of data elements of the same type.
numbers <- c(5, 4, 8, 9, 1)  # Create a numeric vector
letters <- c("a", "b", "c", "abc")  # Create a character vector
logical_vec <- c(TRUE, FALSE, TRUE)  # Create a logical vector

# Perform operations on vectors
numbers + 2   # Add 2 to each element
numbers * 3   # Multiply each element by 3
sum(numbers)  # Sum of elements
mean(numbers) # Average of elements
length(numbers) # Length of the vector

# Indexing and Slicing
# Access elements of a vector using square brackets.
numbers[1]    # First element
numbers[2:4]  # Second to fourth elements
numbers[-1]   # All elements except the first
numbers[numbers > 2]  # Elements greater than 2

head(numbers, 3) # Provides first 3 elements
tail(numbers, 3) # Provides last 3 elements

# Sequence and Repetition
1:10              # Sequence of numbers from 1 to 10
seq(1, 10, 2)     # Sequence from 1 to 10, increment by 2
rep(3, 5)         # Repeat 3 five times
rep(c(1, 2), each = 3) # Repeat each element 3 times
rep(c(1, 2), 3)   # Repeat the set (1,2) 3 times

# Creating and Accessing Lists
# A list is a collection of elements that can have different types.
my_list <- list(name = "Andrew", age = 25, scores = c(85, 90, 88))
my_list
my_list$name      # Access the "name" element
my_list$scores[2] # Access the second score

# Data Frames
# A data frame is a table-like structure where each column can have a different type.
names <- c("Student1", "Student2", "Student3")
scores <- c(85, 90, 88)
data <- data.frame(names, scores)  # Create a data frame
print(data)# Display the data frame
View(data)
data$scores         # Access the "scores" column
data[2, "scores"]   # Access Student2's score
data[1:2, ]         # Access the first two rows
data[, "names"]     # Access the "names" column

# Plotting Basics
# R has built-in functions for creating plots.
x <- c(1, 2, 3, 4, 5)
y <- c(2, 4, 6, 8, 10)
plot(x, y, main = "Simple Plot", xlab = "X-axis", ylab = "Y-axis", col = "blue", pch = 16)

# Advanced Plotting
# Adding lines, points, and legends to plots.
lines(x, y, col = "red", lty = 3)   # Add a red line
points(x, y, col = "green", pch = 19) # Add green points
legend("topleft", legend = c("Line", "Points"), col = c("red", "green"), pch = c(NA, 19), lty = c(1, NA))

# Install and Load Packages
# Packages extend R's functionality. Install a package using install.packages().
install.packages("ggplot2")  # Install ggplot2 for advanced plotting
library(ggplot2)             # Load ggplot2 for use

# Getting Help
# Use ? or help() to access documentation.
?mean          # Documentation for "mean"
help.start()   # Launch the R help system

#------------------Quick Practice----------------------#

# Install Packages
install.packages("readr") # Example ("package name")

# Call a package for use
library(readr)

# Before running these set of codes, guess which type of inputs?
guess_parser("2025-01-08")
guess_parser("16:10")
guess_parser(c("TRUE", "FALSE"))
guess_parser(c("a", "b", "c"))
guess_parser(c("12,352,561"))
