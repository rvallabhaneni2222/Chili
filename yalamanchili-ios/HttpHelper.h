//
//  HttpHelper.h
//  yalamanchili-ios
//
//  Created by Anu Yalamanchili on 9/4/10.
//  Copyright 2010 Dante. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ASIHTTPRequest.h";
#import "XMLUtils.h";

@interface HttpHelper : NSObject {
}
+ (NSData*) httpGetDataFromURL:(NSString*)urlString;
	
+ (id) httpPutData:(NSString*) xmlData withURL:(NSString*) urlString;

+ (NSData*) asyncHttpGetDataFromURL:(NSString*)urlString;
	
+ (id) asyncHttpPutData:(NSString*) xmlData withURL:(NSString*) urlString;

@end
