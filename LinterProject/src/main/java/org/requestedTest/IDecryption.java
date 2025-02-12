package org.requestedTest;

/**
 * This is the API for decrypting a cipher-text character to the corresponding plain-text character.
 * @see {@link IEncryption}
 */
public interface IDecryption {
	/**
	 * Decrypts the supplied character to plain-text.
	 * @param cipher The cipher-text character.
	 * @return The plain-text character.
	 */
	public char decrypt(char cipher);
}
