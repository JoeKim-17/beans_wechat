DROP PROCEDURE InsertMessage;
GO

CREATE PROCEDURE InsertMessage
   @Sender VARCHAR(100),
   @Receiver VARCHAR(100),
   @Content VARCHAR(Max)
AS
BEGIN 
     DECLARE @ChatId INT;
     SELECT @ChatId = ChatId FROM Chat 
     WHERE Sender = (SELECT UserId FROM Users WHERE UserName=@Sender) 
        AND Receiver = ( SELECT UserId FROM Users WHERE UserName= @Receiver);

     IF @ChatId IS NULL
        BEGIN
         EXECUTE InsertIntoChat @Sender, @Receiver;
          
         SELECT @ChatId = ChatId FROM Chat 
         WHERE Sender = (SELECT UserId FROM Users WHERE UserName=@Sender) 
            AND Receiver = ( SELECT UserId FROM Users WHERE UserName= @Receiver);
         INSERT INTO Message(ChatId,Content)
	        VALUES (@ChatId,@Content);
        END 
     ELSE
        BEGIN
            INSERT INTO Message(ChatId,Content)
	        VALUES (@ChatId,@Content);
        END
END;
GO