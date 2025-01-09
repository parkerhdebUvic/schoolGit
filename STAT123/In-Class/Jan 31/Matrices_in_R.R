matrix(c(1:6), nrow=3)

matrix(c(1:6), ncol=2)

matrix(c(1:6), byrow=TRUE, nrow=3)

matrix(c(1:6), byrow=TRUE, ncol=3)

M=matrix(c(80, 12, 40, 3, 20, 0.8, 25, 1), byrow=TRUE, nrow=4)

M

M=matrix(c(80, 40, 20, 25, 12, 3, 0.8, 1), ncol=2)

M

individuals = c("A", "B", "C", "D")

variables = c("Weight", "Age")

colnames(M) = variables

rownames(M) = individuals

M

rowSums(M)

colSums(M)

newM = rbind(M, c(30, 1.5))

newM

M[1,2]

M["A", "Age"]

M[4,3]

M[4,1]

M["D","Weight"]

M[ ,2]

M[,2]

M[, "Age"]

M[3, ]

M[3,]

M["C", ]

M[1:3, ]

M[ , 1:4]

data(PlantGrowth)

head(PlantGrowth, 2)

weightMatrix = as.matrix(PlantGrowth[ ,1])

class(weightMatrix)

is.matrix(weightMatrix)

is.array(weightMatrix)

class(PlantGrowth)

weightMatrix2 = PlantGrowth[ ,1]

class(weightMatrix2)

is.matrix(weightMatrix2)

is.array(weightMatrix2)

is.vector(weightMatrix2)



# time permitted

v1 = c(1, 2, 3)

m1 = matrix(v1, nrow = 2)

m1

individuals

m2 = matrix(individuals, nrow = 2)

m2

c("A", 1)

setwd("/Users/admin/Documents/School/STAT 123/In-Class/Jan 31")
