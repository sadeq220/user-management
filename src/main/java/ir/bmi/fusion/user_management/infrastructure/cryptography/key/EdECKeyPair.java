package ir.bmi.fusion.user_management.infrastructure.cryptography.key;

import java.security.interfaces.EdECPrivateKey;
import java.security.interfaces.EdECPublicKey;

public record EdECKeyPair(EdECPrivateKey edECPrivateKey,
                          EdECPublicKey edECPublicKey) {
}
