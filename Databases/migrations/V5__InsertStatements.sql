INSERT INTO Users(UserName,EmailAddress,MobileNo)
VALUES 
('Duncan96','mkhacane@gmail.com','+27769375616'),
('JohnDoe', 'john.doe@email.com', '123-456-7890'),
('JaneSmith', 'jane.smith@email.com', '987-654-3210'),
('BobJohnson', 'bob.johnson@email.com', '456-789-0123'),
('AliceBrown', 'alice.brown@email.com', '789-012-3456');

INSERT INTO Chat(Sender,Receiver)
VALUES 
(1,2),
(2,1),
(3,4),
(6,7);


INSERT INTO Message(ChatId,Content)
VALUES 
(5,'Hi Dude'),
(6,'Hi Stranger'),
(7,'Hey please call me'),
(8,'Its too late');


CREATE PROCEDURE InsertUser
    @UserName NVARCHAR(50),
    @EmailAddress NVARCHAR(100),
    @MobileNo NVARCHAR(20)
AS
BEGIN
    INSERT INTO Users (UserName, EmailAddress, MobileNo)
    VALUES (@UserName, @EmailAddress, @MobileNo

--Using the stored procedures
EXEC InsertUser
    @UserName = 'Duncan984',
    @EmailAddress = 'mkha@gmail.com',
    @MobileNo = '+27769375';	
	
EXECUTE InsertChat
      @4,
      @6;	  
	
EXECUTE InsertChat
      @Sender= 1,
      @Receiver = 2;	  	
	  
EXECUTE InsertMessage
      @ChatId = 1,
      @Content = 'Ziyakahla';	  
	
SELECT *
FROM Users

SELECT *
FROM Chat;

SELECT * 
FROM Message;