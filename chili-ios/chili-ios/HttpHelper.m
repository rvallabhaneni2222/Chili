//
//  HttpHelper.m
//  chili-ios
//
//  Created by Anu Yalamanchili on 2/19/12.
//  Copyright (c) 2012 Dante. All rights reserved.
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

+ (id) httpPutData:(NSString*) putBody withURL:(NSString*) urlString{
    return nil;
}


+ (NSData*) asyncHttpGetDataFromURL:(NSString*)urlString
{
    NSData *data;
    NSURL *url=[NSURL URLWithString:urlString];
    ASIHTTPRequest *request = [ASIHTTPRequest requestWithURL:url];
    NSLog ( @"HTTPGet with URI: %@", [request url] );
    [request startSynchronous];
    NSError *error = [request error];
    NSLog(@"status code: %d",[request responseStatusCode]);
    if ([request responseStatusCode]>=200 && [request responseStatusCode]<=300) {
        NSString *response = [request responseString];
        data= [response dataUsingEncoding: NSASCIIStringEncoding];
    }else {
        [NSException raise:@"Invalid Http response:" format:@"response code: %d", [request responseStatusCode]];
    }
    return data;
}

+ (id) asyncHttpPutData:(NSString*) xmlData withURL:(NSString*) urlString{
    return nil;
}

@end
