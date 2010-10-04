//
//  IntrospectionUtils.h
//  automanage-ios
//
//  Created by Anu Yalamanchili on 9/1/10.
//  Copyright 2010 Dante. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "objc/runtime.h"

@interface IntrospectionUtils : NSObject {

}
+ (id) performSelectorOnEntity:(id) entity withName:(NSString*) selectorName;
+ (NSString*) sayHello: (NSString*) message;
+ (NSMutableArray*) getMethodsForClassName: (NSString*) className;
+ (id) callGetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName;
+ (void) callSetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName withString:(NSString*) parameter;
+ (void) callSetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName withInteger:(NSInteger*) parameter;
+ (void) callSetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName withLong:(NSNumber*) parameter;
+ (void) callSetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName withDate:(NSDate*) parameter;
+ (void) callSetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName withFloat:(float) parameter;
+ (void) callSetterMethodOnEntity: (id) entity onAttribute: (NSString*) attributeName withBoolean:(bool)  parameter;
+ (NSString*) getGetterMethodNameForAttribute:(NSString*) attributeName;
+ (NSString*) getSetterMethodNameForAttribute:(NSString*) attributeName;
+ (Method) getGetterMethodForClass: (id) entity forAttribute:(NSString*) attributeName;
+ (Method) getSetterMethodForClass: (id) entity forAttribute:(NSString*) attributeName;
@end
