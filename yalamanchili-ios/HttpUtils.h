//
//  HttpUtils.h
//  HelloObjC
//
//  Created by Anu Yalamanchili on 5/31/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "GDataXMLNode.h";

@interface HttpUtils : NSObject {

}
+ (NSData*) httpGetDataFromURL:(NSString*)urlString;
+ (id) httpPutData:(NSString*) xmlData withURL:(NSString*) urlString;
+ (id) parseFromXML:(NSData*) data;
+ (NSString*) parseToXML:(id) entity;
+ (id) parseEntityFromXML:(GDataXMLElement*) xml;
@end
