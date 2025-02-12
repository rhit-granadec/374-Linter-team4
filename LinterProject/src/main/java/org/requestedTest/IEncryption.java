package org.requestedTest;

/**
 * This is the API for encrypting a given plain-text character to the corresponding cipher-text character.
 * {@link IDecryption}
 */
public interface IEncryption {
	/**
	 * Encrypts the supplied plain-text character to cipher-text character.
	 * 
	 * @param plain A plain text character.
	 * @return The cipher-text character.
	 */
	public char encrypt(char plain);
}
