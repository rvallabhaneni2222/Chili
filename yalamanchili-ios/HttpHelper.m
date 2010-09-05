//
//  HttpHelper.m
//  yalamanchili-ios
//
//  Created by Anu Yalamanchili on 9/4/10.
//  Copyright 2010 Dante. All rights reserved.
//

#import "HttpHelper.h"


@implementation HttpHelper
+ (NSData*) httpGetDataFromURL:(NSString*)urlString
{
	NSMutableURLRequest *request = [[[NSMutableURLRequest alloc] init] autorelease];
	[request setURL:[NSURL URLWithString:urlString]];
	[request setHTTPMethod:@"GET"];
	NSHTTPURLResponse* urlResponse = nil;  
	NSError *error = [[NSError alloc] init];	
	NSData *responseData = [NSURLConnection sendSynchronousRequest:request returningResponse:&urlResponse error:&error];
	return responseData;
}

+ (id) httpPutData:(NSString*) xmlData withURL:(NSString*) urlString{
	NSMutableURLRequest *request = [[[NSMutableURLRequest alloc] init] autorelease];
	[request setURL:[NSURL URLWithString:urlString]];
	[request setHTTPMethod:@"PUT"];
	
	//set headers
	NSString *contentType = [NSString stringWithFormat:@"application/xml"];
	[request addValue:contentType forHTTPHeaderField: @"Content-Type"];
	
	//create the body
	NSMutableData *putBody = [NSMutableData data];
	[putBody appendData:[[NSString stringWithFormat:xmlData] dataUsingEncoding:NSUTF8StringEncoding]];
	//post
	[request setHTTPBody:putBody];
	
	NSHTTPURLResponse* urlResponse = nil;  
	NSError *error = [[NSError alloc] init];  
	NSData *responseData = [NSURLConnection sendSynchronousRequest:request returningResponse:&urlResponse error:&error]; 
	return [XMLUtils convertObjectToXML:responseData];
}


+ (NSData*) asyncHttpGetDataFromURL:(NSString*)urlString
{
	NSData *data;
	NSURL *url=[NSURL URLWithString:urlString];
	ASIHTTPRequest *request = [ASIHTTPRequest requestWithURL:url];
	[request setUsername:@"admin"];
	[request setPassword:@"admin"];
	NSLog ( @"HTTPGet with URI: %@", [request url] );
	[request startSynchronous];
	NSError *error = [request error];
	NSLog(@"status code: %d",[request responseStatusCode]);
	if ([request responseStatusCode]>=200 && [request responseStatusCode]<=300) {
		NSString *response = [request responseString];
		data= [response dataUsingEncoding: NSASCIIStringEncoding];
	}else {
		[NSException raise:@"Invalid Http response:" format:@"response code: %d ", [request responseStatusCode]];
	}
	return data;
}

+ (id) asyncHttpPutData:(NSString*) xmlData withURL:(NSString*) urlString{
	NSString *response;
	NSURL *url=[NSURL URLWithString:urlString];
	ASIHTTPRequest *request = [ASIHTTPRequest requestWithURL:url];
	[request appendPostData:[xmlData dataUsingEncoding:NSUTF8StringEncoding]];
	[request addRequestHeader:@"Content-Type" value:@"application/xml"];
	[request setRequestMethod:@"PUT"];
	[request setUsername:@"admin"];
	[request setPassword:@"admin"];
	NSLog ( @"HTTPPut with URI: %@", [request url] );
	[request startSynchronous];
	NSError *error = [request error];
	NSLog(@"status code: %d",[request responseStatusCode]);
	if ([request responseStatusCode]>=200 && [request responseStatusCode]<=300) {
		response = [request responseString];
	}else {
		[NSException raise:@"Invalid Http response:" format:@"response code: %d ", [request responseStatusCode]];
	}
	return response;
}

@end
