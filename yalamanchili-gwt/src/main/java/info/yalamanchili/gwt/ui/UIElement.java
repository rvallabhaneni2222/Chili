package info.yalamanchili.gwt.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface UIElement.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UIElement {
	
	/**
	 * Position.
	 * 
	 * @return the position
	 */
	Position position();

	// String name() default "";
}
