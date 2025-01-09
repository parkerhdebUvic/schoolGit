# install packages
if (!require("pacman")) {
  install.packages("pacman") }

pacman::p_load(
  tidyverse,  # data management and visualization
  rio,        # importing data  
  here,       # relative file pathways 
  skimr,      # summarizing data      
  dplyr,      # data wrangling
  kableExtra, # creating customized tables
  janitor,    # data cleaning 
  gtsummary,  # creating summary tables
  gt,          # customize and style tables
  lubridate   # date conversion
)


list <- import(here("data","COVID19_Elementary_School_Outbreak_Line_List.xlsx"))

list_clean <- list %>%
  clean_names() %>%
  mutate(count = 1) %>%
  mutate(episode_date = pmin(date_reported, onset_date, na.rm = TRUE)) %>%
  mutate(onset_date = as.Date(onset_date, format = "%Y-%m-%d")) %>%
  mutate(episode_date = ymd(episode_date)) %>%
  mutate(date_reported = ymd(date_reported)) %>%
  mutate(birth_date = ymd(birth_date)) %>%
  mutate(age = as.integer(difftime(episode_date, birth_date, units = "days") / 365.5)) %>%
  mutate(age_group = case_when(
    age <= 19 ~ "0-19",
    age > 19 & age <= 29 ~ "20-29",
    age > 29 & age <= 39 ~ "30-39",
    age > 39 & age <= 49 ~ "40-49",
    age > 49 & age <= 59 ~ "50-59",
    age > 59 & age <= 69 ~ "60-69",
    age > 69 & age <= 79 ~ "70-79",
    age > 79 & age <= 89 ~ "80-89",
    age > 89             ~ "90+"
  ))

# 
# list_teachers <- list_clean %>%
#   filter(case_role == "Teacher")
# 
# list_students <- list_clean %>%
#   filter(case_role == "Student")
# 
# list_hospitalized <- list_clean %>%
#   filter(ever_hospitalized == "Yes")
# 
# list_deceased <- list_clean %>%
#   filter(deceased == "Yes")
# 
# list_male <- list_clean %>%
#   filter(sex == "Male")
# 
# list_female <- list_clean %>%
#   filter(sex == "Female")
# 
# list_teachers_hospitalized <- list_clean %>%
#   filter(case_role == "Teacher" & ever_hospitalized == "Yes")
# 
# list_teachers_deceased <- list_clean %>%
#   filter(case_role == "Teacher" & deceased == "Yes")
# 
# list_teachers_male <- list_clean %>%
#   filter(case_role == "Teacher" & sex == "Male")
# 
# list_teachers_female <- list_clean %>%
#   filter(case_role == "Teacher" & sex == "Female")
# 
# list_students_hospitalized <- list_clean %>%
#   filter(case_role == "Student" & ever_hospitalized == "Yes")
# 
# list_students_deceased <- list_clean %>%
#   filter(case_role == "Student" & deceased == "Yes")
# 
# list_students_male <- list_clean %>%
#   filter(case_role == "Student" & sex == "Male")
# 
# list_students_female <- list_clean %>%
#   filter(case_role == "Student" & sex == "Female")


# Total
# age_mean = mean(list_clean$age)
# age_min = min(list_clean$age)
# age_max = max(list_clean$age)
# 
# n_male = nrow(list_male)
# n_female = nrow(list_female)
# 
# n_hospitalized = nrow(list_hospitalized)
# n_deceased = nrow(list_deceased)
# 
# # Teachers
# teachers_age_mean = mean(list_teachers$age)
# teachers_age_min = min(list_teachers$age)
# teachers_age_max = max(list_teachers$age)
# 
# teachers_sex_male = nrow(list_teachers_male)
# teachers_sex_female = nrow(list_teachers_female)
# 
# teachers_hospitalized = nrow(list_teachers_hospitalized)
# teachers_deceased = nrow(list_teachers_deceased)
# 
# # Students
# students_age_mean = mean(list_students$age)
# students_age_min = min(list_students$age)
# students_age_max = max(list_students$age)
# 
# students_age_male = nrow(list_students_male)
# students_age_female = nrow(list_students_female)
# 
# students_age_hospitalized = nrow(list_students_hospitalized)
# students_age_deceased = nrow(list_students_deceased)


summary_table <- list_clean %>% select(c("age", "sex", "ever_hospitalized", "deceased", "case_role")) %>% 
  rename(
    "Age" = "age",
    "Sex" = "sex",
    "Hospitalized" = "ever_hospitalized",
    "Deceased" = "deceased",
    "Case Role" = "case_role",
    
  ) %>%
  tbl_summary(
    by = "Case Role",
    statistic = list(
      all_continuous() ~ "{mean} ({min} - {max})"),
    missing = "no") %>%
  add_overall() %>%
  modify_header(all_stat_cols() ~ "**{level}**<br>N = {n} ({style_percent(p)}%)") %>%
  bold_labels() %>%
  modify_spanning_header(all_stat_cols() ~ "**Oct. 2023 COVID-19 School Outbreak: Demographic Information and Severity for Staff and Student Cases**")

summary_table

ggplot(data = linelist_clean)+
  geom_bar(
    mapping = aes(
      x = episode_date,
      fill = case_role),
    color = "black") +
  labs(title = "Number of lab-confirmed COVID-19 cases", 
       subtitle = "Staff and Student cases by case role",
       x = "Episode Date", 
       y = "Number of lab-confirmed cases") +
  scale_fill_discrete(name = "Case Role") + 
  scale_x_date(date_breaks = "1 day",
               date_labels = "%d-%b-%y") +
  theme(axis.text.x = element_text(angle = 90, hjust = 1))  


ggplot(linelist_clean, aes(x = sex, fill = case_role)) +
  geom_bar(color = "black") +
  scale_fill_discrete(name = "Case Role") + 
  labs(title = "COVID-19 cases by case role and sex", x = "Sex", y = "Count")

ggplot(linelist_clean, aes(x = age_group, fill = case_role)) +
  geom_bar(color = "black") +
  scale_fill_discrete(name = "Case Role") + 
  labs(title = "COVID-19 cases by case role and age group", x = "Age Group", y = "Count")

ggplot(linelist_clean, aes(x = ever_hospitalized, fill = case_role)) +
  geom_bar(color = "black") +
  scale_fill_discrete(name = "Case Role") + 
  labs(title = "COVID-19 hospitalizations by case role", x = "Hospitalizations", y = "Count")


