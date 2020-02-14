package singlylinkedlist;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

public class BasicSinglyLinkedList implements SinglyLinkedList {

	int count = 0;
	Node first = null;
	Node last = null;

	@Override
	public Node getFirst() {
		return first;
	}


	@Override
	public Node getLast() {
		return last;
	}


	@Override
	public int size() {
		return count;
	}


	@Override
	public boolean isEmpty() {
		return count == 0;
	}


	@Override
	public void clear() {
		count = 0;
		first = null;
		last = null;
	}


	@Override
	public Node insert(Node cursor, String value) { // insert after cursor
		Node newNode = new Node(value);
		if (cursor == null) { // inserting at the top
			newNode.next = first;
			first = newNode;
		} else {// inserting in the middle
			newNode.next = cursor.next;
			if (cursor.next == null) {// if inserting at the end
				last = newNode;
			}
			cursor.next = newNode;
		}
		if (count == 0) { // if list was empty
			last = newNode;
		}
		count++;
		return newNode;
	}


	@Override
	public Node append(String value) {
		Node newNode = new Node(value);
		if (count == 0) {// if list is empty
			first = newNode;
		} else {
			last.next = newNode;
		}
		last = newNode;
		count++;
		return newNode;
	}


	@Override
	public void remove(Node cursor) {
		if (cursor == first) {
			if (cursor == last) {// top and only
				first = last = null;
			} else {// top
				first = cursor.next;
			}
		} else {
			Node previous = first;
			while (previous.next != cursor) {
				previous = previous.next;
			}
			if (cursor == last) {// end of list
				previous.next = null;
				last = previous;
			} else {// middle of list
				previous.next = cursor.next;
			}
		}
		count--;
	}


	@Override
	public Node find(Node start, String key) {
		Node node = start;
		while (!node.value.equals(key)) {
			node = node.next;
		}
		return node;
	}


	@Override
	public String toCsvString() {
		StringBuilder csv = new StringBuilder();
		Node cursor = first;
		boolean first_node = true;
		while (cursor != null) {
			if (first_node) {
				csv.append(cursor.value);
				first_node = false;
			} else {
				csv.append("," + cursor.value);
			}
			cursor = cursor.next;
		}
		return csv.toString();
	}


	@Override
	public ArrayList<String> toList() {
		Node cursor = first;
		ArrayList<String> list = new ArrayList<>();
		while (cursor != null) {
			list.add(cursor.value);
			cursor = cursor.next;
		}
		return list;
	}


	@Override
	public void loadFile(String filename) throws IOException {
		var lines = readFileAsLines(filename);
		for (var line : lines) {
			append(line);
		}
	}


	@Override
	public void saveFile(String filename) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		Node cursor = first;
		boolean first_node = true;
		while (cursor != null) {
			if (first_node) {
				out.append(cursor.value);
				first_node = false;
			} else {
				out.append("\n" + cursor.value);
			}
			cursor = cursor.next;
		}
		out.close();
	}

}
