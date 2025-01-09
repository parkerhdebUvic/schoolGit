# Script written by: V00837207
# Date: December 15th, 2023
# Purpose: 
    #To clean and analyze CCHS data. 
    #This code can be modified for all variables in the 2015-16 CCHS dataset.


# Load packages -----------------------------------------------------------

# Ensures the package "pacman" is installed

if (!require("pacman")) {
  install.packages("pacman") }

pacman::p_load(
  tidyverse,  # data management and visualization
  rio,        # importing data  
  here,        # relative file pathways 
  gtsummary,
  dplyr, #for data manipulation
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
  rmarkdown,
  devtools,
  psych,
  sjPlot,
  sjmisc,
  sjlabelled,
  survival,
  boot, #for bootstrap
  Hmisc, #for data cleaning
  ggfittext,
  ggplot2,
  gridExtra,
  apyramid,
  patchwork,
  srvyr,
  demography,
  skimr,
  survey,
  kableExtra,
  gt
)

# Import data -------------------------------------------------------------

# Read the BC CCHS data file (replace 'BC CCHS data.csv' with your actual file name)

linelist <- import(here("data","Canada CCHS data.csv")) %>% 
  mutate(WTS_M = as.numeric(WTS_M))

cchs_data <- linelist

# Select desired variables analysis (update with your selected variables of choice) ---------------------------------------
belonging <- cchs_data %>%
  select(
    #ADJUSTMENTS
    GEO_PRV, # 0015-0016 Province of residence of respondent
    DHH_SEX, # 0027-0027 Sex
    DHHGAGE, # 0036-0037 Age
    EHG2DVR3, # Highest level of education - respondent, 3 levels - (D)
    INCDGHH, # Total household income - all sources - (D)
    SDCDGCGT, # Cultural or Racial Background - (D)
    
    #INDEPENDANT VARIABLES
    SPSDVINT, # Concept: Social provisions scale - social integration - (D)
    
    #DEPENDANT VARIABLES
    DRMDVLAY, #illicit grug use - past 12 months
    
    #WEIGHTS
    WTS_M
  ) 

#ADJUSTMENTS: factor province, sex, age, education, income, racial background
#GEO_PRV - Province
belonging <- belonging %>% mutate(
  `Province` = GEO_PRV,
  `Gender` = DHH_SEX,
  `Age Group` = DHHGAGE,
  `Highest Level Of Education` = EHG2DVR3,
  `Total Household Income` = INCDGHH,
  `Cultural or Racial Background` = SDCDGCGT,
  `Social Integration` = SPSDVINT,
  `Illicit Drug Use - past 12 months` = DRMDVLAY
)

total = nrow(belonging) #n = 109,659

belonging$`Province` <- factor(belonging$`Province`,
                             levels = c(10, 11, 12, 13, 24, 35, 46, 47, 48, 59, 60, 61, 62),
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
belonging$`Province` <- factor(belonging$`Province`,
                                         levels = c("Alberta",
                                                    "British Columbia",
                                                    "Manitoba",
                                                    "New Brunswick",
                                                    "Newfoundland and Labrador",
                                                    "Northwest Territories",
                                                    "Nova Scotia",
                                                    "Nunavut",
                                                    "Ontario",
                                                    "Prince Edward Island",
                                                    "Quebec",
                                                    "Saskatchewan",
                                                    "Yukon")
)

inclusions = nrow(belonging) #Total (n = 109659)
exclusions = total - inclusions #n = 0
total = nrow(belonging)

#DHH_SEX - Sex
belonging$`Gender` <- factor(belonging$`Gender`,
                            levels = c(2,1),
                            labels = c("Female","Male"))

inclusions = nrow(belonging) #n = 109659
exclusions = total - inclusions #n = 0
total = nrow(belonging)

#DHHGAGE - Age
belonging <- belonging %>% mutate(`Age Group` = case_when(
  `Age Group` %in% c(1, 2, 3) ~ "12-19",
  `Age Group` %in% c(4, 5, 6, 7) ~ "20-39",
  `Age Group` %in% c(8, 9, 10, 11) ~ "40-59",
  `Age Group` %in% c(12, 13, 14, 15) ~ "60-79",
  TRUE ~ "80+")) %>% mutate(`Age Group` = factor(`Age Group`)) 

inclusions = nrow(belonging) #n = 109659
exclusions = total - inclusions #n = 0
total = nrow(belonging)

#EHG2DVR3 - Highest level of education
belonging <- belonging %>% filter( 
  !`Highest Level Of Education` %in% c(9)) #n = , t = 
belonging$`Highest Level Of Education` <- factor(belonging$`Highest Level Of Education`,
                             levels = c(1,2,3),
                             labels = c("Less than seconday school",
                                        "Secondary school",
                                        "Post-secondary diploma or degree"))
inclusions = nrow(belonging) #n = 108313
exclusions = total - inclusions #Education (n = 1346)
total = nrow(belonging)

#INCDGHH - Total houshold income
belonging <- belonging %>% filter( 
  !`Total Household Income` %in% c(9)) #n = , t = 
belonging$`Total Household Income` <- factor(belonging$`Total Household Income`,
                            levels = c(1,2,3,4,5),
                            labels = c("No income or less than $20,000", 
                                       "$20,000 to $39,999",
                                       "$40,000 to $59,999",
                                       "$60,000 to $79,999",
                                       "$80,000 or more"))

inclusions = nrow(belonging) #n = 108141
exclusions = total - inclusions #Total Household Income (n = 172)
total = nrow(belonging)

#SDCDGCGT - Cultural or Racial Background
belonging <- belonging %>% filter( 
  !`Cultural or Racial Background` %in% c(6,9)) #n = , t = 
belonging$`Cultural or Racial Background` <- factor(belonging$`Cultural or Racial Background`,
                             levels = c(1,2),
                             labels = c("White", 
                                        "Non-white (Aboriginal or Other Visible Minority)"))



inclusions = nrow(belonging) #n = 98985
exclusions = total - inclusions #Cultural or Racial Background (n = 9156)
total = nrow(belonging)

#INDEPENDANT VARIABLES: social provision scale
#SPSDVINT - Social Provision - Social Inclusion
belonging <- belonging %>% filter(
  !`Social Integration` %in% c(96,99)) #n = , t =
belonging <- belonging %>% mutate(`Social Integration` = case_when(
  `Social Integration` %in% c(8) ~ "Very High",
  `Social Integration` %in% c(7) ~ "High",
  `Social Integration` %in% c(6) ~ "Slightly High",
  `Social Integration` %in% c(5) ~ "Medium",
  `Social Integration` %in% c(4) ~ "Slightly Low",
  `Social Integration` %in% c(3) ~ "Low",
  `Social Integration` %in% c(2) ~ "Very Low"
)) #%>% mutate(`Social Integration` = factor(`Social Integration`))
belonging$`Social Integration` <- factor(belonging$`Social Integration`,
                                         levels = c("Very High", "High", "Slightly High", "Medium", "Slightly Low", "Low", "Very Low"))

inclusions = nrow(belonging) #n = 14107
exclusions = total - inclusions #Social Integration (n = 84878)
total = nrow(belonging)

#DEPENDANT VARIABLES: illicit drugs1
#DRMDVLAY - Any Illicit Drug use - 12 months
belonging <- belonging %>% filter( 
  !`Illicit Drug Use - past 12 months` %in% c(9)) #n = 47, t = 15269
belonging$`Illicit Drug Use - past 12 months` <- factor(belonging$`Illicit Drug Use - past 12 months`,
                             levels = c(1,2),
                             labels = c("Yes", "No"))

inclusions = nrow(belonging) #n = 14067
exclusions = total - inclusions #Illicit Drugs (n = 40)
total = nrow(belonging)

# Recode DRMDVLAY variable from 1 and 2 to 0 and 1 (dependent variable)
# NOTE: if i left the code as is, it would work, but i would be running my regression looking at the ABSENCE of an DRMDVLAY disorder
# currently 1 is yes and 2 is no (see data dictionary); the code below is changing 2 to 0 and 'everything else' to 1
# this is because i want to detect the presence of an DRMDVLAY disorder
# logistic regression assumes '1' means the presence and '0' means the absence

belonging$`Illicit Drug Use - past 12 months` <- ifelse(belonging$`Illicit Drug Use - past 12 months` == "Yes", 1, 0)

# Convert DRMDVLAY variable to a factor

belonging$`Illicit Drug Use - past 12 months` <- factor(belonging$`Illicit Drug Use - past 12 months`,
                                 levels = c(0,1),
                                 labels = c("No", "Yes"))
belonging$Province <- droplevels(belonging$Province)

# Create survey design ----------------------------------------------------

survey_design <- belonging %>% # change 'belonging' to the name of the dataframe you are using
  as_survey_design(ids = 1, # no cluster ids (do not change)
                   weights = WTS_M) # weight variable (do not change)


# Unadjusted Odds Ratio ---------------------------------------------------

# Unadjusted ORs

log_model_belonging <- svyglm(`Illicit Drug Use - past 12 months` ~ `Social Integration`, # dependent variable ~ independent variable
                                   data = belonging, # data frame
                                   family = binomial(link="logit"), # logistic regression
                                   design = survey_design) # using 'survey_design' we created

# create table summary of model

unadjusted_table <- log_model_belonging  %>% # replace 'log_model_belonging' with the name of your object in the previous line of code
  tbl_regression(exponentiate = TRUE) %>% 
  bold_labels() %>% 
  bold_p(t = .1) 

# display the table in the 'Viewer' pane

unadjusted_table

# Export Unadjusted Odds Ratio Table --------------------------------------

# Extract table data

table_data <- as.data.frame(unadjusted_table)

# Create a flextable

flex_table <- flextable(table_data)

# Save the table to a Word document in the 'outputs' folder using the 'here' function

doc <- read_docx() %>%
  body_add_flextable(value = flex_table) %>%
  print(target = here("outputs", "Illicit Drug Use - past 12 months_unadjusted_OR.docx"))

# Convert tbl_regression to gt table and save as HTML

gt_table <- as_gt(unadjusted_table)
file_path <- here("outputs", "Illicit Drug Use - past 12 months_unadjusted_OR.html")
gtsave(gt_table, file = file_path)

# Adjusted Odds Ratio -----------------------------------------------------

# adjusted odds ratio for age and sex

log_model_belonging <- svyglm(`Illicit Drug Use - past 12 months` ~ `Social Integration` + `Province` + `Gender` + `Age Group` + `Highest Level Of Education` + `Total Household Income` + `Cultural or Racial Background`,
                                   data = belonging, 
                                   family = binomial(link="logit"), 
                                   design = survey_design)

# create table summary of model 

adjusted_table <- log_model_belonging  %>% 
  tbl_regression(exponentiate = TRUE) %>% 
  bold_labels() %>% 
  bold_p(t = .1) 

# display the table in the 'Viewer' pane

adjusted_table


# Export Adjusted Odds Ratio Table ----------------------------------------

# Extract table data

table_data <- as.data.frame(adjusted_table)

# Create a flextable

flex_table <- flextable(table_data)

# Save the table to a Word document in the 'outputs' folder using the 'here' function

doc <- read_docx() %>%
  body_add_flextable(value = flex_table) %>%
  print(target = here("outputs", "Illicit Drug Use - past 12 months_adjusted_OR.docx"))

# Convert tbl_regression to gt table and save as HTML

gt_table <- as_gt(adjusted_table)
file_path <- here("outputs", "Illicit Drug Use - past 12 months_adjusted_OR.html")
gtsave(gt_table, file = file_path)

# Weighted Prevalence Estimate Tables --------------------------------------------------------------------

# List of variables to analyze (adjust this based on your dataset)



variables_to_analyze <- c("`Province`",
                          "`Gender`",
                          "`Age Group`",
                          "`Highest Level Of Education`",
                          "`Total Household Income`",
                          "`Cultural or Racial Background`",
                          "`Social Integration`",
                          "`Illicit Drug Use - past 12 months`") # update this with your variables



# Create an empty list to store tables for each variable

result_list <- list()

# Loop through each variable

for (variable in variables_to_analyze) {
  
  # Properly handle the variable names with backticks
  #Debug added by GPT for backticks
  formula <- as.formula(paste0("~", variable))
  
  # Calculate weighted prevalence using survey package functions
  
  weighted_prevalence_est <- svymean(as.formula(paste0("~", variable)), design = survey_design)
  
  # Calculate the 95% confidence interval for the weighted prevalence estimate
  
  weighted_prevalence_ci <- confint(weighted_prevalence_est)
  
  # Extract the weighted prevalence estimate and convert to percentage with rounding
  
  weighted_prevalence <- round(as.numeric(weighted_prevalence_est) * 100, 1)
  
  # Extract lower and upper bounds of the 95% confidence interval, convert to percentage with rounding
  
  lower_ci <- round(as.numeric(weighted_prevalence_ci[, 1]) * 100, 1)
  upper_ci <- round(as.numeric(weighted_prevalence_ci[, 2]) * 100, 1)
  
  # Calculate counts and proportions
  
  #variable_counts <- as.data.frame(table(belonging[[variable]])) ## update 'belonging' to correspond to your dataframe ##
  variable_counts <- as.data.frame(table(belonging[[gsub("`", "", variable)]])) # Remove backticks for indexing
  variable_counts$Proportion <- round((variable_counts$Freq / sum(variable_counts$Freq)) * 100, 1)
  
  # Calculate counts with survey weights for the variable
  
  variable_counts_weighted <- as.data.frame(svytable(as.formula(paste0("~", variable)), design = survey_design))
  variable_counts_weighted$Freq <- round(variable_counts_weighted$Freq)
  variable_counts_weighted$Weighted_Proportion <- round((variable_counts_weighted$Freq / sum(variable_counts_weighted$Freq)) * 100, 1)
  
  # Convert factor levels to character
  
  variable_labels <- as.character(variable_counts$Var1)
  
  # Combine the data and create table output
  
  result <- cbind(
    "Variable" = variable_labels,
    "Survey sample, n (%)" = paste0(variable_counts$Freq, " (", variable_counts$Proportion, ")"),
    "Weighted Population, n (%)" = paste0(variable_counts_weighted$Freq, " (", variable_counts_weighted$Weighted_Proportion, ")"),
    "95% CI (Weighted Proportion)" = paste0(lower_ci, " - ", upper_ci)
  )
  
  # Add the result to the result list
  
  result_list[[variable]] <- result
}


# Output each table

for (variable in variables_to_analyze) {
  print(kable(result_list[[variable]], format = "html", align = "c", col.names = c(variable, 
                                                                                   "Survey sample, n (%)", 
                                                                                   "Weighted Population, n (%)", 
                                                                                   "95% CI (Weighted Proportion)")) %>%
          kable_styling(full_width = FALSE))
}


# Loop through each variable and export tables as Word and HTML

for (variable in variables_to_analyze) {

  current_table <- result_list[[variable]]

  if (!is.data.frame(current_table)) {

    if (is.matrix(current_table)) {
      current_table <- as.data.frame(current_table)
    } else {
      next
    }
  }
  
  # Sanitize the variable name to create a valid filename
  filename <- gsub("`|\\s+", "", variable) # Remove backticks and spaces
  
  
  
  # Creating a flextable
  
  ft <- flextable(current_table)
  
  # Save as Word document
  
  
  
  ft_doc <- read_docx() %>%
    body_add_flextable(value = ft) %>%
    print(target = here("outputs", paste0(variable, "_prevalence_estimate_table.docx")))
  
}


#########################
# Frequency Tables
#########################

variables_to_tabulate <- c("`Province`",
                           "`Gender`",
                           "`Age Group`",
                           "`Highest Level Of Education`",
                           "`Total Household Income`",
                           "`Cultural or Racial Background`",
                           "`Social Integration`",
                           "`Illicit Drug Use - past 12 months`") # update this with your variables


# Create an empty list to store the results

results_list <- list()

# Loop through the variables and calculate frequency tables

for (variable in variables_to_tabulate) {
  # Correctly handle variable names with backticks
  variable <- gsub("`", "", variable)
  
  table_result <- svytable(~belonging[[variable]], design = survey_design)
  
  # Store the result in the results_list
  
  results_list[[variable]] <- table_result
}

# Create an empty data frame to store the results

result_df <- data.frame(Variable = character(0), Category = character(0), Frequency = numeric(0), Percent = numeric(0))

# Loop through the variables and results

for (variable in variables_to_tabulate) {
  # Correctly handle variable names with backticks
  variable <- gsub("`", "", variable)
  
  
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
  # Correctly handle variable names with backticks
  variable <- gsub("`", "", variable)
  
  table_result <- table(belonging[[variable]])
  
  # Store the result in the results_list
  
  results_list[[variable]] <- table_result
}

# create an empty data frame

result_df <- data.frame(Variable = character(0), Category = character(0), Frequency = numeric(0), Percent = numeric(0))

# Loop through the variables and results

for (variable in variables_to_tabulate) {
  # Correctly handle variable names with backticks
  variable <- gsub("`", "", variable)
  
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


