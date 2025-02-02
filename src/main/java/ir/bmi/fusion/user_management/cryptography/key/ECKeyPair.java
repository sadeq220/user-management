package ir.bmi.fusion.user_management.cryptography.key;

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public record ECKeyPair(ECPrivateKey ecPrivateKey,
                        ECPublicKey ecPublicKey) {
}
