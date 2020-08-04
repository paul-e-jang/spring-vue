package bashpound.marketplace.domain.common.security;

public interface PasswordEncryptor {

  String encrypt(String rawPassword);
}
