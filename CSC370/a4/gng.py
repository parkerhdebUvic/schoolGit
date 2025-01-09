#!/usr/bin/env python3

import psycopg2


def main():
    dbconn = psycopg2.connect(
        host='studentdb.csc.uvic.ca', user='c370_s029', password='KMJTr3sm')
    cursor = dbconn.cursor()
    
    try:
        cursor.execute("""
            DROP TABLE Regions CASCADE;
            CREATE TABLE Regions (
                regionName CHAR(30) PRIMARY KEY
            );          
            INSERT INTO Regions VALUES ('Vancouver-Island');
            INSERT INTO Regions VALUES ('Vancouver');
            INSERT INTO Regions VALUES ('Victoria');
            INSERT INTO Regions VALUES ('Toronto');

            DROP TABLE Campaigns CASCADE;
            CREATE TABLE Campaigns (
                campaignName CHAR(30) PRIMARY KEY,
                totalExpenses REAL,
                totalIncome REAL,
                totalBalance REAL
            );
            INSERT INTO Campaigns values ('Save-The-Whales', 8000, 10000, 2000);
            INSERT INTO Campaigns VALUES ('Clean-The-Oceans', 2000, 5000, 3000);
            INSERT INTO Campaigns VALUES ('Save-The-Forest', 1500, 7000, 5500);
            INSERT INTO Campaigns VALUES ('Protect-The-Wildlife', 3000, 4000, 1000);


            DROP TABLE EnvironmentalIssues CASCADE;
            CREATE TABLE EnvironmentalIssues (
                issueName CHAR(30) PRIMARY KEY,
                category CHAR(30)
            );
            INSERT INTO EnvironmentalIssues VALUES ('Them-whales-need-saving', 'whales');
            INSERT INTO EnvironmentalIssues VALUES ('Deforestation', 'forest');
            INSERT INTO EnvironmentalIssues VALUES ('Ocean Pollution', 'ocean');
            INSERT INTO EnvironmentalIssues VALUES ('Wildlife Conservation', 'wildlife');

            DROP TABLE CampaignIssue CASCADE;
            CREATE TABLE CampaignIssue (
                issueName CHAR(30),
                campaignName CHAR(30),
                PRIMARY KEY (issueName, campaignName),
                FOREIGN KEY (issueName) REFERENCES EnvironmentalIssues(issueName),
                FOREIGN KEY (campaignName) REFERENCES Campaigns(campaignName)
            );
            INSERT INTO CampaignIssue VALUES ('Them-whales-need-saving', 'Save-The-Whales');
            INSERT INTO CampaignIssue VALUES ('Deforestation', 'Save-The-Forest');
            INSERT INTO CampaignIssue VALUES ('Ocean Pollution', 'Clean-The-Oceans');
            INSERT INTO CampaignIssue VALUES ('Wildlife Conservation', 'Protect-The-Wildlife');

            DROP TABLE SignificantDonors CASCADE;
            CREATE TABLE SignificantDonors (
                donorName CHAR(30) PRIMARY KEY,
                donorType CHAR(30)
            );
            INSERT INTO SignificantDonors VALUES ('Scrooge-mc-duck', 'large');
            INSERT INTO SignificantDonors VALUES ('Green Earth', 'large');
            INSERT INTO SignificantDonors VALUES ('EcoFriendly Inc.', 'large');
            INSERT INTO SignificantDonors VALUES ('Nature Lovers', 'medium');

            DROP TABLE CampaignDonations CASCADE;
            CREATE TABLE CampaignDonations (
                donorName CHAR(30),
                campaignName CHAR(30),
                amount REAL,
                PRIMARY KEY (donorName, campaignName),
                FOREIGN KEY (donorName) REFERENCES SignificantDonors(donorName),
                FOREIGN KEY (campaignName) REFERENCES Campaigns(campaignName)
            );
            INSERT INTO CampaignDonations VALUES ('Scrooge-mc-duck', 'Save-The-Whales', 8000);
            INSERT INTO CampaignDonations VALUES ('Green Earth', 'Save-The-Forest', 3000);
            INSERT INTO CampaignDonations VALUES ('EcoFriendly Inc.', 'Clean-The-Oceans', 4000);
            INSERT INTO CampaignDonations VALUES ('Nature Lovers', 'Protect-The-Wildlife', 2000);

            DROP TABLE Events CASCADE;
            CREATE TABLE Events (
                eventID CHAR(30) PRIMARY KEY
            );
            INSERT INTO Events VALUES ('event1');
            INSERT INTO Events VALUES ('event2');
            INSERT INTO Events VALUES ('event3');
            INSERT INTO Events VALUES ('event4');

            DROP TABLE CampaignPhases CASCADE;
            CREATE TABLE CampaignPhases (
                campaignName CHAR(30),
                eventID CHAR(30),
                startDate TIMESTAMP,
                endDate TIMESTAMP,
                phaseLevel CHAR(30),
                PRIMARY KEY (campaignName, eventID),
                FOREIGN KEY (campaignName) REFERENCES Campaigns(campaignName),
                FOREIGN KEY (eventID) REFERENCES Events(eventID)
            );
            INSERT INTO CampaignPhases VALUES ('Save-The-Whales', 'event1', '2024-03-19 12:00:00', '2024-03-19 12:00:01', 'phase1');
            INSERT INTO CampaignPhases VALUES ('Clean-The-Oceans', 'event2', '2024-03-20 12:00:00', '2024-03-20 12:00:01', 'phase1');
            INSERT INTO CampaignPhases VALUES ('Save-The-Forest', 'event3', '2024-03-21 12:00:00', '2024-03-21 12:00:01', 'phase1');
            INSERT INTO CampaignPhases VALUES ('Protect-The-Wildlife', 'event4', '2024-03-22 12:00:00', '2024-03-22 12:00:01', 'phase1');

            DROP TABLE RaisedFunds CASCADE;
            CREATE TABLE RaisedFunds (
                amount REAL,
                date TIMESTAMP,
                campaignName CHAR(30),
                eventID CHAR(30),
                PRIMARY KEY (amount, date, campaignName, eventID),
                FOREIGN KEY (campaignName) REFERENCES Campaigns(campaignName),
                FOREIGN KEY (eventID) REFERENCES Events(eventID)
            );
            INSERT INTO RaisedFunds VALUES (4000, '2024-03-19 12:00:00', 'Save-The-Whales', 'event1');
            INSERT INTO RaisedFunds VALUES (3000, '2024-03-20 12:00:00', 'Clean-The-Oceans', 'event2');
            INSERT INTO RaisedFunds VALUES (5000, '2024-03-21 12:00:00', 'Save-The-Forest', 'event3');
            INSERT INTO RaisedFunds VALUES (1500, '2024-03-22 12:00:00', 'Protect-The-Wildlife', 'event4');

            DROP TABLE CampaignExpenses CASCADE;
            CREATE TABLE CampaignExpenses (
                amount REAL,
                date TIMESTAMP,
                category CHAR(30),
                campaignName CHAR(30),
                eventID CHAR(30),
                PRIMARY KEY (amount, date, campaignName, eventID),
                FOREIGN KEY (campaignName) REFERENCES Campaigns(campaignName),
                FOREIGN KEY (eventID) REFERENCES Events(eventID)
            );
            INSERT INTO CampaignExpenses VALUES (2000, '2024-03-19 12:00:01', 'wages', 'Save-The-Whales', 'event1');
            INSERT INTO CampaignExpenses VALUES (1000, '2024-03-20 12:00:01', 'equipment', 'Clean-The-Oceans', 'event2');
            INSERT INTO CampaignExpenses VALUES (500, '2024-03-21 12:00:01', 'wages', 'Save-The-Forest', 'event3');
            INSERT INTO CampaignExpenses VALUES (800, '2024-03-22 12:00:01', 'materials', 'Protect-The-Wildlife', 'event4');

            DROP TABLE Locations CASCADE;
            CREATE TABLE Locations (
                address CHAR(30),
                locationName CHAR(30) PRIMARY KEY,
                street1 CHAR(30),
                street2 CHAR(30)
            );
            INSERT INTO Locations VALUES ('1382 Finerty Road', 'UVic', NULL, NULL);
            INSERT INTO Locations VALUES ('1234 Beach Ave', 'Beach Park', NULL, NULL);
            INSERT INTO Locations VALUES ('5678 Forest Rd', 'Forest Park', NULL, NULL);
            INSERT INTO Locations VALUES ('9876 Wildlife Dr', 'Wildlife Reserve', NULL, NULL);

            DROP TABLE RegionLocations CASCADE;
            CREATE TABLE RegionLocations (
                locationName CHAR(30),
                regionName CHAR(30),
                PRIMARY KEY (locationName, regionName),
                FOREIGN KEY (locationName) REFERENCES Locations(locationName),
                FOREIGN KEY (regionName) REFERENCES Regions(regionName)
            );
            INSERT INTO RegionLocations VALUES ('UVic', 'Vancouver-Island');
            INSERT INTO RegionLocations VALUES ('Beach Park', 'Vancouver');
            INSERT INTO RegionLocations VALUES ('Forest Park', 'Victoria');
            INSERT INTO RegionLocations VALUES ('Wildlife Reserve', 'Toronto');

            DROP TABLE CampaignRegions CASCADE;
            CREATE TABLE CampaignRegions (
                regionName CHAR(30),
                campaignName CHAR(30),
                PRIMARY KEY (regionName, campaignName),
                FOREIGN KEY (regionName) REFERENCES Regions(regionName),
                FOREIGN KEY (campaignName) REFERENCES Campaigns(campaignName)
            );
            INSERT INTO CampaignRegions VALUES ('Vancouver-Island', 'Save-The-Whales');
            INSERT INTO CampaignRegions VALUES ('Vancouver', 'Clean-The-Oceans');
            INSERT INTO CampaignRegions VALUES ('Victoria', 'Save-The-Forest');
            INSERT INTO CampaignRegions VALUES ('Toronto', 'Protect-The-Wildlife');

            DROP TABLE RegionEvents CASCADE;
            CREATE TABLE RegionEvents (
                regionName CHAR(30),
                eventID CHAR(30),
                PRIMARY KEY (regionName, eventID),
                FOREIGN KEY (regionName) REFERENCES Regions(regionName),
                FOREIGN KEY (eventID) REFERENCES Events(eventID)
            );
            INSERT INTO RegionEvents VALUES ('Vancouver-Island', 'event1');
            INSERT INTO RegionEvents VALUES ('Vancouver', 'event2');
            INSERT INTO RegionEvents VALUES ('Victoria', 'event3');
            INSERT INTO RegionEvents VALUES ('Toronto', 'event4');

            DROP TABLE Members CASCADE;
            CREATE TABLE Members (
                memberID CHAR(30) PRIMARY KEY,
                memberStatus CHAR(30),
                memberWage REAL,
                memberName CHAR(30),
                campaignCount INT,
                memberTier INT
            );
            INSERT INTO Members VALUES ('parker1', 'volunteer', 0, 'Parker DeBruyne', 4, 1);
            INSERT INTO Members VALUES ('johndoe1', 'volunteer', 0, 'John Doe', 3, 1);
            INSERT INTO Members VALUES ('janedoe1', 'volunteer', 0, 'Jane Doe', 5, 2);
            INSERT INTO Members VALUES ('bobsmith1', 'volunteer', 0, 'Bob Smith', 2, 1);       

            DROP TABLE Activities CASCADE;
            CREATE TABLE Activities (
                activityCategory CHAR(30) PRIMARY KEY,
                materialsUsed CHAR(30)
            );
            INSERT INTO Activities VALUES ('volunteer-booth', 'posters');
            INSERT INTO Activities VALUES ('cleanup', 'gloves');
            INSERT INTO Activities VALUES ('tree-planting', 'shovels');
            INSERT INTO Activities VALUES ('education', 'brochures');
                    
            DROP TABLE EventSchedule CASCADE;
            CREATE TABLE EventSchedule (
                memberID CHAR(30),
                activityCategory CHAR(30),
                locationName CHAR(30),
                startDate TIMESTAMP,
                endDate TIMESTAMP,
                PRIMARY KEY (startDate, locationName, activityCategory),
                FOREIGN KEY (locationName) REFERENCES Locations(locationName),
                FOREIGN KEY (activityCategory) REFERENCES Activities(activityCategory),
                FOREIGN KEY (memberID) REFERENCES Members(memberID)
            );
            INSERT INTO EventSchedule VALUES ('parker1', 'volunteer-booth', 'UVic', '2024-03-19 12:00:00', '2024-03-19 12:00:01');
            INSERT INTO EventSchedule VALUES ('johndoe1', 'volunteer-booth', 'Beach Park', '2024-03-20 12:00:00', '2024-03-20 12:00:01');
            INSERT INTO EventSchedule VALUES ('janedoe1', 'volunteer-booth', 'Forest Park', '2024-03-21 12:00:00', '2024-03-21 12:00:01');
            INSERT INTO EventSchedule VALUES ('bobsmith1', 'cleanup', 'Wildlife Reserve', '2024-03-22 12:00:00', '2024-03-22 12:00:01');
                    
            DROP TABLE GlobalOrgs CASCADE;
            CREATE TABLE GlobalOrgs (
                orgName CHAR(30) PRIMARY KEY
            );
            INSERT INTO GlobalOrgs VALUES ('Camosun');
            INSERT INTO GlobalOrgs VALUES ('Green Peace');
            INSERT INTO GlobalOrgs VALUES ('World Wildlife Fund');
            INSERT INTO GlobalOrgs VALUES ('Nature Conservancy');

            DROP TABLE InterOrgMembers CASCADE;
            CREATE TABLE InterOrgMembers (
                orgName CHAR(30),
                memberID CHAR(30),
                PRIMARY KEY (orgName, memberID),
                FOREIGN KEY (orgName) REFERENCES GlobalOrgs(orgName),
                FOREIGN KEY (memberID) REFERENCES Members(memberID)
            );
            INSERT INTO InterOrgMembers VALUES ('Camosun', 'parker1');
            INSERT INTO InterOrgMembers VALUES ('Green Peace', 'johndoe1');
            INSERT INTO InterOrgMembers VALUES ('World Wildlife Fund', 'janedoe1');
            INSERT INTO InterOrgMembers VALUES ('Nature Conservancy', 'bobsmith1');

            DROP TABLE CampaignMembers CASCADE;
            CREATE TABLE CampaignMembers (
                campaignName CHAR(30),
                memberID CHAR(30),
                PRIMARY KEY (campaignName, memberID),
                FOREIGN KEY (campaignName) REFERENCES Campaigns(campaignName),
                FOREIGN KEY (memberID) REFERENCES Members(memberID)
            );

            INSERT INTO CampaignMembers VALUES ('Save-The-Whales', 'parker1');
            INSERT INTO CampaignMembers VALUES ('Clean-The-Oceans', 'johndoe1');
            INSERT INTO CampaignMembers VALUES ('Save-The-Forest', 'janedoe1');
            INSERT INTO CampaignMembers VALUES ('Protect-The-Wildlife', 'bobsmith1');
                    
            --Q1: What's the total expenses, total income, and total balance for each campaign?

            CREATE VIEW Q1 AS
            SELECT campaignName, totalExpenses, totalIncome, totalBalance
            FROM Campaigns;

            --Q2: How many donors of each type contributed to each campaign?

            CREATE VIEW Q2 AS
            SELECT campaignName, donorType, COUNT(CampaignDonations.donorName) AS donorCount
            FROM CampaignDonations
            JOIN SignificantDonors ON CampaignDonations.donorName = SignificantDonors.donorName
            GROUP BY campaignName, donorType;

            --Q3: What are the top campaigns with the highest total balance?

            CREATE VIEW Q3 AS
            SELECT campaignName, totalBalance
            FROM Campaigns
            ORDER BY totalBalance DESC;

            --Q4: Which campaigns have expenses exceeding $500?
            CREATE VIEW Q4 AS
            SELECT campaignName, totalExpenses
            FROM Campaigns
            WHERE totalExpenses > 500;

            --Q5: What is the total amount of funds raised for each campaign?

            CREATE VIEW Q5 AS
            SELECT campaignName, SUM(amount) as totalFundsRaised
            FROM RaisedFunds
            GROUP BY campaignName;

            --Q6: How many events have been organized in each region?

            CREATE VIEW Q6 AS
            SELECT regionName, COUNT(eventID) AS eventCount
            FROM RegionEvents
            GROUP BY regionName;

            --Q7; What are the most common environmental issues addressed by the campaigns?

            CREATE VIEW Q7 AS
            SELECT issueName, COUNT(*) AS campaignCOUNT
            FROM CampaignIssue
            GROUP BY issueName
            ORDER BY campaignCount DESC;

            --Q8: Which members have the highest campaign count?

            CREATE VIEW Q8 AS
            SELECT memberID, memberName, campaignCount
            FROM Members
            WHERE campaignCount = (SELECT MAX(campaignCount) FROM Members);

            --Q9: What is the net balance of each campaign? (Income - expenses)

            CREATE VIEW Q9 AS
            SELECT campaignName, (SELECT COALESCE(SUM(amount), 0) 
                FROM CampaignDonations 
                WHERE CampaignDonations.campaignName = Campaigns.campaignName) - (SELECT COALESCE(SUM(amount), 0) 
                FROM CampaignExpenses 
                WHERE CampaignExpenses.campaignName = Campaigns.campaignName) AS netBalance
            FROM 
                Campaigns;


            --Q10: What is the most common event start time?

            CREATE VIEW Q10 AS
            SELECT startDate AS most_common_event_start_time
            FROM EventSchedule
            GROUP BY startDate
            ORDER BY COUNT(*) DESC
            LIMIT 1;
        """)

        with open('gng_dump.sql', 'w') as sql_file:  # open SQL file
            # iterate over each command and write
            for command in cursor.query.decode().split(';'):
                sql_file.write(command.strip() + ';\n')

        def query_database():
            while True:
                # prompt user to select a query
                view_number = input("""
                                    DATABASE QUERIES
                                    1: Total expenses, income, and balance for each campaign
                                    2: Number of donors by type for each campaign
                                    3: Top campaigns by highest total balance
                                    4: Campaigns exceeding $500 in expenses
                                    5: Total funds raised for each campaign
                                    6: Number of events organized in each region
                                    7: Most common environmental issues addressed by campaigns
                                    8: Members with the highest campaign count
                                    9: Net balance of each campaign
                                    10: Most common event start time
                                    b: Back to previous menu
                                    q: Quit the program
                                    Enter the query number you want to display (1-10), or 'b' to go back: """)

                # if user chooses to go back, break
                if view_number.lower() == 'b':
                    break
                # # if user chooses to quit, exit
                # elif view_number.lower() == 'q':
                #     exit()

                try:
                    # convert input to integer
                    view_number = int(view_number)

                    # validate input is within range (1-10)
                    if 1 <= view_number <= 10:
                        view_name = 'Q{}'.format(view_number)

                        # execute select query for chosen view
                        cursor.execute("SELECT * FROM {};".format(view_name))

                        # fetch and print results
                        for row in cursor.fetchall():
                            row_size = len(row)
                            for i in range(row_size):
                                print(row[i], end=' ')
                            print()
                    else:
                        print("Invalid input. Please enter a number between 1 and 10.")
                except ValueError:
                    print("Invalid input. Please enter a number between 1 and 10.")

        def create_campaign():
            while True:
                campaign_name = input("Enter the name of the campaign (or 'cancel' to cancel): ")
                if campaign_name.lower() == 'cancel':
                    return

                # check if campaign name already exists
                cursor.execute("SELECT * FROM Campaigns WHERE campaignName = %s;", (campaign_name,))
                existing_campaign = cursor.fetchone()
                if existing_campaign:
                    print("A campaign with the same name already exists. Please choose a different name.")
                else:
                    # if campaign name is unique, proceed
                    total_expenses = float(input("Enter total expenses for the campaign: "))
                    total_income = float(input("Enter total income for the campaign: "))
                    total_balance = total_income - total_expenses

                    # insert new campaign into db
                    cursor.execute("INSERT INTO Campaigns (campaignName, totalExpenses, totalIncome, totalBalance) VALUES (%s, %s, %s, %s);",
                                (campaign_name, total_expenses, total_income, total_balance))
                    dbconn.commit()
                    print("Campaign '{}' successfully created.".format(campaign_name))
                    break

        def accounting_reports(cursor):
            # retrieve accounting data for each campaign
            cursor.execute("""
                SELECT campaignName, totalExpenses, totalIncome, (totalIncome - totalExpenses) AS netBalance
                FROM Campaigns;
            """)
            accounting_data = cursor.fetchall()

            # display accounting reports
            print("Accounting Reports:")
            print("===================")
            for campaign in accounting_data:
                campaign_name, total_expenses, total_income, net_balance = campaign

                # print textual report
                print(f"Campaign: {campaign_name}")
                print(f"Total Expenses: ${total_expenses:.2f}")
                print(f"Total Income: ${total_income:.2f}")
                print(f"Net Balance: ${net_balance:.2f}")
                print()

            # display accounting report
            print("Accounting Report:")
            print("=" * 90)
            print("{:<30} {:<20} {:<20} {:<20}".format("Campaign", "Total Income", "Total Expenses", "Net Balance"))
            print("=" * 90)
            for campaign in accounting_data:
                campaign_name, total_income, total_expenses, net_balance = campaign

                # concatenate
                financial_info = "{:<30} {:<20.2f} {:<20.2f} {:<20.2f}".format(campaign_name, total_income, total_expenses, net_balance)

                # print info
                print(financial_info)

            print("=" * 90)

        def membership_history():
            
            print("Displaying membership history...")
    
            # retrieve membership history
            cursor.execute("""
                SELECT Members.memberID, Members.memberName, Campaigns.campaignName, EventSchedule.activityCategory
                FROM Members
                LEFT JOIN CampaignMembers ON Members.memberID = CampaignMembers.memberID
                LEFT JOIN Campaigns ON CampaignMembers.campaignName = Campaigns.campaignName
                LEFT JOIN EventSchedule ON Members.memberID = EventSchedule.memberID
                ORDER BY Members.memberID;
            """)
            membership_history_data = cursor.fetchall()

            # display membership history data
            for record in membership_history_data:
                member_id, member_name, campaign_name, activity_category = record
                print(f"Member ID: {member_id}, Member Name: {member_name}")
                print(f"Campaign: {campaign_name}")
                print(f"Activity Category: {activity_category}")
                print()
                
        def other():
            print("I ran out of time, sorry!")

        while True:
            # prompt user to select an option
            option = input("""
                            1: Query the database
                            2: Create campaign
                            3: Accounting reports
                            4: Membership history
                            5: Other
                            Enter the option number you want to select (1-5), or 'q' to quit: """)

            # if user chooses to exit, break
            if option.lower() == 'q':
                break

            try:
                # convert input to integer
                option = int(option)

                # validate that input is within range (1-4)
                if 1 <= option <= 4:
                    if option == 1:
                        # query the db
                        query_database()
                    elif option == 2:
                        # create campaign
                        create_campaign()
                    elif option == 3:
                        # run accounting reports
                        accounting_reports(cursor)
                    elif option == 4:
                        # display membership history
                        membership_history()
                    elif option == 5:
                        # perform option 4
                        other()
                else:
                    print("Invalid input. Please enter a number between 1 and 4.")
            except ValueError:
                print("Invalid input. Please enter a number between 1 and 4.")

        
    finally:

        cursor.close()
        dbconn.close()


if __name__ == "__main__":
    main()