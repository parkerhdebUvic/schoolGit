setwd("/Users/admin/Documents/School/STAT 123/Labs/Lab3")
getwd()


proj.df = read.csv("FantasyProjections.csv")



proj.df$Salary = gsub(",", "", proj.df$Salary)

proj.df$Salary = as.numeric(proj.df$Salary)




length(proj.df)

proj.df.median = median(proj.df$Fpts)
proj.df.median

p_above_0 = proj.df$Value > 0

p_above_0 = proj.df[p_above_0,]

ncol(proj.df)
nrow(proj.df)
n

below_avg = proj.df[proj.df$Value < 0,]
below_avg
