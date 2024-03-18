select * from Users;
/*
ALTER TABLE Users
DROP COLUMN MobileNo;
GO

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
