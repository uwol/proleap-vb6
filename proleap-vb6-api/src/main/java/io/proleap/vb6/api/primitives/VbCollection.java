/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.api.primitives;

import java.util.ArrayList;

public class VbCollection extends ArrayList<Object> {

	public class Element {

		public Object Value;
	}

	private static final long serialVersionUID = 1L;

	public Long Count;

	public void Add(final Object key) {

	}

	public void Add(final Object key, final Object value) {

	}

	public Long Count() {
		return (long) size();
	}

	public Element getElement(final Object key) {
		return null;
	}

	public boolean IsMember(final Object obj) {
		return false;
	}

	public Element Item(final Object key) {
		return null;
	}

	public void Move(final Long lng, final Object obj) {

	}

	public void Remove(final Object key) {
	}

	public void setElement(final Object key, final Object value) {

	}
}
