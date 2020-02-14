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
	// insert after cursor
	public Node insert(Node cursor, String value) {
		Node newNode = new Node(value);
		// if inserting at the top
		if (cursor == null) {
			newNode.next = first;
			first = newNode;
		}
		// inserting in the middle
		else {
			newNode.next = cursor.next;
			// if inserting at the end
			if (cursor.next == null) {
				last = newNode;
			}
			cursor.next = newNode;
		}
		// if list was empty
		if (count == 0) {
			last = newNode;
		}
		count++;
		return newNode;
	}


	@Override
	public Node append(String value) {
		Node newNode = new Node(value);
		// if list is empty
		if (count == 0) {
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
		boolean first_item = true;
		while (cursor != null) {
			if (!first_item) {
				csv.append(",");
			}
			csv.append(cursor.value);
			cursor = cursor.next;
			first_item = false;
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
		boolean first = true;
		while (cursor != null) {
			if (!first) {
				out.append("\n");
			}
			out.append(cursor.value);
			cursor = cursor.next;
			first = false;
		}
		out.close();
	}

}
