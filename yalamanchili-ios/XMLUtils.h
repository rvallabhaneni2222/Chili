//
//  XMLUtils.h
//  yalamanchili-ios
//
//  Created by Anu Yalamanchili on 9/3/10.
//  Copyright 2010 Dante. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "GDataXMLNode.h";
#import "IntrospectionUtils.h";

@interface XMLUtils : NSObject {

}
+ (id) convertXMLToObjects:(NSData*) xmlData;
+ (id) convertXMLToObject:(GDataXMLElement*) entityXML;
+ (NSString*) convertObjectToXML:(id) entity;
+ (NSString*) getOpenXMLTagForString:(NSString*) xmlName;
+ (NSString*) getCloseXMLTagForString:(NSString*) xmlName;
+ (NSMutableDictionary *)propertDictionary:(NSObject *) objt;
@end
