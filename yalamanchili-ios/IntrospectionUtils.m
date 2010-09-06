//
//  IntrospectionUtils.m
//  automanage-ios
//
//  Created by Anu Yalamanchili on 9/1/10.
//  Copyright 2010 Dante. All rights reserved.
//

#import "IntrospectionUtils.h"


@implementation IntrospectionUtils
+ (NSString*) sayHello: (NSString*) message{
	return @"hello world";
}
+ (NSMutableArray*) getPropertyListForEntity:(id) entity{
	
	return nil;
}
+ (NSMutableArray*) getMethodsForClassName: (NSString*) className{
	NSLog(@"Class Name:%@",className);
	NSMutableArray* classMethods=[[[NSMutableArray alloc] init] autorelease];
	id entity = [[[NSClassFromString(className) alloc] init] autorelease];
	u_int count;
	Method* methods = class_copyMethodList([entity class], &count);
	for (int i = 0; i < count ; i++){
		SEL selector = method_getName(methods[i]);
	//	[classMethods addObject:<#(id)anObject#>];
		 const char* methodName = sel_getName(selector);
		NSString *stringFromUTFString = [[NSString alloc] initWithUTF8String:methodName];
		NSLog(@"%@",stringFromUTFString );
	}
	return classMethods;
}

+ (void) callSetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName withString:(NSString*) parameter{
	SEL setterMethod= NSSelectorFromString([self getSetterMethodNameForAttribute:attributeName]);
//	method_copyArgumentType()
	if([entity respondsToSelector:setterMethod]){
		[entity performSelector:setterMethod withObject:parameter];
	}
	else{
		const char* methodName = sel_getName(setterMethod);
		NSString *stringFromUTFString = [[NSString alloc] initWithUTF8String:methodName];
		NSLog(@"Does not respond to slector");
		NSLog(@"%@",stringFromUTFString );
	}
}
+ (void) callSetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName withInteger:(NSInteger*) parameter{
}
+ (void) callSetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName withLong:(NSNumber*) parameter{
	SEL setterMethod= NSSelectorFromString([self getSetterMethodNameForAttribute:attributeName]);
	//	method_copyArgumentType()
	if([entity respondsToSelector:setterMethod]){
		NSLog(@"repos");
		[entity performSelector:setterMethod withObject:parameter];
	}
	else{
		const char* methodName = sel_getName(setterMethod);
		NSString *stringFromUTFString = [[NSString alloc] initWithUTF8String:methodName];
		NSLog(@"Does not respond to slector");
		NSLog(@"%@",stringFromUTFString );
	}
}
+ (void) callSetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName withDate:(NSDate*) parameter{
}
+ (void) callSetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName withFloat:(float) parameter{
}
+ (void) callSetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName withBoolean:(bool)  parameter{
}

+ (NSString*) getSetterMethodNameForAttribute:(NSString*) attributeName{
	NSString *part1=[[attributeName substringToIndex:1] capitalizedString];
	NSString *part2= [attributeName substringFromIndex:1];
	NSString *capAttributeName=[part1 stringByAppendingString:part2];
	NSString * setMethodName = [NSString stringWithFormat:@"set%@:",capAttributeName];
	NSLog(@"setterMethod:%@",setMethodName);
	return setMethodName;
}
+ (NSString*) getGetterMethodNameForAttribute:(NSString*) attributeName{
	NSString *part1=[[attributeName substringToIndex:1] capitalizedString];
	NSString *part2= [attributeName substringFromIndex:1];
	NSString *getMethodName=[part1 stringByAppendingString:part2];
	NSLog(@"getterMethod:%@",getMethodName);
	return getMethodName;
}

+ (Method) getGetterMethodForClass: (id) entity forAttribute:(NSString*) attributeName{
	u_int count;
	Method* methods = class_copyMethodList([entity class], &count);
	for (int i = 0; i < count ; i++){
		SEL selector = method_getName(methods[i]);
		const char* methodName = sel_getName(selector);
		NSString *stringFromUTFString = [[[NSString alloc] initWithUTF8String:methodName] autorelease];
		if ([stringFromUTFString isEqualToString:[self getGetterMethodNameForAttribute:attributeName]]) {
			return methods[i];
		}
	}
	return nil;
}
+ (Method) getSetterMethodForClass: (id) entity forAttribute:(NSString*) attributeName{
	u_int count;
	Method* methods = class_copyMethodList([entity class], &count);
	for (int i = 0; i < count ; i++){
		SEL selector = method_getName(methods[i]);
		const char* methodName = sel_getName(selector);
		NSString *stringFromUTFString = [[[NSString alloc] initWithUTF8String:methodName] autorelease];
		if ([stringFromUTFString isEqualToString:[self getSetterMethodNameForAttribute:attributeName]]) {
			return methods[i];
		}
	}
	return nil;
}
@end
