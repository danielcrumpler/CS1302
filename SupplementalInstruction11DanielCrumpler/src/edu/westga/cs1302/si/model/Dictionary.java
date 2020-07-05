package edu.westga.cs1302.si.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The Class Dictionary.
 * 
 * @author CS1302
 */
public class Dictionary implements Collection<String> {
	private static final String NULL_WORD = "word cannot be null";
	private static final String CONTAINS_NULL = "specified words cannot contain null";
	
	private Map<String, String> words;
	
	/**
	 * Instantiates a new Dictionary.
	 * 
	 * @precondition none
	 * @postcondition size() == 0
	 */
	public Dictionary() {
		this.words = new HashMap<String, String>();
	}
	
	@Override
	public int size() {
		return this.words.size();
	}
	
	@Override
	public boolean add(String word) {
		if (word == null) {
			throw new NullPointerException(NULL_WORD);
		} 
		return this.words.putIfAbsent(word, word) == null;
	}
	
	@Override
	public boolean addAll(Collection<? extends String> words) {
		if (words.contains(null)) {
			throw new NullPointerException(CONTAINS_NULL);
		}
		boolean changed = false;
		for (String word : words) {
			if (this.add(word)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		this.words.clear();
	}

	@Override
	public boolean contains(Object word) {
		return this.words.containsValue(word);
	}

	@Override
	public boolean containsAll(Collection<?> words) {
		if (words.contains(null)) {
			throw new NullPointerException(CONTAINS_NULL);
		}
		for (Object word : words) {
			if (!this.contains(word)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return this.words.isEmpty();
	}

	@Override
	public Iterator<String> iterator() {
		return this.words.values().iterator();
	}

	@Override
	public boolean remove(Object word) {
		if (word == null) {
			throw new NullPointerException(NULL_WORD);
		} 
		return this.words.remove(word, word);
	}

	@Override
	public boolean removeAll(Collection<?> words) {
		if (words.contains(null)) {
			throw new NullPointerException(CONTAINS_NULL);
		}
		boolean changed = false;
		for (Object word : words) {
			if (this.remove(word)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> words) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		return this.words.values().toArray();
	}

	@Override
	public <T> T[] toArray(T[] array) {
		return this.words.values().toArray(array);
	}
}
