package com.famalde.utils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Parseador de RSS.
 * 
 * @author Fernando
 *
 */
public class RssParser {
	
	/**
	 * Elementos de un RSS, titulo y enlace.
	 * 
	 * @author Fernando
	 *
	 */
	public enum RSS_ELEMET {
		TITLE, LINK
	}

	private URL url;

	private static final String TITLE = "title";
	private static final String ITEM = "item";
	private static final String LINK = "link";
	
	/**
	 * Constructor del parseador de url rss.
	 * 
	 * @param url
	 */
	public RssParser(final String url) {
		try {
			this.url = new URL(url);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parsea un xml rss y devuelve la informacion como una lista de mapas
	 * compuestos por titulo y enlace correspondiente.
	 * 
	 * @return
	 */
	public LinkedList<Map<RSS_ELEMET, String>> parse() {
		final DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		final LinkedList<Map<RSS_ELEMET, String>> entries = new LinkedList<Map<RSS_ELEMET, String>>();
		Map<RSS_ELEMET, String> entry;
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document dom = builder.parse(this.url.openConnection()
					.getInputStream());
			final Element root = dom.getDocumentElement();
			final NodeList items = root.getElementsByTagName(ITEM);
			for (int i = 0; i < items.getLength(); i++) {
				entry = new HashMap<RSS_ELEMET, String>();
				final Node item = items.item(i);
				final NodeList properties = item.getChildNodes();
				for (int j = 0; j < properties.getLength(); j++) {
					final Node property = properties.item(j);
					final String name = property.getNodeName();
					if (name.equalsIgnoreCase(TITLE)) {
						entry.put(RSS_ELEMET.TITLE, property.getFirstChild()
								.getNodeValue());
					} else if (name.equalsIgnoreCase(LINK)) {
						entry.put(RSS_ELEMET.LINK, property.getFirstChild()
								.getNodeValue());
					}
				}
				entries.add(entry);
			}
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}

		return entries;
	}
}
