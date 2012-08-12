package de.bessonov.wicket;

import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

public class MessageUtils {

	/**
	 * Shortcut to create message translation model
	 * 
	 * @param message
	 * @param args
	 * @return
	 */
	public static StringResourceModel _(String message, Object... args) {
		return new StringResourceModel(message, new Model<Object[]>(args), message);
	}
}
