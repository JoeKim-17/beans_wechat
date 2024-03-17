use beanwechat;
go

drop procedure getUserChat; 
go

CREATE PROCEDURE getUserChat 
   @senderUserName VARCHAR(100),
   @receiverUserName VARCHAR(100)
as
begin 
    SELECT Message.ChatId as ChatId, messageID, Senders.UserName as SenderName, Receivers.UserName as ReceiverName, Content as Content, Message.CreatedAt as CreatedAt
	FROM Message
	inner join Chat on Chat.ChatId  = Message.ChatId
	inner join Users Senders on Senders.UserId = Chat.Sender and Senders.UserId in (Chat.Sender, Chat.Receiver)
	inner join Users Receivers on Receivers.UserId = Chat.Receiver and Receivers.UserId in  (Chat.Sender, Chat.Receiver)
	where Senders.UserName in (@SenderName, @receiverUserName) and Receivers.UserName in (@SenderName, @receiverUserName)
end
;
go 



drop procedure InsertIntoChat;
go

CREATE PROCEDURE InsertIntoChat
    @Sender VARCHAR(100), 
    @Receiver VARCHAR(100)
AS
BEGIN
IF NOT EXISTS (
	SELECT *
	FROM Chat
	WHERE Sender = (select UserId from Users where username = @Sender) AND Receiver = (select UserId from Users where username = @Receiver)
	)
	begin
		  INSERT INTO Chat (Sender, Receiver)
		  VALUES ((SELECT UserId FROM Users WHERE UserName=@Sender),( SELECT UserId FROM Users WHERE UserName= @Receiver));
	end


	select * from Chat
	where Sender = (SELECT UserId FROM Users WHERE UserName=@Sender) and
	Receiver = ( SELECT UserId FROM Users WHERE UserName= @Receiver)
END;
GO