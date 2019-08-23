package com.gentics.mesh.etc.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.gentics.mesh.etc.config.env.EnvironmentVariable;
import com.gentics.mesh.etc.config.env.Option;

public class OAuth2Options implements Option {

	public static final String MESH_AUTH_OAUTH2_ENABLED_ENV = "MESH_AUTH_OAUTH2_ENABLED";

	@JsonProperty(required = false)
	@JsonPropertyDescription("Flag which indicates whether the OAuth2 support should be enabled.")
	@EnvironmentVariable(name = MESH_AUTH_OAUTH2_ENABLED_ENV, description = "Override the configured OAuth2 enabled flag.")
	private boolean enabled = false;

	@JsonProperty(required = false)
	@JsonPropertyDescription("Name of the oauth provider.")
	private String provider;

	@JsonProperty(required = false)
	@JsonPropertyDescription("X509 formatted public key to be used to verify the JWT.")
	private String publicKey;

	public boolean isEnabled() {
		return enabled;
	}

	public OAuth2Options setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public OAuth2Options setPublicKey(String publicKey) {
		this.publicKey = publicKey;
		return this;
	}

	public String getProvider() {
		return provider;
	}

	public OAuth2Options setProvider(String provider) {
		this.provider = provider;
		return this;
	}

}
