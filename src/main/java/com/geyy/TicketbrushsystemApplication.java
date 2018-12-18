package com.geyy;

import com.geyy.ticketBruch.execute.Vote;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketbrushsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketbrushsystemApplication.class, args);
		Vote vote = new Vote();
		vote.vote();
	}

}

