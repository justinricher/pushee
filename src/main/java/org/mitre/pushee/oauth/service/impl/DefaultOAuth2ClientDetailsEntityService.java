package org.mitre.pushee.oauth.service.impl;

import java.util.List;

import org.mitre.pushee.oauth.model.ClientDetailsEntity;
import org.mitre.pushee.oauth.repository.OAuth2ClientRepository;
import org.mitre.pushee.oauth.service.ClientDetailsEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

@Service
public class DefaultOAuth2ClientDetailsEntityService implements ClientDetailsEntityService {

	@Autowired
	private OAuth2ClientRepository clientRepository;
	
	@Override
	public ClientDetailsEntity loadClientByClientId(String clientId) throws OAuth2Exception {
		if (!Strings.isNullOrEmpty(clientId)) {
			return clientRepository.getClientById(clientId);
		}
		
		return null;
	}
	
	@Override
    public ClientDetailsEntity createClient(String clientId, String clientSecret, 
    		List<String> scope, List<String> grantTypes, String redirectUri, List<GrantedAuthority> authorities, 
    		String name, String description, boolean allowRefresh, Long accessTokenTimeout, 
    		Long refreshTokenTimeout, String owner) {
		
		// TODO: check "owner" locally?
		
		// TODO: switch to builder method instead of megaconstructor
		ClientDetailsEntity client = new ClientDetailsEntity(clientId, clientSecret, 
				scope, grantTypes, redirectUri, authorities, 
				name, description, allowRefresh, accessTokenTimeout, 
				refreshTokenTimeout, owner);

		clientRepository.saveClient(client);
		
		return client;
		
	}
	
	@Override
    public void deleteClient(ClientDetailsEntity client) {
		
		clientRepository.deleteClient(client);
		
	}

	@Override
    public ClientDetailsEntity updateClient(ClientDetailsEntity oldClient, ClientDetailsEntity newClient) {
		if (oldClient != null && newClient != null) {
			return clientRepository.updateClient(oldClient.getClientId(), newClient);
		}
		return null;
    }

}