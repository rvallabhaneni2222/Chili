//
//  HttpUtils.m
//  HelloObjC
//
//  Created by Anu Yalamanchili on 5/31/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "HttpUtils.h"
#import "GDataXMLNode.h"
#import <Foundation/NSObjCRuntime.h>
#import "objc/runtime.h"

static const char* getPropertyType(objc_property_t property) {
	
    // parse the property attribues. this is a comma delimited string. the type of the attribute starts with the
	
    // character 'T' should really just use strsep for this, using a C99 variable sized array.
	
    const char *attributes = property_getAttributes(property);
	char buffer[1 + strlen(attributes)];
    strcpy(buffer, attributes);
	
    char *state = buffer;
	char *attribute;
	
	
	
    while ((attribute = strsep(&state, ",")) != NULL) {
		
        if (attribute[0] == 'T' && strlen(attribute)>2) {
			
            // return a pointer scoped to the autorelease pool. Under GC, this will be a separate block.
			
            return (const char *)[[NSData dataWithBytes:(attribute + 3) length:strlen(attribute)-4] bytes];
			
			
			
        }else if (attribute[0] == 'T' && strlen(attribute)==2) {
			return (const char *)[[NSData dataWithBytes:(attribute + 1) length:strlen(attribute)] bytes];
		}
		
    }
	
    return "@";
	
}

@implementation HttpUtils
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
	return [self parseFromXML:responseData];
}
+ (id) parseFromXML:(NSData*) data
{
	NSMutableArray *results;
	results=[[[NSMutableArray alloc] init] autorelease];
	NSError *error = [[NSError alloc] init];	
	GDataXMLDocument *xml = [[GDataXMLDocument alloc] initWithData:data 
														   options:0 error:&error];
	NSArray *list = [xml nodesForXPath:@"//list" error:nil];
	if(list.count >0){
		NSArray *entities=[xml.rootElement children];
		for(GDataXMLElement *entity in entities){
			[results addObject:[HttpUtils parseEntityFromXML:entity]];
		}
		return results;
	}
	else{
		return [HttpUtils parseEntityFromXML:xml.rootElement];	
		}
	}

+ (NSString*) parseToXML:(id) entity{
	NSMutableString *xmlString=[[[NSMutableString alloc] initWithString:@"<?xml version=\"1.0\"?>"] autorelease];
	const char *objectName = class_getName([entity class]);
	NSString *entityName = [NSString stringWithCString:objectName encoding:NSASCIIStringEncoding];
	entityName = [entityName stringByReplacingOccurrencesOfString:@"_"
												withString:@"."];
	[xmlString appendString:[self getOpenXMLTagForString:entityName]];
	[xmlString appendString:@"\n"];
	NSMutableDictionary * propertyDic = [self propertDictionary:entity];
	for (NSString *key in propertyDic) {
		if ([entity valueForKey:key]!=nil){
			[xmlString appendString:[self getOpenXMLTagForString:key]];
			if([[propertyDic valueForKey:key] isEqualToString:[NSString stringWithFormat:@"NSString"]]){
				[xmlString appendString:[entity valueForKey:key]];
			}
			[xmlString appendString:[self getCloseXMLTagForString:key]];
			[xmlString appendString:@"\n"];
		}
	}
	[xmlString appendString:[self getCloseXMLTagForString:entityName]];
	[xmlString appendString:@"\n"];
	return xmlString;
}

+ (id) parseEntityFromXML:(GDataXMLElement*) xml
{
	NSString *entityName=xml.name;
	entityName = [entityName stringByReplacingOccurrencesOfString:@"."
										 withString:@"_"];
	id entity = [[[NSClassFromString(entityName) alloc] init] autorelease];
	NSArray *attributes=[xml children];
	for(GDataXMLElement *attribute in attributes){
		NSString *part1=[[attribute.name substringToIndex:1] capitalizedString];
		NSString *part2= [attribute.name substringFromIndex:1];
		NSString *capAttributeName=[part1 stringByAppendingString:part2];
		NSString * setMethodName = [NSString stringWithFormat:@"set%@:",capAttributeName];
		SEL setterMethod= NSSelectorFromString(setMethodName);
		if([entity respondsToSelector:setterMethod]){
			[entity performSelector:setterMethod withObject:attribute.stringValue];
		}
	}
	return entity;
}

+ (NSString*) getOpenXMLTagForString:(NSString*) xmlName{
	NSString *s = [[NSString alloc] initWithString:@"<"];
	s = [s stringByAppendingString:xmlName];
	s = [s stringByAppendingString:@">"];
	return s;
}

+ (NSString*) getCloseXMLTagForString:(NSString*) xmlName{
	NSString *s = [[NSString alloc] initWithString:@"</"];
	s = [s stringByAppendingString:xmlName];
	s = [s stringByAppendingString:@">"];
	return s;
}


+ (NSMutableDictionary *)propertDictionary:(NSObject *) objt{
    unsigned int outCount, i;
	
	NSMutableDictionary * dic = [NSMutableDictionary dictionaryWithCapacity:1]; 
    objc_property_t *properties = class_copyPropertyList([objt class], &outCount);
    for(i = 0; i < outCount; i++) {
        objc_property_t property = properties[i];
        const char *propName = property_getName(property);
        if(propName) {
			const char *propType = getPropertyType(property);
			NSString *propertyName = [NSString stringWithCString:propName encoding:NSUTF8StringEncoding];
			//propertyName = [propertyName stringByReplacingOccurrencesOfString:@"@\"NSString\"" withString:@""];		
			//NSLog(@"propName :: %@",propertyName);
			NSString *propertyType = [NSString stringWithCString:propType encoding:NSUTF8StringEncoding];
			
			//NSLog(@"propertyType :: %@",propertyType);
			
			[dic setValue:propertyType forKey:propertyName];
        }
    }
	
	
    free(properties);
	
	return dic;
	
}
@end
