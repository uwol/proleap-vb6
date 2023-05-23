/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.api.adodb;

public class Connection {

	public Long CommandTimeout;

	public String ConnectionString;

	public Errors Errors;

	public Properties Properties;

	public Objectstateenum State;

	public Object BeginTrans() {
		return null;
	}

	public void Close() {

	}

	public void CommitTrans() {

	}

	public String ConnectionString() {
		return ConnectionString;
	}

	public void Execute(final Object obj1) {

	}

	public void Execute(final Object obj1, final Object obj2) {

	}

	public void Execute(final Object obj1, final Object obj2, final Object obj3) {

	}

	public void Open(final String connectionString, final String username, final String password) {

	}

	public void Open(final String connectionString, final String username, final String password,
			final Connectoptionenum options) {

	}

	public void Rollback() {

	}

	public void RollbackTrans() {

	}
}
