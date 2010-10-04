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
+ (NSString*) convertUserObjectToXML:(id) entity withAttributeName:(NSString*) attributeName;
+ (NSString*) getOpenXMLTagForString:(NSString*) xmlName;
+ (NSString*) getCloseXMLTagForString:(NSString*) xmlName;
+ (NSString*) getOpenXMLTagForString:(NSString*) xmlName withAttributes:(NSDictionary*) attributes;
+ (NSMutableDictionary *)propertyDictionaryForClass:(NSObject *) entity;
+ (NSMutableDictionary *)propertyDictionary:(Class) clazz;
+ (Boolean) isUserdefined:(NSString*) type;
@end
