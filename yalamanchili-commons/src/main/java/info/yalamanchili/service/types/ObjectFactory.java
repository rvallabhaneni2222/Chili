package info.yalamanchili.service.types;

import javax.xml.bind.annotation.XmlRegistry;

@SuppressWarnings("restriction")
@XmlRegistry
public class ObjectFactory {

	public ObjectFactory() {
	}

	public Error createError() {
		return new Error();
	}

	public Errors createErrors() {
		return new Errors();
	}
}
