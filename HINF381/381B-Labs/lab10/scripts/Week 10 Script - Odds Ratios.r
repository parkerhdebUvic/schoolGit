# Ensures the package "pacman" is installed

if (!require("pacman")) {
  install.packages("pacman") }

pacman::p_load(
  tidyverse,  # data management and visualization
  rio,        # importing data  
  here,       # relative file pathways 
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
  rmarkdown,
  devtools,
  psych,
  sjPlot,
  sjmisc,
  sjlabelled,
  survival,
  boot, 
  Hmisc, 
  ggfittext,
  ggplot2,
  gridExtra,
  apyramid,
  patchwork,
  srvyr,
  demography,
  skimr,
  logbin,
  survey,
  kableExtra,
  ordinal
)

# Read the CCHS data file (replace 'BC CCHS data.csv' with your actual file name)

linelist <- import(here("data","Canada CCHS data.csv")) %>% 
  mutate(WTS_M = as.numeric(WTS_M))

# filter for BC

cchs_data <- linelist

 
belonging <- cchs_data %>%
  select(
    #ADJUSTMENTS
    GEO_PRV, # 0015-0016 Province of residence of respondent
    DHH_SEX, # 0027-0027 Sex
    DHHGAGE, # 0036-0037 Age
    EHG2DVR3, # Highest level of education - respondent, 3 levels - (D)
    INCDGHH, # Total household income - all sources - (D)
    SDCDGCGT, # Cultural / racial background - (D)
    
    #INDEPENDANT VARIABLES
    SPSDVINT, # Concept: Social provisions scale - social integration - (D)
    
    #DEPENDANT VARIABLES
    DRMDVLAY, #illicit grug use - past 12 months

    #WEIGHTS
    WTS_M
  ) 

total = nrow(belonging)

#ADJUSTMENTS: factor province, sex, age, education, income, racial background
#GEO_PRV - Province
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

#DHH_SEX - Sex
belonging$DHH_SEX <- factor(belonging$DHH_SEX,
                            levels = c(1,2),
                            labels = c("Male", "Female"))

#DHHGAGE - Age
belonging <- belonging %>% mutate(DHHGAGE = case_when(
  DHHGAGE %in% c(1, 2, 3) ~ "12-19",
  DHHGAGE %in% c(4, 5, 6, 7) ~ "20-39",
  DHHGAGE %in% c(8, 9, 10, 11) ~ "40-59",
  DHHGAGE %in% c(12, 13, 14, 15) ~ "60-79",
  TRUE ~ "80+")) %>% mutate(DHHGAGE = factor(DHHGAGE)) 

#EHG2DVR3 - Highest level of education
belonging <- belonging %>% filter( 
  !EHG2DVR3 %in% c(9)) #n = , t = 
belonging$EHG2DVR3 <- factor(belonging$EHG2DVR3,
                             levels = c(1,2,3),
                             labels = c("Less than seconday school",
                                        "Secondary school",
                                        "Post-secondary diploma or degree"))
#INCDGHH - Total houshold income
belonging <- belonging %>% filter( 
  !INCDGHH %in% c(9)) #n = , t = 
belonging$INCDGHH <- factor(belonging$INCDGHH,
                            levels = c(1,2,3,4,5),
                            labels = c("No income or less than $20,000", 
                                       "$20,000 to $39,999",
                                       "$40,000 to $59,999",
                                       "$60,000 to $79,999",
                                       "$80,000 or more"))

#SDCDGCGT - Cultural / racial background
belonging <- belonging %>% filter( 
  !SDCDGCGT %in% c(6,9)) #n = , t = 
belonging$SDCDGCGT <- factor(belonging$SDCDGCGT,
                             levels = c(1,2),
                             labels = c("White", 
                                        "Non-white (Aboriginal or Other Visible Minority)"))


#INDEPENDANT VARIABLES: social provision scale
#SPSDVINT - Social Provision - Social Inclusion
belonging <- belonging %>% filter(
  !SPSDVINT %in% c(96,99)) #n = , t =
belonging <- belonging %>% mutate(SPSDVINT = case_when(
  SPSDVINT %in% c(8) ~ "Very High",
  SPSDVINT %in% c(7) ~ "High",
  SPSDVINT %in% c(6) ~ "Slightly High",
  SPSDVINT %in% c(5) ~ "Medium",
  SPSDVINT %in% c(4) ~ "Slightly Low",
  SPSDVINT %in% c(3) ~ "Low",
  SPSDVINT %in% c(2) ~ "Very Low"
  )) %>% mutate(SPSDVINT = factor(SPSDVINT))

#DEPENDANT VARIABLES: illicit drugs1
#DRMDVLAY - Any Illicit Drug use - 12 months
belonging <- belonging %>% filter( 
  !DRMDVLAY %in% c(9)) #n = 47, t = 15269
belonging$DRMDVLAY <- factor(belonging$DRMDVLAY,
                             levels = c(1,2),
                             labels = c("Yes", "No"))

inclusions = nrow(belonging)
exclusions = total - inclusions


#SET BIVARIATE
belonging$DRMDVLAY <- ifelse(belonging$DRMDVLAY == "Yes", 1, 0)

survey_design <- belonging %>% 
  as_survey_design(ids = 1, # no cluster ids
                   weights = WTS_M) # weight variable


#UNADJUSTED ORS, LOG REGRESSION: DRMDVLAY ~ SPSDVINT
log_model_belonging <- svyglm(DRMDVLAY ~ SPSDVINT,
                              data = belonging,
                              family = binomial(link = "logit"),
                              design = survey_design)

table <- log_model_belonging %>%
  tbl_regression(exponentiate = TRUE) %>%
  bold_labels() %>%
  bold_p(t = .1)

table

#ADJUSTED ORS, LOG REGRESSION: DRMDVLAY ~ SPSDVINT, adjusted for: factor province, sex, age, education, income, racial background
log_model_belonging <- svyglm(DRMDVLAY ~ SPSDVINT + GEO_PRV + DHH_SEX + DHHGAGE + EHG2DVR3 + INCDGHH + SDCDGCGT,
                              data = belonging,
                              family = binomial(link = "logit"),
                              design = survey_design)

table <- log_model_belonging %>%
  tbl_regression(exponentiate = TRUE) %>%
  bold_labels() %>%
  bold_p(t = .1)

table


##########################
##########################

                    
# Identify constant columns
constant_columns <- sapply(belonging, function(x) sd(x) == 0)

# Exclude constant columns
belonging_no_constant <- belonging[, !constant_columns]

# Calculate the correlation matrix without constant columns
correlation_matrix <- cor(belonging_no_constant, use = "complete.obs")

# Convert the correlation matrix to a tidy format
correlation_df <- as.data.frame(correlation_matrix) %>%
  rownames_to_column(var = "Variable1") %>%
  pivot_longer(cols = -Variable1, names_to = "Variable2", values_to = "Correlation")

# Sort the dataframe by the 'Correlation' column
sorted_correlation_df <- correlation_df %>%
  arrange(desc(Variable1))

# Print all rows of the sorted correlation dataframe
print(sorted_correlation_df, n = Inf)

