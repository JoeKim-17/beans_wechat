ALTER TABLE Users
ADD GitUserId NVARCHAR(50) Default '';
GO

ALTER TABLE Users
DROP CONSTRAINT UniqueMobileNo;
GO

/*
DROP PROCEDURE InsertUser;
GO 

CREATE PROCEDURE InsertUser
    @UserName VARCHAR(100),
    @EmailAddress NVARCHAR(500),
    @GitUserId NVARCHAR(50) 
AS
BEGIN
      INSERT INTO Users (UserName, EmailAddress, GitUserId)
      VALUES (@UserName, @EmailAddress, @GitUserId)
END;
GO
*/
