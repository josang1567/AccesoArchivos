package models.message_folder;


import java.time.LocalDateTime;

import models.user_folder.User;

public class Message {
	private LocalDateTime date;
	private String data;
	private User user;
	private int id;
}
