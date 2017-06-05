package com.chatbox.bussiness;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.chatbox.model.API_AI_Response_Mdl;
import com.chatbox.model.Parameters;
import com.chatbox.model.Response_Mdl;
import com.chatbox.model.Result;

@Path("/balance")
public class RequestResponce {

	@GET
	public Response getMsg() {
		return Response.status(200).entity("Hello").build();
		

	}
	
@POST
@Consumes(MediaType.APPLICATION_JSON)
public Response getbal(String outputJSON) throws IOException{
	System.out.println("Request recieved");
	 API_AI_Responce response = new API_AI_Responce();
		
		System.out.println("responceBO : "+response.toString());
		API_AI_Response_Mdl apiAiResponse = response.jsonToJava(outputJSON);
		
		System.out.println("apiAiResponse : " +apiAiResponse);
		
		Result rs=apiAiResponse.getResult();
		
		System.out.println("rs :"+rs.toString());
		//Parameters params=rs.getParameters();
		
		Validate_Data_Excel v=new Validate_Data_Excel();
		int card_no=123456;//Integer.valueOf((String)params.getCardNo());
		String bal= v.getBalance(card_no);
		System.out.println("Current Balance:-"+bal);
		Response_Mdl res=new Response_Mdl();
		res.setSource("policyWS");
		String str1="Your Balance is "+bal;
		res.setSpeech(str1);
	        res.setDisplayText(str1);
		ObjectMapper om=new ObjectMapper();
		String str2=om.writeValueAsString(res);
	return Response.status(200).entity(str2).header("Content-Type", "application/json").build();
}
}
