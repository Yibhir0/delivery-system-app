package com.devops.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health Check", description = "Operations related to health check")
@RestController
@RequestMapping("/")
public class HealthCheckController {


	@Operation(summary = "Welcome", description = "Welcome to the Delivery-WebApp Backend!")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Welcome message"),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
	})
	@GetMapping(value="/", produces="text/html")
	public String index() {
		return "Welcome to the Delivery-WebApp Backend!<br><a href='/ping'>Ping</a><br><a href='/dotenv'>Dotenv</a><br><a href='/api'>Endpoints</a>";
	}


	@Operation(summary = "ping", description = "Check if the server is running")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "pong"),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
	})
	@GetMapping(value="/ping", produces="application/json")
	public String ping() {
		return "{\"ping\": \"pong\"}";
	}


	@Operation(summary = "Dotenv", description = "Get the dotenv variables")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Dotenv variables"),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
	})
	@GetMapping(value="/dotenv", produces="application/json")
	public String db() {
		String dbUrl = System.getenv("DB_URL");
		String dbUser = System.getenv("DB_USERNAME");
		String dbPass = System.getenv("DB_PASSWORD");
		String geminiApi = System.getenv("GEMINI_API");

		return "{\"dbUrl\": \"" + dbUrl + "\", \"dbUser\": \"" + dbUser + "\", \"dbPass\": \"" + dbPass + "\", \"geminiApi\": \"" + geminiApi + "\"}";
	}

}