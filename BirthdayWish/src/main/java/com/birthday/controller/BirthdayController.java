package com.birthday.controller;

import java.time.LocalDate;
import java.util.List;

import org.aspectj.weaver.patterns.DeclareTypeErrorOrWarning;
import org.hibernate.dialect.function.TruncFunction.DatetimeTrunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.birthday.entity.Friend;
import com.birthday.services.MailSendServices;

@Controller
public class BirthdayController 
{
	@Autowired
	MailSendServices sendServices;
	
//	@GetMapping("/")
//	@ResponseBody
//	public String sendEmail()
//	{
//		
//		return "Mail sent successfully";
//	}
	
	
	@GetMapping("/")
	@ResponseBody
	public String GetAllFriends()
	{
		
		List<Friend> friends = sendServices.GetFriends();
		for(Friend friend:friends)
		{
			LocalDate dateOfBirth = friend.getDateOfBirth();
			dateOfBirth.getDayOfMonth();
			dateOfBirth.getMonth();
			
			LocalDate date= LocalDate.now();
			if(dateOfBirth.getDayOfMonth()==date.getDayOfMonth() && dateOfBirth.getMonth()== date.getMonth())
			{
				sendServices.sendEmail(friend.getEmail(), "Happy Birthday!", "Happy Birthday dear,",friend.getFirstName());
			}
		}
		return "Mail sent successfully";
	}
}
