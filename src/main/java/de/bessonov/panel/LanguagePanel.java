package de.bessonov.panel;

import static de.bessonov.wicket.MessageUtils._;

import java.util.Arrays;
import java.util.Locale;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

public class LanguagePanel extends Panel {
	private static final long serialVersionUID = 1L;

	public LanguagePanel(String id, Locale... locales) {
		super(id);

		add(new ListView<Locale>("row", Arrays.asList(locales)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void populateItem(final ListItem<Locale> item) {
				item.add(new Link<Locale>("lang_url", item.getModel()) {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						getSession().setLocale(getModelObject());
					}

				}.add(new Label("name",
						_(item.getModelObject().getDisplayName()))));
			}
		});
	}
}
