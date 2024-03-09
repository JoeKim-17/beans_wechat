CREATE TABLE Users(
    UserId INT IDENTITY(1,1) NOT NULL,
    UserName VARCHAR(100) NOT NULL,
    EmailAddress NVARCHAR(500) NULL,
    MobileNo NVARCHAR(20) NULL,
    CreatedAt DATETIME NULL
)

CREATE TABLE Chat(
    ChatId INT IDENTITY(1,1) NOT NULL,
    Sender INT,
    Receiver INT,
    CreatedAt DATETIME NULL
)

CREATE TABLE Message(
    MessageId INT IDENTITY(1,1) NOT NULL,
    ChatId INT NOT NULL,
    Content VARCHAR(MAX) NOT NULL,
    CreatedAt DATETIME NULL
)

