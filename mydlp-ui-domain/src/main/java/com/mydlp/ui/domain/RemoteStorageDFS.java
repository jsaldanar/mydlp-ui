package com.mydlp.ui.domain;

import javax.persistence.Entity;

@Entity
public class RemoteStorageDFS extends RemoteStorage {



	/**
	 * 
	 */
	private static final long serialVersionUID = 2697168233345406529L;

	protected String windowsShare;
	
	protected String path;

	protected String username;

	protected String password;
	
	public String getWindowsShare() {
		return windowsShare;
	}

	public void setWindowsShare(String windowsShare) {
		this.windowsShare = windowsShare;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}