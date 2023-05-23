/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.api.adodb;

import io.proleap.vb6.api.primitives.VbCollection;

public class Recordset extends VbCollection {

	private static final long serialVersionUID = 1L;

	public Long AbsolutePosition;

	public String ActiveConnection;

	public Long CacheSize;

	public Cursorlocationenum CursorLocation;

	public Cursortypeenum Cursortype;

	public Cursortypeenum CursorType;

	public Object Filter;

	public Locktypeenum LockType;

	public String Source;

	public Objectstateenum State;

	public void AddNew() {

	}

	public void AddNew(final Object obj1, final Object obj2) {

	}

	public boolean BOF() {
		return true;
	}

	public void CancelUpdate() {

	}

	public void Close() {

	}

	public boolean EOF() {
		return true;
	}

	public Fields Fields() {
		return null;
	}

	public Element Find(final Object obj) {
		return null;
	}

	public void MoveFirst() {

	}

	public void MoveLast() {

	}

	public void MoveNext() {

	}

	public void MovePrevious() {

	}

	public void Open(final String source) {

	}

	public void Open(final String source, final Connection conn) {

	}

	public void Open(final String source, final Connection conn, final Cursortypeenum cursortypeenum) {

	}

	public void Open(final String source, final Connection conn, final Cursortypeenum cursortypeenum,
			final Locktypeenum locktypeenum) {

	}

	public int RecordCount() {
		return 0;
	}

	public void Update() {

	}

	public void Update(final Object vFields, final Object vValues) {

	}
}
