# Cloudinary

## Get secrets

 - cloudName
 - apiKey
 - apiSecret

## Imports

```
// https://mvnrepository.com/artifact/com.cloudinary/cloudinary-http44
implementation 'com.cloudinary:cloudinary-http44:1.39.0'
// https://mvnrepository.com/artifact/com.cloudinary/cloudinary-taglib
implementation 'com.cloudinary:cloudinary-taglib:1.39.0'
```

## Create a bean
```Java
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "",
                "api_key", "",
                "api_secret", ""));
    }
```

## Example Usage

```Java
try {
    HashMap<Object, Object> options = new HashMap<>();
    options.put("folder", "");
    Map uploadedFile = cloudinary.uploader().upload(file, options);
    String publicId = (String) uploadedFile.get("public_id");

    return cloudinary.url().secure(true).generate(publicId);
} catch (IOException e) {
    e.printStackTrace();
    return null;
}
```