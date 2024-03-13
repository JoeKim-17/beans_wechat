DROP PROCEDURE InsertUser;
GO

DROP PROCEDURE InsertIntoChat;
GO

DROP PROCEDURE InsertMessage;
GO

CREATE PROCEDURE InsertUser
    @UserName VARCHAR(100),
    @EmailAddress NVARCHAR(500),
    @MobileNo NVARCHAR(20)
AS
BEGIN
      INSERT INTO Users (UserName, EmailAddress, MobileNo)
      VALUES (@UserName, @EmailAddress, @MobileNo)
END;

GO
--Stored proc for chat table
CREATE PROCEDURE InsertIntoChat
    @Sender VARCHAR(100), 
    @Receiver VARCHAR(100)
AS
BEGIN
      INSERT INTO Chat (Sender, Receiver)
      VALUES ((SELECT UserId FROM Users WHERE UserName=@Sender),( SELECT UserId FROM Users WHERE UserName= @Receiver));
END;
GO

CREATE PROCEDURE InsertMessage
   @ChatId INT,
   @Content VARCHAR(Max)
AS
BEGIN 
     INSERT INTO Message(ChatId,Content)
	 VALUES (@ChatId,@Content);
END;
GO