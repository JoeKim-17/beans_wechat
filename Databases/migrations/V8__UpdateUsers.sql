ALTER TABLE Users
ADD GitUserId NVARCHAR(50) Default ''; 
GO

ALTER TABLE Users
DROP CONSTRAINT UniqueMobileNo;
GO

/*
=======
ALTER TABLE Users
DROP COLUMN MobileNo;
GO

>>>>>>> main
DROP PROCEDURE InsertUser;
GO 

CREATE PROCEDURE InsertUser
    @UserName VARCHAR(100),
    @EmailAddress NVARCHAR(500),
<<<<<<< HEAD
    @GitUserId NVARCHAR(50) 
=======
    @GitUserId NVARCHAR(50)
>>>>>>> main
AS
BEGIN
      INSERT INTO Users (UserName, EmailAddress, GitUserId)
      VALUES (@UserName, @EmailAddress, @GitUserId)
END;
<<<<<<< HEAD
GO
*/
=======
GO
>>>>>>> main
