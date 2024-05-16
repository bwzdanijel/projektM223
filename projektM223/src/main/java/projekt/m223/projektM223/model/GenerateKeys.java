package projekt.m223.projektM223.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "keys")
public class GenerateKeys {
    @Id
    private String privatKey;
    private String publicKey;

    public GenerateKeys() {
    }

    public GenerateKeys(String privatKey, String publicKey) {
        this.privatKey = privatKey;
        this.publicKey = publicKey;
    }

    public String getPrivatKey() {
        return privatKey;
    }

    public void setPrivatKey(String privatKey) {
        this.privatKey = privatKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
