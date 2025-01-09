# Ensures the package "pacman" is installed

if (!require("pacman")) {
  install.packages("pacman") }

pacman::p_load(
  tidyverse, 
  rio,         
  here,        
  gtsummary,
  dplyr,
  broom,
  knitr,
  survey,
  janitor,
  epitools,
  questionr,
  stargazer,
  officer,
  flextable,
  htmltools,
  rmarkdown
  )

# Read the CCHS data file (replace 'cchs_data.csv' with your actual file name)

linelist <- import(here("data","Canada CCHS data.csv")) %>% 
  mutate(WTS_M = as.numeric(WTS_M))

# Select the desired columns - add/remove variables as needed for your analysis

cchs_data <- linelist

belonging <- cchs_data %>%
  select(
    SPSDVINT, # Social provisions scale - social integration
    DRMDVLAY, #illicit grug use - past 12 months
    GEO_PRV, # 0015-0016 Province of residence of respondent
    DHH_SEX, # 0027-0027 Sex
    DHHGAGE, # 0036-0037 Age
    EHG2DVR3, # Highest level of education - respondent, 3 levels - (D)
    DHHGMS, # Marital status
    INCDGPER, # 1686-1687 Personal income - all sources - (D))
    SDCDGCGT, # Cultural / racial background - (D)
    GEN_005, # Perceived health
    ALWDVSTR, # Increased short term risks due to drinking
    CCC_195, #Has a mood disorder (depression, bipolar, mania, dysthymia)
    CCC_200, # Has an anxiety disorder (phobia, OCD, panic)
    WTS_M
  ) 

belonging$GEO_PRV <- factor(belonging$GEO_PRV,
                            levels = c(10,11,12,13,24,35,46,47,48,59,60,61,62),
                            labels = c("Newfoundland and Labrador",
                                       "Prince Edward Island",
                                       "Nova Scotia",
                                       "New Brunswick",
                                       "Quebec",
                                       "Ontario",
                                       "Manitoba",
                                       "Saskatchewan",
                                       "Alberta",
                                       "British Columbia",
                                       "Yukon",
                                       "Northwest Territories",
                                       "Nunavut"))

belonging$DHH_SEX <- factor(belonging$DHH_SEX,
                            levels = c(1,2),
                            labels = c("Male", "Female"))

belonging <- belonging %>% mutate(DHHGAGE = case_when(
      DHHGAGE %in% c(1, 2, 3) ~ "12-19",
      DHHGAGE %in% c(4, 5, 6, 7) ~ "20-39",
      DHHGAGE %in% c(8, 9, 10, 11) ~ "40-59",
      DHHGAGE %in% c(12, 13, 14, 15) ~ "60-79",
      TRUE ~ "80+")) %>% mutate(DHHGAGE = factor(DHHGAGE)) 




total = nrow(belonging)
inclusions = nrow(belonging)
exclusions = total - inclusions

belonging <- belonging %>% filter( 
  !SPSDVINT %in% c(96,99)) #n = 94343, t = 15316
belonging$SPSDVINT <- factor(belonging$SPSDVINT,
                                         levels = c(2,3,4,5,6,7,8),
                                         labels = c("Very Low", 
                                                    "Low", 
                                                    "Slightly Low",
                                                    "Moderate",
                                                    "Slightly High",
                                                    "High",
                                                    "Very High"))


inclusions = nrow(belonging)
exclusions = total - inclusions
total = nrow(belonging)

belonging <- belonging %>% filter( 
  !DRMDVLAY %in% c(9)) #n = 47, t = 15269
belonging$DRMDVLAY <- factor(belonging$DRMDVLAY,
                             levels = c(1,2),
                             labels = c("Yes", "No"))

inclusions = nrow(belonging)
exclusions = total - inclusions
total = nrow(belonging)

belonging <- belonging %>% filter( 
  !EHG2DVR3 %in% c(9)) #n = 149, t = 15120
belonging$EHG2DVR3 <- factor(belonging$EHG2DVR3,
                             levels = c(1,2,3),
                             labels = c("Less than seconday school",
                                        "Secondary school",
                                        "Post-secondary diploma or degree"))

inclusions = nrow(belonging)
exclusions = total - inclusions
total = nrow(belonging)

belonging <- belonging %>% filter( 
  !DHHGMS %in% c(9)) #n = 39, t = 15081
belonging$DHHGMS <- factor(belonging$DHHGMS,
                             levels = c(1,2,3,4),
                             labels = c("Married", "Common-law",
                                        "Widowed/Divorced/Separated",
                                        "Single"))

inclusions = nrow(belonging)
exclusions = total - inclusions
total = nrow(belonging)

belonging <- belonging %>% filter( 
  !INCDGPER %in% c(96,99)) #n = 1914, t = 13167
belonging$INCDGPER <- factor(belonging$INCDGPER,
                           levels = c(1,2,3,4,5,6),
                           labels = c("No income", 
                                      "> $20,000", 
                                      "$20,000 to $39,999",
                                      "$40,000 to $59,999",
                                      "$60,000 to $79,999",
                                      "$80,000 or more"))

inclusions = nrow(belonging)
exclusions = total - inclusions
total = nrow(belonging)

belonging <- belonging %>% filter( 
  !SDCDGCGT %in% c(6,9)) #n = 816, t = 12351
belonging$SDCDGCGT <- factor(belonging$SDCDGCGT,
                           levels = c(1,2),
                           labels = c("White", 
                                      "Non-white (Aboriginal or Other Visible Minority)"))

inclusions = nrow(belonging)
exclusions = total - inclusions
total = nrow(belonging)  
  
belonging <- belonging %>% filter( 
  !GEN_005 %in% c(7,8,9)) #n = 22, t = 12329
belonging$GEN_005 <- factor(belonging$GEN_005,
                             levels = c(1,2,3,4,5),
                             labels = c("Excellent",
                                        "Very Good",
                                        "Good",
                                        "Fair",
                                        "Poor"))

inclusions = nrow(belonging)
exclusions = total - inclusions
total = nrow(belonging) 

belonging <- belonging %>% filter( 
  !ALWDVSTR %in% c(6,9)) #n = 1030, t = 11299
belonging$ALWDVSTR <- factor(belonging$ALWDVSTR,
                            levels = c(1,2),
                            labels = c("Yes", "No"))

inclusions = nrow(belonging)
exclusions = total - inclusions
total = nrow(belonging) 

belonging <- belonging %>% filter( 
  !CCC_195 %in% c(7,8,9)) #n = 10, t = 11289
belonging$CCC_195 <- factor(belonging$CCC_195,
                             levels = c(1,2),
                             labels = c("Yes", "No"))

inclusions = nrow(belonging)
exclusions = total - inclusions
total = nrow(belonging) 

belonging <- belonging %>% filter( 
  !CCC_200 %in% c(7,8,9)) #n = 6, t = 11283
belonging$CCC_200 <- factor(belonging$CCC_200,
                            levels = c(1,2),
                            labels = c("Yes", "No"))

inclusions = nrow(belonging)
exclusions = total - inclusions
total = nrow(belonging) 
 
 

# belonging <- belonging %>% filter( #should I use weighted exclusions?
#     !SPSDVINT %in% c(96,99), #n = 94343
#     !DRMDVLAY %in% c(9),#n = 3210
#     !EHG2DVR3 %in% c(9), #n = 1346
#     !DHHGMS %in% c(9), #n = 330
#     !INCDGPER %in% c(96,99), #n = 14779
#     !SDCDGCGT %in% c(6,9), #n = 9366
#     !GEN_005 %in% c(7,8,9), #n = 206
#     !ALWDVSTR %in% c(6,9), #n = 13562
#     !CCC_195 %in% c(7,8,9), #n = 227
#     !CCC_200 %in% c(7,8,9) #n = 223
#   )

inclusions = nrow(belonging)
exclusions = total - inclusions

# Create a survey design

survey_design <- belonging %>% 
  as_survey_design(ids = 1, # no cluster ids
                   weights = WTS_M) # weight variable

# Define the list of variables you want to cross-tabulate ** update this list to include the variables in your analysis
# the variables in this list must be pulled in in the 'select desired variables' step - ie. add them to this list and the list above

variables_to_tabulate <- c("SPSDVINT",
                           "DRMDVLAY",
                           "GEO_PRV",
                           "DHH_SEX",
                           "DHHGAGE",
                           "EHG2DVR3",
                           "DHHGMS",
                           "INCDGPER",
                           "SDCDGCGT",
                           "GEN_005",
                           "ALWDVSTR",
                           "CCC_195",
                           "CCC_200")







# Create an empty list to store the results

results_list <- list()

# Loop through the variables and calculate frequency tables

for (variable in variables_to_tabulate) {

  table_result <- svytable(~belonging[[variable]], design = survey_design)
  
  # Store the result in the results_list
  
  results_list[[variable]] <- table_result
}

# Create an empty data frame to store the results

result_df <- data.frame(Variable = character(0), Category = character(0), Frequency = numeric(0), Percent = numeric(0))

# Loop through the variables and results

for (variable in variables_to_tabulate) {
  
  # Extract the result for the variable
  
  table_result <- results_list[[variable]]
  
  # Convert the result to a data frame
  
  result_df_variable <- as.data.frame(table_result)
  
  # Rename the columns for clarity
  
  colnames(result_df_variable) <- c("Category", "Frequency")
  
  # Calculate the percentage within each category
  
  result_df_variable$Percent <- (result_df_variable$Frequency / sum(result_df_variable$Frequency)) * 100
  
  # Add the variable name as a new column
  
  result_df_variable$Variable = variable
  
  # Bind the variable's results to the overall data frame
  
  result_df <- rbind(result_df, result_df_variable)
}

# Export the result data frame to a CSV file

output_folder <- here("outputs")

output_file <- file.path(output_folder, "Weighted Frequency Table.csv")

write.csv(result_df, output_file, row.names = FALSE)

# csv for unweighted results

# Create an empty list to store the results

results_list <- list()

# Loop through the variables and calculate frequency tables

for (variable in variables_to_tabulate) {
  
  table_result <- table(belonging[[variable]])
  
  # Store the result in the results_list
  
  results_list[[variable]] <- table_result
}

# create an empty data frame

result_df <- data.frame(Variable = character(0), Category = character(0), Frequency = numeric(0), Percent = numeric(0))

# Loop through the variables and results

for (variable in variables_to_tabulate) {
  
  # Extract the result for the variable
  
  table_result <- results_list[[variable]]
  
  # Convert the result to a data frame
  
  result_df_variable <- as.data.frame(table_result)
  
  # Rename the columns for clarity
  
  colnames(result_df_variable) <- c("Category", "Frequency")
  
  # Calculate the percentage within each category
  
  result_df_variable$Percent <- (result_df_variable$Frequency / sum(result_df_variable$Frequency)) * 100
  
  # Add the variable name as a new column
  
  result_df_variable$Variable = variable
  
  # Bind the variable's results to the overall data frame
  
  result_df <- rbind(result_df, result_df_variable)
}

# Export the result data frame to a CSV file

output_folder <- here("outputs")

output_file <- file.path(output_folder, "Non-Weighted Frequency Table.csv")

write.csv(result_df, output_file, row.names = FALSE)

