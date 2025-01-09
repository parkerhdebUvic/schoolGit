data()
Titanic
head(Titanic)
mtcars
gearCounts = table(mtcars$gear)

gearCounts

barplot(gearCounts, main = "Gear", xlab="Number of Gears")

?barplot()

autvsman = table(mtcars$am)
barplot(autvsman, main="BarGraph of Aut. vs Man. cars")

groupCounts = table(mtcars$am, mtcars$gear)
groupCounts

rownames(groupCounts) = c("Automatic", "Manual")
groupCounts

barplot(groupCounts, main="Distribution of Gears and Transmition", xlab="Number of Gears", ylab="Number of Cars", col=c("darkblue", "red"), legend=rownames(groupCounts), beside=TRUE)
lynx
?lynx
class(lynx)
names(lynx)

years = as.character(c(1821:1934))

years
names(lynx) = years

lynx["1935"]

plot(lynx, main="Line Graph for Lynx Data", xlab="Year", ylab="Number of Trappings")

MH.data = read.csv(file.choose(), header=TRUE)
MH.data

percents = MH.data$Percentage
class(percents)

hist(percents)


hist(percents, main="Percent of Population", xlim = c(0, 35), ylim=c(0, 22), breaks=10)
?hist


hist(percents, main="Percent of Population Whose Mental Health has Declined", xlab="Percent of Population Group")

hist(percents, xlim=c(min(percents), max(percents)+10), ylim=c(0,12))
hist(percents, border="red", col="blue")

percents
stem(round(percents, 0))

install.packages("tidyverse")

Titanic
Titanic_adult_survivors = as.table(Titanic[,,2,2])

survived = rowSums(Titanic_adult_survivors)
print(survived)

barplot(survived, main="Adult Survivors vs. Passenger Class", xlab="Passenger Class", col=rainbow(length(survived)))



Titanic_adult_survivors = as.table(Titanic[,,2,1])

died = rowSums(Titanic_adult_survivors)
print(died)

barplot(died, main="Adult Losses vs. Passenger Class", xlab="Passenger Class", col=rainbow(length(died)))

total = died + survived
total
percent.Survived = round(((survived / total) * 100), 0)
percent.Survived
setwd("/Users/admin/Documents/School/STAT 123/homework/homework2")
NHLData = read.csv("NHLData.csv")
NHLData
?NHLData
head(NHLData, 6)

players = NHLData$Player
points = NHLData$P
points
names(points) = players
points
hist(points, main="Distribution of Points", xlab="Points Scored Per Player")


hist(points, main="Distribution of Points", xlab="Points Scored Per Player", xlim=c(0,30), ylim=c(0,400))
median(points)

stem_nhl = sample(NHLData$P, 20)
stem(stem_nhl)
