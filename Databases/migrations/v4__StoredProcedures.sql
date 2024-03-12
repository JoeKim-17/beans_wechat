CREATE PROCEDURE InsertUser
    @UserName VARCHAR(100),
    @EmailAddress NVARCHAR(500),
    @MobileNo NVARCHAR(20)
AS
BEGIN
 BEGIN TRY
        INSERT INTO Users (UserName, EmailAddress, MobileNo)
        VALUES (@UserName, @EmailAddress, @MobileNo)
        PRINT 'Data inserted successfully';
    END TRY
    BEGIN CATCH
        -- Handle errors
        PRINT 'Wrong input: ' + ERROR_MESSAGE();
    END CATCH
END;

--Stored proc for chat table
CREATE PROCEDURE InsertChat
    @Sender INT,
    @Receiver INT
AS
BEGIN
    BEGIN TRY
        INSERT INTO Chat (Sender, Receiver)
        VALUES (@Sender, @Receiver);
        PRINT 'Data inserted successfully';
    END TRY
    BEGIN CATCH
        -- Handle errors
        PRINT 'Wrong input: ' + ERROR_MESSAGE();
    END CATCH
END;

CREATE PROCEDURE InsertMessage
   @ChatId INT,
   @Content VARCHAR(Max)
AS
BEGIN 
   BEGIN TRY 
      INSERT INTO Message(ChatId,Content)
	  VALUES (@ChatId,@Content);
	  PRINT 'Data inserted successfully';
    END TRY
    BEGIN CATCH
        -- Handle errors
        PRINT 'Wrong input: ' + ERROR_MESSAGE();
    END CATCH
END;

