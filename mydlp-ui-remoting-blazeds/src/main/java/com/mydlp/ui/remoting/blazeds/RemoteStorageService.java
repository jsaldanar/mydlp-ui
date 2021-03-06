package com.mydlp.ui.remoting.blazeds;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.mydlp.ui.domain.AuthSecurityRole;
import com.mydlp.ui.domain.RemoteStorage;
import com.mydlp.ui.domain.WebServer;

@Secured({AuthSecurityRole.ROLE_ADMIN, AuthSecurityRole.ROLE_SUPER_ADMIN, AuthSecurityRole.ROLE_AUDITOR, AuthSecurityRole.ROLE_CLASSIFIER})
public interface RemoteStorageService {
	
	public List<RemoteStorage> getRemoteStorages();
	
	public List<String> getRemoteStorageDir(int id);
	
	public void startRemoteFingerprint(int id);
	
	public void stopFingerprinting(int id);
	
	public String testConnection(RemoteStorage remoteStorage);
	
	public String testWebServer(WebServer webServer);
}