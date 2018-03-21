package data;

/**
 * An Item of a single linked List
 * 
 * @author David Koehler
 * @param <T>
 *            Generic Type of the ListItem
 */
public class ListItem<T> {

	public T			key;
	public ListItem<T>	next;

	/**
	 * Constructor of this Class
	 * 
	 * @param key
	 *            the key of the ListItemAbstract
	 */
	public ListItem(T key) {
		this.key = key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		if (this.key == null && ((ListItem<T>) obj).key == null)
			return true;
		if (this.key != null && this.key.equals(((ListItem<T>) obj).key)) {
			if (this.next == null)
				if (((ListItem<T>) obj).next == null)
					return true;
				else
					return false;
			return this.next.equals(((ListItem<T>) obj).next);
		} else
			return false;
	}

	/**
	 * Die Methode gibt den Schlüsselwert an der gesuchten Position in der Liste zurück. Ist die gesuchte Position
	 * nicht in der Liste, soll eine IllegalArgumentException geworfen werden. *
	 * 
	 * @param pos
	 *            the position to return the key from. Position 1 means the current element
	 * @return the key of the element at pos
	 * @throws IllegalArgumentException
	 *             if the position ist not in the list
	 */
	public T get(int pos) throws IllegalArgumentException {
		//shen
		ListItem<T> p = this;
		try {
			for (int i = 1; i < pos; i++) p = p.next;}
		catch(Exception e) {
			throw new IllegalArgumentException();}
		return p.key;
	}

	/**
	 * Die Methode gibt die Länge der Liste zurück. Dabei hat die Liste die Länge null, falls keine Listenelemente
	 * folgen und zudem der aktuelle Schlüssel null ist. Ansonsten ist die Länge einer Liste die Anzahl an
	 * Listenelemente, wobei das letzte Element nicht mitgezählt wird, falls die vorherstehende Bedingung zutrifft.
	 * 
	 * @return the size of this list
	 */
	public int getSize() {
		int length = 0;
		for (ListItem<T> p = this; p != null; p = p.next) {
			length++;
		}
		return length;
	}

	/**
	 * Inserts an key into this list.
	 * 
	 * @param key
	 *            the key to insert.
	 * @throws IllegalArgumentException
	 *             if key is null
	 */
	public void insert(T key) throws IllegalArgumentException {
		if (key == null)
			throw new IllegalArgumentException("Cannot insert null");
		else if (this.key == null && this.next == null)
			this.key = key;
		else {
			ListItem<T> p = this;
			while (p.next != null)
				p = p.next;
			p.next = new ListItem<T>(key);
		}
	}
}