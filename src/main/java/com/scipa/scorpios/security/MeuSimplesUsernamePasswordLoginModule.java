package com.scipa.scorpios.security;

import java.security.acl.Group;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

public class MeuSimplesUsernamePasswordLoginModule extends UsernamePasswordLoginModule {
	private static final Logger logger = Logger.getLogger(MeuSimplesUsernamePasswordLoginModule.class);

	@Override
	public void initialize(final Subject subject, final CallbackHandler callbackHandler, final Map sharedState,
			final Map options) {
		super.initialize(subject, callbackHandler, sharedState, options);
	}

	@Override
	protected String getUsersPassword() throws LoginException {
		logger.info(String.format("MeuLoginModule: autenticando usuario '%s'\n", getUsername()));

		String password = super.getUsername();
		password = password.toUpperCase();

		return password;// senha que vcs gerão que retornar a senha do usuario persistida
	}

	@Override
	protected boolean validatePassword(final String inputPassword, String expectedPassword) {
		final String encryptedInputPassword = inputPassword;
		expectedPassword = inputPassword;
		logger.info(String.format(
				"Validando encriptacao da senha informada (encrypted) input psw '%s' igual a (encriptada) '%s'\n",
				encryptedInputPassword, expectedPassword));

		return super.validatePassword(encryptedInputPassword, expectedPassword);
	}

	@Override
	protected Group[] getRoleSets() throws LoginException {
		final String user = getIdentity().getName();
		logger.info("pegando as roles do usuario: " + user);
		final SimpleGroup group = new SimpleGroup("Roles");

		try {
			if ("msilva".equalsIgnoreCase(user))
				group.addMember(new SimplePrincipal("admin_role"));
			else if ("rmacedo".equalsIgnoreCase(user)) {
				group.addMember(new SimplePrincipal("user_role"));
			}
		} catch (final Exception e) {
			throw new LoginException("Falha para criar o grupo de menbros para " + group);
		}
		return new Group[] { group };
	}

}
