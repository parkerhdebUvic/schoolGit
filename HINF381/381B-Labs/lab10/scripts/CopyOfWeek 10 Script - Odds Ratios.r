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
install.packages("tidyverse")
install.packages("here")
install.packages("rio")
install.packages("dplyr")

# Read the CCHS data file (replace 'BC CCHS data.csv' with your actual file name)

linelist <- import(here("data","Canada CCHS data.csv")) %>% 
  mutate(WTS_M = as.numeric(WTS_M))

# filter for BC

cchs_data <- linelist
# %>% 
# filter(GEO_PRV == 59)  # filter for BC

# select desired variables, remember to update Frequency Tables skript

belonging <- cchs_data %>%
  select(
    #ADJUSTMENTS
    GEO_PRV, # 0015-0016 Province of residence of respondent
    GEODGHR4, # 0021-0025 Health region - (D)
    DHH_SEX, # 0027-0027 Sex
    DHHGAGE, # 0036-0037 Age
    INCDGHH, # 1685-1685 Total household income - all sources - (D)
    INCDGPER, # 1686-1687 Personal income - all sources - (D))
    
    #DEPENDANT VARIABLES
    GEN_020, # 0051-0051 Perceived life stress
    GENDVHDI, # 0054-0054 Perceived health - (D)
    GENDVMHI, # 0055-0055 Perceived mental health - (D)
    GENDVSWL, # 0056-0056 Satisfaction with life in general - (D)
    
    
    #INDEPENDANT VARIABLES
    ########## Important?
    SPS_035, # 1085-1085 Relationships - part of a group who share attitudes and beliefs
    GEN_030, # 0053-0053 Sense of belonging to local community
    SWL_030, # 1035-1035 Satisfaction - relationships with family members
    SWL_035, # 1036-1036 Satisfaction - relationships with friends
    SPS_040, # 1086-1086 Relationships - strong emotional bond with a least one person
    ########## 
    SPS_005, # 1079-1079 Relationships - people to depend on for help
    SPS_010, # 1080-1080 Relationships - people who enjoy same social activities
    SPS_015, # 1081-1081 Relationships - sense of emotional security and wellbeing
    SPS_020, # 1082-1082 Relationships - someone to talk to about important decisions
    SPS_025, # 1083-1083 Relationships - competence and skill are recognized
    SPS_030, # 1084-1084 Relationships - trustworthy person for advice
    SPS_045, # 1087-1087 Relationships - talents and abilities are admired
    SPS_050, # 1088-1088 Relationships - people to count on in an emergency
    SPSDVGUI, # 1089-1090 Social provisions scale - guidance - (D)
    SPSDVINT, # 1091-1092 Social provisions scale - social integration - (D)
    SPSDVATT, # 1093-1094 Social provisions scale - attachment - (D)
    SPSDVWOR, # 1095-1096 Social provisions scale - reassurance of worth - (D)
    SPSDVALL, # 1097-1098 Social provisions scale - reliable alliance - (D)
    SPSDVCON # 1099-1100 Social provisions overall scale - (D)
  ) %>% #clean categories
  filter(
    #GEO_PRV %in% c(), # no filter needed # 0015-0016 Province of residence of respondent
    #GEODGHR4 %in% c(), #no filter needed # 0021-0025 Health region - (D)
    #DHH_SEX %in% c(), # no filter needed # 0027-0027 Sex
    #DHHGAGE %in% c(), # no filter needed # 0036-0037 Age
    INCDGHH %in% c(1,2,3,4,5), #keep: <$20k, $20k-$39k, $40k-$59k, $60k-$79k, $80k <  # 1685-1685 Total household income - all sources - (D)
    INCDGPER %in% c(01,02,03,04,05,06), #keep <$20k, $20k-$39k, $40k-$59k, $60k-$79k, $80k # 1686-1687 Personal income - all sources - (D))
    
    GEN_020 %in% c(1,2,3,4,5), #keep: Not at all stressful, Not very stressful, A bit stressful, Quite a bit stressful, Extremely stressful  # 0051-0051 Perceived life stress
    GENDVHDI %in% c(0,1,2,3,4), #keep: poor, fair, good, very good, excellent # 0054-0054 Perceived health - (D)
    GENDVMHI %in% c(0,1,2,3,4), #keep: poor, fair, good, very good, excellent # 0055-0055 Perceived mental health - (D)
    GENDVSWL %in% c(1,2,3,4,5), #keep: Very Satisfied, satisfied, neither satisfied nor dissatisfied, dissatisfied, very dissatisfied# 0056-0056 Satisfaction with life in general - (D)
    
    SPS_035 %in% c(1,2,3,4,5), #LARGE EXCLUSION, keep: strongly agree, agree, disagree, strongly disagree # 1085-1085 Relationships - part of a group who share attitudes and beliefs
    GEN_030 %in% c(1,2,3,4), # keep "Very strong", "Somewhat strong", "Somewhat weak", "Very Weak" # 0053-0053 Sense of belonging to local community
    SWL_030 %in% c(1,2,3,4,5), #LARGE EXCLUSION, keep: Very satisfied, satisfied, neither satisfied nor dissatisfied, dissatisfied, very dissatisfied # 1035-1035 Satisfaction - relationships with family members
    SWL_035 %in% c(1,2,3,4,5), #LARGE EXCLUSION, keep: Very satisfied, satisfied, neither satisfied nor dissatisfied, dissatisfied, very dissatisfied # 1036-1036 Satisfaction - relationships with friends
    SPS_040 %in% c(1,2,3,4), #LARGE EXCLUSION, keep: Strongly agree, Agree, Disagree, Strongly disagree # 1086-1086 Relationships - strong emotional bond with a least one person
    
  ) %>% #join categories
  mutate(age_group = case_when(
    DHHGAGE %in% c(1, 2, 3) ~ "12-19",
    DHHGAGE %in% c(4, 5, 6, 7) ~ "20-39",
    DHHGAGE %in% c(8, 9, 10, 11) ~ "40-59",
    DHHGAGE %in% c(12, 13, 14, 15) ~ "60-79",
    TRUE ~ "80+"),
    province = GEO_PRV,
    health_region = GEODGHR4,
    sex = DHH_SEX,
    houshold_income = INCDGHH,
    personal_income = INCDGPER,
    life_stress = GEN_020,
    health = GENDVHDI,
    mental_health = GENDVMHI,
    life_satisfaction = GENDVSWL,
    group_belonging = SPS_035,
    community_belonging = GEN_030,
    family_satisfaction = SWL_030,
    friends_satisfaction = SWL_035,
    at_least_one_bond = SPS_040,
    people_to_depend_on = SPS_005,
    people_with_same_social_activities = SPS_010,
    emotional_security = SPS_015,
    someone_for_important_decisions = SPS_020,
    recognized_competence = SPS_025,
    trustworthy_person_advice = SPS_030,
    talents_admired = SPS_045,
    people_to_count_on_emergency = SPS_050,
    guidance = SPSDVGUI,
    social_integration = SPSDVINT,
    attachment = SPSDVATT,
    reassurance_of_worth = SPSDVWOR,
    reliable_alliance = SPSDVALL,
    social_provisions_overall = SPSDVCON
    ) %>% 
  mutate(age_group = factor(age_group))


belonging <- belonging %>%
  mutate(
    life_stress = if_else(life_stress %in% c(1,2), 1, 0), #1 = not at all stressful, not very stressful, 0 = a bit stressful, quite a bit stressful, extremely stressful
    health = if_else(health %in% c(2,3,4), 1, 0), #1 = Good, very good, excellent, 0 = poor, fair
    mental_health = if_else(mental_health %in% c(2,3,4), 1, 0), #1 = Good, very good, excellent, 0 = poor, fair
    life_satisfaction = if_else(life_satisfaction %in% c(1,2,3), 1, 0), #1 = very satisfied, satisfied, 0 = neither, dissatisfied, very satisfied
    group_belonging = if_else(group_belonging %in% c(1,2), 1, 0),
    community_belonging = if_else(community_belonging %in% c(1,2), 1, 0),
    family_satisfaction = if_else(family_satisfaction %in% c(1,2), 1, 0),
    friends_satisfaction = if_else(friends_satisfaction %in% c(1,2), 1, 0),
    at_least_one_bond = if_else(at_least_one_bond %in% c(1,2), 1, 0),
    people_to_depend_on = if_else(people_to_depend_on %in% c(1,2), 1, 0),
    people_with_same_social_activities = if_else(people_with_same_social_activities %in% c(1,2), 1, 0),
    emotional_security = if_else(emotional_security %in% c(1,2), 1, 0),
    someone_for_important_decisions = if_else(someone_for_important_decisions %in% c(1,2), 1, 0),
    recognized_competence = if_else(recognized_competence %in% c(1,2), 1, 0),
    trustworthy_person_advice = if_else(trustworthy_person_advice %in% c(1,2), 1, 0),
    talents_admired = if_else(talents_admired %in% c(1,2), 1, 0),
    people_to_count_on_emergency = if_else(people_to_count_on_emergency %in% c(1,2), 1, 0),
  )


# #SMK_010 - In the past 30 days, did you smoke any cigarettes?
# belonging <- belonging %>% filter(
#   !SMK_010 %in% c(6,7,8)) #n = , t = 
# belonging$SMK_010 <- factor(belonging$SMK_010,
#                             levels = c(1,2),
#                             labels = c("Yes", "No"))
# #DEPENDANT VARIABLES: alcohol use, illicit drugs, smoking
# #ALWDVSTR - Increased short term risks due to drinking - (D)
# belonging <- belonging %>% filter(
#   !ALWDVSTR %in% c(6,9)) #n = , t =
# belonging$ALWDVSTR <- factor(belonging$ALWDVSTR,
#                              levels = c(1,2),
#                              labels = c("Yes", "No"))


belonging2 <- belonging %>%
  select(
    life_stress,
    health,
    mental_health,
    life_satisfaction,
    group_belonging,
    community_belonging,
    family_satisfaction,
    friends_satisfaction,
    at_least_one_bond,
    people_to_depend_on,
    people_with_same_social_activities,
    emotional_security,
    someone_for_important_decisions,
    recognized_competence,
    trustworthy_person_advice,
    talents_admired,
    people_to_count_on_emergency,
  )

belonging2$group_belonging <- factor(belonging2$group_belonging,
                                     levels = c(1,0),
                                     labels = c("High", "Low"))

belonging2$life_stress <- factor(belonging2$life_stress,
                                     levels = c(1,0),
                                     labels = c("low stress", "high stress"))
survey_design <- belonging2 %>%
  as_survey_design()  #no cluster ids
  

log_model_belonging2 <- svyglm(group_belonging ~ life_stress,
                               data = belonging2,
                               family = binomial(link="logit"),
                               design = survey_design)
                    
                    
                    
                    
# Identify constant columns
constant_columns <- sapply(belonging2, function(x) sd(x) == 0)

# Exclude constant columns
belonging2_no_constant <- belonging2[, !constant_columns]

# Calculate the correlation matrix without constant columns
correlation_matrix <- cor(belonging2_no_constant, use = "complete.obs")

# Convert the correlation matrix to a tidy format
correlation_df <- as.data.frame(correlation_matrix) %>%
  rownames_to_column(var = "Variable1") %>%
  pivot_longer(cols = -Variable1, names_to = "Variable2", values_to = "Correlation")

# Sort the dataframe by the 'Correlation' column
sorted_correlation_df <- correlation_df %>%
  arrange(desc(Variable1))

# Print all rows of the sorted correlation dataframe
print(sorted_correlation_df, n = Inf)


# community_smoking <- cchs_data %>%
#   select(WTS_M, # survey weights
#          DHH_SEX, # sex
#          DHHGAGE, # age
#          # GEN_020, # perceived life stress
#          # CCC_200) %>% # has anxiety disorder 
#          GEN_030, # Sense of belonging to a local community
#          SMKG040 # Age - began smoking daily
#   ) %>%
#   filter(
#     # GEN_020 %in% c(1, 2, 3, 4, 5), # keep "Excellent", "Very good", "Good", "Fair", & "Poor"
#     # CCC_200 %in% c(1, 2), # keep "Yes" & "No"
#     GEN_030 %in% c(1,2,3,4), # keep "Very strong", "Somewhat strong", "Somewhat weak", "Very Weak"
#     SMKG040 %in% c(01,02,03,04,05,06,07,08,09,10,11) # keep all
#   )

# Create new age categories 
# 
# community_smoking <- community_smoking %>%
#   mutate(age_group = case_when(
#     DHHGAGE %in% c(1, 2, 3) ~ "12-19",
#     DHHGAGE %in% c(4, 5, 6, 7) ~ "20-39",
#     DHHGAGE %in% c(8, 9, 10, 11) ~ "40-59",
#     DHHGAGE %in% c(12, 13, 14, 15) ~ "60-79",
#     TRUE ~ "80+"
#   )) %>% 
#   mutate(age_group = factor(age_group)) 

# rename variables

community_smoking <- community_smoking %>% 
  mutate(sex = DHH_SEX, # rename DHH_SEX to sex
         # anxiety = CCC_200, # rename CCC_200 to anxiety
         # perceived_stress = GEN_020 # rename GEN_020 to perceived_stress
         community = GEN_030,
         smoking = SMKG040
  )

# recode sex variable 

community_smoking$sex <- factor(community_smoking$sex,
                                levels = c(1,2),
                                labels = c("Male", "Female"))

# recode perceived life stress variable

# community_smoking$perceived_stress <- factor(community_smoking$perceived_stress, 
#                                  levels = c(1, 2, 3, 4, 5), 
#                                  labels = c("Not at all stressful", "Not very stressful", 
#                                             "A bit stressful","Quite a bit stressful", "Extremely stressful"))

# recode sense of belonging to a community
community_smoking$community <- factor(community_smoking$community, 
                                      levels = c(1, 2, 3, 4), 
                                      labels = c("Very strong", "Somewhat strong", "Somewhat weak", "Very Weak"))

# recode age began smoking daily
community_smoking$smoking <- factor(community_smoking$smoking, 
                                    levels = c(01,02,03,04,05,06,07,08,09,10,11), 
                                    labels = c("Began smoking daily between ages 5 and 11", 
                                               "Began smoking daily between ages 12 and 14", 
                                               "Began smoking daily between ages 15 and 17", 
                                               "Began smoking daily between ages 18 and 19", 
                                               "Began smoking daily between ages 20 and 24", 
                                               "Began smoking daily between ages 25 and 29", 
                                               "Began smoking daily between ages 30 and 34", 
                                               "Began smoking daily between ages 35 and 39", 
                                               "Began smoking daily between ages 40 and 44", 
                                               "Began smoking daily between ages 45 and 49", 
                                               "Began smoking daily at age 50 or older"))



# Recode anxiety variable from 1 and 2 to 1 and 0

# community_smoking$anxiety <- ifelse(community_smoking$anxiety == 2, 0, 1)  

# Convert anxiety variable to a factor

# community_smoking$anxiety <- factor(community_smoking$anxiety,
#                                  levels = c(0,1),
#                                  labels = c("No", "Yes"))

# Create a survey design

survey_design <- community_smoking %>% 
  as_survey_design(ids = 1, # no cluster ids
                   weights = WTS_M) # weight variable

# Unadjusted ORs

log_model_community_smoking <- svyglm(community ~ smoking, 
                                      data = community_smoking, 
                                      family = binomial(link="logit"), 
                                      design = survey_design)

# create table summary of model

table <- log_model_community_smoking  %>% 
  tbl_regression(exponentiate = TRUE) %>% 
  bold_labels() %>% 
  bold_p(t = .1) 

# display the table in the 'Viewer' pane

table

# adjust for age and sex

# adjusted odds ratio for age and sex

log_model_community_smoking <- svyglm(community ~ smoking + sex + age_group, 
                                      data = community_smoking, 
                                      family = binomial(link="logit"), 
                                      design = survey_design)

# create table summary of model 

table <- log_model_community_smoking  %>% 
  tbl_regression(exponentiate = TRUE) %>% 
  bold_labels() %>% 
  bold_p(t = .1) 

table

