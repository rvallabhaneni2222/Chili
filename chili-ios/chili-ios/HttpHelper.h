//
//  HttpHelper.h
//  chili-ios
//
//  Created by Anu Yalamanchili on 2/19/12.
//  Copyright (c) 2012 Dante. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ASIHTTPRequest.h"

@interface HttpHelper : NSObject {
}
+ (NSData*) httpGetDataFromURL:(NSString*)urlString;

+ (id) httpPutData:(NSString*) xmlData withURL:(NSString*) urlString;

+ (NSData*) asyncHttpGetDataFromURL:(NSString*)urlString;

+ (id) asyncHttpPutData:(NSString*) xmlData withURL:(NSString*) urlString;

@end