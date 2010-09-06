//
//  XMLUtils.m
//  yalamanchili-ios
//
//  Created by Anu Yalamanchili on 9/3/10.
//  Copyright 2010 Dante. All rights reserved.
//

#import "XMLUtils.h"

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

@implementation XMLUtils

+ (id) convertXMLToObjects: (NSData*) xmlData{
	NSMutableArray *results;
	results=[[[NSMutableArray alloc] init] autorelease];
	NSError *error = [[NSError alloc] init];	
	GDataXMLDocument *xml = [[GDataXMLDocument alloc] initWithData:xmlData 
														   options:0 error:&error];
	
	if([[[xml rootElement] name] isEqualToString:@"string"]){
		NSLog(@"null response");
		return nil;
	}
	NSArray *list = [xml nodesForXPath:@"//list" error:nil];
	if(list.count >0){
		NSArray *entities=[xml.rootElement children];
		for(GDataXMLElement *entityXML in entities){
			[results addObject:[self convertXMLToObject:entityXML]];
		}
		return results;
	}
	else{
		return [self convertXMLToObject:xml.rootElement];	
	}
	return nil;
}

+ (id) convertXMLToObject:(GDataXMLElement*) entityXML{
	NSString *entityName=entityXML.name;
	entityName = [entityName stringByReplacingOccurrencesOfString:@"."
													   withString:@"_"];
	id entity = [[[NSClassFromString(entityName) alloc] init] autorelease];
	NSArray *attributes=[entityXML children];
	NSMutableDictionary *propertyDic=[self propertDictionaryForClass:entity];
	for(GDataXMLElement *attribute in attributes){
		NSLog(@"processing attribute:%@",attribute.name);
		NSString *dataType=[propertyDic objectForKey:attribute.name];
		NSLog(@"dataType:%@",dataType);
		if([@"NSString" isEqualToString:dataType]){
			[IntrospectionUtils callSetterMethodOnEntity:entity onAttribute:attribute.name withString:attribute.stringValue];
		}
		if([attribute.name isEqualToString:@"id"]){
			NSNumber *num=[NSNumber numberWithInteger:[attribute.stringValue integerValue]];
			[IntrospectionUtils callSetterMethodOnEntity:entity onAttribute:@"entityID" withLong:num];		
		}
	}
	NSLog(@"%@",[entity entityID]);
	return entity;
}

+ (NSString*) convertObjectToXML:(id) entity{
	NSLog(@"in convert xml to obj");
	NSMutableString *xmlString=[[[NSMutableString alloc] initWithString:@"<?xml version=\"1.0\"?>"] autorelease];
	const char *objectName = class_getName([entity class]);
	NSString *entityName = [NSString stringWithCString:objectName encoding:NSASCIIStringEncoding];
	entityName = [entityName stringByReplacingOccurrencesOfString:@"_"
													   withString:@"."];
	[xmlString appendString:[self getOpenXMLTagForString:entityName]];
	[xmlString appendString:@"\n"];
	NSMutableDictionary * propertyDic = [self propertyDictionary:[entity class]];
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
	NSLog(@"%@",xmlString);
	return xmlString;
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

+ (NSMutableDictionary *)propertDictionaryForClass:(NSObject *) entity{
	NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithCapacity:1];
	Class clazz=[entity class];
	do {
		[dic addEntriesFromDictionary:[self propertyDictionary:clazz]];
		clazz=class_getSuperclass(clazz);
	} while (![[clazz description] isEqualToString:@"NSObject"]);
	return dic;
}

+ (NSMutableDictionary *)propertyDictionary:(Class) clazz{
    unsigned int outCount, i;
	NSMutableDictionary * dic = [NSMutableDictionary dictionaryWithCapacity:1]; 
    objc_property_t *properties = class_copyPropertyList(clazz, &outCount);
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
	if([dic objectForKey:@"entityID"] !=nil){
		NSLog(@"nil nil nil nil nil");
	}
    free(properties);
	return dic;
}

@end 
