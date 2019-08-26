package com.gentics.mesh.auth;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.gentics.mesh.Mesh;
import com.gentics.mesh.etc.config.AuthenticationOptions;
import com.gentics.mesh.etc.config.MeshOptions;
import com.gentics.mesh.etc.config.OAuth2Options;
import com.gentics.mesh.rest.client.MeshRestClient;

@Ignore
public class OAuth2Auth0IntegrationTest {

	public static void clean() throws IOException {
		deleteDir(new File("config"));
		deleteDir(new File("data"));
	}

	@Test
	public void testAuth0() throws IOException {
		MeshOptions options = new MeshOptions();
		AuthenticationOptions auth = options.getAuthenticationOptions();
		options.setNodeName("mesh");	
		auth.setKeystorePassword("ABC");
		options.getSearchOptions().disable();
		options.getStorageOptions().setDirectory(null);

		OAuth2Options oauth2Config = auth.getOauth2();
		oauth2Config.setEnabled(true);
		oauth2Config.setProvider("keycloak");
		oauth2Config.setPublicKey("PUBLIC_KEY");

		Mesh mesh = Mesh.create(options);
		mesh.rxRun().blockingAwait();

		MeshRestClient client = MeshRestClient.create("localhost", 8080, false);
		client.setAPIKey("TOKEN");

		System.out.println(client.me().blockingGet().toJson());

	}

	private static void deleteDir(File file) throws IOException {
		if (file.exists()) {
			FileUtils.forceDelete(file);
		}
	}
}
