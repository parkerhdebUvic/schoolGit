# Create a sample dataset
data <- data.frame(
  ID = 1:2,
  Name = c("David", "Lucy"),
  Age = c(25,30)
)

#Save the dataset as a CSV file
write.csv(data, "sample_data.csv", row.names = FALSE)

# Check the working directory
getwd()
# List the contents of a directory
list.files()
# Create a new directory
dir.create("my_data")
# Move into the new directory
setwd("./Documents/schoolGit/STAT321/data")

# Save a file in the new directory
write.csv(data, "data_in_new_directory.csv", row.names = FALSE)

# return to previous directory
setwd("..")

#view a csv in R
View(read.csv("data_in_new_directory.csv"))
data2 <- read.csv("data_in_new_directory.csv")
head(data)

# WORKING WITH LOCAL FILES
# install and load the readr library
install.packages("readr")
library("readr")
data <- read_csv("sample_data.csv") #automatically detects headers and data types
spec(data)
head(data) # views the first 6 rows# IMPORTING EXCEL FILES
install.packages("readxl")
library(readxl)
# import an Excel file
excel_data <- read_excel("sample_excel.xlsx", sheet = 2) #just an example
head(excel_data)

#Exporting to Excel Files
install.packages("writexl")
library(writexl)

write_xlsx(data, "processed_data.xlsx")
View(read_excel("processed_data.xlsx"))
#writing to excel with multiple sheet names
write_xlsx(list("Sheet1"=data, "Sheet2"=data2), "multi_sheet.xlsx")
head(read_excel("multi_sheet.xlsx"))

# DATA CLEANING AND PREPROCESSING
# Handling Missing Values
data <- data.frame(
  Name = c("Ellen", "Jason", "Peter", "Paden"),
  Age = c(25, NA, 30, 28),
  Salary = c(50000, 60000, NA, 52000)
)
data
cleaned_data <- na.omit(data)
cleaned_data

#Input missing values by filling with the mean
data$Age[is.na(data$Age)] <- mean(data$Age, na.rm = TRUE)
data
data$Salary[is.na(data$Salary)] <- mean(data$Salary, na.rm = TRUE)
data

#removing duplicates
data <- data.frame(
  Name = c("Ellen", "Jason", "Peter", "Paden"),
  Age = c(25, 30, 28, 25),
  Salary = c(50000, 60000, 52000, 50000)
)
data
unique_data <- data[!duplicated(data), ]
unique_data

#fixing data inconsistencies
#1 inconsistant upper or lower case
data <- data.frame(
  Name = c("Ellen", "Jason", "Peter"),
  City = c("NY", "LA", "ny"),
  Salary = c(50000, 60000, 52000)
)
data

data$City <- toupper(data$City) #convert city names to uppercase
data

#2 Inconsistent Categorical Labels
#install.packages("dplyr")
library(dplyr)
data <- data.frame(Emp = c("self-employed", "Self Employed", "self employed", "teacher", "doctor"))
print(data)
data <- data %>%
  mutate(job = case_when(
    grepl("self[- ]?employed", Emp, ignore.case = TRUE) ~ "Self-Employed",
    TRUE ~ Emp
  ))
print(data)

#3 Variation in Date Formats
data <- data.frame(date = c("01/01/2023", "2023-01-01", "January 1, 2023"))
library(lubridate)
# Standardizing dates
data <- data %>%
  mutate(date = as.Date(date, format = case_when(
    grepl("/", date) ~ "%m/%d/%Y",
    grepl("-", date) ~ "%Y-%m-%d",
    grepl(",", date) ~ "%B %d, %Y",
    TRUE ~ NA_character_
  )))
print(data)

#4 Inconsistent Capitalization

data <- data.frame(city = c("new york", "New York", "NEW YORK", "los angeles"))
print(data)
install.packages("stringr")
library(stringr)
# Standardizing capitalization
data <- data %>%
  mutate(city = str_to_title(city)) # Converts to "New York"
print(data)

#5 Inconsistent Units
data <- data.frame(weight = c("70 kg", "154 lbs", "80 kg", "176 lbs"))
print(data)
# Converting to kilograms
data <- data %>%
  mutate(weight_kg = case_when(
    grepl("kg", weight) ~ as.numeric(sub(" kg", "", weight)),
    grepl("lbs", weight) ~ as.numeric(sub(" lbs", "", weight)) * 0.453592,
    TRUE ~ NA_real_
  ))
print(data)

#6 Inconsistent Values in Numeric Columns
# Example data
data <- data.frame(salary = c("50,000", "75000", "1,00,000"))
print(data)
data <- data %>%
  mutate(salary = as.numeric(gsub(",", "", salary)))
print(data)

# TRANSFORMING DATA
help(mutate)
data <- data.frame(
  Name = c("Ellen", "Jason", "Peter", "Paden", "Carlo", "Matt", "Rosie"),
  Age = c(25, 30, 28, 22, 21, 26, 60), # 60 is an outlier
  Salary = c(50, 60, 52, 65, 55, 62, 100) # in thousand
)
data
boxplot(data$Age)

#Capping outliers
# Calculate IQR bounds for Age
age_q1 <- quantile(data$Age, 0.25)
age_q3 <- quantile(data$Age, 0.75)
age_iqr <- age_q3 - age_q1
age_lower <- age_q1 - 1.5 * age_iqr
age_upper <- age_q3 + 1.5 * age_iqr
# Cap Age
data$Age <- ifelse(data$Age < age_lower, age_lower,
                   ifelse(data$Age > age_upper, age_upper, data$Age))
# Calculate IQR bounds for Salary
salary_q1 <- quantile(data$Salary, 0.25)
salary_q3 <- quantile(data$Salary, 0.75)
salary_iqr <- salary_q3 - salary_q1
salary_lower <- salary_q1 - 1.5 * salary_iqr
salary_upper <- salary_q3 + 1.5 * salary_iqr
# Cap Salary
data$Salary <- ifelse(data$Salary < salary_lower, salary_lower,
                      ifelse(data$Salary > salary_upper, salary_upper, data$Salary))
print(data)
# Calculate IQR bounds for Age
age_q1 <- quantile(data$Age, 0.25)
age_q3 <- quantile(data$Age, 0.75)
age_iqr <- age_q3 - age_q1
age_lower <- age_q1 - 1.5 * age_iqr
age_upper <- age_q3 + 1.5 * age_iqr
# Calculate IQR bounds for Salary
salary_q1 <- quantile(data$Salary, 0.25)
salary_q3 <- quantile(data$Salary, 0.75)
salary_iqr <- salary_q3 - salary_q1
salary_lower <- salary_q1 - 1.5 * salary_iqr
salary_upper <- salary_q3 + 1.5 * salary_iqr
# Identify rows without outliers
removed_outlier_data <- data[
  data$Age >= age_lower & data$Age <= age_upper &
    data$Salary >= salary_lower & data$Salary <= salary_upper,
]
removed_outlier_data

boxplot(data$Age)

#Transforming data with log tansformation to reduce influence of outliers
# Add a transformed Salary column
data$Log_Salary <- log(data$Salary)
data

#2 Data preprocessing
data <- data.frame(
  Age = c(25, 30, 28),
  Salary = c(50000, 60000, 52000)
)
data
#scale the salary column
data$Salary_standard <- scale(data$Salary)
data

#scaling to obtain values between 0 and 1
data$Salary_01 <- (data$Salary - min(data$Salary)) /
  (max(data$Salary) - min(data$Salary))
data

#encoding categorical values
data <- data.frame(
  Name = c("Ellen", "Jason", "Peter"),
  Gender = c("Female", "Male", "Male")
)
data

#one-hot encoding Gender column
data <- cbind(data, model.matrix(~ Gender - 1, data = data))
data

# Feature Engineering
data <- data.frame(
  Name = c("Ellen", "Jason", "Peter"),
  Age = c(25, 30, 28)
)
data
# Create a new feature, AgeGroup
data$AgeGroup <- ifelse(data$Age > 27, "Senior", "Junior")
data

# DATA RESHAPING
data <- data.frame(
  Name = c("Ellen", "Ellen", "Jason", "Jason"),
  Year = c(2020, 2021, 2020, 2021),
  Salary = c(50000, 52000, 60000, 63000)
)
data
#reshape into wide format
data_wide <- tidyr::pivot_wider(data, names_from = Year, values_from = Salary)
data_wide

#INTRODUCTION TO dplyr and tidyr
library(dplyr)
library(tibble)

library(tibble)
data <- tibble(name = c("Ellen", "Jason", "Peter"),
               age = c(25, 35, 30),
               salary = c(50000, 60000, 55000))
print(data)

#1.1
# filter() filters rows based on conditions
?attach
attach(data)
# filter rows based on a condition
filtered_data <- filter(data, age > 30)
filtered_data

#1.2 select()
# selects specific columns
selected_data <- select(data, name, salary)
print(selected_data)

#1.3 mutate()
#creates new columns or modifies existing ones
#ex, adding a new column for yearly bonus (10% of salary)
mutated_data <- mutate(data, bonus = salary * 0.1)
print(mutated_data)

#1.4 summarize()
summary_data <- summarize(data, avg_salary = mean(salary))
print(summary_data)

#1.5 group_by()
#groups data for grouped operations
data <- tibble(name = c("Emp1", "Emp2", "Emp3", "Emp4"),
               department = c("Forestry", "Agriculture", "Forestry", "Agriculture"),
               salary = c(50000, 60000, 55000, 70000))
grouped_data <- data %>%
  group_by(department) %>%
  summarize(avg_salary = mean(salary))
print(grouped_data)

#1.6 arrange()
#sorts rows by a specific column
sorted_data <- arrange(data, desc(salary)) # ascending means only arrange(data)
print(sorted_data)

#2. tidyr functions
library(tidyr)

#2.1 reshaping data with pivot_longer() and pivot_wider()
#start with a tibble
library(tibble)
# Wide-format tibble
wide_data <- tibble(
  Name = c("Robin", "Bob", "David"),
  Math = c(85, 92, 78),
  Science = c(88, 79, 84),
  English = c(90, 85, 82)
)
wide_data

long_data <- wide_data %>%
  pivot_longer(
    cols = Math:English, # Specify columns to pivot
    names_to = "Subject", # New column name for subject names
    values_to = "Score" # New column name for scores
  )
long_data

wide_again <- long_data %>%
  pivot_wider(
    names_from = Subject, # Specify column to create new headers
    values_from = Score # Specify column for values in new headers
  )
wide_again

#2.2 Managing Missing Data with drop_na() and replace_na()
library(tibble)
# Tibble with missing data
data_with_na <- tibble(
  Name = c("Robin", "Bob", "David", "Adrian"),
  Age = c(25, NA, 30, 28),
  Score = c(88, 92, NA, 85)
)
data_with_na
data_cleaned <- data_with_na %>%
  drop_na()
data_cleaned
data_filled <- data_with_na %>%
  replace_na(list(
    Age = 0, # Replace missing Age with 0
    Score = 50 # Replace missing Score with 50
  ))
data_filled

#2.3 Combine and Split Columns
#using unite()
data <- tibble(
  first_name = c("Robin", "Bob", "David", "Adrian"),
  last_name = c("Frost", "Benson", "Hussey", "Henderson"))
#Combine first_name and last_name into a single column.
united_data <- unite(data, "full_name", first_name, last_name, sep = " ")
print(united_data)

# Using separate()
# splits a single column into multiple columns
separated_data <- separate(united_data, full_name, into = c("first_name", "last_name"), sep = " ")

#SECTION 1.5: TIBBLES: A MODERN DATA FRAME IN R
# packages needed
install.packages("tibble")
install.packages("tidyverse")
library(tibble)
# Create a tibble
my_tibble <- tibble(
  Name = c("Russel", "Syn", "Adrian"),
  Age = c(25, 30, 35),
  Score = c(88.5, 92.0, 79.5)
)
print(my_tibble)

# Converting a Data Frame to a Tibble
# Create a traditional data frame
my_dataframe <- data.frame(
  Name = c("Russel", "Syn", "Adrian"),
  Age = c(25, 30, 35),
  Score = c(88.5, 92.0, 79.5)
)
# Convert to tibble
my_tibble <- as_tibble(my_dataframe)
print(my_tibble)

#basic operations: viewing, subsetting, and exploring Tibbles
# View the first few rows
print(my_tibble, width = Inf) # Show all columns
# Select specific columns
library(dplyr)
selected_tibble <- select(my_tibble, Name, Score)
selected_tibble

#Selecting last set of columns of a dataset
my_tibble %>%
  select(tail(names(.), 2))

#filtering based ona condition
filtered_tibble <- filter(my_tibble, Age > 28)
filtered_tibble

#Exploring Tibbles
glimpse(my_tibble)
summary(my_tibble)

#Create a messy Data Frame, Convert, and Preview
# Step 1: create a messy data frame
messy_df <- data.frame(
  Name = c("Hershey", "Tin", "Hershey", NA),
  Age = c("25", "30", "25", "40"),
  Score = c(88.5, NA, 88.5, 70.0)
)
print(messy_df)

# Step 2: Convert the DF to a Tibble
messy_tibble <- as_tibble(messy_df)
print(messy_tibble)

#Step 3: Explore and Clean
clean_tibble <- messy_tibble %>%
  distinct() %>%
  mutate(Age = as.numeric(Age)) %>%
  tidyr::replace_na(list(Score = 0)) %>%
  tidyr::replace_na(list(Name = "unknown"))
#No need to call library(tidyr) if you are using "::"
print(clean_tibble)

#SECTION 1.6: PIPING
library(tibble)
# Sample tibble
data <- tibble(Name = c("Ellen", "Jason", "Peter", "Paden"),
               Age = c(25, 30, 35, 40),
               Score = c(88.5, 92.0, 79.5, 85.0)
)
attach(data)
library(tidyverse)
library(magrittr)
library(dplyr)

#compare the traditional way...
filtered <- filter(data, Age > 30)
filtered
selected <- select(filtered, Name, Score)
selected

#but with piping, you can do this instead...
data %>%
  filter(Age > 30) %>%
  select(Name, Score)

#Chaining Multiple Operations with %>%
data %>%
  filter(Age > 30) %>%
  summarize(Average_Score = mean(Score))
summarize(
  filter(data, Age > 30),
  Average_Score = mean(Score)
)
#vs...
data %>%
  filter(Age > 30) %>%
  summarize(Average_Score = mean(Score))

#Joining two datasets
data1 <- tibble(id = c(1, 2, 3), name = c("Ellen", "Jason", "Peter"))
data2 <- tibble(id = c(1, 3), salary = c(50000, 55000))
joined_data <- data1 %>%
  inner_join(data2, by = "id")
print(joined_data)

###################################################
###Using a Tibble to Demonstrate Basic Chaining ###
###################################################
student_data <- tibble(
  Name = c("Bran", "Shay", "Laura", "Alf", "Dan"),
  Age = c(21, 22, 22, 21, 24),
  Score = c(75, 82, 88, 92, 80)
)

# Task 1: Filter Rows
student_data %>%
  filter(Age > 21) %>%
  select(Name, Score)

#Task 2: Add a new Column And Summarize
student_data %>%
  mutate(Passed = Score > 80) %>%
  summarize(Pass_Rate = mean(Passed))

#Task 3: Group Data and Summarize
student_data %>%
  group_by(Age) %>%
  summarize(Average_Score = mean(Score))
