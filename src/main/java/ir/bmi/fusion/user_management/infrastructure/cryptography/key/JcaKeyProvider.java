package ir.bmi.fusion.user_management.infrastructure.cryptography.key;

import ir.bmi.fusion.user_management.domain.port.outbound.KeyProviderPort;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.EdECPrivateKey;
import java.security.interfaces.EdECPublicKey;

@Component
public class JcaKeyProvider implements KeyProviderPort {
    private final ECKeyPair ecKeyPair;
    private final EdECKeyPair edECKeyPair;
    public JcaKeyProvider() throws NoSuchAlgorithmException {
        // Step 1: Create a KeyFactory for the EC (Elliptic Curve) algorithm
        KeyPairGenerator ec = KeyPairGenerator.getInstance("EC");
        ec.initialize(256);// NIST p-256
        KeyPair keyPair = ec.generateKeyPair();
        this.ecKeyPair = new ECKeyPair((ECPrivateKey) keyPair.getPrivate(), (ECPublicKey) keyPair.getPublic());

        //
        // Create a KeyPairGenerator for EdDSA (Ed25519 curve)
        KeyPairGenerator edDSA = KeyPairGenerator.getInstance("EdDSA");
        KeyPair edDSAKayPair = edDSA.generateKeyPair();
        this.edECKeyPair = new EdECKeyPair((EdECPrivateKey) edDSAKayPair.getPrivate(), (EdECPublicKey) edDSAKayPair.getPublic());

    }
    @Override
    public ECKeyPair provideEllipticCurveKeys() {
        return ecKeyPair;
    }

    @Override
    public EdECKeyPair provideEdwardECKeys() {
        return edECKeyPair;
    }
}
