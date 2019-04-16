package me.im97penis.shitnoob.map;

import java.util.Map.Entry;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntryConstruct<K, V> implements Entry<K, V> {
	
	private final K key;
	private V value;
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		this.value = value;
		
		return value;
	}
	
}
