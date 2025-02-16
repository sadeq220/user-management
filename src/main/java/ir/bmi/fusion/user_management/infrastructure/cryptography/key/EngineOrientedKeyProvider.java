package ir.bmi.fusion.user_management.infrastructure.cryptography.key;

import ir.bmi.fusion.user_management.domain.port.outbound.KeyProviderPort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultResponse;

import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.EdECPrivateKey;
import java.security.interfaces.EdECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

@Service
@Profile("!test")
public class EngineOrientedKeyProvider implements KeyProviderPort {

    private final ECKeyPair ecKeyPair;
    private final EdECKeyPair edECKeyPair;


    public EngineOrientedKeyProvider(VaultOperations vaultOperations) throws NoSuchAlgorithmException, InvalidKeySpecException {
        VaultResponse storedEC = vaultOperations.opsForKeyValue("kv/", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get("user-management/ec");
        if (storedEC!=null){
            // Step 1: Create a KeyFactory for the EC (Elliptic Curve) algorithm
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            KeyPairHolder keyPairHolder = this.decodeKeyPair(keyFactory, storedEC.getData());

            this.ecKeyPair=new ECKeyPair((ECPrivateKey) keyPairHolder.privateKey,(ECPublicKey) keyPairHolder.publicKey);
        } else {
            KeyPairGenerator ec = KeyPairGenerator.getInstance("EC");
            ec.initialize(256);// NIST p-256
            KeyPair keyPair = ec.generateKeyPair();
            this.ecKeyPair = new ECKeyPair((ECPrivateKey) keyPair.getPrivate(), (ECPublicKey) keyPair.getPublic());
            vaultOperations.opsForKeyValue("kv/", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).put("user-management/ec",
                    Map.of("prik", this.ecKeyPair.ecPrivateKey().getEncoded(), "pubk", this.ecKeyPair.ecPublicKey().getEncoded()));
        }

        VaultResponse storedEdDSA = vaultOperations.opsForKeyValue("kv/", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get("user-management/edDSA");
        if (storedEdDSA!=null){
            // Step 1: Create a KeyFactory for the EdDSA (Edwards Curve) algorithm
            KeyFactory keyFactory = KeyFactory.getInstance("EdDSA");
            KeyPairHolder keyPairHolder = this.decodeKeyPair(keyFactory, storedEdDSA.getData());

            this.edECKeyPair = new EdECKeyPair((EdECPrivateKey) keyPairHolder.privateKey, (EdECPublicKey) keyPairHolder.publicKey);
        } else {
            //
            // Create a KeyPairGenerator for EdDSA (Ed25519 curve)
            KeyPairGenerator edDSA = KeyPairGenerator.getInstance("EdDSA");
            KeyPair edDSAKayPair = edDSA.generateKeyPair();
            this.edECKeyPair = new EdECKeyPair((EdECPrivateKey) edDSAKayPair.getPrivate(), (EdECPublicKey) edDSAKayPair.getPublic());
            vaultOperations.opsForKeyValue("kv/", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).put("user-management/edDSA",
                    Map.of("prik", this.edECKeyPair.edECPrivateKey().getEncoded(), "pubk", this.edECKeyPair.edECPublicKey().getEncoded()));
        }
    }
    private record KeyPairHolder(PrivateKey privateKey,PublicKey publicKey){ }
    private KeyPairHolder decodeKeyPair(KeyFactory keyFactory,Map<String,Object> data) throws InvalidKeySpecException {
        byte[] prikPKCS8Encoded = Base64.getDecoder().decode((String) data.get("prik"));

        // Step 2: Create a PKCS8EncodedKeySpec from the byte array (usually PKCS#8 format)
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(prikPKCS8Encoded);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        byte[] pubkX509Encoded = Base64.getDecoder().decode((String) data.get("pubk"));
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pubkX509Encoded);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        return new KeyPairHolder(privateKey,publicKey);
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
